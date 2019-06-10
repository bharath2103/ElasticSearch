package com.bootelastic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootelastic.model.MongoFileModel;

public interface MongoFileRepository extends MongoRepository<MongoFileModel, Long> {

}
