package com.wikitech.organizer.domain;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wikitech.organizer.presentation.MainService;

public class ScreenOnBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Intent serviceIntent = new Intent(context, MainService.class);
            serviceIntent.putExtra(MainService.TYPE_KEY, MainService.TYPE_BASIC);
            context.startService(serviceIntent);
        }
    }
}
