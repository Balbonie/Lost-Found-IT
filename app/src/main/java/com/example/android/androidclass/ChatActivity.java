package com.example.android.androidclass;

/**
 * Created by josep on 18/04/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.androidclass.FireBase.ChatMessage;
import com.example.android.androidclass.FireBase.CreateUserForChat;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.android.androidclass.SignInActivity.user;

public class ChatActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CODE = 10;
    private FirebaseListAdapter<ChatMessage> adapter;

    private String fName = user.getDisplayName();
    private String eMail = user.getEmail();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        final CreateUserForChat user = new CreateUserForChat(fName, eMail);
        FirebaseDatabase.getInstance()
                .getReference()
                .child("users")
                .setValue(user);

        // User is already signed in. Therefore, display
        // a welcome Toast


        // Load chat room contents
        ListView listOfMessages = (ListView) findViewById(R.id.list_of_messages);

        final String userChat = getIntent().getStringExtra("userChat");
        final String descChat = getIntent().getStringExtra("descChat");
        final String statusOfItem = getIntent().getStringExtra("statusOfItem");

        FirebaseListAdapter<ChatMessage> adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference("User Chat Rooms").child( userChat + " " + descChat + " " + statusOfItem + " Chat Room")) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView) v.findViewById(R.id.message_text);
                TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);


        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database


                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("User Chat Rooms")
                        .child( userChat + " " + descChat + " " + statusOfItem + " Chat Room")
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(), fName
                                )
                        );

                // Clear the input
                input.setText("");
            }
        });


    }
}








