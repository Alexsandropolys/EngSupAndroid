package com.example.alexander.engsup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class main extends Activity {
    private static final String MY_SETTINGS = "mySettings";
    private String login_value;
    private String password_value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        Button okButton = (Button) findViewById(R.id.okButton);
        TextView login = (TextView) findViewById(R.id.login);
        TextView password = (TextView) findViewById(R.id.password);
        Toast.makeText(this, "А вы ничего и не делали)", Toast.LENGTH_LONG).show();
        if(sp.contains("login_value")){
            login_value = sp.getString("login_value","");
            login.setText(login_value);
            if (sp.contains("password_value")){
                password_value = sp.getString("password_value", "");
                password.setText(password_value);
            }
        }


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                Intent intent = new Intent(main.this, ChoosingTaskActivity.class);
                startActivity(intent);
                CheckBox rememberMe = (CheckBox) findViewById(R.id.rememberMe);
                if (rememberMe.isChecked()){
                    login_value = ((TextView) findViewById(R.id.login)).getText().toString();
                    password_value = ((TextView) findViewById(R.id.password)).getText().toString();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("login_value", login_value);
                    editor.putString("password_value", password_value);
                    editor.apply();
                }
            }
        });

    }
}
