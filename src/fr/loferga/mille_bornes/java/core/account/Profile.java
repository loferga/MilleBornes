package fr.loferga.mille_bornes.java.core.account;

import java.util.UUID;

public abstract class Profile {
	
	protected UUID uuid;
	protected String name;
	protected boolean uuidSuffix;
	private transient String nameWithSuffix = null;
	
	protected Profile(String name, boolean uuidSuffix) {
		this.uuid = UUID.randomUUID();
		this.name = name;
		this.uuidSuffix = uuidSuffix;
		if (uuidSuffix)
			initNameWithSuffix();
	}
	
	private void initNameWithSuffix() {
		assert uuidSuffix && nameWithSuffix == null;
		this.nameWithSuffix = this.name + '_' + this.uuid.getMostSignificantBits();
	}
	
	public UUID getUID() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		if (uuidSuffix) {
			if (nameWithSuffix == null)
				initNameWithSuffix();
			return nameWithSuffix;
		}
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Profile profile
				&& this.uuid.equals(profile.uuid);
	}
	
	@Override
	public int hashCode() {
		return uuid.hashCode();
	}
	
}