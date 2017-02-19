package com.example.alexander.engsup.Controllers;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.example.alexander.engsup.R;

/**
 * Created by Alexander on 26.01.2017.
 */

public class MyOnClickListener implements View.OnClickListener {
    private Activity activity;
    private int right;
    public MyOnClickListener(Activity activity, int right){
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {
        Button answer;
        Button[] option_word = new Button[4];
        option_word[0] = (Button) activity.findViewById(R.id.option_word_1);
        option_word[1] = (Button) activity.findViewById(R.id.option_word_2);
        option_word[2] = (Button) activity.findViewById(R.id.option_word_3);
        option_word[3] = (Button) activity.findViewById(R.id.option_word_4);



        if (v.getId() == R.id.option_word_1){
            answer = (Button)activity.findViewById(R.id.option_word_1);
            answer.setBackgroundColor(Color.GREEN);
            for (int i = 1; i<option_word.length;i++){
                option_word[i].setEnabled(false);
            }
        }
        else{
            answer = (Button)activity.findViewById(v.getId());
            answer.setBackgroundColor(Color.RED);
            for(int i = 0; i<option_word.length;i++){
                option_word[i].setEnabled(false);
            }
            answer.setEnabled(true);
        }
    }
}
