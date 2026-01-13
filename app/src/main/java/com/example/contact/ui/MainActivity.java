package com.example.contact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contact.helpers.DataBase;
import com.example.contact.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputName = findViewById(R.id.inputName);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnContact = findViewById(R.id.btnContact);

        // OK button
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBase.getInstance(getApplicationContext()).getWritableDatabase();

                java.io.File dbFile = getApplicationContext().getDatabasePath("Contact.db");

                if (dbFile.exists()) {
                    Toast.makeText(MainActivity.this,
                            "Database created successfully! ✅", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                    intent.putExtra("user_name", name);
                    startActivity(intent);
                }
            }
        });
    }
}
