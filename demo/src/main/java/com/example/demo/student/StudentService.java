package com.example.demo.student;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;




//	Service layer

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}



	public List<Student> getStudents() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

	public void addNewStudent(Student student) {
		java.util.Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
		
		if (!optionalStudent.isPresent()) {
			studentRepository.save(student);
		}
		else {
			throw new IllegalStateException("Email has already taken");
		}
		
	}
	
	public void deleteStudent(Long Id) {
		boolean exists = studentRepository.existsById(Id);
		if (!exists) {
			throw new IllegalStateException("student with Id = "+Id+" does not exists");
		}
		else {
			studentRepository.deleteById(Id);
		}
	}
	
	@Transactional
	public void updateStudent(Long studentId,String name,String email) {
		Student student = studentRepository.findById(studentId).orElseThrow(
				() -> new IllegalStateException("student with Id = "+studentId+" does not exists"));
		
		if (name != null && name.length()> 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if (email != null && email.length()> 0 && !Objects.equals(student.getEmail(), email)) {
			java.util.Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
			
			if (optionalStudent.isPresent()) {
				throw new IllegalStateException("Email has already taken");
			}
			
			student.setEmail(email);
			
		}
		
	}
}
