package com.splash.billy.chatchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class RegisterAktivity extends AppCompatActivity {

    User user;
    EditText etName, etTlp, etEmail;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_aktivity);

        etName = (EditText) findViewById(R.id.etName);
        etTlp = (EditText) findViewById(R.id.etTlp);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btRegister = (Button) findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                user.setNama( etName.getText().toString() );
                user.setTelepon( etTlp.getText().toString() );
                user.setEmail( etEmail.getText().toString() );
                user.register();
                Toast.makeText(getApplicationContext(),"login berhasil",Toast.LENGTH_SHORT).show();
            }
        });
    }
}



