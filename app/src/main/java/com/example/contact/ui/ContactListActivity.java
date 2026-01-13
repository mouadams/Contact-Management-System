package com.example.contact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contact.model.Contact;
import com.example.contact.helpers.DataBase;
import com.example.contact.R;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private ListView listContact;
    private DataBase db;
    private ArrayAdapter<Contact> contactAdapter;
    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        listContact = findViewById(R.id.Listveiw);
        db = DataBase.getInstance(this);

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

    @Override
    protected void onResume() {
        super.onResume();
        contacts = db.getAllContacts();
        contactAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contacts
        );
        listContact.setAdapter(contactAdapter);
    }
}