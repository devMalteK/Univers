package de.kochnetonline.service.universitystatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.kochnetonline.repository.univers.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class UniversityStatisticsServiceImpl implements UniversityStatisticsService {

	@Autowired
	private UniversityRepository universityRepository;
	
	@Override
	public Integer getNumofStudentsForUniversity(Integer universityId) {
		return universityRepository.getNumOfStudentsForUniversity(universityId);
	}
	

}
