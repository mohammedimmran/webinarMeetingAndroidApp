package com.example.webinarmeeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webinarmeeting.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Post extends AppCompatActivity {

    private FloatingActionButton fabBtn;

    private RecyclerView recyclerView;

    private FirebaseAuth mAuth;
    private FirebaseRecyclerAdapter adapter;
    private DatabaseReference PostDataBase;
    private LinearLayoutManager linearLayoutManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_post);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Posts");


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabBtn = findViewById(R.id.fab_add);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        String uId = mUser.getUid();

        PostDataBase = FirebaseDatabase.getInstance().getReference().child("Post").child(uId);

        recyclerView = findViewById(R.id.recycler_post_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),InsertPost.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Data> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Data>().setQuery(PostDataBase , Data.class).build();

        FirebaseRecyclerAdapter<Data , MyViewHolder> adapter = new FirebaseRecyclerAdapter<Data , MyViewHolder>(firebaseRecyclerOptions){


            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item , parent , false);
                return new MyViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i, @NonNull Data model) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDate(model.getDate());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setTime(model.getTime());
            }


        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }






    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myview = itemView;

        }

        public void setTitle(String title){
            TextView mTitle = myview.findViewById(R.id.title);
            mTitle.setText("Title :"+ title);

        }
        public void setDescription(String description){
            TextView mDescription = myview.findViewById(R.id.description);
            mDescription.setText(description);

        }public void setDate(String date){
            TextView mDate = myview.findViewById(R.id.date);
            mDate.setText("Date :"+ date);

        }public void setTime(String time){
            TextView mTime = myview.findViewById(R.id.time);
            mTime.setText("Time :"+ time);

        }




    }

}