package com.aionsigma.android.Receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import com.aionsigma.android.Room.AppDatabase
import com.aionsigma.android.Room.UserInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SmsReceiver: BroadcastReceiver() {
    @Suppress("DEPRECATION")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (intent.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                val bundle = intent?.extras          //---get the SMS message passed in---
                var msgs: Array<SmsMessage?>? = null
                var msgFrom: String
                if (bundle != null) {
                    //---retrieve the SMS message received---

                    val userDao = AppDatabase.getAppDatabase(context!!).userInfoDao()
                    val compositeDisposable = CompositeDisposable()
                    if(userDao == null) return
                    try {
                        val pdus = bundle.get("pdus") as Array<*>
                        msgs = arrayOfNulls<SmsMessage>(pdus.size)
                        for (i in msgs.indices) {
                            msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                            msgFrom = msgs[i]!!.originatingAddress
                            val msgBody = msgs[i]!!.messageBody
                            val userInfo = UserInfo("""from: ${msgFrom!!}
                                |body: ${msgBody!!}
                                |-----------------
                            """.trimMargin(), "123456789", "sms")
                            compositeDisposable.add(Observable.fromCallable { userDao.insert(userInfo) }
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            { _ ->
                                                Log.d("SMS", "Inserted")
                                            },
                                            { error ->
                                                Log.d("SMS", error.message)
                                            }
                                    )
                            )
                        }
                    } catch (e: Exception) {
                        //Log.d("Exception caught",e.getMessage());
                    }

                }
            }
        }
    }

}
