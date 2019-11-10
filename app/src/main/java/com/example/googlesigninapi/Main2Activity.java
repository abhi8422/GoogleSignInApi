package com.example.googlesigninapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.view.View;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main2Activity extends AppCompatActivity {
    private Handler handler = new Handler();

    private Executor executor =Executors.newSingleThreadExecutor();
    /*= new Executor() {
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };*/
    Main2Activity activity=this;
    BiometricPrompt promptInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        promptInfo=
                new BiometricPrompt.Builder(this)
                        .setTitle("Biometric login for my app")
                        .setSubtitle("Log in using your biometric credential")
                        .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .build();
    }

    public void Login(View view) {
        promptInfo.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }
        });
    }


}
