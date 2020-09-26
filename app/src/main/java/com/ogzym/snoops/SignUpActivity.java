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

public class SignUpActivity extends AppCompatActivity {
    EditText emailText, pwdText, confirmText;
    String email, pwd, confirm;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailText = findViewById(R.id.emailText);
        pwdText = findViewById(R.id.pwdText);
        confirmText = findViewById(R.id.confirmPwdText);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void signUpClicked(View view){
        email = emailText.getText().toString();
        pwd = pwdText.getText().toString();
        confirm = confirmText.getText().toString();
        if (email.matches("") && pwd.matches("") && confirm.matches("")){
            Toast.makeText(SignUpActivity.this, "email ve sifre bos birakilamaz !", Toast.LENGTH_SHORT).show();
        }
        else{
            if (pwd.matches(confirm)) {
                firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignUpActivity.this, "User created !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else {
                Toast.makeText(this, "Passwords do not match !", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
