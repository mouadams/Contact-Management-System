package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listContact = findViewById(R.id.Listveiw);
        DataBase db = DataBase.getInstance(this);

        ArrayList<String> contacts = db.getAllContacts();



        // String[] Items = {"contact 1", "contact2", "contact3"};

        ArrayAdapter<String> contactAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contacts

        );
        listContact.setAdapter(contactAdapter);


        listContact.setOnItemClickListener((parent, view, position, id) -> {

            Contact selectedContact = (Contact) parent.getItemAtPosition(position);

            Intent intent = new Intent(
                    ContactListActivity.this,
                    ContactDetailActivity.class
            );

            intent.putExtra("contact_id", selectedContact.getId());
            startActivity(intent);
        });

    }
}