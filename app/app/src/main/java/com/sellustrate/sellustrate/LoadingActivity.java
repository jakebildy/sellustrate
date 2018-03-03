package com.sellustrate.sellustrate;

import android.graphics.Color;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rockerhieu.emojicon.EmojiconTextView;

public class LoadingActivity extends AppCompatActivity implements View.OnClickListener {

    int CURRENT_QUALITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        setLoading(false);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        LinearLayout emojiLayout = findViewById(R.id.layout);
        ScaleAnimation expandEmoji = new ScaleAnimation(1,1.5f,1,1.5f);
        expandEmoji.setDuration(300);
        expandEmoji.setFillAfter(true);
        expandEmoji.setFillEnabled(true);
        emojiLayout.getChildAt(0).startAnimation(expandEmoji);
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


    public void openQuality()
    {

    }

    //dir should be -1 or 1
    public void swipeEmoji(int dir)
    {
        LinearLayout emojiLayout = findViewById(R.id.layout);

        TranslateAnimation swipeEmoji = new TranslateAnimation((CURRENT_QUALITY-1)*-241.0f,(CURRENT_QUALITY)*-241.0f,0.0f,0.0f);
        swipeEmoji.setDuration(300);
        swipeEmoji.setFillAfter(true);
        swipeEmoji.setFillEnabled(true);

        emojiLayout.startAnimation(swipeEmoji);

        ScaleAnimation expandEmoji = new ScaleAnimation(1,1.5f,1,1.5f);
        expandEmoji.setDuration(300);
        expandEmoji.setFillAfter(true);
        expandEmoji.setFillEnabled(true);

        ScaleAnimation popEmoji = new ScaleAnimation(1,0.66f,1,0.66f);
        expandEmoji.setDuration(300);
        expandEmoji.setFillAfter(true);
        expandEmoji.setFillEnabled(true);

        emojiLayout.getChildAt(CURRENT_QUALITY*2).startAnimation(expandEmoji);
        if (CURRENT_QUALITY>0)
        emojiLayout.getChildAt(CURRENT_QUALITY*2-2).startAnimation(popEmoji);
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


    @Override
    public void onClick(View view) {

        if (CURRENT_QUALITY < 9)
            CURRENT_QUALITY++;
        else
            CURRENT_QUALITY = 0;;

        swipeEmoji(1);

        updateDescription();
    }

    public void updateDescription()
    {
        TextView description = findViewById(R.id.description);

        if (CURRENT_QUALITY == 0)
        {
            description.setText(R.string.q1);
        }
        if (CURRENT_QUALITY == 1)
        {
            description.setText(R.string.q2);
        }
        if (CURRENT_QUALITY == 2)
        {
            description.setText(R.string.q3);
        }
        if (CURRENT_QUALITY == 3)
        {
            description.setText(R.string.q4);
        }
        if (CURRENT_QUALITY == 4)
        {
            description.setText(R.string.q5);
        }
        if (CURRENT_QUALITY == 5)
        {
            description.setText(R.string.q6);
        }
        if (CURRENT_QUALITY == 6)
        {
            description.setText(R.string.q7);
        }
        if (CURRENT_QUALITY == 7)
        {
            description.setText(R.string.q8);
        }
        if (CURRENT_QUALITY == 8)
        {
            description.setText(R.string.q9);
        }
        if (CURRENT_QUALITY == 9)
        {
            description.setText(R.string.q10);
        }
    }
}
