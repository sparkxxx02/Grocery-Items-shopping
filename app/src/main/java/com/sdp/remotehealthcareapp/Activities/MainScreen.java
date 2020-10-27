package com.sdp.remotehealthcareapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.sdp.remotehealthcareapp.Fragments.CartFragment;
import com.sdp.remotehealthcareapp.Fragments.MainActivity;
import com.sdp.remotehealthcareapp.Fragments.ProfileFragment;
import com.sdp.remotehealthcareapp.Fragments.Selected_Category.description.DescriptionFragment;
import com.sdp.remotehealthcareapp.R;

import java.util.Objects;

public class MainScreen extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private Fragment selectorFragment;
    CoordinatorLayout coordinatorLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView user;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        getDrawer_started();

    }
    private void getDrawer_started()
    {

        drawerLayout= findViewById(R.id.drawerlayout);
        coordinatorLayout= findViewById(R.id.coordinator);
        toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("Hua kya?");
        navigationView= findViewById(R.id.navigation);
        setUpToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sc,
                new MainActivity()).addToBackStack("MainScreen").commit();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getDisplayname();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectorFragment = new MainActivity();
                if (item.getItemId() == R.id.menu_logout) {
                    //finish();
                        /*sharedPreferences= getSharedPreferences(getString(R.string.preference_file_name), MODE_PRIVATE);
                        sharedPreferences.edit().putBoolean("isLoggedin", false).apply();*/
                    startActivity(new Intent(getApplicationContext(), Signin.class));
                    finish();
                } else if (item.getItemId() == R.id.menu_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sc,
                            new MainActivity()).addToBackStack("Home").commit();

                } else if (item.getItemId() == R.id.menu_cart) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sc,
                            new CartFragment()).addToBackStack("Cart").commit();
                } else if (item.getItemId() == R.id.menu_profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sc,
                            new ProfileFragment()).addToBackStack("Home").commit();


                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sc,selectorFragment).addToBackStack("Profile Setup").commit();
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
    private void setUpToolbar() {
        //setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        (getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void getDisplayname(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.user_name);
        navUsername.setText(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName());
    }
}
