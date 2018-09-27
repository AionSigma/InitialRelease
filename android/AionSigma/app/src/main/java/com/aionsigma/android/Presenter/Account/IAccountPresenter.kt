package com.aionsigma.android.Presenter.Account

import com.aionsigma.android.Model.Account.User

interface IAccountPresenter {
    fun authenticate(
            username:String,
            password: String
    )

}