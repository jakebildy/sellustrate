package com.sellustrate.sellustrate;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by Jacob Bildy on 2018-03-04.
 */

public class DescriptionJava {
    //Jake Bildy, 2018


    String KEYWORD = "computer mouse";
    int QUALITY = 4;


    JSONObject responseJSON;

        public String qualityIntToDescription(int i)
        {
        switch(QUALITY) {
            case 0:
                return "The product is brand new and has never been opened.";
            case 1:
                return "The product is brand new, but already opened. Barely touched.";
            case 2:
                return "The product is brand new, but contains some defects. Still more than functional.";
            case 3:
                return "The product is in good condition, having been recently refurbished by the manufacturer.";
            case 4:
                return "The product is in good condition - recently refurbished.";
            case 5:
                return "The product is in absolute mint condition.";
            case 6:
                return "The product is in very good condition.";
            case 7:
                return "The product is used, but still in very good condition.";
            case 8:
                return "The product is quite well used, but still in an acceptable condition. Still works.";
            case 9:
                return "The product is very heavily used, and no longer functions.";
        }
            return null;
        }
        

        public String randomComment(int i) {
        switch (i) {

            case 0:
                return " Definitely a great deal.";
            case 1:
                return " Hurry now before it's too late!";
            case 2:
                return " Going fast - make it yours today.";
            case 3:
                return " You don't find a lot of deals like this.";
            case 4:
                return " Definitely worth the price.";
            case 5:
                return " Deals like this are hard to come by.";
        }
            return null;
        }

    public String getCondition(int j){
        switch(j){
        case 0:
        return "New";
        case 1:
        return "New other";
        case 2:
        return "New with defects";
        case 3:
        return "Manufacturer refurbished";
        case 4:
        return "Seller refurbished";
        case 5:
        return "Used";
        case 6:
        return "Very Good";
        case 7:
        return "Good";
        case 8:
        return "Acceptable";
        case 9:
        return "For parts or not working";
        }
        return null;
        }

        public String returnFinalDescription()
        {
            return "Selling " + KEYWORD + ": " + qualityIntToDescription(QUALITY) + randomComment(4);
        }

    JSONObject finalJSON;

        public JSONObject constructFinalJSON()
        {
             finalJSON = new JSONObject();


            JSONObject availability = new JSONObject();
            JSONObject shipToLocationAvailability = new JSONObject();
            try {
                availability.put("shipToLocationAvailability", shipToLocationAvailability);
                shipToLocationAvailability.put("quantity",1);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject json1 = new JSONObject();
            try {
                json1.put("title", KEYWORD);
                json1.put("description", returnFinalDescription());
                json1.put("imageUrls", "");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                finalJSON.put("product", json1);
                finalJSON.put("condition", getCondition(QUALITY));
                finalJSON.put("availability",availability);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        return finalJSON;
        }

    public static String post(String url){
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();

            // make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            // set json to StringEntity
            StringEntity se = new StringEntity(finalJSON);

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
          //  if(inputStream != null)
           //     result = convertInputStreamToString(inputStream);
       //     else
           //     result = "Did not work!";

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

        }
    }


}
