package com.aionsigma.android.Model.Account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class UserDataModel(
        @SerializedName("id") @Expose val id: Int,
        @SerializedName("name") @Expose val name: String,
        @SerializedName("phone") @Expose val phone: String,
        @SerializedName("createAt") @Expose val createAt: Date,
        @SerializedName("username") @Expose val username: String,
        @SerializedName("password") @Expose val password: String
)