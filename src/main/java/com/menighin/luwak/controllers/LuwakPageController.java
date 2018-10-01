package com.menighin.luwak.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menighin.luwak.AbstractLuwakApplication;
import com.menighin.luwak.core.dtos.CrudResponse;
import com.menighin.luwak.core.enums.ResponseStatusEnum;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.models.AbstractLuwakMasterDetailPage;
import com.menighin.luwak.core.models.AbstractLuwakPage;
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto;
import kotlin.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LuwakPageController {

    @Autowired
	private AbstractLuwakApplication luwakApplication;

	@Autowired
	private ObjectMapper mapper;

	@ResponseBody
	@GetMapping(value = "/{page}/meta")
	public LuwakPageMetadataDto getMetadata(@PathVariable("page") String pageName) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);
		return page.getPageMetadata();
	}

	@ResponseBody
	@GetMapping(value = "/{pageName}/filterOptions")
	public CrudResponse<List<Pair<Integer, String>>> getFilterOptions(@PathVariable("pageName") String pageName,
																	  @RequestParam(value = "field", required = false) String field,
																	  @RequestParam(value = "input", required = false) String input) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);

		try {
			List<Pair<Integer, String>> options = page.getFilterValues(field, input);
			return new CrudResponse<>(ResponseStatusEnum.SUCCESS, options, null, null);
		} catch (Exception e) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, null, e.getMessage());
		}
	}

    @ResponseBody
    @GetMapping(value = "/{pageName}/getAll")
    public CrudResponse<ArrayList<ILuwakDto>> getAll(@PathVariable("pageName") String pageName,
									   @RequestParam(value = "page", required = false) Integer page,
									   @RequestParam(value = "filter", required = false) String filterJson) {

		AbstractLuwakPage luwakPage = luwakApplication.getPage(pageName);
		try {
			ILuwakFilter filter = filterJson == null ? null : (ILuwakFilter) mapper.readValue(filterJson, luwakPage.getFilterClass());
			return luwakPage.getAll(page == null ? 0 : page.intValue(), filter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

	@ResponseBody
	@PostMapping(value = "/{page}/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CrudResponse> create(@PathVariable("page") String pageName,
											   @RequestBody Map<String, Object> model) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);
		CrudResponse crudResponse = page.create(model);

		if (crudResponse.getStatus() == ResponseStatusEnum.SUCCESS)
			return new ResponseEntity<>(crudResponse, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(crudResponse, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@PutMapping(value = "/{page}/update/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CrudResponse> edit(@PathVariable("page") String pageName,
							   @PathVariable("id") int id,
							   @RequestBody  Map<String, Object> model) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);
		CrudResponse crudResponse = page.editModel(id, model);

		if (crudResponse.getStatus() == ResponseStatusEnum.SUCCESS)
			return new ResponseEntity<>(crudResponse, HttpStatus.OK);
		else
			return new ResponseEntity<>(crudResponse, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@DeleteMapping(value = "/{page}/delete/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CrudResponse> delete(@PathVariable("page") String pageName,
								 @PathVariable("id") int id,
								 @RequestBody Map<String, Object> model) {
    	AbstractLuwakPage page = luwakApplication.getPage(pageName);
    	CrudResponse crudResponse = page.deleteModel(id, model);

		if (crudResponse.getStatus() == ResponseStatusEnum.SUCCESS)
    		return new ResponseEntity<>(crudResponse, HttpStatus.OK);
    	else
    		return new ResponseEntity<>(crudResponse, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@GetMapping(value = "/{page}/count")
	public ResponseEntity<CrudResponse<Integer>> count(@PathVariable("page") String pageName) {
		AbstractLuwakPage luwakPage = luwakApplication.getPage(pageName);
		try {
			return new ResponseEntity<CrudResponse<Integer>>(luwakPage.count(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@ResponseBody
	@GetMapping(value = "/{pageName}/detail/getAll")
	public CrudResponse<ArrayList<ILuwakDto>> getDetailAll(@PathVariable("pageName") String pageName,
											 @RequestParam(value = "masterId") Integer masterId,
											 @RequestParam(value = "page", required = false) Integer page,
											 @RequestParam(value = "filter", required = false) String filterJson) {

		AbstractLuwakMasterDetailPage luwakPage = (AbstractLuwakMasterDetailPage) luwakApplication.getPage(pageName);
		try {
			ILuwakFilter filter = filterJson == null ? null : (ILuwakFilter) mapper.readValue(filterJson, luwakPage.getFilterClass());
			return luwakPage.getDetailAll(masterId, page == null ? 0 : page.intValue(), filter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@ResponseBody
	@GetMapping(value = "/{page}/detail/count")
	public ResponseEntity<CrudResponse<Integer>> countDetail(@PathVariable("page") String pageName, @RequestParam(value = "masterId") Integer masterId) {
		AbstractLuwakMasterDetailPage luwakPage = (AbstractLuwakMasterDetailPage) luwakApplication.getPage(pageName);
		try {
			return new ResponseEntity<CrudResponse<Integer>>(luwakPage.countDetail(masterId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@GetMapping("/i18nTest")
	public String test(Locale loc) {
		return messageSource.getMessage("msg.testing", null, loc);
	}

}
