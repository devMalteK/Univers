package de.kochnetonline.service.addstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kochnetonline.model.entity.Student;
import de.kochnetonline.repository.student.StudentRepository;

@Service
public class AddStudentServiceImpl implements AddStudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public void saveStudent(Student studentDAO) {
		
		Student student=new Student();
		
		student.setFirstName(studentDAO.getFirstName());
		student.setLastName(studentDAO.getLastName());
		student.setAge(studentDAO.getAge());
		student.setGender(studentDAO.getGender());
		student.setUniversity(studentDAO.getUniversity());
		
		studentRepository.save(student);
	}


}
