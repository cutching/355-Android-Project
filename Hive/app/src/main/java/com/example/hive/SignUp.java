package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    // variables that correspond to xml elements activity_sign_up.xml
    // note that the "s" in the variable names correspond to "sign up"
    TextInputLayout sName, sPhoneNum, sEmail, sPassword;
    Button sSignUpButton, sLoginButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        // hooks connecting variables to xml elements in activity_sign_up.xml
        sName = findViewById(R.id.sName);
        sPhoneNum = findViewById(R.id.sPhoneNum);
        sEmail = findViewById(R.id.sEmail);
        sPassword = findViewById(R.id.sPassword);
        sSignUpButton = findViewById(R.id.sSignUpButton);
        sLoginButton = findViewById(R.id.sLoginButton);

    }

    private Boolean validateName() {
        String value = sName.getEditText().getText().toString();

        if (value.isEmpty()) {
            sName.setError("Field cannot be empty");
            return false;
        }
        else {
            sName.setError(null);
            sName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNum() {
        String value = sPhoneNum.getEditText().getText().toString();

        if (value.isEmpty()) {
            sPhoneNum.setError("Field cannot be empty");
            return false;
        }
        else {
            sPhoneNum.setError(null);
            sPhoneNum.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String value = sEmail.getEditText().getText().toString();
        //String emailPattern = "[a-zA-Z0-9._-] + @[a-z] + \\. + [a-z]+";

        if (value.isEmpty()) {
            sEmail.setError("Field cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            sEmail.setError("Invalid email");
            return false;
        }
        else {
            sEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String value = sPassword.getEditText().getText().toString();
        String passwordRequirements = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        // password requirements: > lower case, > upper case, > number, > special char, > 4 characters total

        if (value.isEmpty()) {
            sPassword.setError("Field cannot be empty");
            return false;
        }
        else if (!value.matches(passwordRequirements)) {
            sPassword.setError("Invalid Password");
            return false;
        }
        else {
            sPassword.setError(null);
            sPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser (View view) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        if (!validateName() | !validatePhoneNum() | !validateEmail() | !validatePassword())
            return;

        // assign all values from text fields on sign up page to variables
        String name = sName.getEditText().getText().toString();
        String phoneNum = sPhoneNum.getEditText().getText().toString();
        String email = sEmail.getEditText().getText().toString();
        String password = sPassword.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, phoneNum, email, password);
        reference.child(phoneNum).setValue(helperClass);

    }

    /*
    * ALL goTo methods start the corresponding activity
    * */
    public void goToCalendar(View view){
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    public void goToNotes(View view) {
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    public void goToGroceries(View view) {
        Intent intent = new Intent(this, Groceries.class);
        startActivity(intent);
    }

    public void goToReminders(View view) {
        Intent intent = new Intent(this, Reminders.class);
        startActivity(intent);
    }
}