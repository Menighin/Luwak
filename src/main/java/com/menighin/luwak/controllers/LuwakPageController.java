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
import com.menighin.luwak.exceptions.CrudException;
import kotlin.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
			return new CrudResponse<>(ResponseStatusEnum.SUCCESS, options, null);
		}
		catch (CrudException ce) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, ce);
		}
		catch (Exception e) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, new CrudException(e.getMessage(), new HashMap()));
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
			return new CrudResponse<ArrayList<ILuwakDto>>(ResponseStatusEnum.SUCCESS, luwakPage.getAll(page == null ? 0 : page.intValue(), filter), null);
		}
		catch (CrudException ce) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, ce);
		}
		catch (Exception e) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, new CrudException(e.getMessage(), new HashMap()));
		}
    }

	@ResponseBody
	@PostMapping(value = "/{page}/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CrudResponse create(@PathVariable("page") String pageName,
											   @RequestBody Map<String, Object> model) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);

		try {
			Boolean result = page.create(model);
			return new CrudResponse<>(ResponseStatusEnum.SUCCESS, result, null);
		}
		catch (CrudException ce) {
			return new CrudResponse<Boolean>(ResponseStatusEnum.ERROR, null, ce);
		}
		catch (Exception e) {
			return new CrudResponse<Boolean>(ResponseStatusEnum.ERROR, null, new CrudException(e.getMessage(), new HashMap()));
		}

	}

	@ResponseBody
	@PutMapping(value = "/{page}/update/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CrudResponse edit(@PathVariable("page") String pageName,
							   @PathVariable("id") int id,
							   @RequestBody  Map<String, Object> model) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);

		try {
			Boolean result = page.editModel(id, model);
			return new CrudResponse<>(ResponseStatusEnum.SUCCESS, result, null);
		}
		catch (CrudException ce) {
			return new CrudResponse<Boolean>(ResponseStatusEnum.ERROR, null, ce);
		}
		catch (Exception e) {
			return new CrudResponse<Boolean>(ResponseStatusEnum.ERROR, null, new CrudException(e.getMessage(), new HashMap()));
		}
	}

	@ResponseBody
	@DeleteMapping(value = "/{page}/delete/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CrudResponse delete(@PathVariable("page") String pageName,
								 @PathVariable("id") int id,
								 @RequestBody Map<String, Object> model) {
    	AbstractLuwakPage page = luwakApplication.getPage(pageName);

		try {
			Boolean result = page.deleteModel(id, model);
			return new CrudResponse<>(ResponseStatusEnum.SUCCESS, result, null);
		}
		catch (CrudException ce) {
			return new CrudResponse<Boolean>(ResponseStatusEnum.ERROR, null, ce);
		}
		catch (Exception e) {
			return new CrudResponse<Boolean>(ResponseStatusEnum.ERROR, null, new CrudException(e.getMessage(), new HashMap()));
		}
	}

	@ResponseBody
	@GetMapping(value = "/{page}/count")
	public CrudResponse<Integer> count(@PathVariable("page") String pageName) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);

		try {
			Integer result = page.count();
			return new CrudResponse<>(ResponseStatusEnum.SUCCESS, result, null);
		}
		catch (CrudException ce) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, ce);
		}
		catch (Exception e) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, new CrudException(e.getMessage(), new HashMap()));
		}
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
			return new CrudResponse<ArrayList<ILuwakDto>>(ResponseStatusEnum.SUCCESS,
					luwakPage.getDetailAll(masterId, page == null ? 0 : page.intValue(), filter), null);
		}
		catch (CrudException ce) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, ce);
		}
		catch (Exception e) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, new CrudException(e.getMessage(), new HashMap()));
		}

	}

	@ResponseBody
	@GetMapping(value = "/{page}/detail/count")
	public CrudResponse<Integer> countDetail(@PathVariable("page") String pageName, @RequestParam(value = "masterId") Integer masterId) {
		AbstractLuwakMasterDetailPage page = (AbstractLuwakMasterDetailPage) luwakApplication.getPage(pageName);

		try {
			return new CrudResponse<>(ResponseStatusEnum.SUCCESS, page.countDetail(masterId), null);
		}
		catch (CrudException ce) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, ce);
		}
		catch (Exception e) {
			return new CrudResponse<>(ResponseStatusEnum.ERROR, null, new CrudException(e.getMessage(), new HashMap()));
		}
	}

	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@GetMapping("/i18nTest")
	public String test(Locale loc) {
		return messageSource.getMessage("msg.testing", null, loc);
	}

}
