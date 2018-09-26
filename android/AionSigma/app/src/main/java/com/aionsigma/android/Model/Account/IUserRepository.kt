package com.aionsigma.android.Model.Account


import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface IUserRepository{
    @GET("/users")
    fun getAll(): Flowable<List<UserDataModel>>

    //https://128.199.134.53:8443/fineract-provider/api/v1/authentication?username=mifos&password=password

    @POST("authentication")
    fun authentication(@Query("username") username:String, @Query("password") password:String): Flowable<User>
    //fun authentication(@Body username:String, @Body password:String): Flowable<User>

}