package com.bootelastic.service;

import com.bootelastic.adapters.AdapterTypeEnum;
import com.bootelastic.model.FileModel;

public interface FileService{
	FileModel save(FileModel fileModel, AdapterTypeEnum adapter);
	
	void deleteAll(AdapterTypeEnum adapter);
}
