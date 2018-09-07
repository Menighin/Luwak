package com.menighin.luwak.com.menighin.luwak.controllers;

import com.menighin.luwak.LuwakApplication;
import com.menighin.luwak.core.LuwakPage;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.models.LuwakPageMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LuwakController {

    @Autowired
	private LuwakApplication luwakApplication;

    @ResponseBody
    @RequestMapping(value = "/{page}/getAll", method = RequestMethod.GET)
    public ArrayList getAll(@PathVariable("page") String pageName) {

		LuwakPage page = luwakApplication.getPage(pageName);

        return page.getMasterTable().getRepository().getAll();
    }

    @ResponseBody
	@RequestMapping(value = "/{page}/meta", method = RequestMethod.GET)
	public LuwakPageMetadata getMetadata(@PathVariable("page") String pageName) {
		LuwakPage page = luwakApplication.getPage(pageName);
		return page.getPageMetadata();
	}

}
