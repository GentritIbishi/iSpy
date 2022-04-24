package com.gentritibishi.ispy;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class GetStartedActivity extends AppCompatActivity {

    private Button bt_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        bt_getStarted=findViewById(R.id.bt_getStarted);

        bt_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goes to login
             Intent intent = new Intent(GetStartedActivity.this, LoginActivity.class);
             startActivity(intent);
            }
        });

        bt_getStarted.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                bt_getStarted.setBackgroundColor(getResources().getColor(R.color.black_low));
                return true;
            }
        });
    }
}