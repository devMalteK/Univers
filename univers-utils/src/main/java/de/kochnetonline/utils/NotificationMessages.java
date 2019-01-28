package de.kochnetonline.utils;

public enum NotificationMessages {
	STUDENT_SAVE_VALIDATION_ERROR_TITLE("ERROR"), STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION("Fields must be filled!"), STUDENT_SAVE_SUCCESS_DESCRIPTION("Successfully Saved"), STUDENT_SAVE_SUCCESS_TITLE("Saved");

	private final String string;

	private NotificationMessages(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

}
