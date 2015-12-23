package org.topdank.bot.net.packet.codecs;

import java.util.List;

import org.topdank.bot.Protocol;
import org.topdank.bot.net.Client;
import org.topdank.bot.net.io.bytebuf.ByteBufReadWriteInOut;
import org.topdank.bot.net.packet.WriteablePacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

public class PacketWriterCodec extends ByteToMessageCodec<WriteablePacket> {

	private final Client<?> client;

	public PacketWriterCodec(Client<?> client) {
		this.client = client;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, WriteablePacket packet, ByteBuf buf) throws Exception {
		ByteBufReadWriteInOut out = new ByteBufReadWriteInOut(buf);
		if (packet.isIdentifiable()) {// write id
			Protocol protocol = client.getProtocol();
			protocol.getPacketHeader().writePacketId(out, protocol.getOutgoingId(packet.getClass()));
		}
		packet.write(out);
		// System.out.println(String.format("Writing %d bytes (avail=%d)",
		// out.written(), out.getWriterIndex()));
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
	}
}