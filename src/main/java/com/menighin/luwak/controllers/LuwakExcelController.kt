package com.menighin.luwak.controllers

import com.menighin.luwak.AbstractLuwakApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
open class LuwakExcelController {

	@Autowired
	private val luwakApplication: AbstractLuwakApplication? = null

	@ResponseBody
	@GetMapping(value = "/{pageName}/export")
	fun export(@PathVariable pageName: String) {
		val page = luwakApplication?.getPage(pageName)
		


	}

}