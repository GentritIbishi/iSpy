package com.gentritibishi.ispy;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Allow2 {

    // static variable single_instance of type Allow2
    private static Allow2 _shared = null;
    public static int responseCode = 0;
    public static String responseString = "";

    private volatile int x = 0;
    public Allow2EnvType env = Allow2EnvType.SANDBOX;		// default to sandbox

    @SuppressWarnings("unused")
    private final String getApiUrl() {
        switch (env) {
            case SANDBOX:	return "https://api.allow2.com:8443"; // "https://sandbox-api.allow2.com";
            case STAGING:	return "https://staging-api.allow2.com";
            default:		return "https://api.allow2.com";
        }
    }

    @SuppressWarnings("unused")
    private final String getServiceUrl () {
        switch (env) {
            case SANDBOX:	return "https://api.allow2.com:9443"; // "https://sandbox-service.allow2.com";
            case STAGING:	return "https://staging-service.allow2.com";
            default:		return "https://service.allow2.com";
        }
    }

    /**
     * No-argument constructor.
     * @return
     */
    private Allow2()
    {
    }

    // static method to create instance of Singleton class
    public static Allow2 getShared()
    {
        if (_shared == null)
            _shared = new Allow2();

        return _shared;
    }


    /**
     * Method to get the current integer value.
     * @return the value (int)
     */

    void pairInMyWay(String user, String password, String deviceName) {

        OkHttpClient client = new OkHttpClient();
        try {
            String deviceToken = "z0BvvGEv53zdWGtU";
            // Build the request
            RequestBody body = new FormBody.Builder()
                    .add("user", user)
                    .add("pass", password)
                    .add("name",deviceName)
                    .add("deviceToken",deviceToken)
                    .build();

            Request request = new Request.Builder()
                    .url("https://api.allow2.com/api/pairDevice")
                    .post(body)
                    .addHeader("Accept", "/*")
                    .addHeader("Accept-Encoding", "gzip, deflate, br")
                    .addHeader("Content-Type", "application/json; charset=UTF-8")
                    .build();
            Response responses = null;

            // Reset the response code
            responseCode = 0;

            // Make the request
            responses = client.newCall(request).execute();

            if ((responseCode = responses.code()) == 200) {
                // Get response
                String jsonData = responses.body().string();

                // Transform reponse to JSon Object
                JSONObject jsonObject = new JSONObject(jsonData);
                String status = jsonObject.getString("status");
                Integer pairId = jsonObject.getInt("pairId");
                String token = jsonObject.getString("token");
                String name = jsonObject.getString("name");
                Integer userId = jsonObject.getInt("userId");

                JSONArray children = jsonObject.getJSONArray("children");
                //get childrens


            } else {
                System.out.println("Error!");
            }

        } catch (IOException e) {
            responseString = e.toString();
        } catch (JSONException e) {
            responseString = e.toString();
        }
    }


//    /**
//     * Test this lightly.
//     * @param args (none are ever processed)
//     */
//    public static void main( String[] args )
//    {
//        Allow2 allow2 = Allow2.getShared();
//        allow2.env = Allow2EnvType.SANDBOX;
//        allow2.pair("user@gmail.com", "password", "Java Device Name");
//    }

}
