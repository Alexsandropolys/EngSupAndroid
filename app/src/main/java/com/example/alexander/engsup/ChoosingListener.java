package com.example.alexander.engsup;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

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
    private ChoosingTaskActivity activity;
    private int userId;
    private Random rnd = new Random();
    private static final String URL = "jdbc:mysql://192.168.0.71:3306/project";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String TAG = "Exception";

    public ChoosingListener(int unitIndex, ChoosingTaskActivity activity, int userId) {
        this.unitIndex = unitIndex;
        this.activity = activity;
        this.userId = userId;
    }

    @Override
    public void onClick(View v) {
        new Task().execute(unitIndex);
    }

    class Task extends AsyncTask<Integer, Void, Connection> {
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
                PreparedStatement statement2 = connection.prepareStatement(
//                        выборка всех слов топика
                        "SELECT * FROM `words` WHERE \"num\" IN " +
                                "(SELECT `wordId` FROM `progress` WHERE " +
                                "(\"userId\" = ? AND \"status\" = 1 AND \"wordId\" >= ? AND \"wordId\" <= ?))"

//                        выборка 1 слова
//                        "SELECT * FROM `words` WHERE \"num\" IN " +
//                                "(SELECT `wordId` FROM `progress` WHERE " +
//                                "(\"userId\" = ? AND \"status\" = 1 AND \"wordId\" >= ? AND \"wordId\" <= ?)) " +
//                                "ORDER BY RAND() LIMIT 1;"
                );

                statement1.setInt(1, unitId);
                ResultSet resultSet = statement1.executeQuery();
                int start = resultSet.getInt("startBookId");
                int finish = resultSet.getInt("finishBookId");


                statement2.setInt(1, userId);
                statement2.setInt(2, start);
                statement2.setInt(3, finish);
                resultSet = statement2.executeQuery();

                statement1.close();
                statement2.close();

                int taskNum = rnd.nextInt(4);
                switch (taskNum){
                    case 0:
                        intent = new Intent(activity, Task1_Activity.class);

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
//                для 1 слова
//                if (intent != null) {
//                    intent.putExtra("word", new Word(resultSet.getInt("num"), resultSet.getString("lang1"),
//                            resultSet.getString("def1"), resultSet.getString("lang2"), resultSet.getString("def2"),
//                            resultSet.getInt("synId"), resultSet.getInt("oppId"), resultSet.getString("pos"),
//                            resultSet.getString("sent1"), resultSet.getString("sent2")
//                            ));
//                }
                ArrayList<Word> words = new ArrayList<>();
                while (resultSet.next()){
                    words.add(new Word(resultSet.getInt("num"), resultSet.getString("lang1"),
                            resultSet.getString("def1"), resultSet.getString("lang2"), resultSet.getString("def2"),
                            resultSet.getInt("synId"), resultSet.getInt("oppId"), resultSet.getString("pos"),
                            resultSet.getString("sent1"), resultSet.getString("sent2")));

                }
                if (intent != null) {
                    intent.putExtra("words", words);
                }
                // TODO: 17.02.2017 Make 2-4 Tasks
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
