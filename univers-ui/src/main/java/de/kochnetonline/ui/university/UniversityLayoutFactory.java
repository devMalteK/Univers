package de.kochnetonline.ui.university;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.kochnetonline.ui.commons.UniversMainUI;

@SpringView(name = UniversityLayoutFactory.NAME, ui = UniversMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6161591964895718737L;
	public static final String NAME = "operations";

	public void enter(ViewChangeEvent event) {
		addComponent(new Label("University Layout..."));
	}

}
