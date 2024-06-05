package com.example.studentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHoler> {

    private final List<StudentModel> listStudents;
    private final ItemClickListener itemClickListener;
    private final Context cont;

    public StudentAdapter(List<StudentModel> listStudents, ItemClickListener itemClickListener, Context cont) {
        this.listStudents = listStudents;
        this.itemClickListener = itemClickListener;
        this.cont = cont;
    }

    @NonNull
    @Override
    public StudentHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list, parent, false);
        return new StudentHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHoler holder, int position) {
        StudentModel student = listStudents.get(position);
        holder.tvStudent.setText(student.getId() + " " + student.getName() + " " + student.getClasses() + " " + student.getBirthday());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudents != null ? listStudents.size() : 0;
    }

    public static class StudentHoler extends RecyclerView.ViewHolder{

        private final TextView tvStudent;


        public StudentHoler(@NonNull View itemView) {
            super(itemView);
            tvStudent = itemView.findViewById(R.id.tvStudent);
        }


    }
}
