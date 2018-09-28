package com.aionsigma.android.View.Test

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.aionsigma.android.R
import com.aionsigma.android.Room.AppDatabase
import com.aionsigma.android.Room.UserInfo
import com.aionsigma.android.Ultilities.LocationUtils
import com.aionsigma.android.View.Welcome.WelcomeActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    fun btnTestRoomOnClicked(view: View){
//        val populateDbAsync = PopulateDbAsync(this)
//        populateDbAsync.execute()
        tvResult.text = callTest()
    }

    fun callTest(): String{
        try {
            val db = AppDatabase.getAppDatabase(this)
            val userInfo = UserInfo("test", "123456789", "test")
            val myDao = db.userInfoDao()
            myDao.insert(userInfo)
            val userInfoList = myDao.getAll()
            return userInfoList[0].userInfoId + "---" + userInfoList[0].data
        } catch (ex: Exception) {
            return ex.message!!
        }
    }
    fun btnGoAppOnClicked(view:View){
        val app = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }

    private class PopulateDbAsync internal constructor(private val context: Context) : AsyncTask<Void, Void, String>() {

        private val db: AppDatabase
        internal var locationUtils: LocationUtils

        init {
            db = AppDatabase.getAppDatabase(context)
            locationUtils = LocationUtils(context)
        }

        override fun doInBackground(vararg params: Void): String? {
//            val lattitude: String
//            val longitude: String

            try {
                val location = locationUtils.location
                if (location != null) {
                    val latti = location!!.getLatitude()
                    val longi = location!!.getLongitude()
//                    lattitude = latti.toString()
//                    longitude = longi.toString()
                }
            } catch (e: Exception) {
                return e.message
            }

            try {
                val userInfo = UserInfo("test", "123456789", "test")
                val myDao = db.userInfoDao()
                myDao.insert(userInfo)
                val userInfoList = myDao.getAll()
                return userInfoList[0].userInfoId + "---" + userInfoList[0].data
            } catch (ex: Exception) {
                return ex.message
            }

        }

        override fun onPostExecute(agentsCount: String) {
            Toast.makeText(context, agentsCount, Toast.LENGTH_LONG).show()
        }

    }
}
