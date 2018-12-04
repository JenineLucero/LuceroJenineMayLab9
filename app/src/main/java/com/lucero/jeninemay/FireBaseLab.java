package com.lucero.jeninemay;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FireBaseLab extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference root;

    private EditText FullName;
    private EditText Age;
    private EditText Gender;
    private TextView FnameOut;
    private TextView AgeOut;
    private TextView GenderOut;

    private String saveCurrentDate = "";
    private String saveCurrentTime = "";
    private String uniqueId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_lab);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("Names");

        FullName = findViewById(R.id.Fname);
        Age = findViewById(R.id.Age);
        Gender = findViewById(R.id.Gender);
        FnameOut = findViewById(R.id.Fnameout);
        AgeOut = findViewById(R.id.Ageout);
        GenderOut = findViewById(R.id.Genderout);


    }

    public void saveData(EditText fullName, EditText age, EditText gender) {

        Calendar calendarDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMMM:dd:yyyy");
        saveCurrentDate = currentDate.format(calendarDate.getTime());

        Calendar calendarTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss:SSS");
        saveCurrentTime = currentTime.format(calendarTime.getTime());

        uniqueId = saveCurrentDate.concat(saveCurrentTime);

        String getFullName = fullName.getText().toString().trim();
        String getAge = age.getText().toString().trim();
        String getGender = gender.getText().toString().trim();

        FireBase user = new FireBase(getFullName, getAge, getGender);

        root.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FireBaseLab.this, "saving is Successful!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(FireBaseLab.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    public void getData() {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String nameData = dataSnapshot.child("fullname").getValue(String.class);
                    String ageData = dataSnapshot.child("age").getValue(String.class);
                    String genderData = dataSnapshot.child("gender").getValue(String.class);

                    FnameOut.setText(nameData);
                    AgeOut.setText(ageData);
                    GenderOut.setText(genderData);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void btnfunction (View v) {
        if (v.getId() == R.id.btnSave) {
            saveData(FullName, Age, Gender);
        }
        if (v.getId() == R.id.btnSearch) {
            getData();
        }
    }
}
