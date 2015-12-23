package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;
import java.util.UUID;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.settings.GameMode;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS38PlayerList.CompletePlayerListEntry.Property;

public class PacketPS38PlayerList implements IdentifiableReadablePacket {

	// (PlayerListEntryAction.ADD_PLAYER, 0);
	// (PlayerListEntryAction.UPDATE_GAMEMODE, 1);
	// (PlayerListEntryAction.UPDATE_LATENCY, 2);
	// (PlayerListEntryAction.UPDATE_DISPLAY_NAME, 3);
	// (PlayerListEntryAction.REMOVE_PLAYER, 4);

	private CompletePlayerListEntry[] entries;

	public PacketPS38PlayerList() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		int action = in.readVarInt();
		int len = in.readVarInt();

		entries = new CompletePlayerListEntry[len];

		for (int i = 0; i < len; i++) {
			UUID uuid = in.readUUID();

			CompletePlayerListEntry currentEntry = entries[i] = new CompletePlayerListEntry(uuid);

			switch (action) {
				case 0:
					currentEntry.setUsername(in.readString());

					int pLen = in.readVarInt();
					Property[] properties = new Property[pLen];
					for (int pI = 0; pI < pLen; pI++) {
						String n = in.readString();
						String v = in.readString();
						String s = null;
						if (in.readBoolean()) { // is signed
							s = in.readString();
						}
						properties[pI] = currentEntry.new Property(n, v, s);
					}
					currentEntry.setProperties(properties);

					currentEntry.setGameMode(GameMode.getGameModeById(in.readVarInt()));
					currentEntry.setPing(in.readVarInt());
					if (in.readBoolean()) { // Has Display Name
						currentEntry.setDisplayName(in.readString());
					}
					break;
				case 1:
					currentEntry.setGameMode(GameMode.getGameModeById(in.readVarInt()));
					break;
				case 2:
					currentEntry.setPing(in.readVarInt());
					break;
				case 3:
					if (in.readBoolean()) { // Has Display Name
						currentEntry.setDisplayName(in.readString());
					}
					break;
				case 4:
					break;
			}
		}
	}

	public CompletePlayerListEntry[] getEntries() {
		return entries;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x38;
	}

	public abstract class PlayerListEntry {

		private final UUID uuid;

		public PlayerListEntry(UUID uuid) {
			this.uuid = uuid;
		}

		public UUID getUUID() {
			return uuid;
		}
	}

	public class CompletePlayerListEntry extends PlayerListEntry {

		private String username;
		private Property[] properties;
		private GameMode gameMode;
		private int ping;
		private String displayName;

		public CompletePlayerListEntry(UUID uuid) {
			super(uuid);
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Property[] getProperties() {
			return properties;
		}

		public void setProperties(Property[] properties) {
			this.properties = properties;
		}

		public GameMode getGameMode() {
			return gameMode;
		}

		public void setGameMode(GameMode gameMode) {
			this.gameMode = gameMode;
		}

		public int getPing() {
			return ping;
		}

		public void setPing(int ping) {
			this.ping = ping;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public class Property {

			private final String name;
			private final String value;
			private final String signature;

			public Property(String name, String value, String signature) {
				this.name = name;
				this.value = value;
				this.signature = signature;
			}

			public String getName() {
				return name;
			}

			public String getValue() {
				return value;
			}

			public String getSignature() {
				return signature;
			}

			public boolean isSigned() {
				return signature != null;
			}

			@Override
			public String toString() {
				return "Property [name=" + name + ", value=" + value + ", signature=" + signature + "]";
			}
		}
	}
}