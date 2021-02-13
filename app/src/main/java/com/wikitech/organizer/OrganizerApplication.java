package com.wikitech.organizer;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.wikitech.organizer.presentation.notification.NotificationChannelFactory;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class OrganizerApplication extends Application {
    public static OrganizerApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        setTools();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(NotificationChannelFactory.createProcessingWorkNotificationChannel());
        }
    }

    private void setTools() {
        Stetho.initializeWithDefaults(this);

        Timber.plant(new Timber.Tree() {
            @Override
            protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable throwable) {
                if (priority < Log.INFO) {
                    return;
                }

                if (throwable != null) {
                    FirebaseCrashlytics.getInstance().recordException(throwable);
                }

                String crashlyticsMessage = String.format("[%s] %s", tag, message);
                FirebaseCrashlytics.getInstance().log(crashlyticsMessage);
            }
        });
    }
}

