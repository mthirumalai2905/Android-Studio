package com.example.unitconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView welcomeTextView, resultTextView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcome_textView);
        resultTextView = findViewById(R.id.result_textView);
        View btn = findViewById(R.id.btn);
        editText = findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double kilosEntered = Double.parseDouble(editText.getText().toString());
                convertToPounds(kilosEntered);
            }
        });
    }

    public void convertToPounds(double kilos) {
        double poundsResult = kilos * 2.20462;
        String resultString = String.format("%.2f", poundsResult);
        resultTextView.setText(resultString);
    }
}
