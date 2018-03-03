package com.sellustrate.sellustrate;

import android.graphics.Color;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        setLoading(true);
        setLoading(false);



    }

    public void setLoading(boolean b)
    {
        if (b)
        {
            initGears();
        }
        if (!b)
        {
            deInitGears();
        }
    }

    public void initGears()
    {
        ImageView gear1 = findViewById(R.id.gear1);
        gear1.setVisibility(View.VISIBLE);
        gear1.setColorFilter(Color.RED);

        ImageView gear2 = findViewById(R.id.gear2);
        gear1.setVisibility(View.VISIBLE);
        gear2.setColorFilter(Color.BLUE);

        ImageView gear3 = findViewById(R.id.gear3);
        gear1.setVisibility(View.VISIBLE);
        gear3.setColorFilter(Color.YELLOW);

        ImageView gear4 = findViewById(R.id.gear4);
        gear1.setVisibility(View.VISIBLE);
        gear4.setColorFilter(Color.GREEN);

        RotateAnimation rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatCount(Animation.INFINITE);

        RotateAnimation rotateBackwards = new RotateAnimation(0,-360,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateBackwards.setDuration(1000);
        rotateBackwards.setRepeatCount(Animation.INFINITE);

        gear1.startAnimation(rotate);
        gear2.startAnimation(rotateBackwards);
        gear3.startAnimation(rotate);
        gear4.startAnimation(rotateBackwards);
    }

    public void deInitGears()
    {
        ImageView gear1 = findViewById(R.id.gear1);
        gear1.setVisibility(View.GONE);

        ImageView gear2 = findViewById(R.id.gear2);
        gear2.setVisibility(View.GONE);

        ImageView gear3 = findViewById(R.id.gear3);
        gear3.setVisibility(View.GONE);

        ImageView gear4 = findViewById(R.id.gear4);
        gear4.setVisibility(View.GONE);
    }
}
