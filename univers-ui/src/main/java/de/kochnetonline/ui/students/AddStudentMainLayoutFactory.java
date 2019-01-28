package de.kochnetonline.ui.students;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.kochnetonline.model.entity.Student;
import de.kochnetonline.service.addstudent.AddStudentService;
import de.kochnetonline.utils.Gender;
import de.kochnetonline.utils.NotificationMessages;
import de.kochnetonline.utils.StudentStringUtils;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {

	private class AddStudentMainLayout extends VerticalLayout implements Button.ClickListener {

		private TextField firstName;
		private TextField lastName;
		private TextField age;
		private ComboBox gender;
		private Button saveButton;
		private Button clearButton;

		private BeanFieldGroup<Student> fieldGroup;
		private Student student;

		public AddStudentMainLayout init() {

			fieldGroup = new BeanFieldGroup<Student>(Student.class);
			student = new Student();

			firstName = new TextField(StudentStringUtils.FIRST_NAME.getString());
			lastName = new TextField(StudentStringUtils.LAST_NAME.getString());
			age = new TextField(StudentStringUtils.AGE.getString());
			gender = new ComboBox(StudentStringUtils.GENDER.getString());

			saveButton = new Button(StudentStringUtils.SAVE_BUTTON.getString());
			clearButton = new Button(StudentStringUtils.CLEAR_BUTTON.getString());

			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

			saveButton.addClickListener(this);
			clearButton.addClickListener(this);

			gender.addItem(Gender.MALE.getString());
			gender.addItem(Gender.FEMALE.getString());

			firstName.setNullRepresentation("");
			lastName.setNullRepresentation("");
			age.setNullRepresentation("");

			return this;
		}

		public Component layout() {
			setMargin(true);

			GridLayout gridLayout = new GridLayout(2, 3);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);

			gridLayout.addComponent(firstName, 0, 0);
			gridLayout.addComponent(lastName, 1, 0);

			gridLayout.addComponent(age, 0, 1);
			gridLayout.addComponent(gender, 1, 1);

			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 2);

			return gridLayout;

		}

		public void buttonClick(ClickEvent event) {
			if (event.getSource() == this.saveButton) {
				save();
			} else {
				clearField();
			}
		}

		private void save() {
			try {
				fieldGroup.commit();
			} catch (CommitException e) {
				Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(), NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				return;
			}
			addStudentService.saveStudent(student);
			clearField();
			
			Notification.show(NotificationMessages.STUDENT_SAVE_SUCCESS_TITLE.getString(), NotificationMessages.STUDENT_SAVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);

		}

		private void clearField() {
			firstName.setValue(null);
			lastName.setValue(null);
			gender.setValue(null);
			age.setValue(null);
			
		}

		public AddStudentMainLayout bind() {
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(student);
			return this;
		}
	}

	@Autowired
	private AddStudentService  addStudentService;
	
	public Component createComponent() {
		return new AddStudentMainLayout().init().bind().layout();
	}

}
