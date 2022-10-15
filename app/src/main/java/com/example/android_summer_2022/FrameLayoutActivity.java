package com.example.android_summer_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class FrameLayoutActivity extends AppCompatActivity {
    private FrameLayout frameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        frameList = findViewById(R.id.frameList);
        int [] images = {R.drawable.la_bai_1, R.drawable.la_bai_1, R.drawable.la_bai_1 };
        try {
            for (int i  : images) {
                Log.i("DEMO", i+"");
                System.out.println(i);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                );
                params.leftMargin = (int) (i * 5 * this.getResources().getDisplayMetrics().density);
                params.width = (int) (200 * this.getResources().getDisplayMetrics().density);
                params.leftMargin = (int) (200 * this.getResources().getDisplayMetrics().density);
                ImageView imv = new ImageView(this);
                imv.setLayoutParams(params);
                imv.setImageResource(images[i]);
                frameList.addView(imv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
