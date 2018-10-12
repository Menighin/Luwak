package testapp.models

import com.menighin.luwak.core.interfaces.ILuwakModel

class Country(var id: Int, var code: String?) : ILuwakModel {

    companion object {
        const val ID: String = "id"
        const val CODE: String = "code"
    }

}