package com.nytimes.android.compass;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class MainActivity extends Activity {

    public static final int TO_DEGREES = 30;
    public static final int FROM_DEGREES = -30;
    public static final int START_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View compass = findViewById(R.id.compass);
        final View eyes = findViewById(R.id.eyes);


        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        compass.startAnimation(fadeInAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                eyesAnimation(eyes);
            }
        }, START_DELAY);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startOfPreview(compass);
            }
        }, 2000 + START_DELAY);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                previewAnimation(compass);
            }
        }, 3000 + START_DELAY);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                endOfPreview(compass);
            }
        }, 7000 + START_DELAY);


    }


    private void eyesAnimation(View eyes) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(eyes, "rotationX", 0.0f, 360f);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
    }

    private RotateAnimation startOfPreview(View compass) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, FROM_DEGREES,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setFillAfter(true);
        compass.startAnimation(rotateAnimation);
        return rotateAnimation;
    }

    private RotateAnimation previewAnimation(View compass) {
        RotateAnimation rotateAnimation = new RotateAnimation(FROM_DEGREES, TO_DEGREES,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setFillAfter(true);
        compass.startAnimation(rotateAnimation);
        return rotateAnimation;
    }

    private RotateAnimation endOfPreview(View compass) {
        RotateAnimation rotateAnimation = new RotateAnimation(FROM_DEGREES, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setFillAfter(true);
        compass.startAnimation(rotateAnimation);
        return rotateAnimation;
    }


}
