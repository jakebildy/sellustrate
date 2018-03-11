package com.sellustrate.sellustrate;

import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String subscriptionKey = "cfa2ac95fcf04101b79b839837876d16";
    public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze?visualFeatures=Tags&language=en";
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    private boolean safeToTakePicture = false;

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
        safeToTakePicture = true;
        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(safeToTakePicture) {
                    mCamera.takePicture(null, null, mPicture);
                    System.out.println("the picture was taken");
                    safeToTakePicture=false;
                }

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onClick(View view) {

    }

    /*
     * Helper method to access the camera returns null if it cannot get the
     * camera or does not exist
     * @return
     */
    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            System.out.println("this camera doesnt exist lol");
        }
        return camera;
    }
    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            System.out.println("picture was taken successfully :)))))");

            File pictureFile = getOutputMediaFile();

            System.out.println(pictureFile.toString() + "<----- file strig");
            if (pictureFile == null) {
                return;
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
        //JSONObject pictureTags =/
        getPictureTags(mediaFile);

        return mediaFile;
    }


    public static InputStream getPictureTags(File file) {

     CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder uriBuilder = new URIBuilder(uriBase);
           // uriBuilder.setParameter("returnFaceId", "true");
           // uriBuilder.setParameter("returnFaceLandmarks", "false");
           // uriBuilder.setParameter("returnFaceAttributes", "age");

            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);

            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);


            // Request body
            FileEntity reqEntity = new FileEntity(file, ContentType.APPLICATION_JSON);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine());


            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
                return entity.getContent();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

}//end cameraActivity
