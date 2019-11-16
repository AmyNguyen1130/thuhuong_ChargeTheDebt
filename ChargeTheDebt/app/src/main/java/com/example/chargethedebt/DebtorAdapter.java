package com.example.chargethedebt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chargethedebt.Model.debtor;
import java.util.ArrayList;
import java.util.List;


public class DebtorAdapter extends RecyclerView.Adapter<DebtorAdapter.DebtorViewHolder> {
    private OnItemClicked onClick;
    static List<debtor> data = new ArrayList<>();

    public interface OnItemClicked {
        void onDelete(int position);
        void onUpdate(int position);
    }


    @NonNull
    @Override
    public DebtorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debtor, parent, false);
        return new DebtorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DebtorViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getName() + "  (" + data.get(position).getPhoneNumber() + ")");
        holder.tvAmount.setText("Amount: " + data.get(position).getCurrentAmount() + "  InterestRate: " + data.get(position).getInterestRate());
        holder.tvPaid.setText("Paid: " + data.get(position).getPaid() + "  date: " + data.get(position).getdateDebt());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onDelete(position);
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onUpdate(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        if (data == null) {
            return 0;
        }
        return data.size();
    }

    class DebtorViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvAmount, tvPaid;
        Button btnDelete, btnUpdate;

        public DebtorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_name);
            tvAmount = itemView.findViewById(R.id.text_Amount);
            tvPaid = itemView.findViewById(R.id.text_paid);
            btnDelete = itemView.findViewById(R.id.btn_Delete);
            btnUpdate = itemView.findViewById(R.id.btn_Update);
        }
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}
