package com.example.hackcbs30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;


public class register extends AppCompatActivity {

    String[] genders = {"male", "female", "N/a"};
    String[] grp={"A+","O+","B+","AB+","A-","O-","B-","AB-"};
    private Spinner gender;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    bloodgroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        gender = findViewById(R.id.gender_spinner);


        recyclerView= findViewById(R.id.recycler_wall);
        layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new bloodgroupAdapter(grp);
        recyclerView.setAdapter(adapter);



        ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList(genders));
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, R.layout.gender_dummy, arrayList1);
        gender.setAdapter(arrayAdapter1);

    }
}