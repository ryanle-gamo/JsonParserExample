package com.wisdomgeek.jsonparser.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.wisdomgeek.jsonparser.helpers.HttpHelper;
import com.wisdomgeek.jsonparser.interfaces.AsyncCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class GetRepositories extends AsyncTask<Void, Void, List<HashMap<String, String>>> {
    private static final String url = "https://api.github.com/orgs/google/repos";
    private Context context;
    private AsyncCallback asyncCallback;

    public GetRepositories(Context context, AsyncCallback asyncCallback) {
        this.context = context;
        this.asyncCallback = asyncCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        asyncCallback.onAsyncStarted();
    }

    @Override
    protected List<HashMap<String, String>> doInBackground(Void... params) {
        HttpHelper httpHelper = new HttpHelper();
        String httpResponse = httpHelper.getHttpResponse(url);
        List<HashMap<String, String>> result = new ArrayList<>();
        if (httpResponse != null && !httpResponse.isEmpty()) {
            try {
                JSONArray repositories = new JSONArray(httpResponse);
                for (int i = 0; i < repositories.length(); i++) {
                    JSONObject repository = repositories.getJSONObject(i);
                    if(repository != null) {
                        String name = repository.getString("name");
                        String description = repository.getString("description");
                        HashMap<String, String> repo = new HashMap<>();
                        repo.put("name", name);
                        repo.put("description", description);
                        result.add(repo);
                    }
                }
            } catch (JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<HashMap<String, String>> result) {
        super.onPostExecute(result);
        asyncCallback.onAsyncCompleted(result);
    }
}
