package com.example.phonedetect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT
                >= android.os.Build.VERSION_CODES.TIRAMISU) {

            requestPermissions(
                    new String[]{
                            android.Manifest.permission.POST_NOTIFICATIONS
                    },
                    100);
        }

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {

            Intent serviceIntent =
                    new Intent(
                            MainActivity.this,
                            MotionDetectionService.class);

            startService(serviceIntent);
        });
    }
}