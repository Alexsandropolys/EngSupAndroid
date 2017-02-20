package com.example.alexander.engsup.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexander.engsup.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MainActivity extends Activity {
    private static final String MY_SETTINGS = "mySettings";
    private String login_value;
    private String password_value;
    private static final String URL = "jdbc:mysql://192.168.0.71:3306/project";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String TAG = "Exception";
    private static Activity activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        activity = this;
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
                new Registration().execute();
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

    class Registration extends AsyncTask<Void, Void, Connection>{
        @Override
        protected Connection doInBackground(Void... params) {
            Connection connection = null;
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (ClassNotFoundException e) {
                Log.d(TAG, "doInBackground: Class not founded");
            } catch (SQLException e){
                Log.d(TAG, "doInBackground: No connection");
            }
            return connection;
        }

        @Override
        protected void onPostExecute(Connection connection) {
            super.onPostExecute(connection);
            if (connection == null)
                Toast.makeText(activity, "No connection:(", Toast.LENGTH_LONG).show();
                else
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT `id`, `isAdmin`, `unit` FROM `users` WHERE (\"login\" = ? AND \"password\" = ?);"
                );
                preparedStatement.setString(1, ((TextView) findViewById(R.id.login)).getText().toString());
                preparedStatement.setString(2, ((TextView) findViewById(R.id.password)).getText().toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                int isAdmin = -1;
                int id = -1;
                int unit = -1;
                while (resultSet.next()){
                    isAdmin = resultSet.getInt("isAdmin");
                    id = resultSet.getInt("id");
                    unit = resultSet.getInt("unit");
                }
                Intent intent;
                switch (isAdmin){
                    case -1:
                        Toast.makeText(activity, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
                        break;
                    case 0:
                        intent = new Intent(MainActivity.this, ChoosingTaskActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("maxUnit", unit);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, AdminPanelActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        break;
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
