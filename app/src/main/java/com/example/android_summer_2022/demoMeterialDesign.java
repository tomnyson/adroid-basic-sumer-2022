package com.example.android_summer_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class demoMeterialDesign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_meterial_design);
//        Button btn = findViewById(R.id.btnSnackBar);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TextInputLayout txtWrapText = findViewById(R.id.txtWrapName);
//                txtWrapText.setError("tên bắt buộc");
//
//                Snackbar.make(findViewById(R.id.layoutRoot),"Are you you", 2000)
//                .setActionTextColor(Color.RED)
//                .setAction("Ok", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.i("TEST", "Call OK");
//                    }
//                }).show();
//            }
//        });
        FloatingActionButton btnButton = findViewById(R.id.floatingActionButton2);
        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(demoMeterialDesign.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

        //
        BottomNavigationView navBottom = findViewById(R.id.navBottom);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        Toast.makeText(demoMeterialDesign.this, "HOME BACK", Toast.LENGTH_SHORT).show();
                        break;
                }
                return  false;
            }
        });
    }
}
