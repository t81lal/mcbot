package org.topdank.mc.bot.api.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityProperty {

	private final String name;
	private final double value;
	private final List<Modifier> modifiers;

	public EntityProperty(String name, double value) {
		this.name = name;
		this.value = value;
		modifiers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public Modifier[] getModifiers() {
		return modifiers.toArray(new Modifier[modifiers.size()]);
	}

	public void addModifier(UUID uuid, double amount, int operation) {
		modifiers.add(new Modifier(uuid, amount, operation));
	}

	@Override
	public String toString() {
		return "EntityProperty [name=" + name + ", value=" + value + ", modifiers=" + modifiers + "]";
	}

	public final class Modifier {

		private final UUID uuid;
		private final double amount;
		private final int operation;

		private Modifier(UUID uuid, double amount, int operation) {
			this.uuid = uuid;
			this.amount = amount;
			this.operation = operation;
		}

		public UUID getUUID() {
			return uuid;
		}

		public double getAmount() {
			return amount;
		}

		public int getOperation() {
			return operation;
		}

		@Override
		public String toString() {
			return "Modifier [uuid=" + uuid + ", amount=" + amount + ", operation=" + operation + "]";
		}
	}
}