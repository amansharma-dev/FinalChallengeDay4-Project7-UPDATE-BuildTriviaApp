package com.example.finalchallengeday4_project7_update_buildtriviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.finalchallengeday4_project7_update_buildtriviaapp.data.QuestionBank;
import com.example.finalchallengeday4_project7_update_buildtriviaapp.data.QuestionListAsyncResponse;
import com.example.finalchallengeday4_project7_update_buildtriviaapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private List<Question> questionList;
    private int currentQuestionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionList = new QuestionBank().getQuestionList(new QuestionListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                Log.d(TAG, "processFinished: "+questionArrayList.get(currentQuestionIndex).getQuestion()
                +" / "+ questionArrayList.get(currentQuestionIndex).isAnswerTrue());
            }
        });
    }
}