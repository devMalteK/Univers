package de.kochnetonline.ui.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.kochnetonline.model.entity.University;
import de.kochnetonline.service.showalluniversities.ShowAllUniversitiesService;
import de.kochnetonline.service.universitystatistics.UniversityStatisticsService;
import de.kochnetonline.ui.commons.UIComponentBuilder;

@Component
public class StastisticsUniversityLayoutFactory implements UIComponentBuilder {

	private List<University> universities;

	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	@Autowired
	private UniversityStatisticsService universityStatisticsService;
	
	private StatisticsUniversityLayout statisticsUniversityLayout;

	private class StatisticsUniversityLayout extends VerticalLayout {

		public StatisticsUniversityLayout load() {
			universities = showAllUniversitiesService.getAllUniversities();
			return this;
		}


		public StatisticsUniversityLayout layout() {
			setMargin(true);
			
			for (University university : universities) {
				int numOfStudents = universityStatisticsService.getNumofStudentsForUniversity(university.getId());
				Label label = new Label("<p><b>" + university.getUniversityName()+"</b>"+ " - " + numOfStudents + " student (s)" + "</p>", ContentMode.HTML);
				addComponent(label);
			}
			
			return this;
		}

	}

	@Override
	public com.vaadin.ui.Component createComponent() {
		statisticsUniversityLayout = new StatisticsUniversityLayout().load().layout();
		return statisticsUniversityLayout;
	}

	public void refresh() {
		if (statisticsUniversityLayout == null ) return;
		statisticsUniversityLayout.removeAllComponents();
		statisticsUniversityLayout.load();
		statisticsUniversityLayout.layout();
	}



}
