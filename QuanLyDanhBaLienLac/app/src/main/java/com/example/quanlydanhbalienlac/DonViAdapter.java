package com.example.quanlydanhbalienlac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DonViAdapter extends RecyclerView.Adapter<DonViAdapter.DonViHolder>{

    private List<DonVi> donViList;

    private Context cont;

    private DatabaseHelper dbHelper;

    private OnItemActionListener actionListener;


    public DonViAdapter(List<DonVi> donViList, Context cont, DatabaseHelper dbHelper, OnItemActionListener actionListener) {
        this.donViList = donViList;
        this.cont = cont;
        this.dbHelper = dbHelper;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public DonViHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item,parent,false);
        return new DonViHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonViHolder holder, int position) {
        DonVi nv = donViList.get(position);

//        holder.tvThongTin.setText(nv.getMaDonVi()+"-"+nv.getTenDonVi());

        holder.tvId.setText("Mã đơn vị: "+String.valueOf(nv.getMaDonVi()));

        holder.tvName.setText("Tên đơn vị: "+nv.getTenDonVi());

        holder.tvPhone.setText("Số điện thoại: "+nv.getSdt());
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionListener.onEditDonVi(nv);
            }
        });

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionListener.onDeleteDonVi(nv);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (!donViList.isEmpty()){
            return donViList.size();
        }
        return 0;
    }

    public static class DonViHolder extends RecyclerView.ViewHolder{

//        private TextView tvThongTin;

        private TextView tvId, tvName, tvPhone;

        private Button btnSua, btnXoa;
        public DonViHolder(@NonNull View itemView) {
            super(itemView);

//            tvThongTin = itemView.findViewById(R.id.tvThongTin);

            tvId = itemView.findViewById(R.id.tvId);

            tvName = itemView.findViewById(R.id.tvName);

            tvPhone = itemView.findViewById(R.id.tvPhone);

            btnSua = itemView.findViewById(R.id.btnSua);

            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}
