package com.example.jh949711.phonecall;
/*
   This app illustrates how to invoke the phone app from a user-built UI.
   The app assumes the user to enter a valid phone number consisting of three digit area-code,
   three digit prefix, and a four digit extension.
   This app requires adding the <uses-permission android:name="android.permission.CALL_PHONE"/>
   element within the manifest element of AndroidManifest.xml
 */

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneCallActivity extends AppCompatActivity {

    EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);
        et1 = findViewById(R.id.area_code);
        et2 = findViewById(R.id.prefix);
        et3 = findViewById(R.id.phone);
        /*
            The method adds a TextWatcher instance to the EditText so its callback functions
            get invoked when there is an activity within the EditText.806
         */
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et1.getText().toString().length() == 3){
                    et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et2.getText().toString().length() == 3){
                    et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    protected void MakeCall(View view){
        if(et1.getText().toString().length() != 3){
            Toast.makeText(getApplicationContext(),"Enter an area code", Toast.LENGTH_SHORT);
        }
        else if (et2.getText().toString().length() != 3){
            Toast.makeText(getApplicationContext(),"Enter a prefix", Toast.LENGTH_SHORT);
        }
        else if (et3.getText().toString().length() != 4){
            Toast.makeText(getApplicationContext(),"Enter last 4 digits", Toast.LENGTH_SHORT);
        }
        else {
            String phone_number = et1.getText().toString() +
                    et2.getText().toString() + et3.getText().toString();
            try {
                Uri uri = Uri.parse("tel:" + phone_number);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                Log.i("Phone Call App", phone_number);
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getApplicationContext(), "Application failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
