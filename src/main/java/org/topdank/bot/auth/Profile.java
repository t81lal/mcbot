package org.topdank.bot.auth;

public final class Profile {

	private String id;
	private String name;

	public Profile(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id, name);
	}
}