package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			
			Student jack = new Student("Jack","jack@gmail.com",LocalDate.of(2000,3, 12));
			Student xxx = new Student("xxx","xxx@gmail.com",LocalDate.of(2004,3, 12));
			Student test = new Student("test","test@gmail.com",LocalDate.of(2004,3, 12));
			
			repository.saveAll(List.of(jack,xxx,test));
		};
	};
}
