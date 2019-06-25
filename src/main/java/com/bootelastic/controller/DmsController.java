package com.bootelastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootelastic.service.AlfrescoService;

@CrossOrigin
@Controller
public class DmsController {
	
	@Autowired
	private AlfrescoService alfrescoService;

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
}
 