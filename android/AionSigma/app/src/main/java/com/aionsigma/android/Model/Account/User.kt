package com.aionsigma.android.Model.Account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("userId")
    @Expose
    var userId: Int? = null
    @SerializedName("base64EncodedAuthenticationKey")
    @Expose
    var base64EncodedAuthenticationKey: String? = null
    @SerializedName("authenticated")
    @Expose
    var authenticated: Boolean? = null
    @SerializedName("officeId")
    @Expose
    var officeId: Int? = null
    @SerializedName("officeName")
    @Expose
    var officeName: String? = null
    @SerializedName("roles")
    @Expose
    var roles: List<Role>? = null
    @SerializedName("permissions")
    @Expose
    var permissions: List<String>? = null
    @SerializedName("shouldRenewPassword")
    @Expose
    var shouldRenewPassword: Boolean? = null
    @SerializedName("isTwoFactorAuthenticationRequired")
    @Expose
    var isTwoFactorAuthenticationRequired: Boolean? = null

}