package com.example.webinarmeeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.webinarmeeting.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class All extends AppCompatActivity {



    private RecyclerView recyclerView;


    private DatabaseReference AllPost;
    private FirebaseRecyclerAdapter adapter;
    private LinearLayoutManager LinearLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_all_post);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Webinars");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




//      Database Connection in firebase
        AllPost = FirebaseDatabase.getInstance().getReference().child("public database");
        AllPost.keepSynced(true);


//        Layout using Recycler View
        recyclerView = findViewById(R.id.recycler_all);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Data> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Data>().setQuery(AllPost , Data.class).build();

        FirebaseRecyclerAdapter<Data , AllPostViewHolder> adapter = new FirebaseRecyclerAdapter<Data , AllPostViewHolder>(firebaseRecyclerOptions){


            @NonNull
            @Override
            public AllPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allpost , parent , false);
                return new AllPostViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull AllPostViewHolder viewHolder, int i, @NonNull Data model) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDate(model.getDate());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setTime(model.getTime());
            }


        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }























    public static class AllPostViewHolder extends RecyclerView.ViewHolder {

        View myview;

        public AllPostViewHolder(@NonNull View itemView) {
            super(itemView);

            myview = itemView;

        }

        public void setTitle(String title) {
            TextView mTitle = myview.findViewById(R.id.all_post_title);
            mTitle.setText(title);

        }

        public void setDescription(String description) {
            TextView mDescription = myview.findViewById(R.id.all_post_description);
            mDescription.setText(description);

        }

        public void setDate(String date) {
            TextView mDate = myview.findViewById(R.id.all_post_date);
            mDate.setText(date);

        }

        public void setTime(String time) {
            TextView mTime = myview.findViewById(R.id.all_post_time);
            mTime.setText(time);

        }
    }
}