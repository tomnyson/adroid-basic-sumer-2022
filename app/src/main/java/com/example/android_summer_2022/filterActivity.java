package com.example.android_summer_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_summer_2022.DAO.HistoryDAO;

public class filterActivity extends AppCompatActivity {
    private EditText edEmail, edSubject, edMessage;
    private Button btnSend;

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        edEmail = findViewById(R.id.edEmail);
        edMessage = findViewById(R.id.edMessage);
        edSubject= findViewById(R.id.edSubject);
        btnSend = findViewById(R.id.btnSend);
        HistoryDAO dao = new HistoryDAO(getBaseContext());
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String message = edMessage.getText().toString();
                String subject = edMessage.getText().toString();
                if(email.trim().equals("") || message.trim().equals("") ||
                subject.trim().equals("")
                ) {
                    Toast.makeText(getBaseContext(), "Dữ liệu ko để trống",
                            Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(getBaseContext(), "email  ko hợp lệ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // ok
                    Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, email);
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    startActivity(Intent.createChooser(intent, "chon app send"));
                }

            }
        });

    }

    public static class ProductActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_product);
        }
    }
}
