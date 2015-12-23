package com.example.spinno.delivernow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Splashactivity extends Activity {
    LinearLayout background;
    boolean previouslyStarted;
    LinearLayout rel;
    ImageView img,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previouslyStarted = prefs.getBoolean("pref_previously_started", false);
        ScreenResolution screenRes = deviceDimensions();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run()

            {

                YoYo.with(Techniques.Hinge)
                        .duration(3000)
                        .playOn(findViewById(R.id.cap));

            }
        }, 1000);




        Handler handler2 = new Handler();

        handler2.postDelayed(new Runnable() {

            @Override
            public void run()

            {
                if (!previouslyStarted) {
                    Intent in = new Intent(Splashactivity.this, Logregactivity.class);
                    startActivity(in);
                    finish();
                }
                else {
                    Intent in = new Intent(Splashactivity.this, MainActivity.class);
                    startActivity(in);
                    finish();
                }

            }
        }, 4200);
    }

    private class ScreenResolution {
        int width, height;
        public ScreenResolution(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    ScreenResolution deviceDimensions() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // getsize() is available from API 13
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return new ScreenResolution(size.x, size.y);
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            // getWidth() & getHeight() are depricated
            return new ScreenResolution(display.getWidth(), display.getHeight());
        }
    }

}
