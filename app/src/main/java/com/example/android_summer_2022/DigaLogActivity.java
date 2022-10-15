package com.example.android_summer_2022;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Calendar;

public class DigaLogActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private DatePicker datePicker;
    private ImageButton btnShow;
    private EditText edDate;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diga_log);
//        timePicker = findViewById(R.id.timepicker);
//        timePicker.setIs24HourView(true);

        datePicker = findViewById(R.id.dataPicker);
        Calendar cal = Calendar.getInstance();

//        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
//                Log.i("TEST", i+"-"+ i1 + "-" + i2);
//            }
//        });
//        timePicker.setHour(cal.getTime().getHours());
//        timePicker.setMinute(cal.getTime().getMinutes());
//        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
//                Log.i("TEST", i+":"+ i1);
//            }
//        });

        btnShow = findViewById(R.id.btnShowDate);
        edDate = findViewById(R.id.txtNgay);

        DatePickerDialog.OnDateSetListener  dateEvent = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                edDate.setText(i+"/"+i1+"/"+i2);
            }
        };
        Log.i("TEST", cal.get(Calendar.YEAR)+ "");
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        DigaLogActivity.this, dateEvent,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH)+1,
                        cal.get(Calendar.DATE));
                dialog.show();
            }
        });

        Button btnWaitng = findViewById(R.id.btnWaiting);
        btnWaitng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ProgressDialog progressDialog = ProgressDialog.show(DigaLogActivity.this,
//                        "wating", "do process ..."
//                        );
//
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressDialog.dismiss();
//                    }
//                }, 2000);

                FancyToast.makeText(getBaseContext(),"Hello World !", FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

            }
        });

        Button btnAlert = findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        DigaLogActivity.this);

                final Drawable positiveIcon = getBaseContext()
                        .getResources().getDrawable(R.drawable.hinh1);
                final Drawable negativeIcon = getBaseContext()
                        .getResources().getDrawable(R.drawable.hinh2);

                builder.setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    Log.i("TEST", "OK");
                    }
                });
                builder.setPositiveButtonIcon(positiveIcon);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("TEST", "Cancel");
                    }
                });

                builder.setTitle("Thông ").setMessage("Bạn có muốn xoá không");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button btnShowCustom = findViewById(R.id.btnShowCustomDiag);
        btnShowCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    // hàm custom lại dialog
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = DigaLogActivity.this.getLayoutInflater();
        // nhúng layout vào dialog alert
        View v = inflater.inflate(R.layout.layout_introduction, null);
        builder.setView(v);
        builder.setPositiveButton("OK",(dialog,with)->{
            Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancel",(dialog, with)->{
            dialog.cancel();
        });
        builder.show();

    }
}
