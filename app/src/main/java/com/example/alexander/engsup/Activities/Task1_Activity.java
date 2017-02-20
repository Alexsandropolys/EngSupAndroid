package com.example.alexander.engsup.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexander.engsup.Controllers.MyOnClickListener;
import com.example.alexander.engsup.R;
import com.example.alexander.engsup.Structure.Progress;
import com.example.alexander.engsup.Structure.Word;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alexander on 26.01.2017.
 */

public class Task1_Activity extends Activity {
    private int unitIndex;
    private int userId;
    private int maxUnit;
    private Word rightWord;
    private ArrayList<Word> words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task1);

        Intent intent = getIntent();
        unitIndex = intent.getIntExtra("unitIndex", -1);
        userId = intent.getIntExtra("userId", -1);
        maxUnit = intent.getIntExtra("maxUnit", -1);
        rightWord = intent.getParcelableExtra("rightWord");
        words = intent.getParcelableArrayListExtra("words");

        Collections.shuffle(words);

        TextView textView = (TextView) findViewById(R.id.trans_word);
        textView.setText(rightWord.getLang1());

        Button[] option_word = new Button[4];
        option_word[0] = (Button) findViewById(R.id.option_word_1);
        option_word[1] = (Button) findViewById(R.id.option_word_2);
        option_word[2] = (Button) findViewById(R.id.option_word_3);
        option_word[3] = (Button) findViewById(R.id.option_word_4);

        int right = -1;
        for (int i = 0; i < 4; i++){
            option_word[i].setText(words.get(i).getLang2());
            if (rightWord.equals(words.get(i)))
                right = i;
        }



        for (int i = 0; i<option_word.length; i++){
            option_word[i].setOnClickListener(new MyOnClickListener(this, right, i, userId, rightWord));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChoosingTaskActivity.class);
        intent.putExtra("id", userId);
        intent.putExtra("maxUnit", maxUnit);
        startActivity(intent);
    }
}
