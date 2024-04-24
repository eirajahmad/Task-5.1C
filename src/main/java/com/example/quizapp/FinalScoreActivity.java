package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class FinalScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        // Retrieve the correct and wrong answer counts passed from QuizActivity
        Intent intent = getIntent();
        int correctCount = intent.getIntExtra("correctCount", 0);
        int wrongCount = intent.getIntExtra("wrongCount", 0);
        final String userName = intent.getStringExtra("userName");

        // Display the final score
        TextView finalScoreTextView = findViewById(R.id.finalScoreTextView);
        finalScoreTextView.setText("Correct Answers: " + correctCount + "\nWrong Answers: " + wrongCount);

        // Set up buttons
        Button takeNewQuizButton = findViewById(R.id.takeNewQuizButton);
        Button finishButton = findViewById(R.id.finishButton);

        takeNewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to MainActivity for a new quiz
                Intent newQuizIntent = new Intent(FinalScoreActivity.this, QuizActivity.class);
                newQuizIntent.putExtra("userName", userName);
                startActivity(newQuizIntent);
                finish(); // Close this activity
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the app
                finishAffinity();
            }
        });
    }
}
