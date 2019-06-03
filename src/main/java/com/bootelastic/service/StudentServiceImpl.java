package com.bootelastic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootelastic.model.Student;
import com.bootelastic.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService  {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student save(Student student) {
		return this.studentRepository.save(student);
	}
}
