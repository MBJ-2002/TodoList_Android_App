package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class viewtask extends AppCompatActivity {
    ListView lst;
    DBHelper db;
    tasklistadapter myListAdapter;
    ArrayList<TaskData> arrayList = new ArrayList<>();
    MaterialButton delBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtask);
        lst = findViewById(R.id.lstvw);
        delBtn = findViewById(R.id.delbtn);
        db = new DBHelper(this);
        loadDataInListView();
    }
    public void loadDataInListView() {
        arrayList=db.getAll();
        myListAdapter = new tasklistadapter(this,arrayList);
        lst.setAdapter(myListAdapter);
        myListAdapter.notifyDataSetChanged();
    }
}