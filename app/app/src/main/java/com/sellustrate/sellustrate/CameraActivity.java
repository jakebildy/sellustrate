package com.sellustrate.sellustrate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.util.Base64.*;
import static org.apache.commons.codec.binary.Base64.encodeBase64;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String subscriptionKey = "cfa2ac95fcf04101b79b839837876d16";
    public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/tag";

    private Camera mCamera;
    private CameraPreview mCameraPreview;
    public static File pictureFile;
    public static String encodedString;
    String mCurrentPhotoPath;

    private ImageView mImageView;

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
        new HttpAsyncTask().execute(uriBase);
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

    JSONObject json = new JSONObject();
    Camera.PictureCallback mPicture;

    {
        mPicture = new Camera.PictureCallback() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                System.out.println("picture was taken successfully :)))))");

                try {
                    pictureFile = getOutputMediaFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (pictureFile == null) {
                    return;
                }


                try {
                    json.put("url", mCurrentPhotoPath);


                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("the json wasnt made correctly");
                }

                new HttpAsyncTask().execute(uriBase);
                startActivity( new Intent(CameraActivity.this, LoadingActivity.class));

            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private File getOutputMediaFile() throws IOException {
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
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        pictureFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );


        mCurrentPhotoPath = pictureFile.getAbsolutePath();
        System.out.println("current photo path is " + mCurrentPhotoPath);
        return pictureFile;
    }

    public String post(String url) {
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            // Prepare the URI for the REST API call.
            URIBuilder builder = new URIBuilder(url);

            builder.setParameter("visualFeatures", "Categories,Description,Color");
            builder.setParameter("language", "en");

            // make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            // set json to StringEntity
            String quote="\"";
            System.out.println("right before temp path " );
            String tempPath="https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Tetragonula_carbonaria_%2814521993792%29.jpg/220px-Tetragonula_carbonaria_%2814521993792%29.jpg";
            String tempURL=("{" + quote+ "url" + quote+":"+ quote+ tempPath +quote+ "}");

            System.out.println(tempURL+" is the temp url");
            StringEntity se = new StringEntity(tempURL);
            //FileEntity reqEntity = new FileEntity(pictureFile, "application/json");

            System.out.println(json.get("url")+ " is the url in the json");
            // set httpPost Entity


            // Set some headers to inform server about the type of the content
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            httpPost.setEntity(se);

          //  System.out.println(reqEntity.toString());
            // Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // receive response as inputStream
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                System.out.println("REST Response:\n");
                System.out.println(json.toString(2));
            } else
                result = "Did not work, result is null:(";


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

            return post(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }




}//end cameraActivity