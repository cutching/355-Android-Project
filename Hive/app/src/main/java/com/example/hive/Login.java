package com.example.hive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button callSignUp, lLoginButton;
    // variables that correspond to xml elements activity_sign_up.xml
    // note that the "l" in the variable names correspond to "login"
    TextInputLayout lPhoneNum, lPassword;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        lPhoneNum = findViewById(R.id.lPhoneNum);
        lPassword = findViewById(R.id.lPassword);
        lLoginButton = findViewById(R.id.lLoginButton);

        callSignUp = findViewById(R.id.signup_button);
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validatePhoneNum() {
        String value = lPhoneNum.getEditText().getText().toString();

        if (value.isEmpty()) {
            lPhoneNum.setError("Field cannot be empty");
            return false;
        }
        else {
            lPhoneNum.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String value = lPassword.getEditText().getText().toString();

        if (value.isEmpty()) {
            lPassword.setError("Field cannot be empty");
            return false;
        }
        else {
            lPassword.setError(null);
            return true;
        }
    }

    public void loginUser (View view) {
        if (!validatePhoneNum() | !validatePassword())
            return;
        else
            isUserExisting();
    }

    private void isUserExisting() {
        String enteredPhoneNum = lPhoneNum.getEditText().getText().toString().trim();
        String enteredPassword = lPassword.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("phoneNum").equalTo(enteredPhoneNum);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    lPhoneNum.setError(null);

                    String dBPassword = snapshot.child(enteredPhoneNum).child("password").getValue(String.class);


                    assert dBPassword != null;
                    if(dBPassword.equals(enteredPassword)) {
                        lPhoneNum.setError(null);
                        lPhoneNum.setErrorEnabled(false);

                        String dBPhoneNum = snapshot.child(enteredPhoneNum).child("phoneNum").getValue(String.class);
                        String dBEmail = snapshot.child(enteredPhoneNum).child("email").getValue(String.class);
                        String dBName = snapshot.child(enteredPhoneNum).child("name").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext(),HomePage.class);


                        intent.putExtra("name", dBName);
                        intent.putExtra("phoneNum", dBPhoneNum);
                        intent.putExtra("email", dBEmail);

                        startActivity(intent);
                    }
                    else {
                        lPassword.setError("Incorrect Password");
                        lPassword.requestFocus();
                    }
                }
                else {
                    lPhoneNum.setError("Phone number does not exist");
                    lPhoneNum.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}