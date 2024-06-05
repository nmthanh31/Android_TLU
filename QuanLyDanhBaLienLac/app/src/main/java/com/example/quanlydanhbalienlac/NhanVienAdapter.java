package com.example.quanlydanhbalienlac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienHolder> {

    private List<NhanVien> nhanVienList;

    private Context cont;

    private OnItemActionListener actionListener;

    private DatabaseHelper dbHelper;

    public NhanVienAdapter(List<NhanVien> nhanVienList, Context cont, OnItemActionListener actionListener, DatabaseHelper dbHelper) {
        this.nhanVienList = nhanVienList;
        this.cont = cont;
        this.actionListener = actionListener;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public NhanVienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item,parent,false);
        return new NhanVienHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienHolder holder, int position) {
        NhanVien nv = nhanVienList.get(position);

//        holder.tvThongTin.setText(nv.getMaNhanVien()+"-"+nv.getHoten());

        holder.tvId.setText("Mã nhân viên: "+String.valueOf(nv.getMaNhanVien()));

        holder.tvName.setText("Họ và tên: "+nv.getHoten());

        holder.tvPhone.setText("Số điện thoại: "+nv.getSdt());

        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionListener.onEditNhanVien(nv);
            }
        });

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionListener.onDeleteNhanVien(nv);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (nhanVienList!=null){
            return nhanVienList.size();
        }
        return 0;
    }

    public static class NhanVienHolder extends RecyclerView.ViewHolder{

//        private TextView tvThongTin;

        private TextView tvId, tvName, tvPhone;

        private Button btnSua, btnXoa;
        public NhanVienHolder(@NonNull View itemView) {
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
