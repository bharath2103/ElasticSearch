package com.bootelastic.service;

import com.bootelastic.adapters.AdapterTypeEnum;
import com.bootelastic.model.Student;

public interface StudentService {

	Student save(Student student, AdapterTypeEnum adapter);
	
/*	List<StudentElastic> fetchAll();
	
	void deleteById(String id);*/
	
	void deleteAll(AdapterTypeEnum adapter);

	
	
/*	void update(StudentElastic student);*/
	
//	Optional<StudentMongo> fetchById(Long id);
	

}
