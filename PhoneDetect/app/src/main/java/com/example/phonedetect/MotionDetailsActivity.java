package com.example.phonedetect;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MotionDetailsActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(
            Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_motion_details);

        TextView txtDetails =
                findViewById(
                        R.id.txtMotionDetails);

        String time =
                getIntent().getStringExtra(
                        "TIME");

        txtDetails.setText(
                "Motion was detected at: "
                        + time);
    }
}