package com.bootelastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bootelastic.model.Student;

public interface StudentRepository extends ElasticsearchRepository<Student, String> {
}
