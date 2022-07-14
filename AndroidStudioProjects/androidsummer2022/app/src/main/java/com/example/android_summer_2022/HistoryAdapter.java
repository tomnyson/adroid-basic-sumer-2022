package com.example.android_summer_2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        History detail = historys.get(i);
        // do du lieu vao control
        mvketqua.setImageResource(detail.getHinh());
        String thongbao ="";
        if(detail.isKetqua()) {
            thongbao+="Bạn đã chiến thắng " + detail.getKetQuaCuoiCung() + "-" + detail.getSoDuDoan();
        } else {
            thongbao+="Chúc bạn ra đê " + detail.getKetQuaCuoiCung() + "-" + detail.getSoDuDoan();
        }
        tvThongBao.setText(thongbao);
        // in thoi gian
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(detail.getThoigian());
        tvThoiGian.setText(strDate);

        return view;
    }
}
