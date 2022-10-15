package com.example.android_summer_2022.ASM;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_summer_2022.DAO.UserDAO;
import com.example.android_summer_2022.DTO.User;
import com.example.android_summer_2022.LoginActivity;
import com.example.android_summer_2022.R;
import com.example.android_summer_2022.Utils.SendMailService;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DangKyActivity extends AppCompatActivity {
    private EditText txtName, txtTaiKhoan,
            txtMatKhau, txtEmail, txtBirthDay;
    private RadioButton rdNam, rdNu;
    private CheckBox ckOk;
    private Button btnDangKy;
    private ImageButton btnOpenDatePicker;
    private TextInputLayout wrTxtName, wrTxtTaiKhoan, wrTxtMatKhau, wrTxtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        //-------biding id at view
        btnDangKy = findViewById(R.id.dk_btnDangKy);
        btnDangKy.setEnabled(false);
        txtName = findViewById(R.id.dk_txtName);
        txtTaiKhoan = findViewById(R.id.dk_txtTaiKhoan);
        txtMatKhau = findViewById(R.id.dk_txtMatKhau);
        txtEmail = findViewById(R.id.dk_txtEmail);
        txtBirthDay= findViewById(R.id.dk_txtBirthDay);
        //----------- show error ui--------------
        wrTxtName = findViewById(R.id.dk_wrtxtName);
        wrTxtTaiKhoan = findViewById(R.id.dk_wrtxtTaiKhoan);
        wrTxtMatKhau = findViewById(R.id.dk_wrtxtMatKhau);
        wrTxtEmail = findViewById(R.id.dk_wrtxtEmail);
        rdNam = findViewById(R.id.dk_rdNam);
        rdNam.setChecked(true);
        rdNu = findViewById(R.id.dk_rdNu);
        ckOk = findViewById(R.id.dk_ckDongY);

        ckOk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b) {
//                   btnDangKy.setEnabled(true);
//                } else {
//                    btnDangKy.setEnabled(false);
//                }
                btnDangKy.setEnabled(b);
            }
        });
        openDatePicker();
        submitDangKy();
        Button btnSendEmail = findViewById(R.id.testEmail);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMailService javaMailAPI = new SendMailService(getBaseContext(), "tabletkindfire@gmail.com",
                        "đăng ký thành công", "chào mừng bạn đến với app demo");

                javaMailAPI.execute();
            }
        });
    }

    public void openDatePicker() {
        btnOpenDatePicker = findViewById(R.id.dk_btnOpenDatePicker);
        Calendar cal = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener  dateEvent = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int month = i1 +1;
                txtBirthDay.setText(i+"/"+month+"/"+i2);
                txtBirthDay.setError(null);
            }
        };

        btnOpenDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        DangKyActivity.this, dateEvent,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                dialog.show();

            }
        });
    }

    public  void submitDangKy() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String taikhoan= txtTaiKhoan.getText().toString();
                String matkhau = txtMatKhau.getText().toString();
                String email = txtEmail.getText().toString();
                String birthday = txtBirthDay.getText().toString();
                String gioiTinh = "nam";
                //----reset error
                TextInputLayout [] inputLayouts =  new TextInputLayout[] {
                        wrTxtName, wrTxtTaiKhoan, wrTxtEmail, wrTxtMatKhau,
                };

                for (TextInputLayout input: inputLayouts
                     ) {
                    if(input != null) {
                        input.setError("");
                    }
                }
                if(rdNu.isChecked()) {
                    gioiTinh="nu";
                }
                //-------kt loi của input
                /**
                 * TODO: kiểm tra độ dài của mật khẩu, mật hẩu hoa, số, chữ
                 * email đúng định dạng
                 */
                boolean isCorrect = true;
                if(name.trim().equals("")) {
                    isCorrect= false;
                    wrTxtName.setError("Tên không được để trống");
                }
                if(taikhoan.trim().equals("")) {
                    isCorrect= false;
                    wrTxtTaiKhoan.setError("Tên tài khoản không được để trống");
                }
                if(matkhau.trim().equals("")) {
                    isCorrect= false;
                    wrTxtMatKhau.setError("Mật khẩu không được để trống");
                }
                if(email.trim().equals("")) {
                    isCorrect= false;
                    wrTxtEmail.setError("Email không được để trống");
                }
                if(birthday.trim().equals("")) {
                    isCorrect= false;
                    txtBirthDay.setError("Bạn chưa chọn ngày sinh");
                }
                //------ submit
                if(isCorrect) {
                    // kiểm tra username đã có chưa ?
                    UserDAO dao = new UserDAO(getBaseContext());
                    boolean isExistUserName = dao.checkExistUsername(taikhoan);
                    if(isExistUserName) {
                        FancyToast.makeText(getBaseContext(),"Tài khoản đã sử dụng",
                                FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    } else {
                        // thêm vào database
                        SimpleDateFormat formatter=new SimpleDateFormat("yyyy/dd/MM");
                        Date parseBirthDay = null;
                        try {
                            parseBirthDay = formatter.parse(birthday);
                        } catch (Exception e) {

                        }

                        User user = new User(
                                taikhoan, matkhau, name, email, gioiTinh, parseBirthDay
                        );
                        boolean isCreated = dao.create(user);
                        if(isCreated) {
                            FancyToast.makeText(getBaseContext(),"Đăng ký tài khoản thành công",
                                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            SendMailService javaMailAPI = new SendMailService(getBaseContext(), email,
                                    "đăng ký thành công", "chúc mừng bạn đăng ký thành công tài khoản:"+ taikhoan + "\n"+
                                    "mật khẩu: "+ matkhau);
                            javaMailAPI.execute();
                            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("taikhoan", taikhoan);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}
