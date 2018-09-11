package testapp.filters

import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.LuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakFilter

@LuwakFilter
class CityPageFilter : ILuwakFilter {

    @Label("Id")
    var id: Int = 0

    @Label("Name")
    var name: String = ""

}