package com.example.android_summer_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ControlButtonActivity extends AppCompatActivity {
    /**
     * khai bao bien
     * anh xa
     * */
    private RadioButton rdC, rdJava, rdJs;
    private CheckBox ckCode, ckChoiGame, ckHenHo, ckNgu, ckSport;
    private TextView tvKetQua;
    private ToggleButton tgbtnChangeColor;
    private Switch switchMode;
    private ConstraintLayout layoutSimple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_button);
        rdC = findViewById(R.id.rdC);
        rdJava = findViewById(R.id.rdJava);
        rdJs = findViewById(R.id.rdJs);
        rdC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(ControlButtonActivity.this, "đã chon C++" + b,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //---------check box handle

        ckCode =findViewById(R.id.ckCode);
        ckChoiGame=findViewById(R.id.ckplayGame);
        ckNgu = findViewById(R.id.ckSleep);
        ckHenHo = findViewById(R.id.ckHenho);
        ckSport = findViewById(R.id.ckPlaySport);
        tvKetQua = findViewById(R.id.tvKetQuaSoThich);
        tgbtnChangeColor = findViewById(R.id.tgbtnChangeColor);
        switchMode = findViewById(R.id.swMode);
        layoutSimple = findViewById(R.id.layoutControl);
        ArrayList<String> lsSoThich = new ArrayList<>();
        ckCode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    lsSoThich.add("code");
                } else {
                    lsSoThich.remove("code");
                }

                String soThich = covertArrayListToString(lsSoThich);
                tvKetQua.setText("Sở thích của bạn là:" + soThich);
            }
        });

        tgbtnChangeColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //tvKetQua.setTextColor(getResources().getColor(b ? R.color.purple_200 : R.color.green));
                if(b) {
                    tvKetQua.setTextColor(getResources().getColor(R.color.purple_200));
                } else {
                    tvKetQua.setTextColor(getResources().getColor(R.color.green));
                }
            }
        });

        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //tvKetQua.setTextColor(getResources().getColor(b ? R.color.purple_200 : R.color.green));
                if(b) {
                    layoutSimple.setBackgroundColor(getResources().getColor(R.color.purple_200));
                } else {
                    layoutSimple.setBackgroundColor(getResources().getColor(R.color.green));
                }
            }
        });

    }
    public String covertArrayListToString(ArrayList<String> list) {
        String ketqua="";
        for (String item: list) {
            ketqua+="," + item;
        }
        return ketqua;
    }
}
