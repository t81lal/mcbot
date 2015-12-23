package org.topdank.mc.bot.api.world;

import java.util.HashMap;
import java.util.Map;

public class BlockId {

	private static Map<Integer, BlockId> cache = new HashMap<Integer, BlockId>();

	// lots of objacts D:
	public static BlockId create(int id) {
		BlockId bId = cache.get(id);
		if (bId != null)
			return bId;
		return new BlockId(id);
	}

	protected final int id;

	protected BlockId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof BlockId))
			return false;
		BlockId b = (BlockId) o;
		return (b.id == id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + id;
		return result;
	}
}