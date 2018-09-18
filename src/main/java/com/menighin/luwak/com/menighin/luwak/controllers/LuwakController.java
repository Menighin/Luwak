package com.menighin.luwak.com.menighin.luwak.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menighin.luwak.AbstractLuwakApplication;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.models.AbstractLuwakPage;
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class LuwakController {

    @Autowired
	private AbstractLuwakApplication luwakApplication;

	@Autowired
	private ObjectMapper mapper;

    @ResponseBody
    @GetMapping(value = "/{pageName}/getAll")
    public ArrayList<ILuwakDto> getAll(@PathVariable("pageName") String pageName,
									   @RequestParam(value = "page", required = false) Integer page,
									   @RequestParam(value = "filter", required = false) String filterJson) {

		AbstractLuwakPage luwakPage = luwakApplication.getPage(pageName);
		try {
			ILuwakFilter filter = filterJson == null ? null : (ILuwakFilter) mapper.readValue(filterJson, luwakPage.getFilterClass());
			return luwakPage.getTableData(page == null ? 0 : page.intValue(), filter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

    @ResponseBody
	@GetMapping(value = "/{page}/meta")
	public LuwakPageMetadataDto getMetadata(@PathVariable("page") String pageName) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);
		return page.getPageMetadata();
	}

	@ResponseBody
	@PostMapping(value = "/{page}/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity create(@PathVariable("page") String pageName,
								 @RequestBody Map<String, Object> model) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);
		boolean created = page.create(model);

		if (created)
			return new ResponseEntity(HttpStatus.CREATED);
		else
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@PutMapping(value = "/{page}/update/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity edit(@PathVariable("page") String pageName,
							   @PathVariable("id") int id,
							   @RequestBody  Map<String, Object> model) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);
		boolean edited = page.editModel(id, model);

		if (edited) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@DeleteMapping(value = "/{page}/delete/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity delete(@PathVariable("page") String pageName,
								 @PathVariable("id") int id,
								 @RequestBody Map<String, Object> model) {
    	AbstractLuwakPage page = luwakApplication.getPage(pageName);
    	boolean deleted = page.deleteModel(id, model);

    	if (deleted)
    		return new ResponseEntity(HttpStatus.OK);
    	else
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

}
