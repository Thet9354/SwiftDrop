package com.example.swiftdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.swiftdrop.fragments.Cart;
import com.example.swiftdrop.fragments.Categories;
import com.example.swiftdrop.fragments.Profile;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private com.google.android.material.bottomnavigation.BottomNavigationView bottomNav;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.cart:
                        replaceFragment(new Cart());
                        break;

                    case R.id.profile:
                        replaceFragment(new Profile());
                        break;

                    case R.id.home:

                    default:
                        replaceFragment(new Categories());
                        break;
                }

                return true;
            }
        });
    }

    private void initWidget() {
        bottomNav = findViewById(R.id.bottomNav);
        frameLayout = findViewById(R.id.frameLayout);

        replaceFragment(new Categories());

    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}