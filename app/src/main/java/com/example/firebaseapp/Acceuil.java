package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Acceuil extends AppCompatActivity {
    ListView lv;
    FirebaseDatabase database;
    DatabaseReference ref;

    ArrayList<Personne> data = new ArrayList<Personne>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        lv = findViewById(R.id.lv);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("personnes");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot dsp : snapshot.getChildren()) {
                    //parcourir tous les childs sous personnes
                    data.add(dsp.getValue(Personne.class));
                }
                lv.setAdapter(new ArrayAdapter(Acceuil.this, android.R.layout.simple_list_item_1, data));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
