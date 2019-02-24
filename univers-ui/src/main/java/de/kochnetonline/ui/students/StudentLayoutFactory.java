package de.kochnetonline.ui.students;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import de.kochnetonline.ui.commons.UniversMainUI;
import de.kochnetonline.utils.StudentStringUtils;


@SpringView(name = StudentLayoutFactory.NAME, ui = UniversMainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View, StudentSavedListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 958667917465248700L;
	public static final String NAME = "addstudent";

	@Autowired
	private AddStudentMainLayoutFactory mainLayoutFactory;
	
	@Autowired
	private ShowAllStudentsLayoutFactory showAllStudentsLayoutFactory; 
	
	private TabSheet tabSheet;
	
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
		
	}

	private void addLayout() {
		setMargin(true);
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addStudentMainTab = mainLayoutFactory.createComponent(this);
		Component showStudentsTab = showAllStudentsLayoutFactory.createComponent();
		
		tabSheet.addTab(addStudentMainTab, StudentStringUtils.MAIN_MENU.getString());
		tabSheet.addTab(showStudentsTab, StudentStringUtils.SHOW_ALL_STUDENTS.getString());
		
		addComponent(tabSheet);
		
		
		
	}

	@Override
	public void saved() {
		showAllStudentsLayoutFactory.refresh();
		
	}
	
}
