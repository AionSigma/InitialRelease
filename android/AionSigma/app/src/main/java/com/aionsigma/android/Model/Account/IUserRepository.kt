package com.aionsigma.android.Model.Account


import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.Call

interface IUserRepository{
    @GET("/users")
    fun getAll(): Flowable<List<UserDataModel>>
}