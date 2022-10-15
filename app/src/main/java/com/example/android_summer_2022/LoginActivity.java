package com.example.android_summer_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_summer_2022.ASM.DangKyActivity;
import com.example.android_summer_2022.DAO.UserDAO;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {
    private TextView navDangKy;
    private EditText txtTaiKhoan, txtMatKhau;
    private Button btnDangNhap;
    private CheckBox ckLuuTaiKhoan;
    private static String TAIKHOAN="LuuTaiKhoan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtTaiKhoan= findViewById(R.id.dn_txtTaiKhoan);
        txtMatKhau = findViewById(R.id.dn_txtMatKhau);
        btnDangNhap = findViewById(R.id.dn_btnDangNhap);
        ckLuuTaiKhoan = findViewById(R.id.ckLuuTaiKhoan);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String taiKhoan = bundle.getString("taikhoan");
            txtTaiKhoan.setText(taiKhoan);
        }
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDAO dao = new UserDAO(getBaseContext());
                String username = txtTaiKhoan.getText().toString();
                String password = txtMatKhau.getText().toString();
                if(username.trim().equals("") || password.trim().equals("")) {
                    FancyToast.makeText(getBaseContext(),"tài khoản hoặc mật khẩu ko để trống",
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }else {
                boolean isLogin = dao.checkLogin(username, password);
                if(isLogin) {
                    FancyToast.makeText(getBaseContext(),"đăng nhập thành công",
                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    // kiểm tra xem user có lưu tài khoản lại không?
                    if(ckLuuTaiKhoan.isChecked()) {
                        LuuTaiKhoan(username, password);
                    }
                    // main activity
                    Intent intent = new Intent(getBaseContext(), ProductActivity.class);
                    startActivity(intent);
                } else {
                    FancyToast.makeText(getBaseContext(),"đăng nhập ko thành công",
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
                }

            }
        });
        navDangKy = findViewById(R.id.navDangKy);
        navDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), DangKyActivity.class);
                startActivity(intent);
            }
        });
        // load user password nếu đã lưu
        KhoiPhucTaiKhoan();
    }

    public void LuuTaiKhoan(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(TAIKHOAN, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password", password);
        editor.commit();
    }
    public void KhoiPhucTaiKhoan() {
        SharedPreferences sharedPreferences = getSharedPreferences(TAIKHOAN, MODE_PRIVATE);
        String username =  sharedPreferences.getString("username","");
        String matkhau =  sharedPreferences.getString("password","");
        if(!username.equals("") && !matkhau.equals("")) {
            txtTaiKhoan.setText(username);
            txtMatKhau.setText(matkhau);
            ckLuuTaiKhoan.setChecked(true);
        }


    }
}
