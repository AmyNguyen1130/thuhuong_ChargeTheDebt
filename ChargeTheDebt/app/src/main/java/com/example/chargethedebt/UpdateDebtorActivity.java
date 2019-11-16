package com.example.chargethedebt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateDebtorActivity extends AppCompatActivity {
    EditText edName, edPhone, edAdd, edPurpose, edAmount, edRate;
    Button btnUpdate;
    DebtorAdapter debtorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_debtor);

        edName = findViewById(R.id.edtName);
        edPhone = findViewById(R.id.edtPhone);
        edAdd = findViewById(R.id.edtAddress);
        edPurpose = findViewById(R.id.edtpurpose);
        edAmount = findViewById(R.id.edtAmount);
        edRate = findViewById(R.id.edtInterestRate);
        btnUpdate = findViewById(R.id.btnUpdate);

        getData(edName, "udName");
        getData(edPhone, "udPhone");
        getData(edAdd, "udAddress");
        getData(edPurpose, "udPurpose");
        getData(edAmount, "udAmount");
        getData(edRate, "udRate");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
    }

    void getData(EditText edtText, String text) {
        Bundle extras = getIntent().getExtras();
        edtText.setText(extras.getString(text));
    }

    void setData() {
        Bundle extras = getIntent().getExtras();
        int position = extras.getInt("position");
        debtorAdapter.data.get(position).setName(edName.getText().toString());
        debtorAdapter.data.get(position).setPhoneNumber(edPhone.getText().toString());
        debtorAdapter.data.get(position).setOriginAmount(Double.valueOf(edAmount.getText().toString()));
        debtorAdapter.data.get(position).setAddress(edAdd.getText().toString());
        debtorAdapter.data.get(position).setInterestRate(Double.valueOf(edRate.getText().toString()));
        debtorAdapter.data.get(position).setPurpose(edPurpose.getText().toString());
        // chuyển về màn hình chính
        finish();
    }
}
