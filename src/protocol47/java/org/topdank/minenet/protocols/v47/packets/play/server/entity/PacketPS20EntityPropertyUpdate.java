package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.impl.entity.Entity.EntityProperty;

public class PacketPS20EntityPropertyUpdate implements IdentifiableReadablePacket {

	private int entityId;
	private Map<String, EntityProperty> properties;

	public PacketPS20EntityPropertyUpdate() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		int length = in.readInt();
		properties = new HashMap<String, EntityProperty>(length);
		for (int i = 0; i < length; i++) {
			String name = in.readString();
			double value = in.readDouble();

			EntityProperty property = new EntityProperty(name, value);

			int modifiers = in.readVarInt();
			for (int j = 0; j < modifiers; j++) {
				// long msb = in.readLong();
				// long lsb = in.readLong();
				// UUID uuid = new UUID(msb, lsb);
				UUID uuid = in.readUUID();
				double amount = in.readDouble();
				int operation = in.readByte();
				property.addModifier(uuid, amount, operation);
			}
			properties.put(property.getName(), property);
		}
	}

	public int getEntityId() {
		return entityId;
	}

	public Map<String, EntityProperty> getProperties() {
		return properties;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x20;
	}
}