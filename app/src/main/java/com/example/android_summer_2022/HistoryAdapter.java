package com.example.android_summer_2022;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_summer_2022.DAO.HistoryDAO;
import com.example.android_summer_2022.DTO.History;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private List<History> historys;

    public HistoryAdapter(Context context, List<History> historys) {
        this.context = context;
        this.historys = historys;
    }

    @Override
    public int getCount() {
        return historys.size();
    }

    @Override
    public Object getItem(int i) {
        if(i>0 && i <= historys.size()) {
            return historys.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            // view = LayoutInflater.from(context).inflate(R.layout.layout_nhanvien_item, null);
            view = LayoutInflater.from(context).inflate(R.layout.layout_history_item, null);
        }
        //anh xa
        ImageView mvketqua = view.findViewById(R.id.mvketqua);
        TextView tvThongBao = view.findViewById(R.id.tvthongbao);
        TextView tvThoiGian = view.findViewById(R.id.tvthoigian);

        // lấy detail của dòng hiện tại
        Log.i("TEST", i+"");
        if(i<=historys.size()) {
            History detail = historys.get(i);

            ImageButton btnXoa = view.findViewById(R.id.btnRemove);
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HistoryDAO dao = new HistoryDAO(context);
                    dao.delete(detail.getId());
                    historys.remove(i);
                    notifyDataSetChanged();
                    Intent i = new Intent(context, LoginActivity.class);
//                Toast.makeText(context,"Delete success", Toast.LENGTH_SHORT ).show();
                    Log.i("TEST", "click here" + detail.getId());
//                    getList();
                }
            });

            // do du lieu vao control
            int[] lsHinh = {R.drawable.hinh1, R.drawable.hinh2, R.drawable.hinh3, R.drawable.hinh4, R.drawable.hinh5, R.drawable.hinh6};
            mvketqua.setImageResource(lsHinh[detail.getHinh() - 1]);
            String thongbao = "";
            if (detail.isKetqua()) {
                thongbao += "Bạn đã chiến thắng " + detail.getKetQuaCuoiCung() + "-" + detail.getSoDuDoan();
            } else {
                thongbao += "Chúc bạn ra đê " + detail.getKetQuaCuoiCung() + "-" + detail.getSoDuDoan();
            }
            tvThongBao.setText(thongbao);
            // in thoi gian
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(detail.getThoigian());
            tvThoiGian.setText(strDate);

            return view;
        }
        return null;
    }
    public  void getList() {
        HistoryDAO dao = new HistoryDAO(context);
        historys = dao.getList();
        notifyDataSetChanged();
    }
}
