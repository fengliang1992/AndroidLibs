package com.fltry.module.screen;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fltry.module.screen.databinding.ActivityScreenBinding;

public class ScreenActivity extends AppCompatActivity {

    ActivityScreenBinding bind;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_screen);
        toolbar = findViewById(R.id.toolbar);
        StatusBarUtil.setColor(this, Color.YELLOW);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onClick1(View v) {
        toolbar.setBackgroundColor(Color.RED);
        StatusBarUtil.setColor(this, Color.RED);
    }

    public void onClick2(View v) {
        toolbar.setBackgroundColor(Color.BLUE);
        StatusBarUtil.setColor(this, Color.BLUE, 0);
    }
}
