package com.example.alexander.engsup.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alexander.engsup.Activities.Task1_Activity;
import com.example.alexander.engsup.Structure.Word;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by slava on 17.02.2017.
 */

public class ChoosingListener implements View.OnClickListener {
    private  int unitIndex;
    private Activity activity;
    private int userId;
    private int maxUnit;
    private Random rnd = new Random();
    private int start;
    private int finish;
    private static final String URL = "jdbc:mysql://192.168.0.71:3306/project";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String TAG = "Exception";

    private Intent task1(Connection connection, Activity newActivity){
        Intent intent = null;
        try {
             intent = new Intent(activity, newActivity.getClass());

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM `words` WHERE \"num\" IN " +
                            "(SELECT `wordId` FROM `progress` WHERE " +
                            "(\"userId\" = ? AND \"status\" = 1 AND \"wordId\" >= ? AND \"wordId\" <= ?)) " +
                            "ORDER BY RAND() LIMIT 1;"
            );
            statement.setInt(1, userId);
            statement.setInt(2, start);
            statement.setInt(3, finish);

            Word rightWord = null;
            ResultSet set = statement.executeQuery();
            statement.close();
            // TODO: 19.02.2017 возможно, нужно переставить close в другое место

            if (!set.next()){
                //проверка на полную решенность юнита
                statement = connection.prepareStatement(
                        "SELECT * FROM `words` WHERE \"num\" IN " +
                                "(SELECT `wordId` FROM `progress` WHERE " +
                                "(\"userId\" = ? AND \"status\" = 0 AND \"wordId\" >= ? AND \"wordId\" <= ?)) " +
                                "ORDER BY RAND() LIMIT 1;"
                );
                statement.setInt(1, userId);
                statement.setInt(2, start);
                statement.setInt(3, finish);

                set = statement.executeQuery();
                statement.close();
                if (set.next()){
                    Toast.makeText(activity, "Completed", Toast.LENGTH_LONG).show();
                    return null;
                }
//                добавление строк
//                 TODO: 19.02.2017 возможно, очень межленно
                for (int i = start; i <= finish; i++){
                    statement = connection.prepareStatement(
                            "INSERT INTO `progress`(`userId`, `wordId`, `points`, `status`) VALUES (?,?,0,1);"
                    );
                    statement.setInt(1, userId);
                    statement.setInt(2, i);
                    statement.execute();
                    statement.close();
                }
//               конец добавления

                statement = connection.prepareStatement(
                        "SELECT * FROM `words` WHERE \"num\" IN " +
                                "(SELECT `wordId` FROM `progress` WHERE " +
                                "(\"userId\" = ? AND \"status\" = 1 AND \"wordId\" >= ? AND \"wordId\" <= ?)) " +
                                "ORDER BY RAND() LIMIT 1;"
                );
                statement.setInt(1, userId);
                statement.setInt(2, start);
                statement.setInt(3, finish);

                set = statement.executeQuery();
                set.next();
            }


            int id = -1;
            do {
                id = set.getInt("num");
                rightWord = new Word(set.getInt("num"), set.getString("lang1"), set.getString("def1"),
                        set.getString("lang2"), set.getString("def2"), set.getInt("synId"),
                        set.getInt("oppid"), set.getString("pos"), set.getString("sent1"),
                        set.getString("sent2"));
            } while (set.next());

            statement = connection.prepareStatement(
                    "SELECT * FROM `words` WHERE " +
                            "(\"pos\" IN (SELECT `pos` FROM `words` WHERE \"num\" = ?)" +
                            " AND \"num\" NOT IN (?)) ORDER BY RAND() LIMIT 3;"
            );
            statement.setInt(1, id);
            statement.setInt(2, id);

            ArrayList<Word> words = new ArrayList<>();
            set = statement.executeQuery();
            statement.close();

            while (set.next()){
                words.add(new Word(set.getInt("num"), set.getString("lang1"), set.getString("def1"),
                        set.getString("lang2"), set.getString("def2"), set.getInt("synId"),
                        set.getInt("oppid"), set.getString("pos"), set.getString("sent1"),
                        set.getString("sent2")));
            }
            words.add(rightWord);
            intent.putExtra("words", words);
            intent.putExtra("rightWord", rightWord);
            intent.putExtra("userId", userId);
            intent.putExtra("unitIndex", unitIndex);
            intent.putExtra("maxUnit", maxUnit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return intent;
    }

    public ChoosingListener(int unitIndex, Activity activity, int userId, int maxUnit) {
        this.unitIndex = unitIndex;
        this.activity = activity;
        this.userId = userId;
        this.maxUnit = maxUnit;
    }

    @Override
    public void onClick(View v) {
        new Task().execute(unitIndex);
    }

    private class Task extends AsyncTask<Integer, Void, Connection> {
        private int unitId;

        @Override
        protected Connection doInBackground(Integer... params) {
            Connection connection = null;
            unitId = params[0];
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
            try {
                Intent intent = null;
                PreparedStatement statement1 = connection.prepareStatement(
                        "SELECT `startBookId`, `finishBookId` FROM `unit` WHERE \"id\" = ?;"
                );
                statement1.setInt(1, unitId);
                ResultSet resultSet = statement1.executeQuery();
                start = resultSet.getInt("startBookId");
                finish = resultSet.getInt("finishBookId");

                statement1.close();

                PreparedStatement statement;
                ResultSet set;

                int taskNum = rnd.nextInt(4);
                switch (taskNum){
                    // TODO: 17.02.2017 Make 2-4 Tasks
                    case 0:
                        intent = task1(connection, new Task1_Activity());

                        break;
                    case 1:
//                        intent = new Intent(activity, Task2_Activity.class);
                        break;
                    case 2:
//                        intent = new Intent(activity, Task3_Activity.class);
                        break;
                    case 3:
//                        intent = new Intent(activity, Task4_Activity.class);
                        break;
                }

                connection.close();
                if (intent != null)
                    activity.startActivity(intent);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
