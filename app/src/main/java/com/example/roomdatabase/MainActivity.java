package com.example.roomdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity{
  ContactViewModel contactViewModel;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        contactViewModel.getAllConacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {

                setRecyclerView(contacts);

            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDialog();
            }
        });

    }


    public void setRecyclerView(List<Contacts> list){

        adapter = new Adapter(MainActivity.this,list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }


    public void EditDialog() {

        final AlertDialog BalertDialog;
        final EditText name, contact;


        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View itemView = inflater.inflate(R.layout.post, null);

        name = itemView.findViewById(R.id.name);
        contact = itemView.findViewById(R.id.contact);


        BalertDialog = new AlertDialog.Builder(MainActivity.this).setView(itemView).create();
        BalertDialog.setButton(AlertDialog.BUTTON1, "Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Contacts c = new Contacts(name.getText().toString(),contact.getText().toString());
                contactViewModel.insert(c);
                adapter.notifyDataSetChanged();

                BalertDialog.dismiss();
            }
        });
        BalertDialog.setButton(AlertDialog.BUTTON2, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BalertDialog.dismiss();
            }
        });
        BalertDialog.show();


    }

}
