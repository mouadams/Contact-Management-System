package com.example.contact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contact.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        TextView tvHello = findViewById(R.id.welcome);
        Button btnBack = findViewById(R.id.btnBack);


        String name = getIntent().getStringExtra("user_name");
        if (name != null && !name.isEmpty()) {
            tvHello.setText("Hello " + name + "!");
        }


        btnBack.setOnClickListener(view -> {

            Intent intent = new Intent(ContactActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button Display = findViewById(R.id.Display);
        Display.setOnClickListener(view -> {
            Intent ContactIntent = new Intent(ContactActivity.this, ContactListActivity.class);
            startActivity(ContactIntent);
        });

        Button addContact = findViewById(R.id.AddContact);

        addContact.setOnClickListener(view -> {
            Intent btnAdd = new Intent(ContactActivity.this, FormContactActivity.class);
            startActivity(btnAdd);

        });
    }
}