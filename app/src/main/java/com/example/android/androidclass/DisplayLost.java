package com.example.android.androidclass;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.androidclass.Utilities.ChromeHelpPopup;
import com.example.android.androidclass.Utilities.ConnectionHelper;


public class DisplayLost extends AppCompatActivity {

    private TextView statusField;
    private TextView lostName;
    private TextView lostContact;
    private TextView lostDesc;
    private TextView lostWhere;
    private TextView lostStat;

    private String lostOrFound;

    private String nameLost;
    private String contactLost;
    private String descLost;
    private String whereLost;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_lost);


        if (ConnectionHelper.verifyConnection(getApplicationContext()) == true) {

            Toast toast = Toast.makeText(getApplicationContext(),
                    "The fields are scrollable.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 15);
            toast.show();


            lostOrFound = getIntent().getStringExtra("LostOrFound");


            nameLost = getIntent().getStringExtra("nameLost");
            contactLost = getIntent().getStringExtra("contactLost");
            descLost = getIntent().getStringExtra("descLost");
            whereLost = getIntent().getStringExtra("whereLost");


            statusField = (TextView) findViewById(R.id.statusFieldL);
            statusField.setText(lostOrFound);

            lostName = (TextView) findViewById(R.id.lostName);
            lostName.setMovementMethod(new ScrollingMovementMethod());

            lostContact = (TextView) findViewById(R.id.lostContact);

            lostDesc = (TextView) findViewById(R.id.lostDesc);
            lostDesc.setMovementMethod(new ScrollingMovementMethod());

            lostWhere = (TextView) findViewById(R.id.lostWhere);
            lostWhere.setMovementMethod(new ScrollingMovementMethod());

            lostStat = (TextView) findViewById(R.id.lostStat);
            lostStat.setText("Not Claimed");


            lostName.setText(nameLost);
            lostContact.setText(contactLost);
            lostDesc.setText(descLost);
            lostWhere.setText(whereLost);


            final ChromeHelpPopup chromeHelpPopup = new ChromeHelpPopup(DisplayLost.this, "Click the number to contact directly.");

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    chromeHelpPopup.show(lostContact);


                }
            }, 100);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    chromeHelpPopup.dismiss();


                }
            }, 5000);


            //TextView messageTime = (TextView) v.findViewById(R.id.message_time);


            // Format the date before showing it
            //messageTime.setText(DateFormat.format("MM-dd-yyyy (HH:mm:ss)",
            //  model.getMessageTime()));
        }
        else{
            Toast.makeText(getApplicationContext(), "No Internet.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),LostFoundMain.class);
            startActivity(intent);
            finish();
        }
    }




    public void close(View v)
    {
        super.finish();
        Intent i = new Intent(this, LostFoundMain.class);
        i.putExtra("4th", 0);
        startActivity(i);
        finish();
    }


    public void chat(View v)
    {

        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("userChat", nameLost);
        intent.putExtra("descChat", descLost);
        intent.putExtra("statusOfItem", lostOrFound);
        startActivity(intent);
    }

    public void contact(View v)
    {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.setData(Uri.parse("sms:" + contactLost));
        startActivity(smsIntent);
        Toast.makeText(this, "Messaging cost is not covered by the application.", Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onBackPressed() {

    }

}