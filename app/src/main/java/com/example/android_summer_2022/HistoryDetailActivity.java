package com.example.android_summer_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {
    private ImageView mvKetQua, mvBieuTuong;
    private TextView tvThongBao, tvThoiGian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        // anh xa
        mvKetQua = findViewById(R.id.mvKetQua);
        mvBieuTuong = findViewById(R.id.mvBieuTuong);
        tvThongBao = findViewById(R.id.tvThoiGian);
        tvThoiGian = findViewById(R.id.tvThoiGian);
        // lay du lieu duoc truyen qua

//        bundle.putInt("hinh", detail.getHinh());
//        bundle.putInt("soRandom",detail.getKetQuaCuoiCung());
//        bundle.putInt("soDuDoan", detail.getSoDuDoan());
//        bundle.putBoolean("ketqua", detail.isKetqua());
//        bundle.putString("date", strDate);
//        intent.putExtra("detail", bundle);
        Intent intent = getIntent();
        if(intent != null) {
            Bundle detailBundle = intent.getBundleExtra("detail");
            int hinh =  detailBundle.getInt("hinh");
            Log.i("TEST", hinh+"");
            int soRandom = detailBundle.getInt("soRandom");
            int soDuDoan = detailBundle.getInt("soDuDoan");
            boolean ketqua = detailBundle.getBoolean("ketqua");
            String date = detailBundle.getString("date");
            // binding data to view
            int[] lsHinh = {R.drawable.hinh1,R.drawable.hinh2,R.drawable.hinh3,R.drawable.hinh4,R.drawable.hinh5,R.drawable.hinh6};
            Drawable res = getResources().getDrawable(hinh);
            mvKetQua.setImageDrawable(res);
            String thongbao ="";
            if(ketqua) {
                thongbao+="Bạn đã chiến thắng " + soRandom + "-" + soDuDoan;
                mvBieuTuong.setImageResource(R.drawable.win);
            } else {
                thongbao+="Chúc bạn ra đê " + soRandom + "-" + soDuDoan;
                mvBieuTuong.setImageResource(R.drawable.loser);
            }
            tvThongBao.setText(thongbao);
            tvThoiGian.setText(date);
        }
    }
}
