package com.menighin.luwak.com.menighin.luwak.controllers;

import com.menighin.luwak.LuwakApplication;
import com.menighin.luwak.core.LuwakPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testapp.models.Country;

@RestController
public class LuwakController {

    @Autowired
	private LuwakApplication luwakApplication;

    @RequestMapping(value = "/{page}/getAll", method = RequestMethod.GET)
    public String getAll(@PathVariable("page") String pageName) {

		LuwakPage page = luwakApplication.getPage(pageName);

        return "It is working D: " + page;
    }

    @ResponseBody
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public Object getMetadata(@PathVariable("page") String pageName) {

		LuwakPage page = luwakApplication.getPage(pageName);

		Object test = page.getMetadata();

		return new Country(0, "Brazil");
	}

}
