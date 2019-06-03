package com.bootelastic.controller;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootelastic.model.Student;
import com.bootelastic.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping(value="/saveCustomer")
	public void saveCustomer(@RequestBody Student student)
	{
		studentService.save(student);

	}

/*	public static void main(String[] args)
	{
		Student student = new Student();
		student.setName("vinod");
		student.setAge("20");
		student.setCity("Coimbatore");
		student.setCountry("india");
		System.out.println("God is great");

		ObjectMapper Obj = new ObjectMapper(); 
		try { 

			// get Oraganisation object as a json string 
			String jsonStr = Obj.writeValueAsString(student); 

			// Displaying JSON String 
			System.out.println(jsonStr); 
		} 

		catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}*/
}
