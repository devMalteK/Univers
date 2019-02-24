package de.kochnetonline.service.removestudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kochnetonline.model.entity.Student;
import de.kochnetonline.repository.student.StudentRepository;

@Service
public class RemoveStudentServiceImpl implements RemoveStudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public void removeStudent(Student student) {
		studentRepository.delete(student);
	}

}
