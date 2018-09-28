package com.aionsigma.android.View.Test

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.aionsigma.android.R
import com.aionsigma.android.Room.AppDatabase
import com.aionsigma.android.Room.UserInfo
import com.aionsigma.android.Room.UserInfoDao
import com.aionsigma.android.Ultilities.LocationUtils
import com.aionsigma.android.View.Welcome.WelcomeActivity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test.*

import java.util.*

class TestActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()
    lateinit var db : AppDatabase
    lateinit var userDao : UserInfoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        db = AppDatabase.getAppDatabase(this)
        userDao = db.userInfoDao()
    }

    fun btnTestRoomOnClicked(view: View) {
//        val populateDbAsync = PopulateDbAsync(this)
//        populateDbAsync.execute()
        callTest()
        callGetUserFromRoom()
    }

    fun callTest() {
        val TAG = "ROOM"
        try {
            val userInfo = UserInfo("test", "123456789", "test")
            compositeDisposable.add(Observable.fromCallable { userDao.insert(userInfo) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { _ ->
                                Log.d(TAG, "Inserted")
                            },
                            { error ->
                                Log.d(TAG, error.message)
                            }
                    )
            )
        } catch (ex: Exception) {
            Log.d(TAG, ex.message)
        }
    }
    @SuppressLint("CheckResult")
    fun callGetUserFromRoom(){
        userDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result->Log.d("ROOM", result[0].userInfoId)
                }
    }

    fun btnGoAppOnClicked(view: View) {
        val app = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }

//    private class PopulateDbAsync internal constructor(private val context: Context) : AsyncTask<Void, Void, String>() {
//
//        private val db: AppDatabase
//        internal var locationUtils: LocationUtils
//
//        init {
//            db = AppDatabase.getAppDatabase(context)
//            locationUtils = LocationUtils(context)
//        }
//
//        override fun doInBackground(vararg params: Void): String? {
////            val lattitude: String
////            val longitude: String
//
//            try {
//                val location = locationUtils.location
//                if (location != null) {
//                    val latti = location!!.getLatitude()
//                    val longi = location!!.getLongitude()
////                    lattitude = latti.toString()
////                    longitude = longi.toString()
//                }
//            } catch (e: Exception) {
//                return e.message
//            }
//
//            try {
//                val userInfo = UserInfo("test", "123456789", "test")
//                val myDao = db.userInfoDao()
//                myDao.insert(userInfo)
//                val userInfoList = myDao.getAll()
//                return userInfoList[0].userInfoId + "---" + userInfoList[0].data
//            } catch (ex: Exception) {
//                return ex.message
//            }
//
//        }
//
//        override fun onPostExecute(agentsCount: String) {
//            Toast.makeText(context, agentsCount, Toast.LENGTH_LONG).show()
//        }
//
//    }
}
