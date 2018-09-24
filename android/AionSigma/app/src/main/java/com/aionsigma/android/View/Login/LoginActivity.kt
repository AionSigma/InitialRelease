package com.aionsigma.android.View.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aionsigma.android.R
import com.aionsigma.android.View.Main.MainActivity
import com.aionsigma.android.View.Register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
}
