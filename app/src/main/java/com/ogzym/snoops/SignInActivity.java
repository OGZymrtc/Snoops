package com.ogzym.snoops;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText emailText,pwdText;
    private String email,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        pwdText = findViewById(R.id.pwdText);


        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            Intent intent = new Intent(SignInActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void signInClicked(View view){
        email = emailText.getText().toString();
        pwd = pwdText.getText().toString();
        if (email.matches("") && pwd.matches("")){
            Toast.makeText(SignInActivity.this, "email ve sifre bos birakilamaz !", Toast.LENGTH_SHORT).show();
        }
        else {
            firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(SignInActivity.this, "Sign In Success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignInActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void signUpClicked(View view){
        Intent intentToSignUp = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intentToSignUp);
    }
}
