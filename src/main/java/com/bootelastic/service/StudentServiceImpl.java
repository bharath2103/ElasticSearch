package com.bootelastic.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import com.bootelastic.adapters.AdapterTypeEnum;
import com.bootelastic.config.MongoConfig;
import com.bootelastic.model.Student;
import com.bootelastic.model.StudentElastic;
import com.bootelastic.model.StudentMongo;
import com.bootelastic.repository.ElasticRepository;
import com.bootelastic.repository.MongooRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class StudentServiceImpl implements StudentService  {
	
	@Autowired
	private ElasticRepository elasticRepository;
	
	@Autowired
	private MongooRepository mongoRepository;
	
	@Autowired
	private MongoDatabase mongoDatabase;
	
	@Autowired
	private MongoConfig mongoConfig;
	
	List<StudentElastic> list = new ArrayList<>();
	
	@Override
	public void save(Student student, AdapterTypeEnum adapter) {
		if(adapter.equals(AdapterTypeEnum.ELASTICSEARCH)){
			elasticRepository.save(this.getStudentElasticModel(student));
		}
		if(adapter.equals(AdapterTypeEnum.MONGO)){
			mongoRepository.save(this.getStudentMongoModel(student));
/*			MongoCollection<Document> col = mongoDatabase.getCollection(mongoConfig.getMongoCollName());
			Document emp1 = new Document();
	        emp1.put("name", "bharath batra");
	        emp1.put("age", "30");
	        emp1.put("city", "cbe");
	        emp1.put("country", "india");
	        col.insertOne(emp1);*/
			
			
			
		}
	}

	@Override
	public List<StudentElastic> fetchAll() {
		list.clear();
		Iterable<StudentElastic> itr = elasticRepository.findAll();
		itr.forEach(s-> list.add(s));
		return list;
	}
	
	@Override
	public void deleteById(String id) {
		elasticRepository.deleteById(id);
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
	
	@Override
	public void update(StudentElastic student) {
		if(elasticRepository.existsById(student.getId())){
			elasticRepository.save(student);
		}
	}

	@Override
	public Optional<StudentMongo> fetchById(Long id) {
		return mongoRepository.findById(id);
	}
	
	public StudentElastic getStudentElasticModel(Student src){
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Student.class, StudentElastic.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, StudentElastic.class);
	}
	
	public StudentMongo getStudentMongoModel(Student src){
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Student.class, StudentMongo.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, StudentMongo.class);
	}
}
