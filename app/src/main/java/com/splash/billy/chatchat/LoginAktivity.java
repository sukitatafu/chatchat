package com.splash.billy.chatchat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginAktivity extends AppCompatActivity {

    EditText etNomorHp;
    Button btLogin;
    TextView btRegister;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef  = database.getReference("users");

    SharedPreferences mylocaldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aktivity);

        etNomorHp = (EditText)findViewById(R.id.etNomorHp);
        btLogin = (Button)findViewById(R.id.btLogin);
        btRegister = (TextView) findViewById(R.id.btRegister);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomorhp = etNomorHp.getText().toString();

                // PEMANGGILAN DATA PADA FIREBASE BERDASARKAN nomorhp
                userRef.child(nomorhp).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mylocaldata = getSharedPreferences("mylocaldata",MODE_PRIVATE);
                        User user = new User();
                        if( dataSnapshot.exists()){
                            user.setNama(dataSnapshot.child("nama").getValue(String.class) );
                            user.setEmail( dataSnapshot.child("email").getValue(String.class) );
                            user.setTelepon( dataSnapshot.child("telepon").getValue(String.class) );

                            SharedPreferences.Editor editor = mylocaldata.edit();
                            editor.putString("uid", user.getTelepon() );
                            editor.apply();

                            // PINDAH KE MainActivity
                            Intent intent = new Intent(LoginAktivity.this, MainActivity.class);
                            intent.putExtra("user",user);
                            startActivity(intent);
                            finish();
                        }else{
                            Context context = getApplicationContext();
                            CharSequence text = "User tidak ditemukan";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAktivity.this, RegisterAktivity.class);
                startActivity(intent);
            }
        });

    }
}
