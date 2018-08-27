package com.aionsigma_app.receivers;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.aionsigma_app.services.SyncDataService;

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, SyncDataService.class);
            context.startService(pushIntent);
        }
    }
}