package org.topdank.bot.net.packet.codecs;

import java.util.List;

import org.topdank.bot.net.Client;
import org.topdank.bot.net.packet.encryption.EncryptionProtocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

public class PacketEncryptorCodec extends ByteToMessageCodec<ByteBuf> {

	private final Client<?> client;
	private byte[] decryptedArray;
	private byte[] encryptedArray;

	public PacketEncryptorCodec(Client<?> client) {
		this.client = client;
		decryptedArray = new byte[0];
		encryptedArray = new byte[0];
	}

	@Override
	public void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
		EncryptionProtocol enc = client.getProtocol().getPacketEncryptionProtocol();
		if (enc != null) {
			int length = in.readableBytes();
			byte[] bytes = getBytes(in);
			int outLength = enc.calcEncryptLength(length);
			if (encryptedArray.length < outLength) {
				encryptedArray = new byte[outLength];
			}
			out.writeBytes(encryptedArray, 0, enc.encrypt(bytes, 0, length, encryptedArray, 0));
		} else {
			out.writeBytes(in);
		}
	}

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
		EncryptionProtocol enc = client.getProtocol().getPacketEncryptionProtocol();
		if (enc != null) {
			int length = buf.readableBytes();
			byte[] bytes = getBytes(buf);
			ByteBuf result = ctx.alloc().heapBuffer(enc.calcDecryptedLength(length));
			result.writerIndex(enc.decrypt(bytes, 0, length, result.array(), result.arrayOffset()));
			out.add(result);
		} else {
			out.add(buf.readBytes(buf.readableBytes()));
		}
	}

	private byte[] getBytes(ByteBuf buf) {
		int length = buf.readableBytes();
		if (decryptedArray.length < length) {
			decryptedArray = new byte[length];
		}
		buf.readBytes(decryptedArray, 0, length);
		return decryptedArray;
	}
}