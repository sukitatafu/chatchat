package com.splash.billy.chatchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UserListAktivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    ArrayList<User> users = new ArrayList<>();

    RecyclerView rvUser;
    UserListAdapter adapter;

    User user;
    SharedPreferences mylocaldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_aktivity);

        mylocaldata = getSharedPreferences("mylocaldata", MODE_PRIVATE);
        user = getIntent().getParcelableExtra("user");

        // PEMBACAAN DATA DARI FIREBASE
        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    User user = postSnapshot.getValue( User.class );
                    users.add(user);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        rvUser =(RecyclerView)findViewById(R.id.rvUsers);
        rvUser.setHasFixedSize(true);
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserListAdapter(this,users);
        rvUser.setAdapter(adapter);

    }
}


