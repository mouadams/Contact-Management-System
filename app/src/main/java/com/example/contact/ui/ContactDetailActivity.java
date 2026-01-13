package com.example.contact.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contact.model.Contact;
import com.example.contact.helpers.DataBase;
import com.example.contact.R;

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

        int contactId = getIntent().getIntExtra("contact_id", -1);

        if (contactId != -1) {
            DataBase db = DataBase.getInstance(this);
            Contact contact = db.getContactById(contactId);

            if (contact != null) {
                inputName.setText(contact.getName());
                inputPhone.setText(contact.getPhone());
            } else {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show();
                finish();
            }
        }


        btnUpdate.setOnClickListener(view -> {
            String newName = inputName.getText().toString().trim();
            String newPhone = inputPhone.getText().toString().trim();

            if (newName.isEmpty() || newPhone.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            new AlertDialog.Builder(this).setTitle("UPDATE")
                    .setMessage("ARE YOU SURE YOU WANT TO UPDATE")
                    .setPositiveButton("UPDATE", (d , w )->{
                        boolean updated = DataBase.getInstance(this)
                                .updateContact(contactId, newName, newPhone);

                        if (updated) {
                            Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show();
                            finish(); // go back to list
                        } else {
                            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("CANCEL",null ).
                    show();


        });



        btnDelete.setOnClickListener(view -> {
         new AlertDialog.Builder(this).setTitle("Delete")
                 .setMessage("Are you sure !!")
                 .setPositiveButton("Delete", (d, w)->{
                     boolean deleted = DataBase.getInstance(this)
                             .deleteContact(contactId);

                     if (deleted) {
                         Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show();
                         finish();
                     } else {
                         Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
                     }


                 })
                 .setNegativeButton("Cancel", null)
                 .show();


        });





    }
}