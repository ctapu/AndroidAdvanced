package com.wikitech.organizer.presentation;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.wikitech.organizer.data.local.SharedPreferencesDataSource;
import com.wikitech.organizer.data.model.PermissionsItemDto;
import com.wikitech.organizer.data.remote.PermissionsRemoteDataSource;
import com.wikitech.organizer.domain.di.Container;
import com.wikitech.organizer.presentation.notification.NotificationFactory;

import timber.log.Timber;

import static com.wikitech.organizer.presentation.notification.NotificationFactory.SERVICE_NOTIFICATION_ID;

public class MainService extends Service {
    public static String TYPE_KEY = "type";

    public static int TYPE_BASIC = 0;
    public static int TYPE_FINISH = 2;

    private PermissionsRemoteDataSource permissionsRemoteDataSource;
    private SharedPreferencesDataSource sharedPreferencesDataSource;


    @Override
    public void onCreate() {
        super.onCreate();
        permissionsRemoteDataSource = Container.getInstance().getPermissionsRemoteDataSource();
        sharedPreferencesDataSource = Container.getInstance().getSharedPreferencesDataSource(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int type = intent.getIntExtra(TYPE_KEY, TYPE_BASIC);

        if (type == TYPE_BASIC) {
            startForeground(SERVICE_NOTIFICATION_ID, NotificationFactory.createProcessingWorkNotification(this));

            String currentVersion = sharedPreferencesDataSource.getString(SharedPreferencesDataSource.VERSION_KEY);
            PermissionsItemDto info = permissionsRemoteDataSource.getInfo();

            if (currentVersion.equals(info.version)) {
                stopSelf();
            } else {
                startForeground(SERVICE_NOTIFICATION_ID, NotificationFactory.createNewPermissionsNotification(this));
                sharedPreferencesDataSource.putString(SharedPreferencesDataSource.VERSION_KEY, info.version);
            }
        } else {
            Timber.w("Unknown service type. Killing.");
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // ignore, unused for now
        return null;
    }
}
