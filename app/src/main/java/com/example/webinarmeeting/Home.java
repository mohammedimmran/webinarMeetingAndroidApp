package com.example.webinarmeeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {
    private Button btnAll;
    private Button btnPost;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//        setSupportActionBar(toolbar);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Webinar Meeting");

        mAuth = FirebaseAuth.getInstance();

        btnAll = findViewById(R.id.btn_all);
        btnPost = findViewById(R.id.btn_post);

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),All.class));

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Post.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())){
            case R.id.logout:
                              mAuth.signOut();
                              startActivity(new Intent(getApplicationContext() , MainActivity.class));
                              break;
        }
        return super.onOptionsItemSelected(item);
    }
}