package com.example.guessandlearn;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class EnglishNormal extends English {

    private TextView questionTextView;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private TextView scoreTextView;
    private TextView questionNumberTextView;
    private TextView timerTextView;

    private List<Question> questionList;
    private int currentQuestionIndex;
    private int score;
    private int numCorrectAnswers;
    private int numQuestionsAnswered;
    private boolean isGameOver;
    private CountDownTimer timer;

    private static final long COUNTDOWN_TIME_MS = 60000; // 1 minute
    private static final int MIN_CORRECT_ANSWERS = 20; // Minimum number of correct answers to pass

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutenglishnormal);

        // Initialize UI elements
        questionTextView = questionTextView.findViewById(R.id.questionTextView);
        option1Button = option1Button.findViewById(R.id.option1Button);
        option2Button = option2Button.findViewById(R.id.option2Button);
        option3Button = option3Button.findViewById(R.id.option3Button);
        scoreTextView = scoreTextView.findViewById(R.id.scoreTextView);
        questionNumberTextView = questionNumberTextView.findViewById(R.id.questionNumberTextView);
        timerTextView = timerTextView.findViewById(R.id.timerTextView);

        // Create the list of questions
        questionList = new ArrayList<>();
        questionList.add(new Question("What is the capital of the United States?", "Washington D.C.", "New York City", "Los Angeles"));
        questionList.add(new Question("What is the largest continent?", "Asia", "Africa", "Europe"));
        questionList.add(new Question("What is the highest mountain in the world?", "Mount Everest", "K2", "Kilimanjaro"));
        // Add more questions here...

        // Shuffle the question list
        Collections.shuffle(questionList);

        // Initialize variables
        currentQuestionIndex = 0;
        score = 0;
        numCorrectAnswers = 0;
        numQuestionsAnswered = 0;
        isGameOver = false;

        // Display the first question
        displayQuestion(currentQuestionIndex);

        // Start the timer
        startTimer();
    }

    private void displayQuestion(int index) {
        // Display the question
        Question question = questionList.get(index);
        questionTextView.setText(question.getQuestion());

        // Display the answer choices
        option1Button.setText(question.getOption1());
        option2Button.setText(question.getOption2());
        option3Button.setText(question.getOption3());

        // Display the current question number
        int questionNumber = index + 1;
        int totalQuestions = questionList.size();
        questionNumberTextView.setText(String.format(Locale.getDefault(), "Question %d of %d", questionNumber, totalQuestions));
    }

    private void startTimer() {
        timer = new CountDownTimer(COUNTDOWN_TIME_MS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer text view every second
                long secondsRemaining = millisUntilFinished / 1000;
                timerTextView.setText(String.format(Locale.getDefault(), "Time remaining: %d seconds", secondsRemaining));
            }

            @Override
            public void onFinish() {
                // Game over when the timer finishes
                isGameOver = true;
                checkEndGame();
            }
        }.start();
    }

    private void checkAnswer(String answer) {
        if (!isGameOver) {
// Check if the answer is correct
            Question currentQuestion = questionList.get(currentQuestionIndex);
            if (currentQuestion.getAnswer().equals(answer)) {
// Increase the score and the number of correct answers
                score += 10;
                numCorrectAnswers++;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
// Decrease the score
                score -= 5;
                Toast.makeText(this, "Incorrect.", Toast.LENGTH_SHORT).show();
            }

            typescript
            Copy code;
            // Increase the number of questions answered
            numQuestionsAnswered++;

            // Display the new score
            scoreTextView.setText(String.format(Locale.getDefault(), "Score: %d", score));

            // Go to the next question
            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                displayQuestion(currentQuestionIndex);
            } else {
                // End the game when all questions have been answered
                isGameOver = true;
                checkEndGame();
            }
        }
    }

    private void checkEndGame() {
        // Stop the timer
        timer.cancel();

        // Check if the player passed or failed
        if (numCorrectAnswers >= MIN_CORRECT_ANSWERS) {
            Toast.makeText(this, "Congratulations, you passed!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Sorry, you failed.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Cancel the timer to avoid memory leaks
        if (timer != null) {
            timer.cancel();
        }
    }

    private class Question {
        public Question(String s, String s1, String new_york_city, String los_angeles) {

        }

        public int getOption1() {
            return 0;
        }

        public int getQuestion() {
            return 0;
        }

        public int getOption2() {
            return 0;
        }
    }

    private class Copy {
    }
}