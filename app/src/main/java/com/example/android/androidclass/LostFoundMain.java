package com.example.android.androidclass;

/**
 * Created by android on 3/1/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.androidclass.FireBase.RegisterFound;
import com.example.android.androidclass.FireBase.RegisterLost;
import com.example.android.androidclass.Utilities.ConnectionHelper;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.android.androidclass.SignInActivity.currentUserName;
import static com.example.android.androidclass.SignInActivity.loginStatus;
import static com.example.android.androidclass.SignInActivity.user;


public class LostFoundMain extends AppCompatActivity {

    private static TextView lostName;
    private static EditText lostContact;
    private static EditText lostDesc;
    private static EditText lostWhere;
    private static TextView lostStat;

    private static TextView foundName;
    private static EditText foundContact;
    private static EditText foundDesc;
    private static EditText foundWhere;
    private static TextView foundStat;
    private static String userID;
    private static String userName;
    private static String userContact;
    private static String userEmail;

    private static String found_name;
    private static String found_contact;
    private static String found_desc;
    private static String found_where;
    private static String found_stat;
    private static String found_statusFF;
    private static String found_nameStatDescWhere;
    private static String user_ID;

    private static String lost_name;
    private static String lost_contact;
    private static String lost_desc;
    private static String lost_where;
    private static String lost_stat;
    private static String lost_statusFL;
    private static String lostNameStatDescWhere;

    private static TextView statusOfItemF;
    private static TextView statusOfItemL;


    private static TextView lost_name_TW;
    private static EditText lost_contact_ET;
    private static EditText lost_desc_ET;
    private static EditText lost_where_ET;
    private static TextView lost_statusFL_TW;


    private static TextView found_name_TW;
    private static EditText found_contact_ET;
    private static EditText found_desc_ET;
    private static EditText found_where_ET;
    private static TextView found_statusFF_TW;

    private static TextView noItems;


    public void buttonPress(View button)
    {



        Button b = (Button) button;
        String a = b.getText().toString();
        switch (a)
        {
            case "Register as Lost":
                if (lostName.getText().toString().equals("") || lostContact.getText().toString().equals("")
                        || lostDesc.getText().toString().equals("") || lostWhere.getText().toString().equals("")
                        )

                    //|| !StringUtils.isNumeric(lostcontact.getText().toString())
                {
                    Toast.makeText(this, "Please fill up all fields.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //setContentView(R.layout.fragment_lost_item);
                    lost_name_TW = (TextView) findViewById(R.id.lostName);
                    lost_statusFL_TW = (TextView) findViewById(R.id.statusFieldL);
                    lost_contact_ET = (EditText) findViewById(R.id.lostContact);
                    lost_desc_ET = (EditText) findViewById(R.id.lostDesc);
                    lost_where_ET = (EditText) findViewById(R.id.lostWhere);
                    statusOfItemL = (TextView) findViewById(R.id.lostStat);


                    try {
                        lost_name = lost_name_TW.getText().toString();
                        lost_statusFL = lost_statusFL_TW.getText().toString();
                        lost_contact = lost_contact_ET.getText().toString();
                        lost_desc = lost_desc_ET.getText().toString();
                        lost_where = lost_where_ET.getText().toString();
                        lost_stat = statusOfItemL.getText().toString();
                        lostNameStatDescWhere = lost_name + " " + " " + lost_stat + " " + " " + lost_desc + " " + " " + lost_where;

                    }catch(Exception e){
                        lost_name = lost_name_TW.getText().toString();
                        lost_statusFL = lost_statusFL_TW.getText().toString();
                        lost_contact = lost_contact_ET.getText().toString();
                        lost_desc = lost_desc_ET.getText().toString();
                        lost_where = lost_where_ET.getText().toString();
                        lost_stat = "Not Claimed";
                        lostNameStatDescWhere = lost_name + " " + " " + lost_stat + " " + " " + lost_desc + " " + " " + lost_where;
                    }
                    user_ID = userID;
                    //DBHelper.reportLost(data, _dbHelper.getWritableDatabase());

                    RegisterLost regLost = new RegisterLost(lost_name , lost_contact, lost_desc, lost_where, user_ID, lost_stat, lost_statusFL, lostNameStatDescWhere);
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Lost Items")
                            .child(lostNameStatDescWhere)
                            .setValue(regLost);

                    Toast.makeText(this, lost_name, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Registered as Lost", Toast.LENGTH_SHORT).show();
                    lostDesc.setText("");
                    lostWhere.setText("");


                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                    // Set up the ViewPager with the sections adapter.
                    mViewPager = (ViewPager) findViewById(R.id.container);

                    mViewPager.setCurrentItem(0);

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    //this.recreate();





                }

                break;
            case "Register as Found":
                if (foundName.getText().toString().equals("") || foundContact.getText().toString().equals("")
                        || foundDesc.getText().toString().equals("") || foundWhere.getText().toString().equals("")
                        )
                {
                    Toast.makeText(this, "Please fill up all fields.", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    found_name_TW = (TextView) findViewById(R.id.foundName);
                    found_name_TW.setMovementMethod(new ScrollingMovementMethod());
                    found_statusFF_TW = (TextView) findViewById(R.id.statusFieldF);
                    found_contact_ET = (EditText) findViewById(R.id.foundContact);
                    found_desc_ET = (EditText) findViewById(R.id.foundDesc);
                    found_where_ET = (EditText) findViewById(R.id.foundWhere);
                    statusOfItemF = (TextView) findViewById(R.id.founderStat);
                    user_ID = userID;

                    try {
                        found_name = found_name_TW.getText().toString();
                        found_statusFF = found_statusFF_TW.getText().toString();
                        found_contact = found_contact_ET.getText().toString();
                        found_desc = found_desc_ET.getText().toString();
                        found_where = found_where_ET.getText().toString();
                        found_stat = statusOfItemF.getText().toString();
                        found_nameStatDescWhere = found_name + " " + " " + found_stat + " " + " " + found_desc + " " + " " + found_where;
                    }catch(Exception e){
                        found_name = found_name_TW.getText().toString();
                        found_statusFF = found_statusFF_TW.getText().toString();
                        found_contact = found_contact_ET.getText().toString();
                        found_desc = found_desc_ET.getText().toString();
                        found_where = found_where_ET.getText().toString();
                        found_stat = "Not Claimed";
                        found_nameStatDescWhere = found_name + " " + " " + found_stat + " " + " " + found_desc + " " + " " + found_where;
                    }
                    RegisterFound regFound = new RegisterFound(found_name , found_contact, found_desc, found_where, user_ID, found_stat, found_statusFF, found_nameStatDescWhere);
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Found Items")
                            .child(found_nameStatDescWhere)
                            .setValue(regFound);

                    Toast.makeText(this, "Registered as Found", Toast.LENGTH_SHORT).show();
                    foundDesc.setText("");
                    foundWhere.setText("");

                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                    // Set up the ViewPager with the sections adapter.
                    mViewPager = (ViewPager) findViewById(R.id.container);

                    mViewPager.setCurrentItem(0);

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
                break;

        }

    }

    public void logoutUser ()
    {
        Intent loginIntent;
        loginIntent = new Intent(this, SignInActivity.class);
        //UsersHelper.clearSharedPreferences(this);
        loginStatus = 1;
        startActivity(loginIntent);
        //Toast.makeText(this, "User logged out.", Toast.LENGTH_SHORT).show();
        super.finish();
    }

    public void about()
    {
        Intent loginIntent;
        loginIntent = new Intent(this, AboutActivity.class);
        //UsersHelper.clearSharedPreferences(this);
        startActivity(loginIntent);
        Toast.makeText(this, "Created by Joseph Lawrence Sobong and Luigi Hatol", Toast.LENGTH_LONG).show();
        super.finish();
    }



    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    /*private static DBHelper _dbHelper = null;
    private static SQLiteDatabase _database = null;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_ore);




        //setSupportActionBar(toolbar);
        if (ConnectionHelper.verifyConnection(this))
        {
            if (user.getEmail().contains("ust-ics.mygbiz.com")) {
                try {


                /*Toast.makeText(this,
                        "Welcome " + FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName(),
                        Toast.LENGTH_LONG)
                        .show();

                */
                    // Create the adapter that will return a fragment for each of the three
                    // primary sections of the activity.
                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


                    // Set up the ViewPager with the sections adapter.
                    mViewPager = (ViewPager) findViewById(R.id.container);
                    mViewPager.setAdapter(mSectionsPagerAdapter);




                    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                    tabLayout.setupWithViewPager(mViewPager);




                    //Toast.makeText(this, userID, Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Intent loginIntent;
                    loginIntent = new Intent(this, SignInActivity.class);
                    startActivity(loginIntent);
                    Toast.makeText(getApplicationContext(), "No login.", Toast.LENGTH_SHORT).show();
                    user = null;
                    loginStatus = 1;
                }
            }else{
                Intent loginIntent;
                loginIntent = new Intent(this, SignInActivity.class);
                startActivity(loginIntent);
                Toast.makeText(getApplicationContext(), "No login.", Toast.LENGTH_SHORT).show();
                user = null;
                loginStatus = 1;
            }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });(/
        /*try
        {
            //Creates the db upon instantiation (executes on create)
            _dbHelper = new DBHelper(getApplicationContext());
            _database = _dbHelper.getWritableDatabase();
        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Something went wrong on create.", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }*/

        }
        else {
            Toast.makeText(this, "You are not connected to the internet.", Toast.LENGTH_SHORT).show();
        }

    }



    public void
    onResume()
    {
        super.onResume();
        Intent mIntent = getIntent();
        int page = mIntent.getIntExtra("4th", 0);
        final int pos = 3;



        if(page !=0) {
            mViewPager.postDelayed(new Runnable() {

                @Override
                public void run() {
                    mViewPager.setCurrentItem(pos);
                }
            }, 150);
        }



        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {



                if (position == 2 || position == 1){
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "The fields are scrollable.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 15);
                    toast.show();
                }
                else{


                    //Toast.makeText(LostFoundMain.this, "hey", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lost_found_ore, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_action:
                about();
                return true;

            case R.id.logout_action:
                logoutUser();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lost_found_ore, container, false);


            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1)
            {

                rootView = inflater.inflate(R.layout.fragment_general_list, container, false);
                final ListView listF = (ListView)rootView.findViewById(R.id.list_of_found_items);
                final ListView listL = (ListView)rootView.findViewById(R.id.list_of_lost_items);
                final TextView noItemsGeneral = (TextView)rootView.findViewById(R.id.noItems);
                noItemsGeneral.setVisibility(View.GONE);
                final ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);



                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                final ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(getContext(), R.array.main_dropdown, android.R.layout.simple_spinner_item);
                adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapters);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id ) {

                        if (parent.getItemIdAtPosition(pos) == 0) {

                            progressBar.setVisibility(View.VISIBLE);
                            listF.setVisibility(View.GONE);
                            noItemsGeneral.setVisibility(View.GONE);
                            listL.setVisibility(View.VISIBLE);



                            FirebaseListAdapter<RegisterLost> adapter = new FirebaseListAdapter<RegisterLost>(getActivity(), RegisterLost.class,
                                    R.layout.lost_items_lv, FirebaseDatabase.getInstance().getReference("Lost Items").orderByChild("lost_stat").equalTo("Not Claimed")) {

                                @Override
                                protected void populateView(View v, RegisterLost model, int position) {
                                    // Get references to the views of message.xml
                                    TextView lostName = (TextView) v.findViewById(R.id.loserName);
                                    TextView lostContact = (TextView) v.findViewById(R.id.loserContact);
                                    TextView lostDesc = (TextView) v.findViewById(R.id.loserDesc);
                                    TextView lostWhere = (TextView) v.findViewById(R.id.loserWhere);
                                    TextView messageTime = (TextView) v.findViewById(R.id.message_time);
                                    TextView lostStatusF = (TextView) v.findViewById(R.id.loserStatusF);
                                    TextView lostStat = (TextView) v.findViewById(R.id.loserStat);

                                    // Set their text
                                    lostName.setText(model.getLost_name());
                                    lostContact.setText(model.getLost_contact());
                                    lostDesc.setText(model.getLost_desc());
                                    lostWhere.setText(model.getLost_where());
                                    lostStatusF.setText(model.getLost_statusFL());
                                    lostStat.setText(model.getLost_stat());

                                    final TextView claimedL = (TextView)v.findViewById(R.id.claimed);
                                    final TextView unclaimedL = (TextView)v.findViewById(R.id.not_claimed);


                                    claimedL.setVisibility(View.GONE);
                                    unclaimedL.setVisibility(View.GONE);

                                    // Format the date before showing it
                                    messageTime.setText(DateFormat.format("MM-dd-yyyy (HH:mm:ss)",
                                            model.getMessageTime()));


                                }
                            };

                            listL.setAdapter(adapter);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {



                                    if(listL.getAdapter().getCount() == 0 ){
                                        progressBar.setVisibility(View.GONE);
                                        noItemsGeneral.setVisibility(View.VISIBLE);

                                    }else{

                                    }



                                }
                            }, 5000);




                            adapter.registerDataSetObserver(new DataSetObserver() {



                                @Override
                                public void onChanged() {
                                    super.onChanged();
                                    // the first time you get here, hide the progress bar
                                    progressBar.setVisibility(View.GONE);
                                    noItemsGeneral.setVisibility(View.GONE);

                                }

                                @Override
                                public void onInvalidated() {
                                    super.onInvalidated();
                                }
                            });





                            listL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position,
                                                        long id) {


                                    TextView loserName =(TextView)view.findViewById(R.id.loserName);
                                    String nameLost = loserName.getText().toString();

                                    TextView loserContact =(TextView)view.findViewById(R.id.loserContact);
                                    String contactLost = loserContact.getText().toString();

                                    TextView loserDesc =(TextView)view.findViewById(R.id.loserDesc);
                                    String descLost = loserDesc.getText().toString();

                                    TextView loserWhere =(TextView)view.findViewById(R.id.loserWhere);
                                    String whereLost = loserWhere.getText().toString();

                                    TextView loserStat =(TextView)view.findViewById(R.id.loserStatusF);
                                    String statLost = loserStat.getText().toString();




                                    Intent intent = new Intent(getActivity(), DisplayLost.class);




                                    intent.putExtra("nameLost", nameLost);
                                    intent.putExtra("contactLost", contactLost);
                                    intent.putExtra("descLost", descLost);
                                    intent.putExtra("whereLost", whereLost);
                                    intent.putExtra("LostOrFound", statLost);
                                    startActivity(intent);

                                    getActivity().finish();




                                }
                            });
                        } else if (parent.getItemIdAtPosition(pos) == 1) {


                            progressBar.setVisibility(View.VISIBLE);
                            listF.setVisibility(View.VISIBLE);
                            listL.setVisibility(View.GONE);
                            noItemsGeneral.setVisibility(View.GONE);
                            FirebaseListAdapter<RegisterFound> adapter = new FirebaseListAdapter<RegisterFound>(getActivity(), RegisterFound.class,
                                    R.layout.found_items_lv, FirebaseDatabase.getInstance().getReference("Found Items").orderByChild("found_stat").equalTo("Not Claimed")) {

                                @Override
                                protected void populateView(View v, RegisterFound model, int position) {
                                    // Get references to the views of message.xml
                                    TextView foundName = (TextView) v.findViewById(R.id.founderName);
                                    TextView foundContact = (TextView) v.findViewById(R.id.founderContact);
                                    TextView foundDesc = (TextView) v.findViewById(R.id.founderDesc);
                                    TextView foundWhere = (TextView) v.findViewById(R.id.founderWhere);
                                    TextView messageTime = (TextView) v.findViewById(R.id.message_time);
                                    TextView foundStat =(TextView) v.findViewById(R.id.founderStatusF);


                                    // Set their text
                                    foundName.setText(model.getFound_name());
                                    foundContact.setText(model.getFound_contact());
                                    foundDesc.setText(model.getFound_desc());
                                    foundWhere.setText(model.getFound_where());
                                    foundStat.setText(model.getFound_statusFF());

                                    TextView claimedF = (TextView)v.findViewById(R.id.claimed);
                                    TextView unclaimedF = (TextView)v.findViewById(R.id.not_claimed);


                                    claimedF.setVisibility(View.GONE);
                                    unclaimedF.setVisibility(View.GONE);

                                    // Format the date before showing it
                                    messageTime.setText(DateFormat.format("MM-dd-yyyy (HH:mm:ss)",
                                            model.getMessageTime()));
                                }
                            };

                            listF.setAdapter(adapter);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if(listF.getAdapter().getCount() == 0){
                                        progressBar.setVisibility(View.GONE);
                                        noItemsGeneral.setVisibility(View.VISIBLE);
                                    }else{

                                    }



                                }
                            }, 5000);

                            adapter.registerDataSetObserver(new DataSetObserver() {
                                @Override
                                public void onChanged() {
                                    super.onChanged();
                                    // the first time you get here, hide the progress bar
                                    progressBar.setVisibility(View.GONE);
                                    noItemsGeneral.setVisibility(View.GONE);
                                }

                                @Override
                                public void onInvalidated() {
                                    super.onInvalidated();
                                }
                            });


                            listF.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position,
                                                        long id) {


                                    TextView founderName = (TextView) view.findViewById(R.id.founderName);
                                    String nameFound = founderName.getText().toString();

                                    TextView founderContact = (TextView) view.findViewById(R.id.founderContact);
                                    String contactFound = founderContact.getText().toString();

                                    TextView founderDesc = (TextView) view.findViewById(R.id.founderDesc);
                                    String descFound = founderDesc.getText().toString();

                                    TextView founderWhere = (TextView) view.findViewById(R.id.founderWhere);
                                    String whereFound = founderWhere.getText().toString();

                                    TextView founderStat =(TextView)view.findViewById(R.id.founderStatusF);
                                    String statFound = founderStat.getText().toString();


                                    Intent intent = new Intent(getActivity(), DisplayLost.class);


                                    intent.putExtra("nameLost", nameFound);
                                    intent.putExtra("contactLost", contactFound);
                                    intent.putExtra("descLost", descFound);
                                    intent.putExtra("whereLost", whereFound);
                                    intent.putExtra("LostOrFound", statFound);
                                    startActivity(intent);

                                    getActivity().finish();
                                }
                            });
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });










            }
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2)
            {


                rootView = inflater.inflate(R.layout.fragment_found_item, container, false);

                statusOfItemF = (TextView) (rootView.findViewById(R.id.statusFieldF));
                //statusOfItemF.setText("FOUND");
                foundName = (TextView)(rootView.findViewById(R.id.foundName));
                foundName.setMovementMethod(new ScrollingMovementMethod());
                foundName.setText(currentUserName);
                foundContact = (EditText)(rootView.findViewById(R.id.foundContact));
                foundContact.setText(userContact);
                //foundContact.setEnabled(false);
                foundDesc = (EditText)(rootView.findViewById(R.id.foundDesc));
                foundWhere = (EditText)(rootView.findViewById(R.id.foundWhere));
                foundStat = (TextView)(rootView.findViewById(R.id.foundStat));



            }
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                rootView = inflater.inflate(R.layout.fragment_lost_item, container, false);

                statusOfItemL = (TextView) (rootView.findViewById(R.id.statusFieldL));
                //statusOfItemL.setText("LOST");
                lostName = (TextView) (rootView.findViewById(R.id.lostName));
                lostName.setMovementMethod(new ScrollingMovementMethod());
                lostName.setText(currentUserName);
                lostContact = (EditText)(rootView.findViewById(R.id.lostContact));
                lostContact.setText(userContact);
                //lostContact.setEnabled(false);
                lostDesc = (EditText)(rootView.findViewById(R.id.lostDesc));
                lostWhere = (EditText)(rootView.findViewById(R.id.lostWhere));
                lostStat = (TextView)(rootView.findViewById(R.id.lostStat));

            }
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 4)
            {
                rootView = inflater.inflate(R.layout.fragment_personal_list, container, false);
                final ListView listFHis = (ListView)rootView.findViewById(R.id.list_of_found_items_history);
                final ListView listLHis = (ListView)rootView.findViewById(R.id.list_of_lost_items_history);

                final ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
                final TextView noItemsPersonal = (TextView)rootView.findViewById(R.id.noItems);



                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerA);


                ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(getContext(), R.array.main_dropdown, android.R.layout.simple_spinner_item);
                adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapters);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id ) {

                        if (parent.getItemIdAtPosition(pos) == 0) {

                            progressBar.setVisibility(View.VISIBLE);
                            listFHis.setVisibility(View.GONE);
                            listLHis.setVisibility(View.VISIBLE);
                            noItemsPersonal.setVisibility(View.GONE);




                            String userName = user.getDisplayName();
                            FirebaseListAdapter<RegisterLost> adapter = new FirebaseListAdapter<RegisterLost>(getActivity(), RegisterLost.class,
                                    R.layout.lost_items_lv, FirebaseDatabase.getInstance().getReference("Lost Items").orderByChild("lost_name").equalTo(userName)) {




                                @Override
                                protected void populateView(View v, RegisterLost model, int position) {
                                    // Get references to the views of message.xml
                                    TextView lostName = (TextView) v.findViewById(R.id.loserName);
                                    TextView lostContact = (TextView) v.findViewById(R.id.loserContact);
                                    TextView lostDesc = (TextView) v.findViewById(R.id.loserDesc);
                                    TextView lostWhere = (TextView) v.findViewById(R.id.loserWhere);
                                    TextView messageTime = (TextView) v.findViewById(R.id.message_time);
                                    TextView claimedLHis = (TextView)v.findViewById(R.id.claimed);
                                    TextView unclaimedLHis = (TextView)v.findViewById(R.id.not_claimed);
                                    TextView loserStat =(TextView)v.findViewById(R.id.loserStat);



                                    // Set their text
                                    lostName.setText(model.getLost_name());
                                    lostContact.setText(model.getLost_contact());
                                    lostDesc.setText(model.getLost_desc());
                                    lostWhere.setText(model.getLost_where());
                                    loserStat.setText(model.getLost_stat());

                                    String statLost = loserStat.getText().toString();

                                    if (statLost.contains("Not Claimed")){
                                        claimedLHis.setVisibility(View.GONE);
                                        unclaimedLHis.setVisibility(View.VISIBLE);
                                        //Toast.makeText(mActivity, "Not Claimed", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        claimedLHis.setVisibility(View.VISIBLE);
                                        unclaimedLHis.setVisibility(View.GONE);
                                        //Toast.makeText(mActivity, "Claimed", Toast.LENGTH_SHORT).show();
                                    }

                                    // Format the date before showing it
                                    messageTime.setText(DateFormat.format("MM-dd-yyyy (HH:mm:ss)",
                                            model.getMessageTime()));









                                }
                            };

                            listLHis.setAdapter(adapter);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if(listLHis.getAdapter().getCount() == 0){
                                        progressBar.setVisibility(View.GONE);
                                        noItemsPersonal.setVisibility(View.VISIBLE);
                                    }else{

                                    }



                                }
                            }, 5000);

                            adapter.registerDataSetObserver(new DataSetObserver() {
                                @Override
                                public void onChanged() {
                                    super.onChanged();
                                    // the first time you get here, hide the progress bar
                                    progressBar.setVisibility(View.GONE);
                                    noItemsPersonal.setVisibility(View.GONE);
                                }

                                @Override
                                public void onInvalidated() {
                                    super.onInvalidated();
                                }
                            });


                            listLHis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position,
                                                        long id) {


                                    TextView loserName =(TextView)view.findViewById(R.id.loserName);
                                    String nameLost = loserName.getText().toString();

                                    TextView loserContact =(TextView)view.findViewById(R.id.loserContact);
                                    String contactLost = loserContact.getText().toString();

                                    TextView loserDesc =(TextView)view.findViewById(R.id.loserDesc);
                                    String descLost = loserDesc.getText().toString();

                                    TextView loserWhere =(TextView)view.findViewById(R.id.loserWhere);
                                    String whereLost = loserWhere.getText().toString();

                                    TextView loserStatusF =(TextView)view.findViewById(R.id.loserStatusF);
                                    String statusFLost = loserStatusF.getText().toString();

                                    TextView loserStat =(TextView)view.findViewById(R.id.loserStat);
                                    String statLost = loserStat.getText().toString();


                                    Intent intent = new Intent(getActivity(), DisplayLostPersonal.class);




                                    intent.putExtra("nameLost", nameLost);
                                    intent.putExtra("contactLost", contactLost);
                                    intent.putExtra("descLost", descLost);
                                    intent.putExtra("whereLost", whereLost);
                                    intent.putExtra("LostOrFound", statusFLost);
                                    intent.putExtra("itemStat", statLost);
                                    startActivity(intent);

                                    getActivity().finish();



                                }
                            });
                        } else if (parent.getItemIdAtPosition(pos) == 1) {

                            progressBar.setVisibility(View.VISIBLE);
                            listFHis.setVisibility(View.VISIBLE);
                            listLHis.setVisibility(View.GONE);
                            noItemsPersonal.setVisibility(View.GONE);
                            String userName = user.getDisplayName();
                            FirebaseListAdapter<RegisterFound> adapter = new FirebaseListAdapter<RegisterFound>(getActivity(), RegisterFound.class,
                                    R.layout.found_items_lv, FirebaseDatabase.getInstance().getReference("Found Items").orderByChild("found_name").equalTo(userName)) {

                                @Override
                                protected void populateView(View v, RegisterFound model, int position) {
                                    // Get references to the views of message.xml
                                    TextView foundName = (TextView) v.findViewById(R.id.founderName);
                                    TextView foundContact = (TextView) v.findViewById(R.id.founderContact);
                                    TextView foundDesc = (TextView) v.findViewById(R.id.founderDesc);
                                    TextView foundWhere = (TextView) v.findViewById(R.id.founderWhere);
                                    TextView messageTime = (TextView) v.findViewById(R.id.message_time);
                                    TextView claimedFHis = (TextView)v.findViewById(R.id.claimed);
                                    TextView unclaimedFHis = (TextView)v.findViewById(R.id.not_claimed);
                                    TextView foundStat =(TextView)v.findViewById(R.id.founderStat);

                                    // Set their text
                                    foundName.setText(model.getFound_name());
                                    foundContact.setText(model.getFound_contact());
                                    foundDesc.setText(model.getFound_desc());
                                    foundWhere.setText(model.getFound_where());
                                    foundStat.setText(model.getFound_stat());

                                    String statFound = foundStat.getText().toString();

                                    if (statFound.contains("Not Claimed")){
                                        claimedFHis.setVisibility(View.GONE);
                                        unclaimedFHis.setVisibility(View.VISIBLE);
                                        //Toast.makeText(mActivity, "Not Claimed", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        claimedFHis.setVisibility(View.VISIBLE);
                                        unclaimedFHis.setVisibility(View.GONE);
                                        //Toast.makeText(mActivity, "Claimed", Toast.LENGTH_SHORT).show();
                                    }

                                    // Format the date before showing it
                                    messageTime.setText(DateFormat.format("MM-dd-yyyy (HH:mm:ss)",
                                            model.getMessageTime()));
                                }
                            };

                            listFHis.setAdapter(adapter);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if(listFHis.getAdapter().getCount() == 0){
                                        progressBar.setVisibility(View.GONE);
                                        noItemsPersonal.setVisibility(View.VISIBLE);
                                    }else{


                                    }



                                }
                            }, 5000);

                            adapter.registerDataSetObserver(new DataSetObserver() {
                                @Override
                                public void onChanged() {
                                    super.onChanged();
                                    // the first time you get here, hide the progress bar
                                    progressBar.setVisibility(View.GONE);
                                    noItemsPersonal.setVisibility(View.GONE);
                                }

                                @Override
                                public void onInvalidated() {
                                    super.onInvalidated();
                                }
                            });



                            listFHis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position,
                                                        long id) {


                                    TextView founderName = (TextView) view.findViewById(R.id.founderName);
                                    String nameFound = founderName.getText().toString();

                                    TextView founderContact = (TextView) view.findViewById(R.id.founderContact);
                                    String contactFound = founderContact.getText().toString();

                                    TextView founderDesc = (TextView) view.findViewById(R.id.founderDesc);
                                    String descFound = founderDesc.getText().toString();

                                    TextView founderWhere = (TextView) view.findViewById(R.id.founderWhere);
                                    String whereFound = founderWhere.getText().toString();

                                    TextView founderStatusF =(TextView)view.findViewById(R.id.founderStatusF);
                                    String statusFFound = founderStatusF.getText().toString();

                                    TextView founderStat =(TextView)view.findViewById(R.id.founderStat);
                                    String statFound = founderStat.getText().toString();


                                    Intent intent = new Intent(getActivity(), DisplayLostPersonal.class);


                                    intent.putExtra("nameLost", nameFound);
                                    intent.putExtra("contactLost", contactFound);
                                    intent.putExtra("descLost", descFound);
                                    intent.putExtra("whereLost", whereFound);
                                    intent.putExtra("LostOrFound", statusFFound);
                                    intent.putExtra("itemStat", statFound);
                                    startActivity(intent);

                                    getActivity().finish();
                                }
                            });
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

            }
            return rootView;
        }

        public void onPause()
        {
            super.onPause();
        }

        public void onResume()
        {
            super.onResume();

        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "View Lost and Found Items";
                case 1:
                    return  "Found an Item";
                case 2:
                    return "Lost an Item";
                case 3:
                    return "Find History";
            }
            return null;
        }
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







}

