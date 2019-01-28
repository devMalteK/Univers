package de.kochnetonline.ui.commons;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

import de.kochnetonline.navigator.UniversNavigator;
import de.kochnetonline.utils.StringUtils;

@org.springframework.stereotype.Component
public class UniversMenuFactory implements UIComponentBuilder {

	private class UniversMenu extends VerticalLayout implements Property.ValueChangeListener {

		private static final long serialVersionUID = -8860788494493733511L;
		private Tree mainMenu;

		public UniversMenu init() {
			mainMenu = new Tree();
			mainMenu.addValueChangeListener(this);
			return this;
		}

		public UniversMenu layout() {

			setWidth("100%");
			setHeightUndefined();
			mainMenu.addItem(StringUtils.MENU_STUDENT.getString());
			mainMenu.addItem(StringUtils.MENU_UNIVERSITY.getString());

			mainMenu.expandItem(StringUtils.MENU_STUDENT.getString());
			mainMenu.expandItem(StringUtils.MENU_UNIVERSITY.getString());

			mainMenu.addItem(StringUtils.MENU_ADD_STUDENT.getString());
			mainMenu.addItem(StringUtils.MENU_REMOVE_STUDENT.getString());
			mainMenu.setChildrenAllowed(StringUtils.MENU_ADD_STUDENT.getString(), false);
			mainMenu.setChildrenAllowed(StringUtils.MENU_REMOVE_STUDENT.getString(), false);
			mainMenu.setParent(StringUtils.MENU_ADD_STUDENT.getString(), StringUtils.MENU_STUDENT.getString());
			mainMenu.setParent(StringUtils.MENU_REMOVE_STUDENT.getString(), StringUtils.MENU_STUDENT.getString());

			mainMenu.addItem(StringUtils.MENU_OPERATIONS.getString());
			mainMenu.setChildrenAllowed(StringUtils.MENU_OPERATIONS.getString(), false);

			mainMenu.setParent(StringUtils.MENU_OPERATIONS.getString(), StringUtils.MENU_UNIVERSITY.getString());

			addComponent(mainMenu);

			return this;
		}

		public void valueChange(ValueChangeEvent event) {
			String selectedItemPath =  (String) event.getProperty().getValue();
			
			if (selectedItemPath == null) return;
			
			String path=selectedItemPath.toLowerCase().replace("//s+", "");
			
			UniversNavigator.navigate(path);

		}

	}

	public Component createComponent() {
		return new UniversMenu().init().layout();
	}

}
