package de.kochnetonline.ui.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import de.kochnetonline.model.entity.University;
import de.kochnetonline.service.showalluniversities.ShowAllUniversitiesService;
import de.kochnetonline.ui.commons.UIComponentBuilder;

@org.springframework.stereotype.Component
public class ShowAllUniversitiesLayoutFactory implements UIComponentBuilder {

	private List<University> universities;
	private BeanItemContainer<University> container;

	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	private class ShowUniversitiesLayout extends VerticalLayout {

		private Grid universityTable;

		public ShowUniversitiesLayout init() {
			setMargin(true);
			container = new BeanItemContainer<University>(University.class, universities);

			universityTable = new Grid(container);
			universityTable.setColumnOrder("universityName", "universityCountry", "universityCity");
			// universityTable.removeColumn("id");
			universityTable.setImmediate(true);
			;

			return this;
		}

		public ShowUniversitiesLayout load() {
			universities = showAllUniversitiesService.getAllUniversities();
			return this;
		}

		public ShowUniversitiesLayout layout() {
			addComponent(universityTable);
			return this;
		}

	}

	@Override
	public Component createComponent() {
		// TODO Auto-generated method stub
		return new ShowUniversitiesLayout().load().init().layout();
	}

	public void refreshTable() {
		universities = showAllUniversitiesService.getAllUniversities();
		container.removeAllItems();
		container.addAll(universities);
	}

}
