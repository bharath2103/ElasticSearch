package com.bootelastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bootelastic.model.StudentElastic;

public interface ElasticRepository extends ElasticsearchRepository<StudentElastic, String> {
}
