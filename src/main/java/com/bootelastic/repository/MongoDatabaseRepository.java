package com.bootelastic.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bootelastic.model.StudentMongo;

public interface MongoDatabaseRepository extends MongoRepository<StudentMongo, Long> {

}
