package com.aionsigma.android.View.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.aionsigma.android.Model.Account.IUserRepository
import com.aionsigma.android.Model.Repository
import com.aionsigma.android.R
import com.aionsigma.android.View.Main.MainActivity
import com.aionsigma.android.View.Register.RegisterActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getUsers()
    }

    fun btnLoginOnClicked(view: View){
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    fun btnSignUpOnClicked(view:View){
        val signUpIntent = Intent(this, RegisterActivity::class.java)
        startActivity(signUpIntent)
    }

    fun getUsers(){
        val TAG = "getUsers"

        Repository.createService(IUserRepository::class.java).getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            result->
                            Log.d(TAG,result.count().toString())
                        },
                        {
                            error->
                            Log.d(TAG,error.message)
                        }
                )
    }
}
