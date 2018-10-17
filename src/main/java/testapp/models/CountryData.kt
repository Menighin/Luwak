package testapp.models

import com.menighin.luwak.core.interfaces.ILuwakModel

data class CountryData(val id: Long, val country: Country, val key: String, val value: Int) : ILuwakModel {

	companion object {
		const val ID: String = "id"
		const val KEY: String = "key"
		const val VALUE: String = "value"
	}

}