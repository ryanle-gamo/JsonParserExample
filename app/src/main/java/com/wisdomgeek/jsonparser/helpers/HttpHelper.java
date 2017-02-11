package com.wisdomgeek.jsonparser.helpers;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by saranshkataria on 11/02/17.
 */
public class HttpHelper {

    private static final String TAG = HttpHelper.class.getSimpleName();

    public String getHttpResponse(String url) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append('\n');
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        }
        return stringBuilder.toString();
    }
}
