package com.pslyp.quailsmartfarm_home_client.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pslyp.quailsmartfarm_home_client.R;
import com.pslyp.quailsmartfarm_home_client.fragments.HomeFragment;
import com.pslyp.quailsmartfarm_home_client.fragments.MeFragment;
import com.pslyp.quailsmartfarm_home_client.services.Prefs;

public class MainActivity extends AppCompatActivity {

//    private HomeFragment homeFragment = new HomeFragment();
//    private MeFragment meFragment = new MeFragment();
//
//    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.configs:
                break;
            case R.id.sign_out:
                signOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initInstance() {
//        loadFragment(homeFragment);

//        navView = findViewById(R.id.nav_view);
//        navView.setOnNavigationItemSelectedListener(this);
    }

    Toolbar.OnMenuItemClickListener menuClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();

            switch(id) {
                case R.id.configs:
                    break;
                case R.id.sign_out:
                    signOut();
                    break;
            }

            return false;
        }
    };

    private void signOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.want_sign_out)
                .setPositiveButton(R.string.sign_out, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Prefs prefs = new Prefs.Builder(getApplicationContext())
                                .name("Authen")
                                .mode(MODE_PRIVATE)
                                .build();

                        prefs.clear();

                        startActivity(new Intent(MainActivity.this, StartActivity.class));
                        finish();

                        Toast.makeText(MainActivity.this, "Sign out", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        builder.show();
    }

    private void initToolbar() {
        Prefs prefProfile = new Prefs.Builder(getApplicationContext())
                .name("Profile")
                .mode(MODE_PRIVATE)
                .build();
    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case R.id.nav_home :
//                toolbarText.setText("Home");
//                loadFragment(homeFragment);
//                return true;
//            case R.id.nav_me :
//                toolbarText.setText("Me");
//                loadFragment(meFragment);
//                return true;
//        }
//        return false;
//    }

//    private void loadFragment(Fragment fragment) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_container, fragment);
////        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}
