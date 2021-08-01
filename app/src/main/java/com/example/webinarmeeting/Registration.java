package com.example.webinarmeeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registration extends AppCompatActivity {
    private EditText emailReg;
    private EditText passReg;
    private Button btnReg;
    private Button btnLogin;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        mAuth=FirebaseAuth.getInstance();
        mDialog= new ProgressDialog(this);

        Registration();
    }

    private void Registration(){
        emailReg = findViewById(R.id.email_registration);
        passReg  = findViewById(R.id.registration_password);

        btnLogin = findViewById(R.id.btn_login);
        btnReg = findViewById(R.id.btn_registration);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailReg.getText().toString().trim();
                String pass = passReg.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    emailReg.setError("Required field...");
                    return;

                }
                if (TextUtils.isEmpty(pass)) {
                    passReg.setError("Required field...");
                    return;

                }

                mDialog.setMessage("Processing");
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Successfull " , Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),Home.class));
                            mDialog.dismiss();


                        } else {
                            Toast.makeText(getApplicationContext(),"registration failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }

}