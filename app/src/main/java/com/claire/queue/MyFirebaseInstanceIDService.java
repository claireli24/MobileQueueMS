package com.claire.queue;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token",token)
                .build();
        Request request = new Request.Builder()
                .url("https://rasppiqueuems.000webhostapp.com/register.php")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
