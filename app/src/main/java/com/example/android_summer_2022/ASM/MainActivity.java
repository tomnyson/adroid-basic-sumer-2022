package com.example.android_summer_2022.ASM;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android_summer_2022.Adapter.ViewPagerAdapter;
import com.example.android_summer_2022.LoginActivity;
import com.example.android_summer_2022.R;
import com.example.android_summer_2022.Utils.ZoomOutPageTransformer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Button btnThemSV;
    private BottomNavigationView homeBottom;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        viewPager2 = findViewById(R.id.mainViewPager);
        homeBottom = findViewById(R.id.bottomHome);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_supervised_user_circle_24);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close
                );
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                 switch (position) {
                     case 0:
                         homeBottom.getMenu().getItem(0).setChecked(true);
                         break;
                     case 1:
                         homeBottom.getMenu().getItem(1).setChecked(true);
                     case 2:
                         homeBottom.getMenu().getItem(2).setChecked(true);
                         break;

                 }
            }
        });
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        homeBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btt_home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.btt_product:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.btt_cat:
                        viewPager2.setCurrentItem(2);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_dangxuat) {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.menu_thongtin) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = MainActivity.this.getLayoutInflater();
            // nhúng layout vào dialog alert
            View v = inflater.inflate(R.layout.layout_introduction, null);
            builder.setTitle("thông tin sản phẩm");
            builder.setView(v);
            builder.setPositiveButton("OK",(dialog,with)->{
                Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
            });
            builder.setNegativeButton("Cancel",(dialog, with)->{
                dialog.cancel();
            });
            builder.show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
