package com.example.shivam.attendanceappminor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText user_name,password;
    Button button;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        user_name = (EditText) findViewById(R.id.user_name_edit);
        password = (EditText)findViewById(R.id.password_edit);
        button = (Button)findViewById(R.id.submit_btn);
        Intent intent = getIntent();
        final String type  = intent.getStringExtra("type");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : Do validations
                //username will be student_id(enrollment number)

        if(type.equals( "admin")){
            databaseReference.child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String id  = dataSnapshot.child("adminId").getValue().toString();
                    String pass  = dataSnapshot.child("adminPassword").getValue().toString();
                    if (id.equals(user_name.getText().toString().trim()) && pass.equals(password.getText().toString())){

                        Intent intent1 = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                        startActivity(intent1);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


            }
        });

    }
}
