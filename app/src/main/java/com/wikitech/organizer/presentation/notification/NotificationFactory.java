package com.wikitech.organizer.presentation.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.wikitech.organizer.presentation.MainActivity;
import com.wikitech.organizer.presentation.MainService;

public class NotificationFactory {
    private static final int BASE_ID = 1;
    public static final int SERVICE_NOTIFICATION_ID = BASE_ID + 1;

    public static Notification createProcessingWorkNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationChannelFactory.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_search)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentTitle("Updating")
                .setContentText("Checking for latest news in your country")
                .setAutoCancel(true)
                .setContentIntent(createContentIntent(context))
                .addAction(createStopAction(context));

        return builder.build();
    }

    public static Notification createNewPermissionsNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationChannelFactory.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.star_on)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentTitle("Info")
                .setContentText("New permissions found")
                .setAutoCancel(true)
                .setContentIntent(createContentIntent(context));

        return builder.build();
    }

    private static PendingIntent createContentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(context,
                MainActivity.NOTIFICATION_LAUNCH_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    private static NotificationCompat.Action createStopAction(Context context) {
        return new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_close_clear_cancel,
                "Stop",
                createStopActionPendingIntent(context))
                .build();
    }

    private static PendingIntent createStopActionPendingIntent(Context context) {
        Intent intent = new Intent(context, MainService.class);
        intent.putExtra(MainService.TYPE_KEY, MainService.TYPE_FINISH);

        return PendingIntent.getService(context,
                MainService.TYPE_FINISH,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
}
