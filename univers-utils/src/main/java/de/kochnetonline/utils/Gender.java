package de.kochnetonline.utils;

public enum Gender {

	MALE ("male"),
	FEMALE ("female");
	
	private String string;

	private Gender(String string) {
		this.string = string;
	}

	public String getString() {
		return this.string;
	}
}
