package com.example.android_summer_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultActivity extends AppCompatActivity {
    private TextView tvketQua;
    private Button btnBack;

    // kiểm tra chẵn lẻ

    public boolean isChan(int n) {
        if(n % 2 == 0) return  true;
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvketQua = findViewById(R.id.tvKetQua);
        btnBack = findViewById(R.id.bntBack);
        // lấy data được truyền qua
        Intent intent = getIntent();
        if(intent != null) {
            int  ketqua = intent.getExtras().getInt("ketqua");
            if(isChan(ketqua)) {
                tvketQua.setText("Kết quả là: " + ketqua + " là số chẵn");
            } else {
                tvketQua.setText("Kết quả là: " + ketqua + " là số lẻ");
            }

        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), constraintLayoutActivity.class);
                startActivity(i);
            }
        });
    }
}
