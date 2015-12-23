package org.topdank.mc.bot.api.item.tool;

public enum ToolEnum {

	SWORD(268, 272, 267, 283, 276),
	PICKAXE(270, 274, 257, 285, 278),
	SHOVEL(269, 273, 256, 284, 277),
	AXE(271, 275, 258, 286, 279),
	HOE(290, 291, 292, 294, 293),
	SHEARS(359);

	private final int[] ids;
	private final ToolMaterial[] materials;

	private ToolEnum(int... ids) {
		this.ids = ids;
		materials = new ToolMaterial[ids.length];
		for (int i = 0; i < ids.length; i++) {
			materials[i] = ToolMaterial.values()[i];
		}
	}

	public int[] getIds() {
		return ids;
	}

	// public ToolMaterial getMaterialForTool(int id) {
	//
	// }
}