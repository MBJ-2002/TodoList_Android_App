package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class addtask extends AppCompatActivity {
    MaterialButton addTaskBtn, BackBtn;
    EditText Desc, Heading;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        addTaskBtn = findViewById(R.id.addtaskbtn);
        BackBtn = findViewById(R.id.backbtn);
        Desc = findViewById(R.id.descp);
        Heading = findViewById(R.id.taskheading);
        db = new DBHelper(this);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HeadingText = Heading.getText().toString();
                String Description = Desc.getText().toString();
                if(HeadingText.equals("") || Description.equals(""))
                {

                    Toast.makeText(addtask.this, "Enter Heading and Description!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean res = db.InsertTaskToList(HeadingText,Description);
                    if(res)
                    {
                        Toast.makeText(addtask.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(addtask.this, "Failed to add task", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(addtask.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}