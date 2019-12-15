package com.apahayo.tubespabpaim;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReceiverSedih extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        Intent repeat = new Intent(context,PilihGejalaActivity.class);
        repeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,1,
                repeat,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"channel2")
                .setContentTitle("Apakah kamu mulai membaik?")
                .setContentText("Kuharap kau sekarang baik baik saja ")
                .setSmallIcon(R.drawable.icon_launcher)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.icon_launcher,"Belum",pendingIntent)
                .setAutoCancel(true)
                ;

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(0,builder.build());
    }
}
