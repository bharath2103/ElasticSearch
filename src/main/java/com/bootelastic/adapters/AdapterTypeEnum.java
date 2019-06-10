package com.bootelastic.adapters;

import java.util.stream.Stream;

public enum AdapterTypeEnum {

	ELASTICSEARCH("elasticsearch", "com.bootelastic.model.StudentElastic"),
	MONGO("mongo", "com.bootelastic.model.StudentMongo");
	private String adapterName;
	private String className;

	private AdapterTypeEnum(String adapterName, String className) {
		this.adapterName = adapterName;
		this.className = className;
	}
	public String getAdapterName() {
		return adapterName;
	}
	public String getClassName() {
		return className;
	}
	public static Stream<AdapterTypeEnum> stream() {
		return Stream.of(AdapterTypeEnum.values()); 
	}
}
