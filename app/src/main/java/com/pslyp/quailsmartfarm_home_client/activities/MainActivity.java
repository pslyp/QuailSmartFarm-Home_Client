package com.pslyp.quailsmartfarm_home_client.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pslyp.quailsmartfarm_home_client.R;
import com.pslyp.quailsmartfarm_home_client.fragments.HomeFragment;
import com.pslyp.quailsmartfarm_home_client.fragments.MeFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private HomeFragment homeFragment = new HomeFragment();
    private MeFragment meFragment = new MeFragment();

    private BottomNavigationView navView;

    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    private void initInstance() {
        toolbarText = findViewById(R.id.text_toolbar);
        toolbarText.setText("Home");

        loadFragment(homeFragment);

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home :
                toolbarText.setText("Home");
                loadFragment(homeFragment);
                return true;
            case R.id.nav_me :
                toolbarText.setText("Me");
                loadFragment(meFragment);
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }
}
