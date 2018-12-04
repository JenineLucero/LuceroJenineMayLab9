package com.lucero.jeninemay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseLab extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference root;

    private EditText FullName;
    private EditText Age;
    private EditText Gender;
    private TextView FnameOut;
    private TextView AgeOut;
    private TextView GenderOut;

    ArrayList<String> keylist;

    private Button Save;
    private Button Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_lab);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("Names");
        keylist = new ArrayList<>();
        init();

    }
    private void init(){
        FullName = findViewById(R.id.Fname);
        Age = findViewById(R.id.Age);
        Gender = findViewById(R.id.Gender);
        FnameOut = findViewById(R.id.Fnameout);
        AgeOut = findViewById(R.id.Ageout);
        GenderOut = findViewById(R.id.Genderout);
        Search = findViewById(R.id.btnSearch);
        Save = findViewById(R.id.btnSave);

    }

    public void aveClick(View v){
        if(v.getId() == R.id.AverageButton){
            int a, b;
            String fname, lname;
            try{
                fname = firstName.getText().toString().trim();
                lname = lastName.getText().toString().trim();
                a = Integer.parseInt(grade1.getText().toString().trim());
                b = Integer.parseInt(grade2.getText().toString().trim());
                int ave = (a+b)/2;
                Student student_info = new Student(fname,lname,ave);
                String key = root.push().getKey();
                root.child(key).setValue(student_info);
                keylist.add(key);
                root.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int lastObj = (int) dataSnapshot.getChildrenCount() -1;
                        System.out.println(lastObj);
                        Student stud = dataSnapshot.child(keylist.get(lastObj)).getValue(Student.class);
                        aveOut.setText(stud.getAve().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            catch (NumberFormatException e){
                Toast.makeText(this, "Cannot put null values", Toast.LENGTH_LONG).show();
            }


        }
    }
}
