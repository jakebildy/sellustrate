package com.sellustrate.sellustrate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Intent thingy = new Intent(this, LoadingActivity.class);
         // startActivity(thingy);

        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(this);

        Button buttonNew = findViewById(R.id.buttonNew);
        buttonNew.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        try {
            doSomething(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doSomething(View view) throws IOException {
        //The camera activity
        Intent cameraIntent = new Intent(this, CameraActivity.class);

        if (view.getId() == R.id.buttonNew)
            startActivity(cameraIntent);
        if (view.getId() == R.id.button2)
            signIn();
    }

    public void signIn() throws IOException {
        EditText userView = findViewById(R.id.username);
        final String username = userView.getText().toString();

        EditText passView = findViewById(R.id.password);
        final String password = userView.getText().toString();

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password.toCharArray());
            }
        });
        new HttpAsyncTask().execute("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=");
        slide();
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            HttpURLConnection c = null;
            try {
                c = (HttpURLConnection) new URL("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=").openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            c.setUseCaches(false);
            try {
                c.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(c);
            return "";
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    //slides everything to the right
    public void slide()
    {
        ImageView signIn = findViewById(R.id.imageView7);
        TextView password = findViewById(R.id.password);
        TextView username = findViewById(R.id.username);
        Button buttonOld = findViewById(R.id.button2);
        Button buttonNew = findViewById(R.id.buttonNewPic);
        ImageView user = findViewById(R.id.user);
        ImageView lock = findViewById(R.id.lock);

        TranslateAnimation swipeRight = new TranslateAnimation(0.0f,850.0f,0.0f,0.0f);
        swipeRight.setDuration(1000);
        swipeRight.setFillAfter(true);
        swipeRight.setFillEnabled(true);

        signIn.startAnimation(swipeRight);
        password.startAnimation(swipeRight);
        username.startAnimation(swipeRight);
        buttonOld.startAnimation(swipeRight);
        buttonNew.startAnimation(swipeRight);
        user.startAnimation(swipeRight);
        lock.startAnimation(swipeRight);
        password.setEnabled(false);
        username.setEnabled(false);
        buttonOld.setEnabled(false);

    }

}
