package com.menighin.luwak.controllers

import com.menighin.luwak.AbstractLuwakApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File
import java.io.FileOutputStream

@RestController
@RequestMapping("/api")
open class LuwakExcelController {

	@Autowired
	private val luwakApplication: AbstractLuwakApplication? = null

	@ResponseBody
	@GetMapping(value = "/{pageName}/export", produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
	fun export(@PathVariable pageName: String) : ResponseEntity<FileSystemResource> {
		val page = luwakApplication?.getPage(pageName)
		
		if (page != null) {
			val wb = page.excelFile
			val filename = "${wb.getSheetName(0)}.xlsx";
			val file = File("${wb.getSheetName(0)}.xlsx")
			val fileStream = FileOutputStream(file)
			wb.write(fileStream)
			wb.close()

			return ResponseEntity.ok()
					.header("Content-Disposition", "attachment; filename=$filename")
					.contentLength(file.length())
					.lastModified(file.lastModified())
					.body(FileSystemResource(file))
		}

		return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
	}

}