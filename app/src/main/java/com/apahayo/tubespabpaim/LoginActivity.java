package com.apahayo.tubespabpaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail,userPass;
    private Button loginBtn;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail= findViewById(R.id.UseremailET);
        userPass = findViewById(R.id.UserpasswordET);
        loginBtn = findViewById(R.id.masukBtn);
        mAuth = FirebaseAuth.getInstance();






        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);
                }
            }
        };


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    mAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                            userPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (mAuth.getCurrentUser().isEmailVerified()) {
                                            onStart();
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Tolong Verifikasi Email Anda",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Email atau Password yang Anda Masukkan Salah!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Ada data yang kosong!", Toast.LENGTH_SHORT).show();

                }
            }

        });



    }


}
