package com.wisdomgeek.jsonparser.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wisdomgeek.jsonparser.R;
import com.wisdomgeek.jsonparser.asyncTasks.GetRepositories;
import com.wisdomgeek.jsonparser.interfaces.AsyncCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saranshkataria on 11/02/17.
 * For details about the post refer to http://www.wisdomgeek.com
 */

public class MainActivity extends AppCompatActivity implements AsyncCallback {
    private ListView listView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<HashMap<String, String>> repoList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        new GetRepositories(MainActivity.this, MainActivity.this).execute();
    }

    @Override
    public void onAsyncStarted() {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void onAsyncCompleted(List<HashMap<String, String>> result) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        ListAdapter listAdapter = new SimpleAdapter(
                MainActivity.this,
                result,
                R.layout.list_item,
                new String[]{"name", "description"},
                new int[]{R.id.tv_name, R.id.tv_description}
        );
        listView.setAdapter(listAdapter);
    }
}
