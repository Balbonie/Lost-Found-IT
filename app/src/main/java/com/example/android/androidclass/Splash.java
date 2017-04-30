package com.example.android.androidclass;

/**
 * Created by josep on 3/6/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.android.androidclass.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };

        thread.start();

    }
}
