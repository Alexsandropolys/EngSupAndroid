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
import java.util.Random;

/**
 * Created by slava on 17.02.2017.
 */

public class ChoosingListener implements View.OnClickListener {
    private  int unitIndex;
    private ChoosingTaskActivity activity;
    private Random rnd = new Random();
    private static final String URL = "jdbc:mysql://192.168.0.71:3306/project";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String TAG = "Exception";

    public ChoosingListener(int unitIndex, ChoosingTaskActivity activity) {
        this.unitIndex = unitIndex;
        this.activity = activity;
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
                PreparedStatement statement = connection.prepareStatement("SELECT `startBookId`, `finishBookId` FROM `unit` WHERE \"id\" = ?;");
                statement.setInt(1, unitId);
                ResultSet resultSet = statement.executeQuery();
                int start = resultSet.getInt("startBookId");
                int finish = resultSet.getInt("finishBookId");

                Intent intent;
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
                // TODO: 17.02.2017 Make 2-4 Tasks

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
