package com.aionsigma.android.View.Test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aionsigma.android.R
import com.aionsigma.android.Room.AppDatabase
import com.aionsigma.android.Room.UserInfoDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test_sms_receiver.*

class TestSmsReceiverActivity : AppCompatActivity() {
    val compositeDisposable = CompositeDisposable()
    lateinit var db : AppDatabase
    lateinit var userDao : UserInfoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_sms_receiver)

        db = AppDatabase.getAppDatabase(this)
        userDao = db.userInfoDao()
        callGetUserFromRoom()
    }

    fun callGetUserFromRoom(){
        val sb = StringBuffer()
        userDao.loadAllByType("sms").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result->
                    for (i in result)
                    {
                        sb.append(i.data)
                    }
                    tvResults.text = sb.toString()
                }

    }
}
