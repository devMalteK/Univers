package de.kochnetonline.utils;

public enum NotificationMessages {
	STUDENT_SAVE_VALIDATION_ERROR_TITLE("ERROR"),
	STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION("Fields must be filled!"),
	STUDENT_SAVE_SUCCESS_DESCRIPTION("Successfully Saved"),
	STUDENT_SAVE_SUCCESS_TITLE("Saved"),
	STUDENT_REMOVE_BUTTON("Remove"),
	STUDENT_REMOVE_SUCCESS_TITLE("Remove successful"),
	STUDENT_REMOVE_SUCCESS_DISCRIPTION("Studend(s) successfully removed");

	private final String string;

	private NotificationMessages(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

}
