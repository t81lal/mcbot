package org.topdank.mc.bot.impl.entity.living;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.api.world.pos.Rotation;

public class ArmourStandEntity extends BaseLivingEntity {

	private static final Rotation DEFAULT_HEAD_ROTATION = new Rotation(0.0F, 0.0F, 0.0F);
	private static final Rotation DEFAULT_BODY_ROTATION = new Rotation(0.0F, 0.0F, 0.0F);
	private static final Rotation DEFAULT_LEFTARM_ROTATION = new Rotation(-10.0F, 0.0F, -10.0F);
	private static final Rotation DEFAULT_RIGHTARM_ROTATION = new Rotation(-15.0F, 0.0F, 10.0F);
	private static final Rotation DEFAULT_LEFTLEG_ROTATION = new Rotation(-1.0F, 0.0F, -1.0F);
	private static final Rotation DEFAULT_RIGHTLEG_ROTATION = new Rotation(1.0F, 0.0F, 1.0F);

	private boolean small;
	private boolean noGravity;
	private boolean showArms;
	private boolean showBasePlate;
	private boolean hasMarker;

	private Rotation headRotation     = DEFAULT_HEAD_ROTATION;
	private Rotation bodyRotation     = DEFAULT_BODY_ROTATION;
	private Rotation leftArmRotation  = DEFAULT_LEFTARM_ROTATION;
	private Rotation rightArmRotation = DEFAULT_RIGHTARM_ROTATION;
	private Rotation leftLegRotation  = DEFAULT_LEFTLEG_ROTATION;
	private Rotation rightLegRotation = DEFAULT_RIGHTLEG_ROTATION;

	public ArmourStandEntity(World world, int id) {
		super(world, id, 0.5F, 1.975F);
	}

	public boolean isSmall() {
		return small;
	}

	public void setSmall(boolean small) {
		this.small = small;
	}

	public boolean isNoGravity() {
		return noGravity;
	}

	public void setNoGravity(boolean noGravity) {
		this.noGravity = noGravity;
	}

	public boolean isShowArms() {
		return showArms;
	}

	public void setShowArms(boolean showArms) {
		this.showArms = showArms;
	}

	public boolean isShowBasePlate() {
		return showBasePlate;
	}

	public void setShowBasePlate(boolean showBasePlate) {
		this.showBasePlate = showBasePlate;
	}

	public boolean isHasMarker() {
		return hasMarker;
	}

	public void setHasMarker(boolean hasMarker) {
		this.hasMarker = hasMarker;
	}

	public Rotation getHeadRotation() {
		return headRotation;
	}

	public void setHeadRotation(Rotation headRotation) {
		this.headRotation = headRotation;
	}

	public Rotation getBodyRotation() {
		return bodyRotation;
	}

	public void setBodyRotation(Rotation bodyRotation) {
		this.bodyRotation = bodyRotation;
	}

	public Rotation getLeftArmRotation() {
		return leftArmRotation;
	}

	public void setLeftArmRotation(Rotation leftArmRotation) {
		this.leftArmRotation = leftArmRotation;
	}

	public Rotation getRightArmRotation() {
		return rightArmRotation;
	}

	public void setRightArmRotation(Rotation rightArmRotation) {
		this.rightArmRotation = rightArmRotation;
	}

	public Rotation getLeftLegRotation() {
		return leftLegRotation;
	}

	public void setLeftLegRotation(Rotation leftLegRotation) {
		this.leftLegRotation = leftLegRotation;
	}

	public Rotation getRightLegRotation() {
		return rightLegRotation;
	}

	public void setRightLegRotation(Rotation rightLegRotation) {
		this.rightLegRotation = rightLegRotation;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if(metadata.containsKey(10)) {
			byte val = (byte) metadata.get(10);
			small = (val & 1) != 0;
			noGravity = (val & 2) != 0;
			showArms = (val & 4) != 0;
			showBasePlate = (val & 8) != 0;
			hasMarker = (val & 16) != 0;
		}
		if(metadata.containsKey(11)) {
			headRotation = (Rotation) metadata.get(11);
		}
		if(metadata.containsKey(12)) {
			bodyRotation = (Rotation) metadata.get(12);
		}
		if(metadata.containsKey(13)) {
			leftArmRotation = (Rotation) metadata.get(13);
		}
		if(metadata.containsKey(14)) {
			rightArmRotation = (Rotation) metadata.get(14);
		}
		if(metadata.containsKey(15)) {
			leftLegRotation = (Rotation) metadata.get(15);
		}
		if(metadata.containsKey(16)) {
			rightArmRotation = (Rotation) metadata.get(16);
		}
		
		System.out.println("ArmourStand Meta: " + metadata);
	}
}