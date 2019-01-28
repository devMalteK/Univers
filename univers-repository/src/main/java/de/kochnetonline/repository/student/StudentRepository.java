package de.kochnetonline.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.kochnetonline.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
