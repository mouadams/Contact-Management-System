package com.example.contact;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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



        EditText etName = findViewById(R.id.name);
        EditText etPhone = findViewById(R.id.phoneNumber);
        Button sub = findViewById(R.id.submit);

        sub.setOnClickListener(v -> {

            String nameInput = etName.getText().toString().trim();
            String phoneInput = etPhone.getText().toString().trim();


            if (nameInput.isEmpty() || phoneInput.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = DataBase.getInstance(this).getWritableDatabase();
            db.execSQL("INSERT INTO Contact (name, phone) VALUES ('" + nameInput + "', '" + phoneInput + "')");



            //Contact newContact = new Contact(nameInput, phoneInput);
            //ContactDB.ContactArray.add(String.valueOf(newContact));

            Toast.makeText(this, "Bien ajouté : " + nameInput, Toast.LENGTH_SHORT).show();


            Intent BackToContact = new Intent(FormContactActivity.this, ContactActivity.class);
            startActivity(BackToContact);
        });



        }
}