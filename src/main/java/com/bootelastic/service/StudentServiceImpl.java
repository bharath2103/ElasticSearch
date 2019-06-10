package com.bootelastic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootelastic.adapters.AdapterTypeEnum;
import com.bootelastic.model.FileModel;
import com.bootelastic.model.Student;
import com.bootelastic.model.StudentElastic;
import com.bootelastic.model.StudentMongo;
import com.bootelastic.repository.ElasticRepository;
import com.bootelastic.repository.MongoDatabaseRepository;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class StudentServiceImpl implements StudentService  {
	
	@Autowired
	private ElasticRepository elasticRepository;
	
	@Autowired
	private MongoDatabaseRepository mongoRepository;
	
	List<StudentElastic> list = new ArrayList<>();
	
	@Override
	public Student save(Student student, AdapterTypeEnum adapter) {
		Student stud = null;
		StudentElastic studElastic = null;
		StudentMongo studMongo = null;
		if(adapter.equals(AdapterTypeEnum.ELASTICSEARCH)){
			
			studElastic = elasticRepository.save(this.getStudentElasticModel(student));
			stud = this.getStudentModel(studElastic);
		}
		if(adapter.equals(AdapterTypeEnum.MONGO)){
			
			studMongo = mongoRepository.save(this.getStudentMongoModel(student));
			stud =  this.getStudentModel(studMongo);
		}
		return stud;
	}
	
	@Override
	public void deleteAll(AdapterTypeEnum adapter) {
		
		if(adapter.equals(AdapterTypeEnum.ELASTICSEARCH)){
			elasticRepository.deleteAll();
		}
		if(adapter.equals(AdapterTypeEnum.MONGO)){
			mongoRepository.deleteAll();
		}
	}

/*
	@Override
	public List<StudentElastic> fetchAll() {
		list.clear();
		Iterable<StudentElastic> itr = elasticRepository.findAll();
		itr.forEach(s-> list.add(s));
		return list;
	}
	student
	@Override
	public void deleteById(String id) {
		elasticRepository.deleteById(id);
	}*/

/*	
	@Override
	public void update(StudentElastic student) {
		if(elasticRepository.existsById(student.getId())){
			elasticRepository.save(student);
		}
	}*/

	
	private StudentElastic getStudentElasticModel(Student src){
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Student.class, StudentElastic.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, StudentElastic.class);
	}
	
	private StudentMongo getStudentMongoModel(Student src){
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Student.class, StudentMongo.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, StudentMongo.class);
	}
	
	private Student getStudentModel(StudentMongo src){

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(StudentMongo.class, Student.class).field("_id", "id").byDefault().register();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, Student.class);
	}
	
	private Student getStudentModel(StudentElastic src){

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(StudentElastic.class, Student.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, Student.class);
	}











}
