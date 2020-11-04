package com.mobxpert.men30dayfitnesschallenge.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;

import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.utils.SharedPrefUtility;
import com.mobxpert.men30dayfitnesschallenge.utils.Utility;

public class Splash extends Activity {
    int PERMISSION_ALL = 1;
    Thread splashTread;
    ImageView splashLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utility.hideSystemUI(getWindow().getDecorView());
        String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utility.launchActivity(Splash.this, MainActivity.class, true, null);
               /* if (SharedPrefUtility.getInstance(Splash.this).getBooleanValue("FirstLaunch") == false) {
                    Utility.launchActivity(Splash.this, OnBoardingActivity.class, true, null);
                } else {

                    Utility.launchActivity(Splash.this, MainActivity.class, true, null);

                }*/
            }
        }, 1000);

       /* final RelativeLayout imageView = (RelativeLayout) findViewById(R.id.splashLogo);
        final Animation animation_1 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation animation_2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.antirotate);
        //final Animation animation_3 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        imageView.startAnimation(animation_2);
        animation_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //imageView.startAnimation(animation_3);
                if (SharedPrefUtility.getInstance(Splash.this).getBooleanValue("FirstLaunch") == false) {
                    Utility.launchActivity(Splash.this, OnBoardingActivity.class, true, null);
                } else {

                    Utility.launchActivity(Splash.this, MainActivity.class, true, null);

                }
               // Utility.launchActivity(Splash.this, MainActivity.class, true, null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/


    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
