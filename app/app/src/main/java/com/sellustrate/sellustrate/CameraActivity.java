package com.sellustrate.sellustrate;

import android.content.Intent;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.codec.binary.Base64.encodeBase64;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String subscriptionKey = "cfa2ac95fcf04101b79b839837876d16";
    public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";
    public static String encodedString;
    private JSONObject mTags;
    JSONObject json = new JSONObject();
    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            System.out.println("picture was taken successfully :)))))");

            File pictureFile = getOutputMediaFile();

            System.out.println(pictureFile.toString() + "<----- file strig");
            try {
                byte[] encoded = new byte[0];
                try {
                    encoded = encodeBase64(FileUtils.readFileToByteArray(pictureFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                encodedString = new String(encoded, StandardCharsets.US_ASCII);

                json.put("sell", encodedString);
                new HttpAsyncTask().execute("http://sellustrate.azurewebsites.net/cognition");
                System.out.println(" <---- encoded string " + encodedString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "sellustrate");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("sellustrate", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
        return mediaFile;
    }//end getOutputMediaFile

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCamera = getCameraInstance();
        mCameraPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mCameraPreview);

        final Animation fadeOut = new AlphaAnimation(1.0f,0.0f);
        fadeOut.setDuration(200);
        fadeOut.setFillAfter(true);
        fadeOut.setFillEnabled(true);

        final Animation fadeIn = new AlphaAnimation(0.0f,1.0f);
        fadeIn.setDuration(200);
        fadeIn.setFillAfter(true);
        fadeIn.setFillEnabled(true);

        final Button captureButton = (Button) findViewById(R.id.button_capture);
        final Button uploadButton = (Button) findViewById(R.id.finishButton);
        final Button refreshButton = (Button) findViewById(R.id.refreshButton);

        final TextView bestGuess = (TextView) findViewById(R.id.textView);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
                captureButton.startAnimation(fadeOut);
                uploadButton.startAnimation(fadeIn);
                uploadButton.setVisibility(View.VISIBLE);
                refreshButton.startAnimation(fadeIn);
                refreshButton.setVisibility(View.VISIBLE);
                bestGuess.setText("loading...");

                Runnable doBestGuess = new Runnable() {
                    @Override
                    public void run() {

                        //insert http get request to grab best guess from microsoft azure

                        while (true)  {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (bestGuess.getText() != "loading...")
                                bestGuess.setText(bestGuess.getText()+".");
                            else
                                bestGuess.setText("loading");
                        }
                    }
                };

                doBestGuess.run();
            }
        });
    }//end onCreate

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.finishButton) {
            new HttpAsyncTask().execute("http://sellustrate.azurewebsites.net/cognition");
            Intent i = new Intent(this, LoadingActivity.class);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(this, CameraActivity.class);
            startActivity(i);
        }
    }//end onClick

    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // cannot get camera or does not exist
        }
        return camera;
    }//end getCameraInstance

    //first giant try/catch block uploads pic to website
    //second giant try/catch block sends image to api
    public String post(String url) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();

            // make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            // set json to StringEntity
            StringEntity se = new StringEntity(json.toString());

            // set httpPost Entity
            httpPost.setEntity(se);

            // Set some headers to inform server about the type of the content
            httpPost.setHeader("Content-type", "application/json");

            // Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

        } catch (Exception e) {
            String errorMsg = e.getLocalizedMessage();
            Log.d("InputStream", errorMsg);

        }
        System.out.println("HELLOOOOOOOOOOO");

        try {
            HttpClient client = new DefaultHttpClient();
            URIBuilder builder = new URIBuilder(uriBase);
            builder.setParameter("visualFeatures", "Categories,Description,Color");
            builder.setParameter("language", "en");

            // make POST request to the given URL
            HttpPost post = new HttpPost(uriBase);

            // set json to StringEntity
            String quote = "\"";
            String tempURL = ("{" + quote + "url" + quote + ":" + quote + "http://sellustrate.azurewebsites.net/static/imagetosave.png" + quote + "}");
            StringEntity se = new StringEntity(tempURL);

            // set httpPost Entity and Set some headers to inform server about the type of the content
            post.setHeader("Content-type", "application/json");
            post.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            post.setEntity(se);
            // Execute POST request to the given URL
            HttpResponse httpResponse = client.execute(post);

            // receive response as inputStream
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                String jsonString = EntityUtils.toString(entity);
                mTags = new JSONObject(jsonString);
                System.out.println("REST Response:\n");
                System.out.println(mTags);
            } else
                result = "Did not work, result is null:(";

        } catch (Exception e) {
            String errorMsg = e.getLocalizedMessage();
            Log.d("InputStream", errorMsg);
        }
        System.out.println(result);
        return result;
    }//end post

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
    }//end HttpAsyncTask
}//end cameraActivity