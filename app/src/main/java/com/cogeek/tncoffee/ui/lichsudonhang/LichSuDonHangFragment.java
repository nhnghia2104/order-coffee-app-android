package com.cogeek.tncoffee.ui.lichsudonhang;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.cogeek.tncoffee.models.OrderHistoryDetail;
import com.cogeek.tncoffee.R;

import java.util.ArrayList;

public class LichSuDonHangFragment extends AppCompatActivity {
    ListView lvLichSuDonHang;
    ArrayList<OrderHistoryDetail> dsOrderHistoryDetail;
    LichSuDonHangAdapter lichSuDonHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_hang);

        addControls();
    }


    private void addControls() {
        lvLichSuDonHang = findViewById(R.id.lvLichSuDonHang);
        dsOrderHistoryDetail = new ArrayList<>();
        dsOrderHistoryDetail.add(new OrderHistoryDetail("https://cafebiz.cafebizcdn.vn/2019/7/23/photo-1-15638793092381493027519.jpg","Truong Ngoc Anh Thu", 1,23000));
        //dsLichSuDonHang.add(new com.example.models.LichSuDonHang());

        lichSuDonHangAdapter = new LichSuDonHangAdapter(LichSuDonHangFragment.this, R.layout.item_lichsudonhang, dsOrderHistoryDetail);

        lvLichSuDonHang.setAdapter(lichSuDonHangAdapter);
    }
}