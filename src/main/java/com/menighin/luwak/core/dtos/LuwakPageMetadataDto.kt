package com.menighin.luwak.core.dtos

import java.util.ArrayList

class LuwakPageMetadataDto {

	var title: String? = null

	var masterTable: LuwakDataTableMetadataDto? = null

	var detailTables: List<LuwakDataTableMetadataDto>? = null

	var filters: ArrayList<LuwakFilterMetadataDto> = ArrayList()

}
