package com.menighin.luwak.core.dtos

import com.menighin.luwak.core.enums.ResponseStatusEnum

class CrudResponse<T>(val status: ResponseStatusEnum,
					  val data: T? = null,
					  val validations: Map<String, String>? = null,
					  val msg: String? = null)