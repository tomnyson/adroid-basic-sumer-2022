package com.example.android_summer_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android_summer_2022.DTO.PassData;

public class DemoFragment extends AppCompatActivity implements PassData {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment);
        Button btnFragement1 = findViewById(R.id.btnfragment1);
        Button btnFragement2 = findViewById(R.id.btnfragment2);
        btnFragement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                FragmentOne fragmentOne = new FragmentOne();
                FragmentTwo fragmentTwo = new FragmentTwo();
                // chuyền dữ liệu đến fragment
                Bundle bundle = new Bundle();
                bundle.putString("username", "test here");
                fragmentOne.setArguments(bundle);

                fragmentTransaction.remove(fragmentTwo)
                        .add(R.id.containerFragement,fragmentOne)
                        .commit();
            }
        });

        btnFragement2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                FragmentOne fragmentOne = new FragmentOne();
                FragmentTwo fragmentTwo = new FragmentTwo();

                Bundle bundle = new Bundle();
                bundle.putString("username", "test here");
                fragmentTwo.setArguments(bundle);

                fragmentTransaction.remove(fragmentOne)
                        .add(R.id.containerFragement,fragmentTwo)
                        .commit();
            }
        });
    }

    @Override
    public void sendMessage(String message) {
        Log.i("TEST", "trigger here");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        FragmentOne fragone = new FragmentOne();

        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        fragone.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainerOne,fragone)
                .commit();
    }
}
