package com.aionsigma.android.Receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.aionsigma.android.Services.SyncDataService


class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
//        if ("android.intent.action.BOOT_COMPLETED" == intent.action) {
//            val pushIntent = Intent(context, SyncDataService::class.java)
//            context.startService(pushIntent)
//        }
    }
}