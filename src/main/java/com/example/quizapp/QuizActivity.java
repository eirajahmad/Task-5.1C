package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.FinalScoreActivity;
import com.example.quizapp.R;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerGroup;
    private Button submitButton;
    private ProgressBar progressBar;

    private int currentQuestionIndex = 0;
    private int totalQuestions = 5;

    // Array to hold the questions
    private String[] questions = {
            "Who won the English Premier League title in the 2020-2021 season?",
            "Which club holds the record for the most EPL titles won?",
            "Who is the all-time top scorer in the English Premier League?",
            "Which team has won the most consecutive EPL titles?",
            "Which player holds the record for the most assists in a single EPL season?"
    };

    // Array to hold the options for each question
    private String[][] options = {
            {"Manchester City", "Liverpool", "Manchester United", "Chelsea"},
            {"Manchester United", "Liverpool", "Arsenal", "Chelsea"},
            {"Alan Shearer", "Wayne Rooney", "Thierry Henry", "Andy Cole"},
            {"Manchester United", "Chelsea", "Arsenal", "Manchester City"},
            {"Thierry Henry", "Kevin De Bruyne", "Mesut Ozil", "Frank Lampard"}
    };

    // Array to hold the correct answers
    private int[] correctAnswers = {0, 0, 0, 0, 1}; // Index of correct options

    private int correctCount = 0;
    private int wrongCount = 0;
    private TextView welcomeTextView;
    private TextView nameTextView;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        questionTextView = findViewById(R.id.questionTextView);
        answerGroup = findViewById(R.id.answerGroup);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);
        welcomeTextView = findViewById(R.id.welcomeTextView);
        nameTextView = findViewById(R.id.nameTextView);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");

        // Display welcome message and user's name
        welcomeTextView.setText("Welcome, ");
        nameTextView.setText(userName);

        // Set up initial question and choices
        setupQuestion();

        // Set up submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answer and update UI
                checkAnswer();
                // Move to next question or finish quiz
                moveToNextQuestionOrFinishQuiz();
            }
        });
    }

    private void setupQuestion() {
        // Set question text
        questionTextView.setText(questions[currentQuestionIndex]);
        // Clear existing radio buttons
        answerGroup.removeAllViews();
        // Add answer choices dynamically
        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[currentQuestionIndex][i]);
            answerGroup.addView(radioButton);
        }
        // Update progress bar
        int progress = ((currentQuestionIndex + 1) * 100) / totalQuestions;
        progressBar.setProgress(progress);
        updateProgressBar();
    }

    private void checkAnswer() {
        int selectedRadioButtonId = answerGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int selectedAnswerIndex = answerGroup.indexOfChild(selectedRadioButton);
            if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
                selectedRadioButton.setBackgroundColor(Color.GREEN); // Correct answer
                correctCount++;
            } else {
                selectedRadioButton.setBackgroundColor(Color.RED); // Incorrect answer
                wrongCount++;
            }
            disableRadioButtons(); // Disable further selection until next question
        }
    }
    private void disableRadioButtons() {
        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            answerGroup.getChildAt(i).setEnabled(false);
        }
    }
    private void goToFinalScoreActivity() {
        Intent intent = new Intent(QuizActivity.this, FinalScoreActivity.class);
        intent.putExtra("correctCount", correctCount);
        intent.putExtra("wrongCount", wrongCount);
        intent.putExtra("userName", userName);

        startActivity(intent);
        finish(); // Close this activity
    }

    private void moveToNextQuestionOrFinishQuiz() {
        currentQuestionIndex++;
        if (currentQuestionIndex < totalQuestions) {
            setupQuestion();
        } else {
            // Quiz completed, navigate to final score activity
            goToFinalScoreActivity();
        }
    }
    private void updateProgressBar() {
        int progress = (currentQuestionIndex + 1) * 100 / totalQuestions;
        progressBar.setProgress(progress); // Ensure your progress bar updates here
    }
}
