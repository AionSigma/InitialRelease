package com.aionsigma.android.View.Login

import com.aionsigma.android.Model.Account.User

interface ILoginViewHandle {
    fun authenticateSuccess(user:User)
    fun authenticateError(error:Throwable)
}