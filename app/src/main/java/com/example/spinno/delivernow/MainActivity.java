package com.example.spinno.delivernow;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialize.util.UIUtils;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;
    Fragment fragment = null;
    public static String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;       //String Resource for header view email

    public static Context ctc2;

    public static Context ctc;
    private static String mCurrentPhotoPath;
    public static String NAME = "Apporio Singh";
    public static String EMAIL = "apporio@abc.com";
    public static int PROFILE = R.drawable.cap;
    private Toolbar toolbar;                              // Declaring the Toolbar Object

    public static RecyclerView mRecyclerView;                           // Declaring RecyclerView
    public static RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    public static RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    public static DrawerLayout Drawer;
    public static MainActivity mainact;

    public static String TITLES[] = {"Home", "History", "Doorman Address", "Profile","Payment","Invite Friends","Help"};
    public static int ICONS[] = {
            R.drawable.home, R.drawable.history,
            R.drawable.address, R.drawable.profile,   R.drawable.payment, R.drawable.share,
            R.drawable.help};
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    public static Button cancel2;

    View layout, layout2;
    public static TextView text, textforschedule, cancel, confirm, text22, cabtype, couponsavailable;
    Typeface font;
    Bitmap bitmap1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Doorman");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        // toolbar.setNavigationIcon(R.drawable.cap);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.menuw);

//        setSupportActionBar(toolbar);
        ctc = getApplicationContext();
        mainact = MainActivity.this;
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.download);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, Drawer, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        };

        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Drawer.openDrawer(Gravity.LEFT);
            }
        });

        //cd = new ConnectionDetector(ctc);
        //gps= new GPStracker(ctc);

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = MainActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = MainActivity.this.getWindow();
           // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
           // window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));
        }
    }
    @Override
    public void onResume() {

        super.onResume();
        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

            SharedPreferences.Editor edit2 = prefs.edit();
            edit2.putBoolean("pref_previously_started", Boolean.FALSE);
            edit2.commit();
            Intent in = new Intent(MainActivity.this,Logregactivity.class);
            startActivity(in);
            MainActivity.this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        // Creating a ViewHolder which extends the RecyclerView View Holder
        // ViewHolder are used to to store the inflated views in order to recycle them

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            int Holderid;

            TextView textView;
            ImageView imageView;
            public ImageView profile11;
            TextView Name;
            TextView email;
            LinearLayout itemll, llforprof;

            public ViewHolder(View itemView, int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
                super(itemView);


                // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

                if (ViewType == TYPE_ITEM) {
                    textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                    imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                    itemll = (LinearLayout) itemView.findViewById(R.id.llfornavi);
                    itemll.setOnClickListener(this);
                    // Creating ImageView object with the id of ImageView from item_row.xml
                    Holderid = 1;

                    // setting holder id as 1 as the object being populated are of type item row
                } else {
                    Typeface font = Typeface.createFromAsset(ctc.getAssets(), "fonts/AllerDisplay.ttf");

                    Name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                    email = (TextView) itemView.findViewById(R.id.email);       // Creating Text View object from header.xml for email
                    Name.setTypeface(font);
                    email.setTypeface(font);
                    profile11 = (ImageView) itemView.findViewById(R.id.circleView);
                    llforprof = (LinearLayout) itemView.findViewById(R.id.llforprofile);
                    profile11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //MainActivity.Drawer.closeDrawer(Gravity.LEFT);
                            //  showcamerdialog();
                        }
                    });
                    llforprof.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent in = new Intent(ctc2, Profileactivity.class);
//                            ctc2.startActivity(in);
                        }
                    });

                    // Creating Image view object from header.xml for profile pic
                    Holderid = 0;
                }
            }


            @Override
            public void onClick(View v) {
                try {
                    if (mNavTitles[getPosition() - 1].equals("Home")) {
//                        fragment = new Mainfragment();
//                        if (fragment != null) {
//                            FragmentManager fragmentManager = getFragmentManager();
//                            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
//
//                            Drawer.closeDrawer(Gravity.LEFT);
//
//                        } else {
//                            Log.e("MainActivity", "Error in creating fragment");
//                        }

//                    Intent in = new Intent(ctc2, Emergencyactivity.class);
//                    ctc2.startActivity(in);
                    } else if (mNavTitles[getPosition() - 1].equals("History")) {

//                        fragment = new CreateFragment();
//                        if (fragment != null) {
//                            FragmentManager fragmentManager = getFragmentManager();
//                            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
//
//                            Drawer.closeDrawer(Gravity.LEFT);
//
//                        } else {
//                            Log.e("MainActivity", "Error in creating fragment");
//                        }


//                    Intent in = new Intent(ctc2, Aboutactivity.class);
//                    ctc2.startActivity(in);
                    }
                    else if (mNavTitles[getPosition() - 1].equals("Doorman Address")) {

//                        fragment = new AddressFragment();
//                        if (fragment != null) {
//                            FragmentManager fragmentManager = getFragmentManager();
//                            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
//
//                            Drawer.closeDrawer(Gravity.LEFT);
//
//                        } else {
//                            Log.e("MainActivity", "Error in creating fragment");
//                        }


//                    Intent in = new Intent(ctc2, Aboutactivity.class);
//                    ctc2.startActivity(in);
                    }
                    else if (mNavTitles[getPosition() - 1].equals("Help")) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "abc@gmail.com", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report Issue Regarding Delivery");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));

                        emailIntent.setType("text/plain");
                    }

//                 else if (mNavTitles[getPosition() - 1].equals("Call Support")) {
//                    String posted_by = "0000000000";
//                    String uri = "tel:" + posted_by.trim();
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    intent.setData(Uri.parse(uri));
//                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for Activity#requestPermissions for more details.
//                        return;
//                    }
//                    else {
//                        startActivity(intent);
//                    }
//                }
                    else{
                        //
                    }
                }catch (Exception e){
                    Log.e("ddddd",""+e);
                }
            }

        }



        MyAdapter(MainActivity mainActivity, String Titles[], int Icons[], String Name, String Email, int Profile){ // MyAdapter Constructor with titles and icons parameter
            // titles, icons, name, email, profile pic are passed from the main activity as we
            mNavTitles = Titles;                //have seen earlier
            mIcons = Icons;
            name = Name;
            email = Email;
            profile = Profile;
            ctc2= mainActivity;
            //here we assign those passed values to the values we declared here
            //in adapter


        }



        //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
        //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
        // if the viewType is TYPE_HEADER
        // and pass it to the view holder

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false); //Inflating the layout

                ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhItem; // Returning the created object

                //inflate your layout and pass it to view holder

            } else if (viewType == TYPE_HEADER) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

                ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhHeader; //returning the object created


            }
            return null;

        }

        //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
        // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
        // which view type is being created 1 for item row
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
                // position by 1 and pass it to the holder while setting the text and image
                holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
                holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
            }
            else{

                holder.profile11.setImageBitmap(bitmap1);           // Similarly we set the resources for header view
                holder.Name.setText(name);
                holder.email.setText(email);

            }

        }

        // This method returns the number of items present in the list
        @Override
        public int getItemCount() {
            return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
        }


        // Witht the following method we check what type of view is being passed
        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;

            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

    }
}
