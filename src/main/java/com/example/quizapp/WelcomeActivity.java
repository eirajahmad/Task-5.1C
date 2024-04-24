package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        // Initialize views

        nameEditText = findViewById(R.id.nameEditText);
        startButton = findViewById(R.id.startButton);

        // Set up start button click listener
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString().trim();
                if (!userName.isEmpty()) {
                    // Start the quiz activity and pass the user's name
                    Intent intent = new Intent(WelcomeActivity.this, QuizActivity.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                }
            }
        });
    }
}