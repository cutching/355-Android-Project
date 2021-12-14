package com.example.hive;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserHelperClass{

    String name, phoneNum, email, password;
    int houseCode = -1;

    public UserHelperClass(String name, String phoneNum, String email, String password) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(int houseCode) {
        this.houseCode = houseCode;
    }

    public boolean checkExistingHouseCode(int houseCode){
        final boolean[] result = {false};
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("user").orderByChild("houseCode").equalTo(houseCode);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "houseCode" node with all children matching the houseCode
                   result[0] = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return result[0];
    }



    public void generateNewHouseCode(){
        int temp = -1;
        if(houseCode == -1){
            do{
                temp = (int)(Math.random()*9000)+1000;
            }while(checkExistingHouseCode(temp));

            setHouseCode(temp);
        }

    }
}