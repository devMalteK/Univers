package de.kochnetonline.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import de.kochnetonline.model.entity.Student;
import de.kochnetonline.service.removestudent.RemoveStudentService;
import de.kochnetonline.service.showallstudents.ShowAllStudentsService;
import de.kochnetonline.ui.commons.UniversMainUI;
import de.kochnetonline.utils.NotificationMessages;

@SpringView(name = RemoveStudentLayoutFactory.NAME, ui = UniversMainUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout implements View, Button.ClickListener {

	public static final String NAME = "removestudent";
	private Grid removeStudentTable;
	private Button removeStudentsButton;
	private List<Student> students;

	@Autowired
	private ShowAllStudentsService allStudentsService;
	
	@Autowired
	private RemoveStudentService removeStudentService;
	
	private void addLayout() {
		removeStudentsButton=new Button(NotificationMessages.STUDENT_REMOVE_BUTTON.getString());
		setMargin(true);
		BeanItemContainer<Student> container = new BeanItemContainer<Student>(Student.class, students);
		
		removeStudentTable=new Grid(container);
		removeStudentTable.setColumnOrder("firstName", "lastName", "age", "gender");
		removeStudentTable.removeColumn("id");
		removeStudentTable.setImmediate(true);
		removeStudentTable.setSelectionMode(SelectionMode.MULTI);
		
		removeStudentsButton.addClickListener(this);
		removeStudentsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		addComponent(removeStudentTable);
		addComponent(removeStudentsButton);

	}

	private void loadStudents() {
		this.students=allStudentsService.getAllStudents();
	}

	@Override
	public void enter(ViewChangeEvent event) {

		if (removeStudentTable != null)
			return;

		loadStudents();
		addLayout();

	}

	@Override
	public void buttonClick(ClickEvent event) {
		MultiSelectionModel selectionModel = (MultiSelectionModel) removeStudentTable.getSelectionModel();
		
		for (Object selectedItem : selectionModel.getSelectedRows()) {
			Student student = (Student) selectedItem;
			removeStudentTable.getContainerDataSource().removeItem(student);
			removeStudentService.removeStudent(student);
		}
		
		Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE.getString(), NotificationMessages.STUDENT_REMOVE_SUCCESS_DISCRIPTION.getString(), Type.WARNING_MESSAGE);
		removeStudentTable.getSelectionModel().reset();
		
	}

}
