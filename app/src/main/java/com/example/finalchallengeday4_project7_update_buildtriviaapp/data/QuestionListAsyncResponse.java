package com.example.finalchallengeday4_project7_update_buildtriviaapp.data;

import com.example.finalchallengeday4_project7_update_buildtriviaapp.model.Question;

import java.util.ArrayList;

public interface QuestionListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
