package com.example.beautycentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautycentre.DatabaseTables.MstUsers;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Dashboard";
    //MstUsers mstUsers;
    NavigationView navigationView;
    DrawerLayout drawer;
    TextView welcome,user_email;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.nav_name);
        nav_user.setText(user);
        */

      /*  Bundle i = getIntent().getExtras();

        Log.w(TAG, "onCreate: "+i.getString("fullname")+" "+i.getString("email"));*/

        String username = MstUsers.getFullname();
        String email = MstUsers.getEmail();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
                        startActivity(i);
                        finish();
                        break;
                    case "Salon":
                        Snackbar.make(view, "Salon", Snackbar.LENGTH_LONG).show();
                        break;
                    case "Branch":
                        Snackbar.make(view, "Branch", Snackbar.LENGTH_LONG).show();
                        break;
                    case "Inventory":
                        Snackbar.make(view, "Inventory", Snackbar.LENGTH_LONG).show();
                        break;
                    default:
                        Log.e(TAG, "Unhandled FAB fragment tag " + tag);
                        Snackbar.make(view, "Not sure what to do...my bad", Snackbar.LENGTH_SHORT).show();
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
        welcome.setText("Welcome "+username);
        user_email.setText(email);

        //add this line to display menu1 when the activity is loaded
        displaySelectedScreen(R.id.nav_product);

    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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
        if (id == R.id.action_settings) {
            return true;
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

        //creating fragment object
        Fragment fragment = null;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_product:
                fragment = new Product();
                ft.replace(R.id.content_frame, fragment,"Product");
                break;
            case R.id.nav_salon:
                fragment = new Salon();
                ft.replace(R.id.content_frame, fragment,"Salon");
                break;
            case R.id.nav_branches:
                fragment = new Branch();
                ft.replace(R.id.content_frame, fragment,"Branch");
                break;
            case R.id.nav_inventory:
                fragment = new Inventory();
                ft.replace(R.id.content_frame, fragment,"Inventory");
                break;
            case R.id.nav_changepwd:
                Intent  i = new Intent(Dashboard.this,CreateTable.class);
                startActivity(i);
                break;
            case R.id.nav_logout:
                Intent  i2 = new Intent(Dashboard.this,LoginActivity.class);
                startActivity(i2);
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            ft.commit();
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
