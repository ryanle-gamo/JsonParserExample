package com.wisdomgeek.jsonparser.interfaces;

import java.util.HashMap;
import java.util.List;

/**
 * Created by saranshkataria on 11/02/17.
 */

public interface AsyncCallback {

    void onAsyncStarted();

    void onAsyncCompleted(List<HashMap<String, String>> result);
}
