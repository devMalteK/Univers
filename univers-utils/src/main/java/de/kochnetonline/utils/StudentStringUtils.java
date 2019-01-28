package de.kochnetonline.utils;

public enum StudentStringUtils {

	MAIN_MENU("MAIN MENU"), SHOW_ALL_STUDENTS("SHOW ALL STUDENTS"),
	FIRST_NAME("First Name"),
	LAST_NAME("Last Name"),
	AGE("Age"),
	GENDER("Gender"),
	SAVE_BUTTON("Save"),
	CLEAR_BUTTON("Clear")
	;

	private final String string;

	private StudentStringUtils(String string) {
		this.string = string;
	}

	public String getString() {
		return this.string;
	}

}
