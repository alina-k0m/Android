package com.example.lr8;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

//Работа со службами
public class TimeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static final String CHANNEL_ID = "TimeServiceChannel";
    private Handler handler;
    private Runnable runnable;
    private long startTime;

    @Override
    public void onCreate() {
        Log.i("TimeService", "onCreate");
        super.onCreate();
        createNotificationChannel();
        handler = new Handler(Looper.getMainLooper());
        startTime = SystemClock.elapsedRealtime();
        long hours = (startTime / 3600000) % 24;
        long min = (startTime / 60000) % 60;
        long sec = (startTime / 1000) % 60;
        Log.i("Service", hours + " " + min + " " + sec);

        //startForeground(1,createNotification("Timer starts"));
        startTimer();
    }

    private void startTimer() {
        runnable = new Runnable() {
            @Override
            public void run() {
                long elapsedTime = SystemClock.elapsedRealtime() - startTime;
                long hours = (elapsedTime / 3600000) % 24;
                long min = (elapsedTime / 60000) % 60;
                long sec = (elapsedTime / 1000) % 60;
                Notification notification = createNotification(String.format("Time: %02d:%02d:%02d", hours, min, sec));

                //Log.i("Service", hours + " " + min + " " + sec);
                startForeground(1,notification);

                if (elapsedTime < 60 * 1000) {
                    handler.postDelayed(this, 5000);
                } else {
                    Log.i("Service", hours + " " + min + " " + sec);
                    stopSelf();
                }
            }
        };
        handler.post(runnable);
    }

    private Notification createNotification(String contentText) {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Service Test")
                .setContentText(contentText)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
    }

    private void createNotificationChannel() {
        NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Time Service Channel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);

    }
}
