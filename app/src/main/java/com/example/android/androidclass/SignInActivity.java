package com.example.android.androidclass;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
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

import com.example.android.androidclass.Utilities.ConnectionHelper;
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


public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 0 ;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private com.google.android.gms.common.SignInButton signInButton;
    private GoogleApiClient mGoogleApiClient;
    private Button signOutButton;
    private Button cancelButton;
    private TextView nameTextView;
    private TextView emailTextView;
    private ProgressBar progressBar;
    private TextView logoTextView;

    public static String currentUserName;
    public static int loginStatus;
    public static FirebaseUser user;


    public int media_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().hide();

        signInButton = (com.google.android.gms.common.SignInButton)findViewById(R.id.sign_in_button);
        signOutButton = (Button)findViewById(R.id.sign_out_button);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        nameTextView = (TextView)findViewById(R.id.name_text_view);
        emailTextView = (TextView)findViewById(R.id.email_text_view);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        logoTextView = (TextView)findViewById(R.id.logoTextView);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();




        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();

                if(user == null){
                    signInButton.setVisibility(View.VISIBLE);
                    signOutButton.setVisibility(View.GONE);
                    cancelButton.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    logoTextView.setVisibility(View.VISIBLE);

                }else {
                    signInButton.setVisibility(View.GONE);
                    signOutButton.setVisibility(View.VISIBLE);
                    cancelButton.setVisibility(View.VISIBLE);
                    logoTextView.setVisibility(View.GONE);


                    }





                if (user != null && ConnectionHelper.verifyConnection(getApplicationContext())) {
                    // User is signed in
                    if(loginStatus == 0) {
                        signOutButton.setVisibility(View.GONE);
                        cancelButton.setVisibility(View.GONE);
                        Intent intent = new Intent(getApplicationContext(), LostFoundMain.class);
                        startActivity(intent);
                        finish();
                        currentUserName = user.getDisplayName().toString();

                    }

                    if(loginStatus == 1){
                        progressBar.setVisibility(View.GONE);
                        signOutButton.setVisibility(View.GONE);
                        cancelButton.setVisibility(View.GONE);
                        logoTextView.setVisibility(View.GONE);

                        if(user.getEmail().contains("ust.edu.ph")){
                            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                            if (user.getDisplayName() != null)
                                nameTextView.setText("HI " + user.getDisplayName().toString());
                            emailTextView.setText(user.getEmail().toString() + "\n\nAre you sure you want to logout?");
                            progressBar.setVisibility(View.GONE);
                            signOutButton.setVisibility(View.VISIBLE);
                            cancelButton.setVisibility(View.VISIBLE);
                            logoTextView.setVisibility(View.GONE);
                        }
                        else{
                            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                            if (user.getDisplayName() != null)
                                nameTextView.setText("HI " + user.getDisplayName().toString());
                            emailTextView.setText(user.getEmail().toString() + "\n" +
                                    "is not\na valid email address. \n\nPlease logout!");
                            signOutButton.setVisibility(View.VISIBLE);
                            cancelButton.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            logoTextView.setVisibility(View.GONE);

                            user = null;

                            Toast.makeText(SignInActivity.this, "Please use your 'ust.edu.ph' account. ", Toast.LENGTH_SHORT).show();


                        }
                    }
                } else {
                    if (ConnectionHelper.verifyConnection(getApplicationContext())){
                        // User is signed out
                        Log.d(TAG, "onAuthStateChanged:signed_out");
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No Internet.", Toast.LENGTH_SHORT).show();
                    }


                }
                // ...
            }
        };


            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginStatus = 0;

                    signIn();

                }
            });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginStatus = 0;
                Intent intent = new Intent(getApplicationContext(),LostFoundMain.class);
                startActivity(intent);
                finish();

            }
        });
            signOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status) {

                                    signInButton.setVisibility(View.VISIBLE);
                                    signOutButton.setVisibility(View.GONE);
                                    emailTextView.setText(" ".toString());
                                    nameTextView.setText(" ".toString());
                                }
                            });
                }
                // ..
            });

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }




    public void onPause() {
        super.onPause();

    }


    public void onResume() {
        super.onResume();




    }






    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                progressBar.setVisibility(View.VISIBLE);
                signInButton.setVisibility(View.GONE);
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);

            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
