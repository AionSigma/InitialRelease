package com.aionsigma.android.View.Test

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
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
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_welcome.*


class TestActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()
    lateinit var db : AppDatabase
    lateinit var userDao : UserInfoDao

    companion object {
        val PERMISSIONS_REQUEST_READ_CONTACTS = 100
        val MY_PERMISSIONS_REQUEST_READ_CALL_LOG = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        db = AppDatabase.getAppDatabase(this)
        userDao = db.userInfoDao()

        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECEIVE_SMS
                        ),
                1)
    }

    fun btnTestRoomOnClicked(view: View) {
        testCellTower()
//        callTest()
//        callGetUserFromRoom()
    }

    fun btnTestSmsOnClicked(view: View){
        val intent = Intent(this, TestSmsReceiverActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
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
                val networkOperator = telephony.networkOperator
                var mcc : Int = 0
                var mnc : Int = 0
                if(!TextUtils.isEmpty(networkOperator)){
                    mcc = networkOperator.substring(0,3).toInt()
                    mnc= networkOperator.substring(3).toInt()
                }
                if (location != null) {
                    tvResult.text = """LAC: ${location.lac}
                        |CID: ${location.cid}
                        |MCC: $mcc
                        |MNC: $mnc
                        |Sim: ${telephony.simOperator}
                        |""".trimMargin()
                }
                val api="AIzaSyC5BT9UZzggx-XAnFXBq_IiYJNlgmuLRjo"
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
        startActivity(app)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CALL_LOG -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay!
                    val callLog = getCallDetails();
                    Log.v("CallLog", callLog);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
            PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadContacts()
                } else {
                    //  toast("Permission must be granted in order to display contacts information")
                }
            }
            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun getCallDetails(): String {

        val sb = StringBuffer()
        val managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null)
        val number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER)
        val type = managedCursor.getColumnIndex(CallLog.Calls.TYPE)
        val date = managedCursor.getColumnIndex(CallLog.Calls.DATE)
        val duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION)
        sb.append("Call Details :")
        while (managedCursor.moveToNext()) {
            val phNumber = managedCursor.getString(number)
            val callType = managedCursor.getString(type)
            val callDate = managedCursor.getString(date)
            val callDayTime = Date(java.lang.Long.valueOf(callDate))
            val callDuration = managedCursor.getString(duration)
            var dir: String? = null
            val dircode = Integer.parseInt(callType)
            when (dircode) {
                CallLog.Calls.OUTGOING_TYPE -> dir = "OUTGOING"

                CallLog.Calls.INCOMING_TYPE -> dir = "INCOMING"

                CallLog.Calls.MISSED_TYPE -> dir = "MISSED"
            }
            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                    + dir + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration)
            sb.append("\n----------------------------------")
        }
        managedCursor.close()
        return sb.toString()
    }

    private fun loadContacts() {
        var builder = StringBuilder()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                        Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),
                    WelcomeActivity.PERMISSIONS_REQUEST_READ_CONTACTS)
            //callback onRequestPermissionsResult
        } else {
            builder = getContacts()
            tvResult.text = builder.toString()
        }
    }

    private fun getContacts(): StringBuilder {
        val builder = StringBuilder()
        val resolver: ContentResolver = contentResolver;
        val cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null,
                null)

        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNumber = (cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))).toInt()

                if (phoneNumber > 0) {
                    val cursorPhone = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)

                    if(cursorPhone.count > 0) {
                        while (cursorPhone.moveToNext()) {
                            val phoneNumValue = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            builder.append("Contact: ").append(name).append(", Phone Number: ").append(
                                    phoneNumValue).append("\n\n")
                            Log.e("Name ===>",phoneNumValue);
                        }
                    }
                    cursorPhone.close()
                }
            }
        } else {
            //   toast("No contacts available!")
        }
        cursor.close()
        return builder
    }

    private fun loadCallLog() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_CALL_LOG)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_CALL_LOG),
                        MY_PERMISSIONS_REQUEST_READ_CALL_LOG)
            }
        } else {
            val callLog = getCallDetails();
            tvResult.text = callLog;
        }
    }

    fun btnGetCallLogOnClicked(view: View) {
        loadCallLog();
    }
    fun btnGetContactOnClicked(view: View) {
        loadContacts();
    }
}
