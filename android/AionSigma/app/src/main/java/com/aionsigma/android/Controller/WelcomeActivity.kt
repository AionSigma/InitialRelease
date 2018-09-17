package com.aionsigma.android.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aionsigma.android.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun btnCreateCircleOnClicked(view: View){
        var loginIntent = Intent(this,LoginActivity::class.java)
        startActivity(loginIntent)
    }
}
