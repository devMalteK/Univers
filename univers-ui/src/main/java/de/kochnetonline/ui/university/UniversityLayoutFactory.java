package de.kochnetonline.ui.university;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import de.kochnetonline.ui.commons.UniversMainUI;

@SpringView(name = UniversityLayoutFactory.NAME, ui = UniversMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, UniversitySavedListener {

	public static final String NAME = "operations";

	private TabSheet tabSheet;
	
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}

	@Autowired
	private AddUniversityLayoutFactory addUniversityLayoutFactory;
	
	@Autowired
	private ShowAllUniversitiesLayoutFactory showAllUniversitiesLayoutFactory; 
	
	private void addLayout() {
		setMargin(true);
		tabSheet=new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addUniversityTab=addUniversityLayoutFactory.createComponent(this);
		Component showAllUniversitiesTab = showAllUniversitiesLayoutFactory.createComponent();
		Component showStatisticsTab = new Label ("Stats");
		
		tabSheet.addTab(addUniversityTab, "ADD UNIVERSITY");
		tabSheet.addTab(showAllUniversitiesTab, "SHOW ALL UNIVERSITIES");
		tabSheet.addTab(showStatisticsTab, "STATISTICS");
		
		addComponent(tabSheet);
		
		
	}

	@Override
	public void universitySaved() {
		showAllUniversitiesLayoutFactory.refreshTable();
		
	}

}
