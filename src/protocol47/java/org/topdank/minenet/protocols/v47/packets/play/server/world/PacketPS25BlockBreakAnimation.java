package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS25BlockBreakAnimation implements IdentifiableReadablePacket {

	private int entityId;
	private BlockLocation loc;
	private byte stage;

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		loc = Protocol47DataHelper.decodePosition(in);
		stage = (byte) in.readUnsignedByte();
	}

	public int getEntityId() {
		return entityId;
	}

	public BlockLocation getLoc() {
		return loc;
	}

	public byte getStage() {
		return stage;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x25;
	}
}