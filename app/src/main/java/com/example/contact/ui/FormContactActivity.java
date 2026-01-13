package com.example.contact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contact.helpers.DataBase;
import com.example.contact.R;

public class FormContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        EditText Name = findViewById(R.id.name);
        EditText Phone = findViewById(R.id.phoneNumber);
        Button sub = findViewById(R.id.submit);

        sub.setOnClickListener(v -> {

            String nameInput = Name.getText().toString().trim();
            String phoneInput = Phone.getText().toString().trim();


            if (nameInput.isEmpty() ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            DataBase.getInstance(this).addContact(nameInput, phoneInput);

            Toast.makeText(this, "Contact added: " + nameInput, Toast.LENGTH_SHORT).show();

            Intent backToContact = new Intent(FormContactActivity.this, ContactActivity.class);

            startActivity(backToContact);
        });



    }
}