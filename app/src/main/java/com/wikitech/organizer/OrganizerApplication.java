package com.wikitech.organizer;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.wikitech.organizer.presentation.notification.NotificationChannelFactory;

public class OrganizerApplication extends Application {
    public static OrganizerApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(NotificationChannelFactory.createProcessingWorkNotificationChannel());
        }
    }
}

