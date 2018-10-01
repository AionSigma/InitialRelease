package com.aionsigma.android.Presenter.Account

import android.content.Context
import android.util.Log
import com.aionsigma.android.Model.Account.IUserRepository
import com.aionsigma.android.Model.Account.User
import com.aionsigma.android.Model.Repository
import com.aionsigma.android.Ultilities.SharedPreferencesUtils
import com.aionsigma.android.View.Login.ILoginViewHandle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AccountPresenter(private val loginViewHandle: ILoginViewHandle): IAccountPresenter{

    companion object {
        fun logOut(context: Context){
            SharedPreferencesUtils.clearAll(context)
            Repository.setCurrentUser(null)
        }
    }


    override fun authenticate(username: String, password: String) {
        val map= mapOf<String, String>(
                "Content-Type" to "application/json",
                "Fineract-Platform-TenantId" to "default",
                "Accept" to "application/json")
        Repository.createService(IUserRepository::class.java, map).authentication(username,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            result->
                            loginViewHandle.authenticateSuccess(result)
                            if(result.authenticated!!)
                                Repository.setCurrentUser(result)
                        },
                        {
                            error->
                            loginViewHandle.authenticateError(error)
                        }
                )
    }



}