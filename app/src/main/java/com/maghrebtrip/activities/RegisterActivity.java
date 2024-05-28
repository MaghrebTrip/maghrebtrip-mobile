package com.maghrebtrip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maghrebtrip.R;
import com.maghrebtrip.activities.main.MainActivity;

public class RegisterActivity extends AppCompatActivity {
    TextView alreadyHaveaccount;
    EditText inputFirstName, inputLastName, inputEmail, inputPassword, inputConfirmPassword;
    Button btnRegister;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        alreadyHaveaccount=findViewById(R.id.alreadyHaveaccount);
        inputFirstName=findViewById(R.id.inputFirstName);
        inputLastName=findViewById(R.id.inputLastName);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputConfirmPassword=findViewById(R.id.inputConfirmPassword);
        btnRegister=findViewById(R.id.btnRegister);
        progressBar=new ProgressBar(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        alreadyHaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();

            }
        });

    }

    private void PerformAuth() {

        String firstName=inputFirstName.getText().toString();
        String lastName=inputLastName.getText().toString();
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String ConfirmPassword=inputConfirmPassword.getText().toString();

        if (firstName.isEmpty()) {
            inputFirstName.setError("First name is required");
        } else if (lastName.isEmpty()) {
            inputFirstName.setError("Last name is required");
        } else if (!email.matches(emailPattern)) {
            inputEmail.setError("Enter Correct Email Format!");
        } else if (password.isEmpty() || password.length()<5){
            inputPassword.setError("Enter proper password!");
        } else if (!password.equals(ConfirmPassword)) {
           inputConfirmPassword.setError("Password not matching!");
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registration successful ", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                }else{
                        Toast.makeText(RegisterActivity.this, String.format("%s", task.getException()), Toast.LENGTH_SHORT).show();
                }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
