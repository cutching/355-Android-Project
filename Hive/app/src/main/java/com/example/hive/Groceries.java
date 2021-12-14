package com.example.hive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Groceries extends AppCompatActivity {

    Button addGrocery;

    ArrayList<String> items = new ArrayList<>();
    ListView groceryListView;
    ArrayAdapter<String> groceryAdapter;
    String item;
    EditText editGrocery;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("groceries");

        groceryListView = findViewById(R.id.groceryListView);
        items = new ArrayList<>();

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    String item = ds.getValue().toString();
                    items.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        reference.addListenerForSingleValueEvent(eventListener);

        groceryAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        groceryListView.setAdapter(groceryAdapter);
        groceryAdapter.notifyDataSetChanged();
        
        groceryListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Groceries.this, "Successfully deleted " +  items.get(position), Toast.LENGTH_SHORT).show();
                items.remove(position);
                groceryAdapter.notifyDataSetChanged();
                reference.setValue(items);
                return false;
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(Groceries.this);
        addGrocery = findViewById(R.id.addGroceryButton);
        addGrocery.setOnClickListener(v -> {

            final View groceryPopup = getLayoutInflater().inflate(R.layout.grocery_popup, null);

            builder.setView(groceryPopup)
                    .setTitle("Add a Grocery Item")
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            item = editGrocery.getText().toString();
                            if (item == null || item.length() ==0) {
                                Toast.makeText(Groceries.this, "Enter a Grocery Item", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                items.add(item);
                                groceryAdapter.notifyDataSetChanged();
                                reference.setValue(items);
                            }

                        }
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> {

                    });

            editGrocery = groceryPopup.findViewById(R.id.popupText);

            AlertDialog dialog = builder.create();
            dialog.show();

        });

    }

}