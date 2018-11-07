package com.tmzdh.monitor.common;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.tmzdh.monitor.BizApplication;

/**
 * Created by chris on 10/8/18.
 */
public class BaseActivity extends AppCompatActivity {


    public void narrowBackgroundAnimation() {
        View view = findViewById(android.R.id.content);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, .9f).setDuration(400);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, .9f).setDuration(400);

        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.setInterpolator(new SpringScaleInterpolator(.6f));
        set.start();
    }

    public void expandBackgroundAnimation() {
        View view = findViewById(android.R.id.content);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", .9f, 1.0f).setDuration(400);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", .9f, 1.0f).setDuration(400);

        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.setInterpolator(new SpringScaleInterpolator(.6f));
        set.start();
    }

    public Gson gson() {
        BizApplication application = (BizApplication) getApplication();
        return application.gson();
    }

}
