JSON is the first thing that a developer comes across when using a RESTful API. You need to parse it in order to be able to use it in an android application.
This project demos a sample parsing example which makes use of Github's REST API. The JSON from the url https://api.github.com/orgs/google/repos is fetched in the android application in an async task using HttpUrlConnection and then this is converted into a hashmap of string values and displayed to a list view.
More details can be found at : https://wisdomgeek.com/android/json-parsing-in-android-tutorial/
