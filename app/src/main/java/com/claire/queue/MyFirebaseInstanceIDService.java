package com.claire.queue;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    private static final String TAG = "FirebaseIDService";
//    SharedPreferences sharedPref;

    @Override
    public void onTokenRefresh(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
//        Toast.makeText(this, refreshedToken, Toast.LENGTH_LONG).show();
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
//        SharedPreference.getInstance(this).setValue(getString(R.string.firebase_cloud_messaging_token), token);

    }
}
