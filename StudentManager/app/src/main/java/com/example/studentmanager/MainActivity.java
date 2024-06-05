package com.example.studentmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextBirthday, editTextClass;
    private Button buttonAdd, buttonUpdate, buttonDelete;
    private RecyclerView rcvListStudent;
    private List<StudentModel> studentList;
    private StudentAdapter studentAdapter;

    private FirebaseDatabaseHelper databaseHelper;


    FirebaseDatabase database = FirebaseDatabase.getInstance("https://studentmanager-5d18a-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("students");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        editTextName = findViewById(R.id.editTextName);
        editTextName.setText(myRef.toString());
        editTextId = findViewById(R.id.editTextId);
        editTextBirthday = findViewById(R.id.editTextBirthday);
        editTextClass = findViewById(R.id.editTextClass);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        rcvListStudent = findViewById(R.id.rcvListStudent);



        rcvListStudent.setLayoutManager(new LinearLayoutManager(this));



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });
        loadStudent();

    }

    private void addStudent() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String birthday = editTextBirthday.getText().toString();
        String className = editTextClass.getText().toString();

        // Create StudentModel object
        StudentModel student = new StudentModel(id, name, birthday, className);

        // Save student to Firebase under the node with key as the student's ID
        myRef.child(id).setValue(student).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(MainActivity.this, "Student added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Failed to add student", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteStudent() {
        String id = editTextId.getText().toString();
        myRef.child(id).removeValue();
    }

    private void updateStudent() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String birthday = editTextBirthday.getText().toString();
        String classes = editTextClass.getText().toString();

        StudentModel student = new StudentModel(id, name, birthday, classes);
        myRef.child(id).setValue(student);
    }
    private void loadStudent(){
        studentList = new ArrayList<StudentModel>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    StudentModel student = studentSnapshot.getValue(StudentModel.class);
                    studentList.add(student);
                }

                if (studentAdapter == null) {
                    studentAdapter = new StudentAdapter(studentList, new ItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            StudentModel selectedStudent = studentList.get(position);
                            editTextId.setText(selectedStudent.getId());
                            editTextName.setText(selectedStudent.getName());
                            editTextBirthday.setText(selectedStudent.getBirthday());
                            editTextClass.setText(selectedStudent.getClasses());
                        }
                    }, MainActivity.this);
                    rcvListStudent.setAdapter(studentAdapter);
                } else {
                    studentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

}
