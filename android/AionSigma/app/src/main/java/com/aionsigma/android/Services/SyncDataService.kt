package com.aionsigma.android.Services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.widget.Toast
import com.aionsigma.android.Room.AppDatabase
import com.aionsigma.android.Room.UserInfo
import com.aionsigma.android.Ultilities.LocationUtils

import java.util.Date
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit

class SyncDataService : Service() {
    private val context: Context
    private val timerInterval = 1000 * 30// * Integer.parseInt(getString(R.string.timer_Interval_minutes));

    internal var lattitude: String? = null
    internal var longitude: String? = null
    internal var error = ""
    internal var message = ""
    internal var lastTime = Date()

    @SuppressLint("HandlerLeak")
    private val toastHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (error !== "") {
                Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
            }
            if (message !== "") {
                val diffDate = diffDateInSeconds(lastTime, Date())
                Toast.makeText(applicationContext, "Seconds: $diffDate --$message", Toast.LENGTH_SHORT).show()
                lastTime = Date()
            }
        }
    }

    init {
        context = this

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        timer.scheduleAtFixedRate(mainTask(startId), 0, timerInterval.toLong())
        return Service.START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        AppDatabase.destroyInstance()
        Toast.makeText(context, "Service stopped ..", Toast.LENGTH_LONG).show()
    }

    private fun diffDateInSeconds(startDate: Date, endDate: Date): Long {
        val diffInMs = endDate.time - startDate.time
        return TimeUnit.MILLISECONDS.toSeconds(diffInMs)
    }

    private inner class mainTask(internal var taskId: Int) : TimerTask() {
        override fun run() {

//            try{
//                val db = AppDatabase.getAppDatabase(context);
//                val userInfo = UserInfo("test","123456789","test")
//                val myDao = db.userInfoDao()
//                myDao.insert(userInfo)
//                val userInfoList = myDao.getAll()
//                val index = userInfoList.count() -1
//                message = userInfoList[index].userInfoId + "---" + userInfoList[index].data
//            }catch (ex : java.lang.Exception){
//                error = ex.message!!
//            }
//            toastHandler.sendEmptyMessage(0)

            //stopSelf(taskId);
        }
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

    companion object {
        private val timer = Timer()
    }
}