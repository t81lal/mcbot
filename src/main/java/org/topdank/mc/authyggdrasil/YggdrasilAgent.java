package org.topdank.mc.authyggdrasil;

public class YggdrasilAgent {

	public static final YggdrasilAgent INSTANCE = new YggdrasilAgent();

	private final String name = "Minecraft";
	private final int version = 1;

	private YggdrasilAgent() {
	}

	public String getName() {
		return name;
	}

	public int getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return "{\"name\":\"Minecraft\",\"version\":1}";
	}
}