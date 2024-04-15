package msu.edu.rathodha.cse476project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RequestTable extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_table);

        tableLayout = findViewById(R.id.tableLayout);

        addRequestToTable();

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestTable.this, InitialPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void addRequestToTable() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String date = sharedPreferences.getString("date", "");
        String time = sharedPreferences.getString("time", "");

        if (!date.isEmpty() && !time.isEmpty()) {
            TableRow row = new TableRow(this);
            TextView service = createTextView("Service");
            TextView dateView = createTextView(date);
            TextView timeView = createTextView(time);
            row.addView(service);
            row.addView(dateView);
            row.addView(timeView);
            tableLayout.addView(row);
        }
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        return textView;
    }
}
