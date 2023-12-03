package com.thiru.counterapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeText;
    private TextView counterText;
    private Button counterButton;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        welcomeText = findViewById(R.id.textview);
        counterText = findViewById(R.id.counter_text);
        counterButton = findViewById(R.id.btn);

        // Add click listener to button
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increment counter and update text view
                counter++;
                counterText.setText(String.valueOf(counter));
            }
        });
    }
}
