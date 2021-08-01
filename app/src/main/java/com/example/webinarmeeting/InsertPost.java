package com.example.webinarmeeting;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//Model
import com.example.webinarmeeting.Model.Data;

//Firebase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Date
import java.text.DateFormat;
import java.util.Date;


public class InsertPost extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private EditText time;
    private EditText date;
    private Button post;

    private FirebaseAuth mAuth;
    private DatabaseReference mPost;

    private DatabaseReference mPublicDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_post);


        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.insert_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("WebMeeting");


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser muser = mAuth.getCurrentUser();

        String uId = muser.getUid();

        mPost = FirebaseDatabase.getInstance().getReference().child("Post").child(uId);

        mPublicDatabase= FirebaseDatabase.getInstance().getReference().child("public database");


        Insert();

    }

    private void Insert(){
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        time  = findViewById(R.id.time);
        date = findViewById(R.id.date);
        post = findViewById(R.id.btn_post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"U clicked this button " , Toast.LENGTH_SHORT).show();

                String title1 = title.getText().toString().trim();
                String description1 = description.getText().toString().trim();
                String time1 = time.getText().toString().trim();
                String date1 = date.getText().toString().trim();

                if(TextUtils.isEmpty(title1)){
                    title.setError("Required field");
                    return;
                }

                if(TextUtils.isEmpty(description1)){
                    description.setError("Required field");
                    return;
                }

                if(TextUtils.isEmpty(date1)){
                    date.setError("Required field");
                    return;
                }

                if(TextUtils.isEmpty(time1)){
                    time.setError("Required field");
                    return;
                }

                String id=mPost.push().getKey();

                String mdate = DateFormat.getDateInstance().format(new Date());



                Data data =new Data(title1 , description1 , date1 , time1 , id , mdate);


                mPost.child(id).setValue(data);

                mPublicDatabase.child(id).setValue(data);


                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Post.class));


            }
        });
    }
}