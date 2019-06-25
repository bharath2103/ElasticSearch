package com.bootelastic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootelastic.adapters.AdapterTypeEnum;
import com.bootelastic.model.FileModel;
import com.bootelastic.model.UserAccount;
import com.bootelastic.service.AlfrescoService;
import com.bootelastic.service.AlfrescoServiceImpl;
import com.bootelastic.service.FileService;
import com.bootelastic.service.IUserAccountService;
import com.bootelastic.service.UserAccountServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@Controller
public class DmsController {
	
	@Autowired 
	private FileService fileService;		
	
	@Autowired
	private AlfrescoService alfrescoService;
	
	@Autowired
	private IUserAccountService userAccountService;
	
/*	@PostMapping(value="/saveFile/{adapter}")
	@ResponseBody
	public FileModel saveFile(@PathVariable("adapter") AdapterTypeEnum adapter, @RequestBody FileModel fileModel)
	{
		return fileService.save(fileModel, adapter);
	}
	
	@GetMapping(value="/deleteFile/{adapter}")
	@ResponseBody
	public void deleteFile(@PathVariable("adapter") AdapterTypeEnum adapter) {
		fileService.deleteAll(adapter);
	}*/

	@GetMapping(value= "/fresco")
	@ResponseBody
	public void persistFileAlfresco() {
		alfrescoService.createFileNFolder();
	}
	
	@GetMapping(value= "/fresco/getDocument")
	@ResponseBody
	public String fetchFileAlfresco() {
		return alfrescoService.fetchDocument();
	}
	
	@GetMapping(value= "/fresco/checkDuplicates/{folder}/{file}")
	@ResponseBody
	public boolean checkDuplicates(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		return alfrescoService.checkDuplicates(folder, file);
	}
	
	@PostMapping(value = "/save/useraccount")
	@ResponseBody
	public void saveUserAccount(@RequestBody UserAccount userAccount) {
		userAccountService.save(userAccount);
	}

/*	
	public static void main(String[] args) {
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername("Bharath");
		userAccount.setPassword("abc123");
		List<String> adapterList = new ArrayList<>();
		adapterList.add("Alfresco");
		adapterList.add("sharepoint");
		userAccount.setAdapter(adapterList);
		
		ObjectMapper Obj = new ObjectMapper(); 
		String jsonString = null;
		try {
			jsonString = Obj.writeValueAsString(userAccount);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonString);
		
		
		
	
	}*/
}
 