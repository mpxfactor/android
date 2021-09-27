package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionView;
    TextView timerView, resultView;
    Button goButton;
    int locationOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int score = 0;
    int numberOfQuestions = 0;

    TextView scoreTextView;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    Button playAgain;

    ConstraintLayout gameLayout;


    public void goButton (View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(timerView);
    }

    public void chooseAnswer (View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString() )){
            resultView.setText("Correct!");
            score++;

        } else {
            resultView.setText("Wrong :)");
        }

        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + '/' + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion () {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        questionView = findViewById(R.id.questionView);
        questionView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add (wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void  playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + '/' + Integer.toString(numberOfQuestions));

        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);
        resultView.setText("");


        new CountDownTimer (3100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerView.setText (String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultView.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerView = (TextView) findViewById(R.id.timerView);
        resultView = findViewById(R.id.resultView);
        goButton = findViewById(R.id.goButton);
        scoreTextView = findViewById(R.id.scoreTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        
        playAgain = findViewById(R.id.playAgain);
        gameLayout= findViewById(R.id.gameLayout);


        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        
    }

}