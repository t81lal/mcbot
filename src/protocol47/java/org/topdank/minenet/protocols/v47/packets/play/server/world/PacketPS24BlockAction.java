package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS24BlockAction implements IdentifiableReadablePacket {

	private BlockLocation loc;
	private int action1, action2;
	private int blockType;

	@Override
	public void read(ReadableInput in) throws IOException {
		loc = Protocol47DataHelper.decodePosition(in);
		action1 = in.readUnsignedByte();
		action2 = in.readUnsignedByte();
		blockType = in.readVarInt();
	}

	public BlockLocation getLocation() {
		return loc;
	}

	public int getAction1() {
		return action1;
	}

	public int getAction2() {
		return action2;
	}

	public int getBlockType() {
		return blockType;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x24;
	}
}