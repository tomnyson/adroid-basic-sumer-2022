package com.example.android_summer_2022;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_summer_2022.DAO.HistoryDAO;
import com.example.android_summer_2022.DTO.History;

import java.io.DataOutput;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SimpleControlActivity extends AppCompatActivity {
    /**
     * 1: khai bao bien
     * 2: anh xa
     * 3: handle event
     */
    private ImageButton btnImgAdd;
    private ImageView imvAvatar;
    private TextView ket_qua;
    private Spinner cbDiem;
    private ListView lvHistory;
    private HistoryAdapter historyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_control);

        btnImgAdd = findViewById(R.id.btnImgAdd);
        imvAvatar = findViewById(R.id.imvAvatar);
        imvAvatar.setImageResource(R.drawable.hinh1);
        lvHistory = findViewById(R.id.lvLichSu);
        ket_qua = findViewById(R.id.tvKetQua);
        cbDiem = findViewById(R.id.cbDiem);
        /**
         * tạo ra một list dữ liệu -> array || arraylist
         * set kieu layout
         * set adapter spiner = array | array list
         * event: onItemSelected
        * */
        /**
         *  gọi dao
         *  get data từ dao và gắn vào adapter base
         */
        HistoryDAO dao = new HistoryDAO(this);

//        Date date = Calendar.getInstance().getTime();
//
//        for(int i=0; i<=5; i++) {
//            double randomDouble = Math.random();
//            randomDouble = randomDouble * 100 + 1;
//            int currentValue = (int) randomDouble;
//            History test = new History();
//            test.setId(currentValue);
//            test.setKetqua(true);
//            test.setSoDuDoan(1);
//            test.setHinh(1);
//            test.setKetQuaCuoiCung(1);
//            test.setThoigian(date);
//            dao.createHistory(test);
//        }
        List<History> listHistory = new ArrayList<>();
        listHistory = dao.getList();
//        Date current = new Date(System.currentTimeMillis());
//        listHistory.add(new History(R.drawable.hinh1, current,
//                true, 1,1 ));
//        listHistory.add(new History(R.drawable.hinh2, current,
//                false, 1,2 ));
        historyAdapter = new HistoryAdapter(this, listHistory);
        ArrayList<Integer> listDiem = new ArrayList<Integer>();
        listDiem.add(1);
        listDiem.add(2);
        listDiem.add(3);
        listDiem.add(4);
        listDiem.add(5);
        listDiem.add(6);

//        ArrayList<String> listHistory = new ArrayList<>();
//        listDiem.add(1);
//        listDiem.add(2);
//        listDiem.add(3);
//        listDiem.add(4);
//        listDiem.add(5);
//        listDiem.add(6);
//        listHistory.add("cươc 1 ra 3 kết quả thua");
//        listHistory.add("cươc 2 ra 2 kết quả thắng");
//        listHistory.add("cươc 2 ra 2 kết quả thắng");
        ArrayAdapter<Integer> adapter =
                new ArrayAdapter<Integer>(
                        this,com.google.android.material.R.layout.
                        support_simple_spinner_dropdown_item, listDiem);
        cbDiem.setAdapter(adapter);
        cbDiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("TEST", i+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("TEST", "chua chon");
            }
        });
        /**
         *  tạo adapter;
         */
//        ArrayAdapter<String> historyAdapter = new ArrayAdapter<String>(this,
//                R.layout.simple_listview_item,R.id.tvName, listHistory);
        lvHistory.setAdapter(historyAdapter);
        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                      Log.i("TEST", "test");
//                    Intent intent = new Intent(getBaseContext(), HistoryDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    History detail = listHistory.get(i);
//                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//                    String strDate = dateFormat.format(detail.getThoigian());
//
//                    // gói dữ liệu
//                    Log.i("TEST", detail.getHinh()+"");
//                   bundle.putInt("hinh", detail.getHinh());
//                   bundle.putInt("soRandom",detail.getKetQuaCuoiCung());
//                   bundle.putInt("soDuDoan", detail.getSoDuDoan());
//                   bundle.putBoolean("ketqua", detail.isKetqua());
//                   bundle.putString("date", strDate);
//                   intent.putExtra("detail", bundle);
//                   startActivity(intent);



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dudoan = cbDiem.getSelectedItem().toString();
                int[] lsHinh = {R.drawable.hinh1,R.drawable.hinh2,R.drawable.hinh3,R.drawable.hinh4,R.drawable.hinh5,R.drawable.hinh6};

                Handler handler =  new Handler();
                Runnable myRunnable = new Runnable() {
                    public void run() {
                        // Things to be done
                        double randomDouble = Math.random();
                        randomDouble = randomDouble * 6 + 1;
                        int currentValue = (int) randomDouble;
                        imvAvatar.setImageResource(lsHinh[currentValue-1]);
                        handler.postDelayed(this, 1000);
                        if(currentValue >3) {
                            handler.removeCallbacks(this);
                        }

                    }
                };
                handler.postDelayed(myRunnable, 1000);

                if(dudoan != null) {
                    int prediction_val = Integer.parseInt(dudoan);
                    double randomDouble = Math.random();
                    randomDouble = randomDouble * 6 + 1;
                    int currentValue = (int) randomDouble;

                   // 1 second delay (takes millis)

                    switch (currentValue){
                        case 1:
                            imvAvatar.setImageResource(R.drawable.hinh1);
                            break;
                        case 2:
                            imvAvatar.setImageResource(R.drawable.hinh2);
                            break;
                        case 3:
                            imvAvatar.setImageResource(R.drawable.hinh3);
                            break;
                        case 4:
                            imvAvatar.setImageResource(R.drawable.hinh4);
                            break;
                        case 5:
                            imvAvatar.setImageResource(R.drawable.hinh5);
                            break;
                        case 6:
                            imvAvatar.setImageResource(R.drawable.hinh6);
                            break;
                    }
                    Date date = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    String strDate = dateFormat.format(date);
                    if (prediction_val == currentValue) {
//                        listHistory.add(prediction_val + " đoán đúng " +strDate );
                        ket_qua.setText("Du doan chinh xac");
                    } else {
//                        listHistory.add(prediction_val + " đoán sai " +strDate );
                        ket_qua.setText("Du doan khong chinh xac");
                    }
                } else {
                    Toast.makeText(SimpleControlActivity.this, "Bạn chưa chọn", Toast.LENGTH_SHORT).show();
                }
                historyAdapter.notifyDataSetChanged();
            }
        });
    }
}
