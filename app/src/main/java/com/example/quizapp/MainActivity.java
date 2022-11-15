package com.example.quizapp;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button Ans_A, Ans_B, Ans_C, Ans_D;
    Button submit_btn;

    int score=0;
    int totalQuestion = QuestionAnswer.questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.totalQuestions);
        questionTextView = findViewById(R.id.questions);
        Ans_A = findViewById(R.id.Ans_A);
        Ans_B = findViewById(R.id.Ans_B);
        Ans_C = findViewById(R.id.Ans_C);
        Ans_D = findViewById(R.id.Ans_D);
        submit_btn = findViewById(R.id.submit_btn);

        Ans_A.setOnClickListener(this);
        Ans_B.setOnClickListener(this);
        Ans_C.setOnClickListener(this);
        Ans_D.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {

        Ans_A.setBackgroundColor(Color.WHITE);
        Ans_B.setBackgroundColor(Color.WHITE);
        Ans_D.setBackgroundColor(Color.WHITE);
        Ans_D.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.questions[currentQuestionIndex]);
        Ans_A.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        Ans_B.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        Ans_C.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        Ans_D.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.80){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}