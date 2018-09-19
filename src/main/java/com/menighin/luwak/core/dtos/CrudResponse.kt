package com.menighin.luwak.core.dtos

import com.menighin.luwak.core.enums.ResponseStatusEnum

class CrudResponse(val status: ResponseStatusEnum, val validations: Map<String, String>? = null) {
}