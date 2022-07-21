package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class tasklistadapter extends BaseAdapter {

    Context context;
    ArrayList<TaskData> arrayList;
    DBHelper db;

    public tasklistadapter(Context context, ArrayList<TaskData> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
       return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_tasklistadapter,null);
        TextView head = view.findViewById(R.id.headtxt);
        TextView sub = view.findViewById(R.id.maintxt);
        MaterialButton btn = view.findViewById(R.id.delbtn);
        TaskData tk = arrayList.get(position);
        head.setText(tk.getHeading());
        sub.setText(tk.getDescription());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TaskHeading = arrayList.get(position).Heading;
                String TaskDescription = arrayList.get(position).Description;
                db = new DBHelper(context);
                if(context instanceof viewtask)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    builder.setMessage("Do you want to delete task?");
                    builder.setTitle("Alert!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean res = ((viewtask)context).db.deleteTask(TaskHeading,TaskDescription);
                            if(res)
                            {
                                Toast.makeText(context, "Unable to delete task", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(context, "Task deleted", Toast.LENGTH_SHORT).show();
                                ((viewtask)context).loadDataInListView();
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }

            }
        });
        return view;
    }
}