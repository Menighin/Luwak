package com.menighin.luwak.com.menighin.luwak.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menighin.luwak.AbstractLuwakApplication;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.models.AbstractLuwakPage;
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LuwakController {

    @Autowired
	private AbstractLuwakApplication luwakApplication;

    @ResponseBody
    @RequestMapping(value = "/{page}/getAll", method = RequestMethod.GET)
    public ArrayList<ILuwakDto> getAll(@PathVariable("page") String pageName, @RequestParam("filter") String filterJson) {

		AbstractLuwakPage page = luwakApplication.getPage(pageName);
		try {
			ILuwakFilter filter = (ILuwakFilter) new ObjectMapper().readValue(filterJson, page.getFilterClass());
			int i = 0;
			return page.getMasterTableData(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

    @ResponseBody
	@RequestMapping(value = "/{page}/meta", method = RequestMethod.GET)
	public LuwakPageMetadataDto getMetadata(@PathVariable("page") String pageName) {
		AbstractLuwakPage page = luwakApplication.getPage(pageName);
		return page.getPageMetadata();
	}
}
