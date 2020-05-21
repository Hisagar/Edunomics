package com.example.edunomics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
private Button btnLogin;
private EditText txtEmail,txtPass;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnLogin=findViewById(R.id.login_btn);
        txtEmail=findViewById(R.id.email_edittext);
        txtPass=findViewById(R.id.pass_edittext);
        progressDialog=new ProgressDialog(this);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            Intent intent=new Intent(LoginPage.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("we are fetching your details...");
                progressDialog.show();
                String email=txtEmail.getText().toString();
                String pass=txtPass.getText().toString();
                if(email.isEmpty())
                {
                    Toast.makeText(LoginPage.this, "Empty input 'Email!!'", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else if(pass.isEmpty())
                {
                    Toast.makeText(LoginPage.this, "Empty input 'Password!!'", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else
                {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent intent=new Intent(LoginPage.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                              }else
                            {
                                Toast.makeText(LoginPage.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

                }
            }
        });

    }
}
