package com.sellustrate.sellustrate;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;



import com.rockerhieu.emojicon.EmojiconTextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadingActivity extends AppCompatActivity implements View.OnClickListener {

    int CURRENT_QUALITY = 0;
    final String[]TYPICAL_NAME_CONDITIONS={"New", "New other (see details)", "New with defects", "Manufacturer refurbished", "Seller refurbished", "Used", "Very Good", "Good", "Acceptable", "For parts or not working"};
    boolean DISABLE_SWIPE = false;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        setLoading(false);

        //  String s = getIntent().getStringExtra("KEY");

       // Button button = findViewById(R.id.button);
        //button.setOnClickListener(this);

        LinearLayout emojiLayout = findViewById(R.id.layout);
        ScaleAnimation expandEmoji = new ScaleAnimation(1,1.5f,1,1.5f);
        expandEmoji.setDuration(300);
        expandEmoji.setFillAfter(true);
        expandEmoji.setFillEnabled(true);
        emojiLayout.getChildAt(0).startAnimation(expandEmoji);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.gdt.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private final GestureDetector gdt = new GestureDetector((new GestureListener()));

    private class GestureListener extends GestureDetector.SimpleOnGestureListener
    {

        private final int SWIPE_MIN_DISTANCE = 120;
        private final int SWIPE_THRESHOLD_VELOCITY = 200;

          @Override
         public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
          {
              if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                  //right to left
                  System.out.println("right to lefttttt");
                  if (CURRENT_QUALITY < 9)
                      CURRENT_QUALITY++;
                  else
                      CURRENT_QUALITY = 0;;

                  swipeEmoji(1);

                  updateDescription();
                  return true;
              }
           //   if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                  //left to right
              //  if (CURRENT_QUALITY > 0)
              //        CURRENT_QUALITY--;
              //    else
               //       CURRENT_QUALITY = 9;;

              //    swipeEmoji(-1);

             //     updateDescription();
             //     return true;
         //     }
            return false;
         }
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


    public void deInitQuality()
    {
        Button button = findViewById(R.id.button3);
        LinearLayout emojiLayout = findViewById(R.id.layout);
        TextView description = findViewById(R.id.description);
        DISABLE_SWIPE = true;
        button.setVisibility(View.GONE);
        for (int i=0;i<emojiLayout.getChildCount();i++)
        {
            emojiLayout.getChildAt(i).setVisibility(View.GONE);
            emojiLayout.getChildAt(i).clearAnimation();
        }

        emojiLayout.setVisibility(View.GONE);
        description.setVisibility(View.GONE);


    }

    //dir should be -1 or 1
    public void swipeEmoji(int dir)
    {
        if (!DISABLE_SWIPE) {
            LinearLayout emojiLayout = findViewById(R.id.layout);

            TranslateAnimation swipeEmoji = new TranslateAnimation((CURRENT_QUALITY - 1) * -241.0f, (CURRENT_QUALITY) * -241.0f, 0.0f, 0.0f);
            swipeEmoji.setDuration(300);
            swipeEmoji.setFillAfter(true);
            swipeEmoji.setFillEnabled(true);

            emojiLayout.startAnimation(swipeEmoji);

            ScaleAnimation expandEmoji = new ScaleAnimation(1, 1.5f, 1, 1.5f);
            expandEmoji.setDuration(300);
            expandEmoji.setFillAfter(true);
            expandEmoji.setFillEnabled(true);

            ScaleAnimation popEmoji = new ScaleAnimation(1, 0.66f, 1, 0.66f);
            expandEmoji.setDuration(300);
            expandEmoji.setFillAfter(true);
            expandEmoji.setFillEnabled(true);

            emojiLayout.getChildAt(CURRENT_QUALITY * 2).startAnimation(expandEmoji);

            if (dir > 0)
                if (CURRENT_QUALITY > 0)
                    emojiLayout.getChildAt(CURRENT_QUALITY * 2 - 2).startAnimation(popEmoji);
            //  else
            //    if (CURRENT_QUALITY<9){
            //   emojiLayout.getChildAt(CURRENT_QUALITY*2+2).startAnimation(popEmoji);
            //   emojiLayout.getChildAt(CURRENT_QUALITY*2-2).startAnimation(popEmoji);}
        }
    }

    public void initGears()
    {
        ImageView gear1 = findViewById(R.id.gear1);
        gear1.setVisibility(View.VISIBLE);
        gear1.setColorFilter(Color.RED);

        ImageView gear2 = findViewById(R.id.gear2);
        gear2.setVisibility(View.VISIBLE);
        gear2.setColorFilter(Color.BLUE);

        ImageView gear3 = findViewById(R.id.gear3);
        gear3.setVisibility(View.VISIBLE);
        gear3.setColorFilter(Color.YELLOW);

        ImageView gear4 = findViewById(R.id.gear4);
        gear4.setVisibility(View.VISIBLE);
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

    Intent i = new Intent(this, MakePost.class);
    @Override
    public void onClick(View view) {
        if (view.getId() != R.id.button4) {
            deInitQuality();
            setLoading(true);
            create_JSON();
            new HttpAsyncTask().execute("http://sellustrate.azurewebsites.net/search");
        }
        else
        {
            i.putExtra("key","value");
            startActivity(i);
        }
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

    static JSONObject ebay_query = new JSONObject();

    public void create_JSON()
    {
        try {
        ebay_query.put("Quality", CURRENT_QUALITY);
    }
        catch (Exception e){}
    }

    public static String post(String url){
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();

            // make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = ebay_query.toString();

            // set json to StringEntity
            StringEntity se = new StringEntity(json);

            // set httpPost Entity
            httpPost.setEntity(se);

            // Set some headers to inform server about the type of the content
           // httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);


            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            String errorMsg = e.getLocalizedMessage();
            Log.d("InputStream", errorMsg);

        }
        System.out.println(result);
        System.out.println("HELLOOOOOOOOOOO");
        // return result
        return result;
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return post(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
