package com.wikitech.organizer.presentation;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.wikitech.organizer.data.local.SharedPreferencesDataSource;
import com.wikitech.organizer.data.model.PermissionsItemDto;
import com.wikitech.organizer.data.remote.api.PermissionsApi;
import com.wikitech.organizer.domain.di.Container;
import com.wikitech.organizer.presentation.notification.NotificationFactory;

import java.io.IOException;

import timber.log.Timber;

import static com.wikitech.organizer.presentation.notification.NotificationFactory.SERVICE_NOTIFICATION_ID;

public class MainService extends Service {
    public static String TYPE_KEY = "type";

    public static int TYPE_BASIC = 0;
    public static int TYPE_FINISH = 2;

    private PermissionsApi permissionsApi;
    private SharedPreferencesDataSource sharedPreferencesDataSource;


    @Override
    public void onCreate() {
        super.onCreate();
        permissionsApi = Container.getInstance().getPermissionsApi();
        sharedPreferencesDataSource = Container.getInstance().getSharedPreferencesDataSource(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int type = intent.getIntExtra(TYPE_KEY, TYPE_BASIC);

        if (type == TYPE_BASIC) {
            startForeground(SERVICE_NOTIFICATION_ID, NotificationFactory.createProcessingWorkNotification(this));

            try {
                String currentVersion = sharedPreferencesDataSource.getString(SharedPreferencesDataSource.VERSION_KEY);
                PermissionsItemDto info = permissionsApi.getInfo().execute().body();

                if(currentVersion.equals(info.version)){
                    stopSelf();
                }
                else{
                    startForeground(SERVICE_NOTIFICATION_ID, NotificationFactory.createNewPermissionsNotification(this));
                    sharedPreferencesDataSource.putString(SharedPreferencesDataSource.VERSION_KEY, info.version);
                }
            } catch (IOException e) {
                e.printStackTrace();
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
