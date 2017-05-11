package com.claire.queue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    EditText editUsername, editPhoneNumber;
    Button buttonDone;
    boolean completed;
    SharedPreferences sharedPref;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        sharedPref = getSharedPreferences("sharedpref", 0);
        completed = sharedPref.getBoolean("complete", false);
        if (completed){
            Intent intent = new Intent();
            intent.setClass(UserActivity.this, MainActivity.class);
//            String strUsername = ((EditText) findViewById(R.id.edtUsername)).getText().toString();
//            String strPhoneNumber = ((EditText) findViewById(R.id.edtPhoneNumber)).getText().toString();
//            intent.putExtra("varStrUsername", strUsername);
//            intent.putExtra("varStrPhoneNumber", strPhoneNumber);
            startActivity(intent);
            finish();
        }

        isNetworkAvailable();

        try {
            if (isNetworkAvailable()) {
                Toast.makeText(getApplicationContext(), "Internet connection is active", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Please connect to the internet", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Internet Error", Toast.LENGTH_SHORT).show();
        }

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        editUsername = (EditText) findViewById(R.id.edtUsername);
        editPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        buttonDone = (Button) findViewById(R.id.btnDone);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameVal = editUsername.getText().toString();
                String phoneNumberVal = editPhoneNumber.getText().toString();
                if (!usernameVal.equals("") && !phoneNumberVal.equals("")){
                    SharedPreferences.Editor editors = sharedPref.edit();
                    editors.putString("varStrUsername", editUsername.getText().toString());
                    editors.putString("varStrPhoneNumber", editPhoneNumber.getText().toString());
                    editors.putBoolean("complete", true);
                    editors.apply(); //commit
                    Intent btnDone = new Intent(UserActivity.this, MainActivity.class);
                    startActivity(btnDone);
                }

                isNetworkAvailable();

                try {
                    if (isNetworkAvailable()) {
//                        Toast.makeText(getApplicationContext(), "Internet connection is active", Toast.LENGTH_SHORT).show();
                        addUser();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please connect to the internet", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Internet Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void addUser(){
        String username = editUsername.getText().toString();
        String phoneNumber = editPhoneNumber.getText().toString();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(phoneNumber)){
            String id = databaseUsers.push().getKey();
            Users user = new Users(username, phoneNumber);
            databaseUsers.child(id).setValue(user);
            Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();
            toMainActivity();
        }
        else {
            Toast.makeText(this, "Please fill in the blank", Toast.LENGTH_LONG).show();
        }
    }

    public void toMainActivity(){
        Intent intent = new Intent(UserActivity.this, MainActivity.class);
        String strUsername = ((EditText) findViewById(R.id.edtUsername)).getText().toString();
        String strPhoneNumber = ((EditText) findViewById(R.id.edtPhoneNumber)).getText().toString();
        intent.putExtra("varStrUsername", strUsername);
        intent.putExtra("varStrPhoneNumber", strPhoneNumber);
        startActivity(intent);
        finish();
    }
}
