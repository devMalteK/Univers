package de.kochnetonline.service.universitystatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kochnetonline.repository.univers.UniversityRepository;

@Service
public class UniversityStatisticsServiceImpl implements UniversityStatisticsService {

	@Autowired
	private UniversityRepository universityRepository;
	
	@Override
	public Integer getNumofStudentsForUniversity(Integer universityId) {
		return universityRepository.getNumOfStudentsForUniversity(universityId);
	}
	

}
