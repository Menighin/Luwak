package com.menighin.luwak.core.dtos

import com.menighin.luwak.core.enums.ResponseStatusEnum
import com.menighin.luwak.exceptions.CrudException

class CrudResponse<T>(val status: ResponseStatusEnum,
					  val data: T? = null,
					  val exception: CrudException? = null)