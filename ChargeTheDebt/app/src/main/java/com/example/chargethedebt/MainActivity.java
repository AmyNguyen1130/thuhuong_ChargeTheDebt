package com.example.chargethedebt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainActivity extends AppCompatActivity implements DebtorAdapter.OnItemClicked {

    Button btnOpenAddNew;
    DebtorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpenAddNew = findViewById(R.id.btnOpenAddNew);
        adapter = new DebtorAdapter();
        RecyclerView rv = findViewById(R.id.rvDebtor);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClick(MainActivity.this);
        rv.setAdapter(adapter);
        btnOpenAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewDebtorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        updateCurrentAmount();
    }

    void updateCurrentAmount() {
        // duyệt tất cả các phần tử của adapter tính lại tiền gốc cộng với tiền lời
        for (int i = 0; i < adapter.data.size(); i++) {
            long days = calculateDate(adapter.data.get(i).getDateDebt());
            double rate = adapter.data.get(i).getInterestRate();
            double originAmount = adapter.data.get(i).getOriginAmount();
            double currentAmount = calculateAmount(days, rate, originAmount);
            adapter.data.get(i).setCurrentAmount(currentAmount);
        }
    }

    public long calculateDate(String dateDebt) {
        // lấy ngày hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDay = sdf.format(new java.util.Date());

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        Date date1 = Date.valueOf(dateDebt);
        Date date2 = Date.valueOf(currentDay);

        c1.setTime(date1);
        c2.setTime(date2);
        // c2.getTime() lấy thời gian hiện tại kiểu String (Thu Nov 14 00:00:00 GMT+07:00 2019) != c2.getTime().getTime() lấy thời gian từ 1970 đến c2.getTime() kiểu int (1573664400000)
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        return noDay;
    }

    public double calculateAmount(long count, double interestRate, double originAmount) {
        double totalAmount = originAmount;
        for (int i = 0; i < count; i++) {
            // Số tiền mượn đâu tiên cộng với số phần trăm của mỗi ngày
            totalAmount = totalAmount + totalAmount * (interestRate / 100);
        }
        return totalAmount;
    }

    @Override
    public void onDelete(int position) {
        adapter.data.remove(adapter.data.get(position));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdate(int position) {
        Intent intent = new Intent(MainActivity.this, UpdateDebtorActivity.class);
        // truyền dữ liệu qua bên màn hình update
        intent.putExtra("udName", adapter.data.get(position).getName());
        intent.putExtra("udPhone", adapter.data.get(position).getPhoneNumber());
        intent.putExtra("udAddress", adapter.data.get(position).getAddress());
        intent.putExtra("udAmount", String.valueOf(adapter.data.get(position).getCurrentAmount()));
        intent.putExtra("udPurpose", adapter.data.get(position).getPurpose());
        intent.putExtra("udRate", String.valueOf(adapter.data.get(position).getInterestRate()));
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
