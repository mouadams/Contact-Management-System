package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        TextView tvHello = findViewById(R.id.tvHello);
        Button btnBack = findViewById(R.id.btnBack);


        String name = getIntent().getStringExtra("user_name");
        if (name != null && !name.isEmpty()) {
            tvHello.setText("Hello " + name + "!");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
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
