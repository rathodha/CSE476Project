package msu.edu.rathodha.cse476project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);

        Button hairButton = findViewById(R.id.hairbutton);
        hairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialPage.this, Hair.class);
                startActivity(intent);
                finish();
            }
        });

        Button nailsButton  = findViewById(R.id.nailsbutton);
        nailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialPage.this, Nail.class);
                startActivity(intent);
                finish();
            }
        });

        Button tutoringButton  = findViewById(R.id.tutoringbutton);
        tutoringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialPage.this, Tutoring.class);
                startActivity(intent);
                finish();
            }
        });
    }
}