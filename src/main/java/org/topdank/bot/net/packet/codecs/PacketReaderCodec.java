package org.topdank.bot.net.packet.codecs;

import java.util.List;

import org.topdank.bot.Protocol;
import org.topdank.bot.Protocol.PacketMode;
import org.topdank.bot.net.Client;
import org.topdank.bot.net.event.packet.PacketReceivedEvent;
import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.bytebuf.ByteBufReadableInput;
import org.topdank.bot.net.packet.ReadablePacket;
import org.topdank.bot.net.packet.UnidentifiableReadablePacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

public class PacketReaderCodec extends ByteToMessageCodec<ReadablePacket> {

	private final Client<?> client;

	public PacketReaderCodec(Client<?> client) {
		this.client = client;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, ReadablePacket msg, ByteBuf out) throws Exception {
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
		Protocol protocol = client.getProtocol();
		if (protocol.packetWait()) {
			long start = System.currentTimeMillis();
			while (protocol.packetWait()) {
				Thread.sleep(10);// cpu cycles :(

				long time = System.currentTimeMillis() - start;
				if (time > 10000) {
					protocol.setWait(false);
					break;
				}
			}
		}

		ReadablePacket packet = null;
		ReadableInput in = new ByteBufReadableInput(buf);

		if (protocol.getPacketMode() == PacketMode.IDENTIFIABLE) {
			int initial = buf.readerIndex();
			int id = protocol.getPacketHeader().readPacketId(in);
			if (id == -1) {
				buf.readerIndex(initial);
				return;
			}
			packet = protocol.createIncomingPacket(id);
		} else {// PacketMode.UNIDENTIFIABLE
			packet = new UnidentifiableReadablePacket();
		}
		packet.read(in);
		
		if(buf.readableBytes() > 0) {
			throw new IllegalStateException(String.format("%d unread bytes.", buf.readableBytes()));
		}

		if (packet.isPriorityPacket())
			protocol.onPacketReceived(new PacketReceivedEvent(client, packet));
		else
			out.add(packet);
	}
}