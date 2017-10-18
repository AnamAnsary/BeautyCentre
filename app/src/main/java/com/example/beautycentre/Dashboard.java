package com.example.beautycentre;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstUsers;

import java.util.List;

import static com.example.beautycentre.AddBranch.FRAGMENT_B;
import static com.example.beautycentre.AddProduct.FRAGMENT_P;
import static com.example.beautycentre.AddSalon.FRAGMENT_S;
import static com.example.beautycentre.AddTransaction.FRAGMENT_I;


public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Dashboard";

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String ID = "idKey";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Pwd = "pwdKey";
    public static final String Gender = "genderKey";
    SharedPreferences sharedpreferences;
    boolean flag = true;

    //MstUsers mstUsers;
    NavigationView navigationView;
    FrameLayout fragDash;
    DrawerLayout drawer;
    TextView welcome,user_email;
    FloatingActionButton fab;
    String intentFragment;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString(Name,null);
        String email1 = sharedpreferences.getString(Email,null);

        if((email1 == null) && (name == null))
        {
            Intent intent = new Intent(Dashboard.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      /*  intent.putExtra("email",mstUser.getEmail());
                        intent.putExtra("fullname",mstUser.getFullname());*/
            startActivity(intent);
            finish();
        }

       /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.nav_name);
        nav_user.setText(user);
        */

       /* View hdView =  navigationView.getChildAt(0);
        fragDash = (FrameLayout) hdView.findViewById(R.id.content_frame);
        DashboardFragment battlistfragment = new DashboardFragment();
        transaction.add(R.id.content_frame,battlistfragment);
        transaction.addToBackStack(null);
        transaction.commit();*/

       /*//Code finally added
        Fragment fragmentMain = new HomePage();
        FragmentTransaction mainft = getSupportFragmentManager().beginTransaction();
        Log.w(TAG, "onCreate: Main frag to add");
        mainft.add(R.id.content_frame, fragmentMain,"Dashboard_Frag");
        //mainft.addToBackStack(null);
        mainft.commit();*/


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
*/
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                String tag = currentFragment.getTag();
                switch (tag) {
                    case "Product":
                        //Snackbar.make(view, "Product", Snackbar.LENGTH_LONG).show();
                        Intent  i = new Intent(Dashboard.this,AddProduct.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                        break;
                    case "Salon":
                        Intent  i2 = new Intent(Dashboard.this,AddSalon.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i2);
                        finish();
                        //Snackbar.make(view, "Salon", Snackbar.LENGTH_LONG).show();
                        break;
                    case "Branch":
                        Intent  i3 = new Intent(Dashboard.this,AddBranch.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i3);
                        finish();
                        break;
                    case "Inventory":
                        Intent  i4 = new Intent(Dashboard.this,AddTransaction.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i4);
                        finish();
                        break;
                    default:
                        Log.e(TAG, "Unhandled FAB fragment tag " + tag);
                        Snackbar.make(view, "Not sure what to do...", Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View hdView =  navigationView.getHeaderView(0);
        welcome = (TextView) hdView.findViewById(R.id.welcomename);
        user_email = (TextView) hdView.findViewById(R.id.user_email);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            hdView.findViewById(R.id.imageView).setClipToOutline(true);
        }

       /* // email
        String email = user.get(SessionManager.KEY_EMAIL);
        */

        welcome.setText("Welcome " +name);
        user_email.setText(email1);

        //Bundle extras = this.getIntent().getExtras();

        if(getIntent().getExtras() != null) {
            intentFragment = getIntent().getExtras().getString("frgToLoad");
            Log.w(TAG, "onCreate: intentfragment is "+intentFragment);
            Fragment fragment = null;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(intentFragment != null) {
                switch (intentFragment) {
                    case FRAGMENT_P:
                        fragment = new Product();
                        Log.w(TAG, "onCreate: Product frag to add");
                        ft.replace(R.id.content_frame, fragment, "Product");
                        //navigationView.getMenu().findItem(R.id.nav_product).setChecked(true);
                        //navigationView.getMenu().getItem(1).setChecked(true);
                        break;
                    case FRAGMENT_S:
                        // Load corresponding fragment
                        fragment = new Salon();
                        Log.w(TAG, "onCreate: Salon frag to add");
                        ft.replace(R.id.content_frame, fragment, "Salon");
                        //navigationView.getMenu().findItem(R.id.nav_salon).setChecked(true);
                        break;
                    case FRAGMENT_B:
                        // Load corresponding fragment
                        fragment = new Branch();
                        Log.w(TAG, "onCreate: Branch frag to add");
                        ft.replace(R.id.content_frame, fragment, "Branch");
                        //navigationView.getMenu().findItem(R.id.nav_branches).setChecked(true);
                        break;
                    case FRAGMENT_I:
                        // Load corresponding fragment
                        fragment = new Inventory();
                        Log.w(TAG, "onCreate: Inventory frag to add");
                        ft.replace(R.id.content_frame, fragment, "Inventory");
                        //navigationView.getMenu().findItem(R.id.nav_inventory).setChecked(true);
                        Log.w(TAG, "Transaction selected");
                        break;
           /* case FRAGMENT_C:
                // Load corresponding fragment
                break;*/
                    default:
                        fragment = new Product();
                        Log.w(TAG, "onCreate: Product frag to add");
                        ft.replace(R.id.content_frame, fragment, "Product");
                        //navigationView.getMenu().findItem(R.id.nav_product).setChecked(true);
                        break;
                }
               /* getIntent().getExtras().remove("frgToLoad");
                Log.w(TAG, "After just remove value is "+getIntent().getExtras().getString("frgToLoad") );*/
                getIntent().removeExtra("frgToLoad");
               //  Log.w(TAG, "After remove extra value is "+getIntent().getExtras().getString("frgToLoad") );
               // ft.addToBackStack(null);
                fab.setVisibility(View.VISIBLE);
                flag = false;
                ft.commit();

            }
            else
                //add this line to display menu1 when the activity is loaded
                displaySelectedScreen(R.id.nav_dashboard);

        }
        else {
            //getIntent().removeExtra("frgToLoad");
            //add this line to display menu1 when the activity is loaded
            displaySelectedScreen(R.id.nav_dashboard);
        }
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (flag==true){
            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.CustomAlertDialog);
            //builder.setTitle("Exit");
            //builder.setCancelable(false);
            builder.setMessage("Do you really want to exit?");

            // add the buttons
            builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });

            builder.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.setNeutralButton("Log out and Exit",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    dialog.dismiss();
                    finish();
                    //Toast.makeText(Dashboard.this,"You clicked logout n exit",Toast.LENGTH_LONG).show();
                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            /*if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please press again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 4000);*/
            //showAlertDialogButtonClicked(navigationView);

        }
        else if (flag == false) {
            finish();
            startActivity(getIntent());
        }

    }

   /* public void showAlertDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Do you really want to exit?");

        // add the buttons
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                super.onBackPressed();

            }
        });
        builder.setNeutralButton("Log out and Exit", null);
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.CustomAlertDialog);
            builder.setTitle("Log Out");
            builder.setMessage("Do you really want to log out?");
            // add the buttons
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent  i2 = new Intent(Dashboard.this,LoginActivity.class);
                    SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i2);
                    finish();
                }
            });
            builder.setNegativeButton("No",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

       /* // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_product) {
            // Handle the camera action
        } else if (id == R.id.nav_salon) {

        } else if (id == R.id.nav_branches) {

        } else if (id == R.id.nav_inventory) {

        } else if (id == R.id.nav_changepwd) {

        } else if (id == R.id.nav_logout) {
            Intent i = new Intent(Dashboard.this,LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/

        displaySelectedScreen(item.getItemId());
        return true;
    }


    private void displaySelectedScreen(int itemId) {

      /*  intentFragment = getIntent().getExtras();
        Log.w(TAG, "onCreate: intentfragment is "+intentFragmen

        if (intentFragment != null) {
            itemId = Integer.parseInt(intentFragment.getString("frgToLoad"));
        }*/

        //creating fragment object
        Fragment fragment = null;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_dashboard:
                fab.setVisibility(View.INVISIBLE);
                fragment = new HomePage();
                ft.replace(R.id.content_frame, fragment,"Dashboard_Frag");
                navigationView.setCheckedItem(R.id.nav_dashboard);
                //navigationView.getMenu().getItem(0).setChecked(true);
                break;
            case R.id.nav_product:
                fragment = new Product();
                ft.replace(R.id.content_frame, fragment,"Product");
                navigationView.setCheckedItem(R.id.nav_product);
                //navigationView.getMenu().findItem(R.id.nav_product).setChecked(true);
                break;
            case R.id.nav_salon:
                fragment = new Salon();
                ft.replace(R.id.content_frame, fragment,"Salon");
                navigationView.setCheckedItem(R.id.nav_salon);
                //navigationView.getMenu().findItem(R.id.nav_salon).setChecked(true);
                break;
            case R.id.nav_branches:
                fragment = new Branch();
                ft.replace(R.id.content_frame, fragment,"Branch");
                navigationView.setCheckedItem(R.id.nav_branches);
                //navigationView.getMenu().findItem(R.id.nav_branches).setChecked(true);
                break;
            case R.id.nav_inventory:
                fragment = new Inventory();
                ft.replace(R.id.content_frame, fragment,"Inventory");
                navigationView.setCheckedItem(R.id.nav_inventory);
                //navigationView.getMenu().findItem(R.id.nav_inventory).setChecked(true);
                break;
            case R.id.nav_stockAlert:
                fab.setVisibility(View.INVISIBLE);
                fragment = new StockAlert();
                ft.replace(R.id.content_frame, fragment,"StockAlert");
                navigationView.setCheckedItem(R.id.nav_stockAlert);
                //navigationView.getMenu().findItem(R.id.nav_stockAlert).setChecked(true);
                Log.w(TAG, "StockAlert selected");
                break;
            case R.id.nav_changepwd:
                Intent  i = new Intent(Dashboard.this,ChangePassword.class);
                startActivity(i);
                break;
            case R.id.nav_logout:

                AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.CustomAlertDialog);
                builder.setTitle("Log Out");
                builder.setMessage("Do you really want to log out?");
                // add the buttons
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent  i2 = new Intent(Dashboard.this,LoginActivity.class);
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.clear();
                        editor.commit();
                        i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i2);
                        finish();
                    }
                });
                builder.setNegativeButton("No",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            default:
                fragment = new HomePage();
                ft.replace(R.id.content_frame, fragment,"Dashboard_Frag");
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            if(fragment.getTag() == "Dashboard_Frag") {
                fab.setVisibility(View.INVISIBLE);
                flag = true;
            }
            else if(fragment.getTag() == "StockAlert") {
                fab.setVisibility(View.INVISIBLE);
                flag = false;
            }
            else {
                fab.setVisibility(View.VISIBLE);
                flag = false;
            }
            ft.commit();
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


}
