package com.example.phonedetect;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MotionDetectionService extends Service
        implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float lastX;
    private float lastY;
    private float lastZ;

    private static final String CHANNEL_ID =
            "motion_channel";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();

        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer =
                sensorManager.getDefaultSensor(
                        Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {

            sensorManager.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public int onStartCommand(Intent intent,
                              int flags,
                              int startId) {

        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float movement =
                Math.abs(x - lastX)
                        + Math.abs(y - lastY)
                        + Math.abs(z - lastZ);

        if (movement > 12) {

            String currentTime =
                    new SimpleDateFormat(
                            "HH:mm:ss",
                            Locale.getDefault())
                            .format(new Date());

            showNotification(currentTime);
        }

        lastX = x;
        lastY = y;
        lastZ = z;
    }

    @Override
    public void onAccuracyChanged(
            Sensor sensor,
            int accuracy) {
    }

    private void showNotification(String time) {

        Intent intent =
                new Intent(
                        this,
                        MotionDetailsActivity.class);

        intent.putExtra("TIME", time);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        this,
                        CHANNEL_ID)
                        .setSmallIcon(
                                android.R.drawable.ic_dialog_alert)
                        .setContentTitle(
                                "Motion Detected")
                        .setContentText(
                                "Movement detected at " + time)
                        .setContentIntent(
                                pendingIntent)
                        .setAutoCancel(true);

        NotificationManager manager =
                getSystemService(
                        NotificationManager.class);

        manager.notify(
                (int) System.currentTimeMillis(),
                builder.build());
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Motion Alerts",
                            NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager =
                    getSystemService(
                            NotificationManager.class);

            manager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        sensorManager.unregisterListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
