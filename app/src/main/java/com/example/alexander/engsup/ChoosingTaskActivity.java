package com.example.alexander.engsup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alexander on 26.01.2017.
 */

public class ChoosingTaskActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_task);
        Button set1Button = (Button) findViewById(R.id.set1Button);
        set1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosingTaskActivity.this, Task1_Activity.class);
                startActivity(intent);
            }
        });
    }
}
