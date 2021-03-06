package com.example.finalchallengeday4_project7_update_buildtriviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalchallengeday4_project7_update_buildtriviaapp.data.QuestionBank;
import com.example.finalchallengeday4_project7_update_buildtriviaapp.data.QuestionListAsyncResponse;
import com.example.finalchallengeday4_project7_update_buildtriviaapp.model.Question;
import com.example.finalchallengeday4_project7_update_buildtriviaapp.util.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private CardView cardView;
    private TextView question_textView;
    private Button false_btn;
    private Button true_btn;
    private ImageButton back_imgBtn;
    private ImageButton next_imgBtn;

    private TextView calculateQuestionNumber_textView;
    private TextView currentScore_textView;
    private TextView highestScore_textView;
    private int currentScore = 0;

    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefs = new SharedPrefs(this);

        cardView = findViewById(R.id.cardView);
        question_textView = findViewById(R.id.question_textView);
        false_btn = findViewById(R.id.false_button);
        true_btn = findViewById(R.id.true_button);
        back_imgBtn = findViewById(R.id.previous_ImageButton);
        next_imgBtn = findViewById(R.id.next_ImageButton);

        calculateQuestionNumber_textView = findViewById(R.id.calculateQuestionNumbers_textView);
        currentScore_textView = findViewById(R.id.currentScore_tV);
        highestScore_textView = findViewById(R.id.highestScore_tV);


        cardView.setCardBackgroundColor(getResources().getColor(R.color.colorCard));
        false_btn.setOnClickListener(this);
        true_btn.setOnClickListener(this);
        back_imgBtn.setOnClickListener(this);
        next_imgBtn.setOnClickListener(this);

        int savedHighScore = sharedPrefs.getState();
        highestScore_textView.setText(String.valueOf(savedHighScore));
        Log.d(TAG, "processFinished: Saved High Score::"+savedHighScore);

        currentQuestionIndex =sharedPrefs.getCurrentIndex();

        questionList = new QuestionBank().getQuestionList(new QuestionListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {


                updateQuestion();
                Log.d(TAG, "processFinished: "+questionArrayList.get(currentQuestionIndex).getQuestion()
                +" / "+ questionArrayList.get(currentQuestionIndex).isAnswerTrue());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.false_button:
                checkIfAnswer(false);
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                break;

            case R.id.true_button:
                checkIfAnswer(true);
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                break;

            case R.id.previous_ImageButton:
                if(currentQuestionIndex>0)
                currentQuestionIndex = (currentQuestionIndex - 1) % questionList.size();
                updateQuestion();
                break;

            case R.id.next_ImageButton:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                break;

            default:
        }
    }

    private void updateQuestion(){
        String currentQuestion = questionList.get(currentQuestionIndex).getQuestion();
        question_textView.setText(currentQuestion);
        calculateQuestionNumber_textView.setText(currentQuestionIndex+" / "+questionList.size());


    }

    private void checkIfAnswer(boolean answerIs){
        boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
        if (answerIs == answer){
//            currentScore += 10;
            currentScore_textView.setText(String.valueOf(currentScore+=10));
            Toast.makeText(getApplicationContext(), R.string.correct_answer,Toast.LENGTH_SHORT).show();
        }else{
            currentScore_textView.setText(String.valueOf(currentScore-=5));
            Toast.makeText(getApplicationContext(), R.string.wrong_answer,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

//        Log.d(TAG, "onPause: "+currentScore);
        sharedPrefs.saveState(currentScore);

        sharedPrefs.saveCurrentIndex(currentQuestionIndex);
    }
}