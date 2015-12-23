package org.topdank.mc.bot.api.world;

import java.util.HashMap;
import java.util.Map;

public class MetaBlockId extends BlockId {

	private static Map<Integer, MetaBlockId> cache = new HashMap<Integer, MetaBlockId>();

	public static MetaBlockId create(int id, int metadata) {
		int index = (id << 4) | metadata;
		MetaBlockId bId = cache.get(index);
		if (bId != null)
			return bId;
		return new MetaBlockId(id, metadata);
	}

	protected final int metadata;

	private MetaBlockId(int id, int metadata) {
		super(id);
		this.metadata = metadata;
	}

	public int getMetadata() {
		return metadata;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MetaBlockId))
			return false;
		MetaBlockId b = (MetaBlockId) o;
		return (b.id == id) && (b.metadata == metadata);
	}

	public int hashcode() {
		return (31 * super.hashCode()) + metadata;
	}
}