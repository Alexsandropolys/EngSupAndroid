package com.example.alexander.engsup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by Alexander on 26.01.2017.
 */

public class ChoosingTaskActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_task);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        int unit = intent.getIntExtra("unit", -1);
        Button[] buttons = new Button[]{
                (Button) findViewById(R.id.set1Button),
//                (Button) findViewById(R.id.set2Button),
//                (Button) findViewById(R.id.set3Button),
//                (Button) findViewById(R.id.set4Button),
//                (Button) findViewById(R.id.set5Button),
                // TODO: 16.02.2017 Make new buttons
        };

        for (int i = 0; i < buttons.length; i++){
            if (i < unit)
                buttons[i].setEnabled(true);
            else
                buttons[i].setEnabled(false);
            buttons[i].setOnClickListener(new ChoosingListener(i + 1, this));
        }
    }

}
