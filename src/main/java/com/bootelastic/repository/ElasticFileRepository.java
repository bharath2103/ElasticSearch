package com.bootelastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bootelastic.model.ElasticFileModel;

public interface ElasticFileRepository extends ElasticsearchRepository<ElasticFileModel, String>{

}
