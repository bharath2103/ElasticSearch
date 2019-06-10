package com.bootelastic.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootelastic.model.StudentElastic;
import com.bootelastic.model.StudentMongo;

public interface MongooRepository extends MongoRepository<StudentMongo, Long> {

}
