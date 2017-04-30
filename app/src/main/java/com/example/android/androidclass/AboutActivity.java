package com.example.android.androidclass;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.IOException;


public class AboutActivity extends AppCompatActivity {

    public static MediaPlayer player;
    public static AssetFileDescriptor afd;

    public int media_length;

    private Button marvelBackButton;
    private Button dcBackButton;
    private Button marvelButton;
    private Button dcButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        final WebView Marvelwebview = (WebView) findViewById(R.id.myWebView);
        Marvelwebview.loadUrl("file:///android_asset/marvel_gif.html");


        final WebView DCwebview = (WebView) findViewById(R.id.myOtherWebView);
        DCwebview.loadUrl("file:///android_asset/dc_gif.html");


        marvelBackButton = (Button)findViewById(R.id.back_button_marvel);
        marvelButton = (Button)findViewById(R.id.marvel_button);

        marvelBackButton.setVisibility(View.GONE);
        marvelButton.setVisibility(View.GONE);
        DCwebview.setVisibility(View.GONE);

        dcButton = (Button)findViewById(R.id.DC_button);
        dcBackButton = (Button)findViewById(R.id.back_button_DC);



        try {
// Read the music file from the asset folder
            afd = getAssets().openFd("marvel_bgm.mp3");
// Creation of new media player;
            player = new MediaPlayer();
// Set the player music source.
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
// Set the looping and play the music.
            player.setLooping(true);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        marvelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marvelButton.setVisibility(View.GONE);
                marvelBackButton.setVisibility(View.GONE);
                Marvelwebview.setVisibility(View.VISIBLE);
                dcButton.setVisibility(View.VISIBLE);
                dcBackButton.setVisibility(View.VISIBLE);
                DCwebview.setVisibility(View.GONE);
                player.stop();

                try {
// Read the music file from the asset folder
                    afd = getAssets().openFd("marvel_bgm.mp3");
// Creation of new media player;
                    player = new MediaPlayer();
// Set the player music source.
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
// Set the looping and play the music.
                    player.setLooping(true);
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        dcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marvelButton.setVisibility(View.VISIBLE);
                marvelBackButton.setVisibility(View.VISIBLE);
                Marvelwebview.setVisibility(View.GONE);
                dcButton.setVisibility(View.GONE);
                dcBackButton.setVisibility(View.GONE);
                DCwebview.setVisibility(View.VISIBLE);
                player.stop();

                try {
// Read the music file from the asset folder
                    afd = getAssets().openFd("dc_bgm.mp3");
// Creation of new media player;
                    player = new MediaPlayer();
// Set the player music source.
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
// Set the looping and play the music.
                    player.setLooping(true);
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });






        marvelBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LostFoundMain.class);
                startActivity(intent);
                finish();

            }
        });

        dcBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LostFoundMain.class);
                startActivity(intent);
                finish();

            }
        });






            }





    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),LostFoundMain.class);
        startActivity(intent);
        finish();
    }



    public void onPause() {
        super.onPause();
        player.pause();
        media_length = player.getCurrentPosition();
    }


    public void onResume() {
        super.onResume();

        player.seekTo(media_length);
        player.start();


    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();


    }

    }

