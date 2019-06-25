package com.bootelastic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootelastic.adapters.AdapterTypeEnum;
import com.bootelastic.model.ElasticFileModel;
import com.bootelastic.model.FileModel;
import com.bootelastic.model.MongoFileModel;
import com.bootelastic.repository.ElasticFileRepository;
import com.bootelastic.repository.MongoFileRepository;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private ElasticFileRepository elasticFileRepository;

	@Autowired
	private MongoFileRepository mongoFileRepository;
	
	@Autowired
	private AlfrescoService AlfrescoService;

	@Override
	public FileModel save(FileModel fileModel, AdapterTypeEnum adapter) {
		
		FileModel stud = null;
		ElasticFileModel elasticFileModel = null;
		MongoFileModel mongoFileModel = null;
		if(adapter.equals(AdapterTypeEnum.ELASTICSEARCH)){

			elasticFileModel = elasticFileRepository.save(this.getElasticFileModel(fileModel, 1));
			stud = this.getFileModel(elasticFileModel);
		}
		if(adapter.equals(AdapterTypeEnum.MONGO)){

			mongoFileModel = mongoFileRepository.save(this.getMongoFileModel(fileModel, 2));
			stud =  this.getFileModel(mongoFileModel);
			
			elasticFileRepository.save(this.getElasticFileModel(stud, 2));
			
		}
		return stud;
	}

	@Override
	public void deleteAll(AdapterTypeEnum adapter) {
		
		if(adapter.equals(AdapterTypeEnum.ELASTICSEARCH)){
			elasticFileRepository.deleteAll();
		}
		if(adapter.equals(AdapterTypeEnum.MONGO)){
			mongoFileRepository.deleteAll();
		}
	}
	
	//---Model Converters -----//
	private ElasticFileModel getElasticFileModel(FileModel src, Integer persistlevel){
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		if(persistlevel == 1) {
			mapperFactory.classMap(FileModel.class, ElasticFileModel.class).byDefault();
		}
		if(persistlevel == 2){
			mapperFactory.classMap(FileModel.class, ElasticFileModel.class)
			.field("id", "id")
			.field("fileName", "fileName")
			.field("version", "version")
			.field("fileLocation", "fileLocation").register();
		}
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, ElasticFileModel.class);
	}

	private MongoFileModel getMongoFileModel(FileModel src, Integer persistlevel){
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(FileModel.class, MongoFileModel.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, MongoFileModel.class);
	}

	private FileModel getFileModel(MongoFileModel src){

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(MongoFileModel.class, FileModel.class).field("_id", "id").byDefault().register();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, FileModel.class);
	}

	private FileModel getFileModel(ElasticFileModel src){

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(ElasticFileModel.class, FileModel.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(src, FileModel.class);
	}

}
