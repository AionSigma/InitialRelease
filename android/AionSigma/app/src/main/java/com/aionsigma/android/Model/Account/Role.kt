package com.aionsigma.android.Model.Account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Role {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("disabled")
    @Expose
    var disabled: Boolean? = null

}