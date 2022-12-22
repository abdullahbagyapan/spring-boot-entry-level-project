package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


//				we work on Student object and Student Id' type  = Long

// 		Data Access Layer

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
	
	
	@Query("SELECT std FROM Student std WHERE std.email= ?1")
	java.util.Optional<Student> findStudentByEmail(String email);
	
}
