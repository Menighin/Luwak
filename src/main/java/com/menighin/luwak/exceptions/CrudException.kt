package com.menighin.luwak.exceptions

class CrudException(message: String, validations: Map<String, String> = HashMap()) : Exception(message)