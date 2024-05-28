package com.maghrebtrip.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextView createNewAccount;
    EditText inputEmail, inputPassword;
    CheckBox rememberMeBox;
    boolean rememberMe;
    Button btnLogin;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Get the rememberMe from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("appUser", MODE_PRIVATE);
        rememberMe = sharedPreferences.getBoolean("rememberMe", false);

        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        rememberMeBox=findViewById(R.id.rememberMeBox);
        createNewAccount=findViewById(R.id.createNewAccount);
        btnLogin=findViewById(R.id.btnLogin);
        progressBar=new ProgressBar(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        if (rememberMe && mUser!=null) {
            String userEmail = sharedPreferences.getString("email", "");
            if(Objects.equals(mUser.getEmail(), userEmail)) {
                sendUserToNextActivity();
            }
        } else {
            createNewAccount.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
            btnLogin.setOnClickListener(v -> PerformLogin());
        }
        
    }

    private void PerformLogin() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        boolean rememberMe=rememberMeBox.isChecked();

        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter Correct Email!");

        }else if(password.isEmpty() || password.length()<5){
            inputPassword.setError("Enter proper password!");
        }else{
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    SharedPreferences sharedPreferences = getSharedPreferences("appUser", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.putBoolean("rememberMe", rememberMe);
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Login successful ", Toast.LENGTH_SHORT).show();
                    sendUserToNextActivity();
                }else{
                    Toast.makeText(LoginActivity.this, "Incorrect Email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

    private void sendUserToNextActivity() {
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
