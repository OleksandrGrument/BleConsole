package com.grument.bleconsole.activity.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.grument.bleconsole.R;
import com.grument.bleconsole.activity.base.BaseActivity;
import com.grument.bleconsole.activity.find.FindBleDeviceActivity;

import static com.grument.bleconsole.util.Constants.SPLASH_DISPLAY_LENGTH;

public class SplashActivity extends BaseActivity {

    Handler startActivityHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        YoYo.with(Techniques.Flash)
                .duration(1000)
                .repeat(YoYo.INFINITE)
                .playOn(findViewById(R.id.iv_bluetooth_icon));

        startActivityHandler = new Handler();

        startActivityHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    startActivity(new Intent(SplashActivity.this, FindBleDeviceActivity.class));
                    finish();
                } catch (Exception e) {
                    startActivity(new Intent(SplashActivity.this, FindBleDeviceActivity.class));
                    finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivityHandler.removeCallbacksAndMessages(null);
    }


}
