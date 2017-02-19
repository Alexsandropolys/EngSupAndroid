package com.example.alexander.engsup.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by slava on 16.02.2017.
 */

public class AdminPanelActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        // TODO: 16.02.2017 Make admin panel
    }
}
