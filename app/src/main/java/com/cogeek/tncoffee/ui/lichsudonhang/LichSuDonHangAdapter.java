package com.cogeek.tncoffee.ui.lichsudonhang;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models_old.LichSuDonHang;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LichSuDonHangAdapter extends ArrayAdapter<LichSuDonHang> {
    Activity context;       // màn hình sử dụng layout này (giao diện này)
    int resource;           // Layout cho từng dòng muốn hiển thị (làm theo khách hàng)
    List<LichSuDonHang> objects;   // danh sách nguồn dữ liệu muốn hiển thị lên giao diện
    // chính là item (không lấy item vì rất khó kế thừa)
    public LichSuDonHangAdapter(@NonNull Activity context, int resource, @NonNull List<LichSuDonHang> objects) {
        super(context, resource, objects);
        this.context = context; //this là tham chiếu hiện tại
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_lichsudonhang, null, true);
        }
        LichSuDonHang lichSuDonHang = getItem(position);


        ImageView imgHinh =(ImageView) convertView.findViewById(R.id.imgHinhDH);
        Picasso.get().load(lichSuDonHang.getHinh()).into(imgHinh);

        TextView txtTen = convertView.findViewById(R.id.txtTenDonHang);
        txtTen.setText(lichSuDonHang.getTen());

        TextView txtSoSanPham = convertView.findViewById(R.id.txtSoSanPham);
        txtSoSanPham.setText(String.valueOf(lichSuDonHang.getSoSanPham()));

        TextView txtSoTien = convertView.findViewById(R.id.txtSoTienDonHang);
        txtSoTien.setText(String.valueOf(lichSuDonHang.getSoTien()));
        
        return convertView;
    }
}
