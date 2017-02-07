package com.example.alexander.engsup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by Alexander on 26.01.2017.
 */

public class Task1_Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task1);

        Button[] option_word = new Button[4];
        option_word[0] = (Button) findViewById(R.id.option_word_1);
        option_word[1] = (Button) findViewById(R.id.option_word_2);
        option_word[2] = (Button) findViewById(R.id.option_word_3);
        option_word[3] = (Button) findViewById(R.id.option_word_4);


        for (int i = 0; i<option_word.length; i++){
            option_word[i].setOnClickListener(new MyOnClickListener(this));
        }
    }
}
