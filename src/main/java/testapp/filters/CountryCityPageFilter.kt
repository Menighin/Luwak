package testapp.filters

import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.LuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakFilter

@LuwakFilter
class CountryCityPageFilter : ILuwakFilter {

    @Label("Id")
    var id: Int? = null

    @Label("City")
    var city: String? = null

    @Label("Country")
    var country: String? = null

}