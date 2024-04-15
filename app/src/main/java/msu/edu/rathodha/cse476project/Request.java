package msu.edu.rathodha.cse476project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Request extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String displayName = sharedPreferences.getString("displayName", "");

        TextView nameTextView = findViewById(R.id.textViewNameValue);
        nameTextView.setText(displayName);

        TextView textViewDateValue = findViewById(R.id.textViewDateValue);
        TextView textViewTimeValue = findViewById(R.id.textViewTimeValue);
        TextView textViewCommentsValue = findViewById(R.id.textViewCommentsValue);

        textViewDateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog(textViewDateValue);
            }
        });

        textViewTimeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog(textViewTimeValue);
            }
        });

        textViewCommentsValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog(textViewCommentsValue);
            }
        });

        Button requestService = findViewById(R.id.buttonRequestService);
        requestService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if date and time are not empty
                String date = textViewDateValue.getText().toString().trim();
                String time = textViewTimeValue.getText().toString().trim();
                if (!date.isEmpty() && !time.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Request.this, InitialPage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Required Fields Missing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button cancelButton = findViewById(R.id.requestCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Request.this, InitialPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showInputDialog(final TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Value");

        // Create an EditText view for user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString().trim();
                textView.setText(value);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
