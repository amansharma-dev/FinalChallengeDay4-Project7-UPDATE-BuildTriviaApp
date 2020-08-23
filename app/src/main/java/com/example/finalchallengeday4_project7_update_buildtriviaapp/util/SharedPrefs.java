package com.example.finalchallengeday4_project7_update_buildtriviaapp.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class SharedPrefs {

    private SharedPreferences sharedPreferences;

    public SharedPrefs(Activity activity) {
        this.sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    //saving  score state
    public void saveState(int highScore) {
        int currentScore = highScore;
        int lastScore = sharedPreferences.getInt("KEY_HIGH_SCORE", 0);
        if (currentScore > lastScore) {
            sharedPreferences.edit().putInt("KEY_HIGH_SCORE", highScore).apply();
        }
    }

    //get saved highest score
    public int getState(){
        return sharedPreferences.getInt("KEY_HIGH_SCORE",0);
    }

    //save current index of question
    public void saveCurrentIndex(int currentIndex){
        sharedPreferences.edit().putInt("KEY_INDEX",currentIndex).apply();
    }
    // get current index of question
    public int getCurrentIndex(){
        return sharedPreferences.getInt("KEY_INDEX",0);
    }
}
