package com.apahayo.tubespabpaim;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavBotActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private KegiatanFragment kegiatanFragment;
    private MoodFragment moodFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bot);

        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);

        kegiatanFragment = new  KegiatanFragment();
        moodFragment = new MoodFragment();

        setFragment(kegiatanFragment);


        mMainNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.nav_kegiatan:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(kegiatanFragment);

                    case R.id.nav_mood:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(moodFragment);

                        default:
                }
            }

            private void setFragment(Fragment fragment) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.commit();
            }

        });
    }

    private void setFragment(KegiatanFragment kegiatanFragment) {
    }

}

