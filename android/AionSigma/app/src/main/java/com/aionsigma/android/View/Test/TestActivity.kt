package com.aionsigma.android.View.Test

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
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
import android.telephony.gsm.GsmCellLocation
import android.telephony.TelephonyManager
import android.telephony.cdma.CdmaCellLocation


class TestActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()
    lateinit var db : AppDatabase
    lateinit var userDao : UserInfoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        db = AppDatabase.getAppDatabase(this)
        userDao = db.userInfoDao()

        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE
                        ),
                1)
    }

    fun btnTestRoomOnClicked(view: View) {
        testCellTower()
//        callTest()
//        callGetUserFromRoom()
    }

    @Suppress("DEPRECATION")
    fun testCellTower(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        val telephony = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        when(telephony.phoneType){
            TelephonyManager.PHONE_TYPE_GSM->{
                val location = telephony.cellLocation as GsmCellLocation
                if (location != null) {
                    tvResult.text = """LAC: ${location.lac}
                        |CID: ${location.cid}
                        |""".trimMargin()
                }
            }
            TelephonyManager.PHONE_TYPE_CDMA->{
                val location = telephony.cellLocation as CdmaCellLocation
                if (location != null) {
                    tvResult.text = """SID: ${location.systemId} NID: ${location.networkId} BID: ${location.baseStationId}
                        |Lat: ${location.baseStationLatitude}
                        |Lon: ${location.baseStationLongitude}
                    """.trimMargin()
                }
            }

        }
        if (telephony.phoneType == TelephonyManager.PHONE_TYPE_GSM) {


        }
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


}
