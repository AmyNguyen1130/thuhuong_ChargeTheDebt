package com.example.chargethedebt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chargethedebt.Model.debtor;

public class AddNewDebtorActivity extends AppCompatActivity {

    EditText edtName, edtPhoneNumber, edtPurpose, edtAmount, edtAddress, edtInterestRate;
    Button btnAddNew;
    DebtorAdapter debtorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_debtor);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhone);
        edtPurpose = findViewById(R.id.purpose);
        edtAddress = findViewById(R.id.edtAddress);
        edtAmount = findViewById(R.id.edtAmount);
        edtInterestRate = findViewById(R.id.edtInterestRate);
        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dtName, dtPhone, dtPurpose, dtAddress;
                Double dtAmount, dtInterestRate;
                // Lấy giá trị bên EditText làm thông tin truyền vào deb1
                dtName = edtName.getText().toString();
                dtPhone = edtPhoneNumber.getText().toString();
                dtPurpose = edtPurpose.getText().toString();
                dtAddress = edtAddress.getText().toString();
                dtAmount = Double.parseDouble(edtAmount.getText().toString());
                dtInterestRate = Double.parseDouble(edtInterestRate.getText().toString());

                debtor deb1 = new debtor(dtName, dtPhone, dtAddress, dtPurpose, dtAmount, dtInterestRate);
                debtorAdapter.data.add(deb1);
                // chuyển về lại màn hình chính
                finish();
            }
        });
    }
}
