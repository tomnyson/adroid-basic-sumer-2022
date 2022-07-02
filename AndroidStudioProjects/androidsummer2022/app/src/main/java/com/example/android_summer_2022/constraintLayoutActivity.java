package com.example.android_summer_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class constraintLayoutActivity extends AppCompatActivity {
    private EditText edSoThu1, edSoThu2;
    private Button btnCong, btnTru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        /**
         * TODO: findViewById -> ánh xa;
         * 1: handle event -> bắt su kien khi han button cong, tru
         */
        edSoThu1 = findViewById(R.id.edSoNhuNhat);
        edSoThu2 = findViewById(R.id.edSoThu2);
        btnCong =  findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        // cong event
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int result = Integer.parseInt(edSoThu1.getText().toString()) +
                            Integer.parseInt(edSoThu2.getText().toString());
                    // chuyển màn hình
                    Intent intent = new Intent(getBaseContext(), resultActivity.class);
                    // gửi kèm dữ liệu
                    intent.putExtra("ketqua", result);
                    startActivity(intent);

                    Log.i("Info", "kết quả là: " + result);
                    Toast.makeText(constraintLayoutActivity.this, "kết quả là: " + result, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(constraintLayoutActivity.this, "dữ liệu phải là số ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int result = Integer.parseInt(edSoThu1.getText().toString()) -
                            Integer.parseInt(edSoThu2.getText().toString());
                    // chuyển màn hình
                    Intent intent = new Intent(getBaseContext(), resultActivity.class);
                    // gửi kèm dữ liệu
                    intent.putExtra("ketqua", result);
                    startActivity(intent);

                    Log.i("Info", "kết quả là: " + result);
                    Toast.makeText(constraintLayoutActivity.this, "kết quả là: " + result, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(constraintLayoutActivity.this, "dữ liệu phải là số ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
