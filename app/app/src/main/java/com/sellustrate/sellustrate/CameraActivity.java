package com.sellustrate.sellustrate;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.util.Base64.*;
import static org.apache.commons.codec.binary.Base64.encodeBase64;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String subscriptionKey = "cfa2ac95fcf04101b79b839837876d16";
    public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    public static String encodedString;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCamera = getCameraInstance();
        mCameraPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mCameraPreview);

        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);


            }
        });
    }


    @Override
    public void onClick(View view) {
        new HttpAsyncTask().execute("http://sellustrate.azurewebsites.net/cognition");
    }

    /**
     * Helper method to access the camera returns null if it cannot get the
     * camera or does not exist
     *
     * @return
     */
    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // cannot get camera or does not exist
        }
        return camera;
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            System.out.println("picture was taken successfully :)))))");

            File pictureFile = getOutputMediaFile();
            try {
                analyzeImage(pictureFile.toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            System.out.println(pictureFile.toString());
            if (pictureFile == null) {
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
                System.out.println("this image was not saved correctly :(");
            }
        }
    };

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

        byte[] encoded = new byte[0];
        try {
            encoded = encodeBase64(FileUtils.readFileToByteArray(mediaFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
           encodedString = new String(encoded, StandardCharsets.US_ASCII);

        return mediaFile;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject analyzeImage(String imageUrl) throws URISyntaxException {
        HttpClient httpclient = new DefaultHttpClient();

        try{
            URIBuilder builder = new URIBuilder(uriBase);
            builder.setParameter("visualFeatures", "Categories,Description,Color");
            builder.setParameter("language", "en");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            StringEntity reqEntity = new StringEntity("{\"url\"imageUrl\"}");

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                System.out.println("REST Response:\n");
                System.out.println(json.toString(2));
            }


        }

        catch(Exception e){
            System.out.println("api didnt work:(");

        }
        return new JSONObject();
    }



    public static String post(String url, String encodedFile){
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();

            // make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            // set json to StringEntity
            StringEntity se = new StringEntity(encodedFile);

            // set httpPost Entity
            httpPost.setEntity(se);

            // Set some headers to inform server about the type of the content
            // httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);


            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();


        } catch (Exception e) {
            String errorMsg = e.getLocalizedMessage();
            Log.d("InputStream", errorMsg);

        }
        System.out.println(result);
        // return result
        return result;
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return post(urls[0], encodedString);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }


}//end cameraActivity
