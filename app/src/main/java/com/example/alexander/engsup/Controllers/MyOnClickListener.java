package com.example.alexander.engsup.Controllers;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.alexander.engsup.R;
import com.example.alexander.engsup.Structure.Word;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Alexander on 26.01.2017.
 */

public class MyOnClickListener implements View.OnClickListener {
    private Activity activity;
    private int right;
    private int selected;
    private int userId;
    private int wordId;

    private static final String URL = "jdbc:mysql://192.168.0.71:3306/project";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String TAG = "Exception";

    public MyOnClickListener(Activity activity, int right, int selected, int userId, Word rightWord) {
        this.activity = activity;
        this.right = right;
        this.selected = selected;
        this.userId = userId;
        this.wordId = rightWord.getNum();
    }

    @Override
    public void onClick(View v) {
        Button answer;
        Button[] option_word = new Button[4];
        option_word[0] = (Button) activity.findViewById(R.id.option_word_1);
        option_word[1] = (Button) activity.findViewById(R.id.option_word_2);
        option_word[2] = (Button) activity.findViewById(R.id.option_word_3);
        option_word[3] = (Button) activity.findViewById(R.id.option_word_4);


        if (right == selected) {
            answer = (Button) activity.findViewById(R.id.option_word_1);
            answer.setBackgroundColor(Color.GREEN);
            for (int i = 1; i < option_word.length; i++) {
                option_word[i].setEnabled(false);
            }
        } else {
            answer = (Button) activity.findViewById(v.getId());
            answer.setBackgroundColor(Color.RED);
            for (int i = 0; i < option_word.length; i++) {
                option_word[i].setEnabled(false);
            }
            answer.setEnabled(true);
        }
    }


    private class Task extends AsyncTask<Boolean, Void, Connection> {
        private boolean increasing;

        @Override
        protected Connection doInBackground(Boolean... params) {
            Connection connection = null;
            increasing = params[0];
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (ClassNotFoundException e) {
                Log.d(TAG, "doInBackground: Class not founded");
            } catch (SQLException e) {
                Log.d(TAG, "doInBackground: No connection");
            }
            return connection;
        }

        @Override
        protected void onPostExecute(Connection connection) {
            super.onPostExecute(connection);// TODO: 20.02.2017 Возможно, нужно убрать
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE `progress` SET points = points + ? WHERE userId = ? AND wordId = ?"
                );
                statement.setInt(2, userId);
                statement.setInt(3, wordId);
                if (increasing)
                    statement.setInt(1, 1);
                else
                    statement.setInt(1, -3);

                statement.execute();
                statement.close();

                statement = connection.prepareStatement(
                        "UPDATE `progress` SET status = 0 " +
                                "WHERE userId = ? AND wordId = ? AND points >= 80"
                );
                statement.execute();
                statement.close();

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
}
