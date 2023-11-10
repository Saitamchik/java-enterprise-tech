package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/skillbox";
    private static final String USER = "root";
    private static final String PASS = "CvbnGhjk2@";
    private static final String SQL = "SELECT pl.course_name, count(*), MIN(MONTH(subscription_date)), MAX(MONTH(subscription_date)) FROM purchaselist pl GROUP BY pl.course_name;";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                int maxMonth = resultSet.getInt("MAX(MONTH(subscription_date))");
                int minMonth = resultSet.getInt("MIN(MONTH(subscription_date))");
                int count = resultSet.getInt("count(*)");
                double avg = count / (double) (maxMonth - minMonth);
                System.out.println(courseName + " - " + avg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}