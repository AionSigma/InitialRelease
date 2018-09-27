package com.aionsigma.android.View.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.aionsigma.android.Model.Account.User
import com.aionsigma.android.Presenter.Account.AccountPresenter
import com.aionsigma.android.Presenter.Account.IAccountPresenter
import com.aionsigma.android.R
import com.aionsigma.android.Ultilities.SharedPreferencesUtils
import com.aionsigma.android.View.Main.MainActivity
import com.aionsigma.android.View.Register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), ILoginViewHandle {

    var accountPresenter: IAccountPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        accountPresenter = AccountPresenter(loginViewHandle = this)
    }

    fun btnLoginOnClicked(view: View){
        accountPresenter?.authenticate(login_etUsername.text!!.toString(),login_etPassword.text!!.toString())
    }

    fun btnSignUpOnClicked(view:View){
        val signUpIntent = Intent(this, RegisterActivity::class.java)
        startActivity(signUpIntent)
    }

    override fun authenticateSuccess(result: User){
        if(result.authenticated!!){
            SharedPreferencesUtils.writeUserLogin(this,result)
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
        else{
            Toast.makeText(this, "Login failed!", Toast.LENGTH_LONG).show()
        }
    }

    override fun authenticateError(error : Throwable){
        Toast.makeText(this,"error: ${error.message}",Toast.LENGTH_LONG).show()
    }

}
