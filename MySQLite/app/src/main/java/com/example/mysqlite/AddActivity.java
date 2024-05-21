package com.example.mysqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextAuthor, editTextTags;
    private Button buttonAddBook;
    private DatabaseHelper dbHelper;





        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);


            editTextTitle = findViewById(R.id.editTextTitle);
            editTextAuthor = findViewById(R.id.editTextAuthor);
            editTextTags = findViewById(R.id.editTextTags);
            buttonAddBook = findViewById(R.id.buttonAddBook);
            dbHelper = new DatabaseHelper(this);
            buttonAddBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = editTextTitle.getText().toString();
                    String author = editTextAuthor.getText().toString();
                    String tags = editTextTags.getText().toString();

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.COLUMN_TITLE, title);
                    values.put(DatabaseHelper.COLUMN_AUTHOR, author);
                    values.put(DatabaseHelper.COLUMN_TAGS, tags);

                    db.insert(DatabaseHelper.TABLE_BOOKS, null, values);
                    db.close();

                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    finish();
                }
            });

        }
}