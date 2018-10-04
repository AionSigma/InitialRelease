package com.aionsigma.android.View.Test

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.icu.lang.UCharacter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.aionsigma.android.R
import com.aionsigma.android.Room.AppDatabase
import com.aionsigma.android.Room.UserInfoDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_test_sms_receiver.*
import java.lang.Long
import java.util.*
import android.content.Intent
import android.os.Build
import android.provider.ContactsContract
import com.aionsigma.android.View.Welcome.WelcomeActivity


class TestSmsReceiverActivity : AppCompatActivity() {
    val compositeDisposable = CompositeDisposable()
    lateinit var db : AppDatabase
    lateinit var userDao : UserInfoDao

    companion object {
        val PERMISSIONS_REQUEST_READ_CONTACTS = 100
        val MY_PERMISSIONS_REQUEST_READ_CALL_LOG = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_sms_receiver)

        val intent = intent
        val message = intent.getStringExtra("type")

        if(message == "CALL_LOG") {
            Log.v("CallLog", "Calllog");
            loadCallLog()
        } else if (message == "CONTACT"){
            Log.v("CallLog", "Contact");
            loadContacts()
        } else {
            db = AppDatabase.getAppDatabase(this)
            userDao = db.userInfoDao()
            callGetUserFromRoom()
        }
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

    fun callGetUserFromRoom(){
        val sb = StringBuffer()
        userDao.loadAllByType("sms").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result->
                    for (i in result)
                    {
                        sb.append(i.data)
                        sb.append("\n")
                    }
                    tvResults.text = sb.toString()
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
            val callDayTime = Date(Long.valueOf(callDate))
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
            tvResults.text = callLog;
        }
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
            tvResults.text = builder.toString()
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
}
