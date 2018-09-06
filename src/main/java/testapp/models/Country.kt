package testapp.models

import com.menighin.luwak.core.interfaces.ILuwakModel

class Country(var id: Int, var code: String) : ILuwakModel {

    companion object {
        const val codeField: String = "code"
    }

}