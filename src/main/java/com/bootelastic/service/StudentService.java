package com.bootelastic.service;

import java.util.List;
import java.util.Optional;

import com.bootelastic.adapters.AdapterTypeEnum;
import com.bootelastic.model.Student;
import com.bootelastic.model.StudentElastic;
import com.bootelastic.model.StudentMongo;

public interface StudentService {

	void save(Student student, AdapterTypeEnum adapter );
	
	List<StudentElastic> fetchAll();
	
	void deleteById(String id);
	
	void deleteAll(AdapterTypeEnum adapter);
	
	void update(StudentElastic student);
	
	Optional<StudentMongo> fetchById(Long id);
	

}
