package com.dualvector.pith.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dualvector.pith.R;
import com.dualvector.pith.mvp.base.BaseApplication;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private static final int DELAY_TIME = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(BaseApplication.getContext(), MainActivity.class);
                startActivity(intent);
            }
        }, DELAY_TIME);
    }

    private void init() {
        // TODO init
    }
}
