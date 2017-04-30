package com.example.android.androidclass;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.androidclass.FireBase.RegisterFound;
import com.example.android.androidclass.FireBase.RegisterLost;
import com.example.android.androidclass.Utilities.ConnectionHelper;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//import com.example.vince.protoproject.utilities.JSONHelperEXInvoker;

public class DisplayLostPersonal extends AppCompatActivity {

    private TextView statusField;
    private TextView lostName;
    private TextView lostContact;
    private TextView lostDesc;
    private TextView lostWhere;
    private TextView lostStat;

    private static String userID;
    private static String user_ID;

    private String lostOrFound;

    private String nameLost;
    private String contactLost;
    private String descLost;
    private String whereLost;

    private String itemStat;

    private String lostOrFoundFound;
    private String nameFound;
    private String descFound;
    private String whereFound;
    private String contactFound;
    private String itemStatFound;


    private String statusOfClaimButton;
    private Button btnOpenPopup;








    private static PopupWindow popupWindow;
    private LostFoundMain.SectionsPagerAdapter mSectionsPagerAdapter;
    private static TextView e;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_lost_personal);


        if (ConnectionHelper.verifyConnection(getApplicationContext()) == true) {


            lostOrFound = getIntent().getStringExtra("LostOrFound");


            nameLost = getIntent().getStringExtra("nameLost");
            contactLost = getIntent().getStringExtra("contactLost");
            descLost = getIntent().getStringExtra("descLost");
            whereLost = getIntent().getStringExtra("whereLost");
            itemStat = getIntent().getStringExtra("itemStat");

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(lostOrFound + " Items");
            Query queryRef = ref.orderByChild(lostOrFound.toLowerCase() + "_nameStatDescWhere").equalTo(nameLost + " " + " " + itemStat + " " + " " + descLost + " " + " " + whereLost);

            queryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                    //Toast.makeText(DisplayLostPersonal.this, snapshot.child("lost_stat").toString(), Toast.LENGTH_SHORT).show();
                    String statusOfClaimButton = snapshot.child(lostOrFound.toLowerCase() + "_stat").toString();
                    if (statusOfClaimButton.contains("CLAIMED")) {
                        btnOpenPopup.setVisibility(View.GONE);
                        lostStat.setText("CLAIMED");

                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Item Claimed", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 15);
                        toast.show();

                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "The fields are scrollable.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 15);
                        toast.show();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Item Claimed", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 15);
                    toast.show();


                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
                // ....
            });


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

            lostName.setText(nameLost);
            lostContact.setText(contactLost);
            lostDesc.setText(descLost);
            lostWhere.setText(whereLost);
            lostStat.setText(itemStat);


            lostOrFoundFound = statusField.getText().toString();
            nameFound = lostName.getText().toString();
            contactFound = lostContact.getText().toString();
            descFound = lostDesc.getText().toString();
            whereFound = lostWhere.getText().toString();
            itemStatFound = lostStat.getText().toString();


            //TextView messageTime = (TextView) v.findViewById(R.id.message_time);


            // Format the date before showing it
            //messageTime.setText(DateFormat.format("MM-dd-yyyy (HH:mm:ss)",
            //  model.getMessageTime()));


            btnOpenPopup = (Button) findViewById(R.id.claim);

            if (lostStat.getText().toString() == "CLAIMED") {
                btnOpenPopup.setVisibility(View.GONE);
            }

            btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                        //popupWindow = null;
                    /*if (popupWindow != null)
                    {
                        Toast.makeText(MainActivity.this, "YE TOAST", Toast.LENGTH_SHORT).show();
                    }*/
                    }
                    LayoutInflater layoutInflater
                            = (LayoutInflater) getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.popup, null);
                    popupWindow = new PopupWindow(
                            popupView,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);

                    Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                    btnDismiss.setOnClickListener(new Button.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            popupWindow.dismiss();
                        }
                    });

                    Button btnUpdate = (Button) popupView.findViewById(R.id.update);
                    btnUpdate.setOnClickListener(new Button.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub


                            user_ID = userID;

                            String helloL = nameLost + " " + " " + itemStat + " " + " " + descLost + " " + " " + whereLost;
                            String helloF = nameFound + " " + " " + itemStatFound + " " + " " + descFound + " " + " " + whereFound;


                            if (lostOrFound.contains("Lost")) {

                                RegisterLost regLost = new RegisterLost(nameLost, contactLost, descLost, whereLost, user_ID, "CLAIMED", lostOrFound, helloL);
                                FirebaseDatabase.getInstance()
                                        .getReference()
                                        .child(lostOrFound + " Items")
                                        .child(nameLost + " " + " " + itemStat + " " + " " + descLost + " " + " " + whereLost)
                                        .setValue(regLost);
                            }
                            if (lostOrFound.contains("Found")) {
                                RegisterFound regFound = new RegisterFound(nameFound, contactFound, descFound, whereFound, user_ID, "CLAIMED", lostOrFoundFound, helloF);
                                FirebaseDatabase.getInstance()
                                        .getReference(lostOrFound + " Items")
                                        .child(nameFound + " " + " " + itemStatFound + " " + " " + descFound + " " + " " + whereFound)
                                        .setValue(regFound);
                            }

                            popupWindow.dismiss();
                            btnOpenPopup.setVisibility(View.GONE);
                            lostStat.setText("CLAIMED");
                            //JSONHelperEXInvoker.updateData(getApplicationContext(), ty, id);
                        }
                    });

                    popupWindow.showAtLocation(arg0, Gravity.CENTER, 0, 0);
                }
            });
            if (lostStat.getText().toString().equals("CLAIMED")) {
                btnOpenPopup.setVisibility(View.GONE);
            }

        }
        else{
            Toast.makeText(getApplicationContext(), "No Internet.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),LostFoundMain.class);
            startActivity(intent);
            finish();
        }
    }

    public void closeP(View v)
    {
        Intent i = new Intent(this, LostFoundMain.class);
        i.putExtra("4th", 4);
        startActivity(i);
        finish();

    }


    @Override
    public void onBackPressed() {

    }
}
