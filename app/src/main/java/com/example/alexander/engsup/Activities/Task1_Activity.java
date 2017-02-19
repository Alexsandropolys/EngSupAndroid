package com.example.alexander.engsup.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.alexander.engsup.Controllers.MyOnClickListener;
import com.example.alexander.engsup.R;
import com.example.alexander.engsup.Structure.Progress;
import com.example.alexander.engsup.Structure.Word;

import java.util.ArrayList;

/**
 * Created by Alexander on 26.01.2017.
 */

public class Task1_Activity extends Activity {
    private ArrayList<Word> words;
    private ArrayList<Progress> progresses;
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
