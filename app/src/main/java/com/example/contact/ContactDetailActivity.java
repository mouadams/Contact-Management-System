package com.example.contact;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText inputName = findViewById(R.id.update_name);
        EditText inputPhone = findViewById(R.id.update_phoneNumber);
        Button btnUpdate = findViewById(R.id.update_cnt);
        Button btnDelete = findViewById(R.id.delete_cnt);

        String data = getIntent().getStringExtra("contact_data");

        String[] parts = data.split(":");
        int contactId = Integer.parseInt(parts[0]);

        String[] info = parts[1].split(" - ");
        inputName.setText(info[0].trim());
        inputPhone.setText(info[1].trim());


        btnUpdate.setOnClickListener(view -> {
            String newName = inputName.getText().toString().trim();
            String newPhone = inputPhone.getText().toString().trim();

            if (newName.isEmpty() || newPhone.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean updated = DataBase.getInstance(this)
                    .updateContact(contactId, newName, newPhone);

            if (updated) {
                Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show();
                finish(); // go back to list
            } else {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });



        btnDelete.setOnClickListener(view -> {
            boolean deleted = DataBase.getInstance(this)
                    .deleteContact(contactId);

            if (deleted) {
                Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
            }
        });





    }
}