package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AjoutProfil extends AppCompatActivity {

    private Button btnval;
    private EditText ednom,edprenom,edadr;

    FirebaseDatabase mabase;
    DatabaseReference ref_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_profil);

        mabase = FirebaseDatabase.getInstance();
        ref_msg = mabase.getReference("personnes");

        ref_msg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //String s = snapshot.getValue(String.class);
               //Toast.makeText(AjoutProfil.this,"la personne est modifiée",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnval = findViewById(R.id.btncalider_acc);
        ednom = findViewById(R.id.ednom_acc);
        edprenom = findViewById(R.id.edprenom_acc);
        edadr = findViewById(R.id.edadr_acc);

        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = ednom.getText().toString();
                String p = edprenom.getText().toString();
                String a = edadr.getText().toString();
                //ref_msg.setValue(n+p+a);

                String cle=ref_msg.push().getKey();
                ref_msg.child(cle).setValue(new Personne(n,p,a));
            }
        });
    }
}