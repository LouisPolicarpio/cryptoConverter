package model.DB;

import model.cryptoInfo.Info;
import model.cryptoInfo.Urls;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String cachedbName = "cache.db";
    private static final String cachedbURL = "jdbc:sqlite:" + cachedbName;



    public static void createCacheDB() {
        File dbFile = new File(cachedbName);
        if (dbFile.exists()) {
            System.out.println("DB.Database already created");
            return;
        }
        try (Connection ignored = DriverManager.getConnection(cachedbURL)) {
            // If we get here that means no exception raised from getConnection - meaning it worked
            System.out.println("A new database has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }



    public static void setupDB() {


        String infoCacheSQL =
         """
         CREATE TABLE IF NOT EXISTS infoCache (
            cur_id integer PRIMARY KEY,
            logo text ,
            cur_name text ,
            symbol text ,
            description text,
            date_launched text ,
            website text 
         );
         """;


        try (Connection conn = DriverManager.getConnection(cachedbURL);
             Statement statement = conn.createStatement()) {
            statement.execute(infoCacheSQL);
            System.out.println("Created tables cache");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }



    public static boolean doesCurExistInCache(int cur_id) throws Exception {

        String doesCurExistInCacheSQL =
                """
                     SELECT EXISTS 
                     ( SELECT 1
                      FROM infoCache
                      WHERE cur_id = (?));
                """;

        try (Connection conn = DriverManager.getConnection(cachedbURL);
            PreparedStatement preparedStatement = conn.prepareStatement(doesCurExistInCacheSQL)) {
            preparedStatement.setInt(1, cur_id) ;
            ResultSet results = preparedStatement.executeQuery();

            boolean res =  results.getBoolean(1);

            System.out.println("doesCurExistInCacheSQL = " + res );
            return res;

        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

    }

    public static Info getInfoCache(int targetCur_id) throws Exception {
        Info info ;
        String getInfoCacheSQL =
                """
                    SELECT * FROM infoCache WHERE cur_id = (?);
                """;

        try (Connection conn = DriverManager.getConnection(cachedbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(getInfoCacheSQL)) {
            preparedStatement.setInt(1, targetCur_id) ;
            ResultSet results = preparedStatement.executeQuery();

             int cur_id = results.getInt("cur_id");
             String logo = results.getString("logo");
             String cur_name = results.getString("cur_name");
             String symbol = results.getString("symbol");
             String description = results.getString("description");
             String date_launched = results.getString("date_launched");
             String website = results.getString("website");

             Urls url = new Urls(website);
             info = new Info(url, logo, Integer.toString(cur_id), cur_name, symbol, description, date_launched);
             System.out.println(" getInfoCache");
                return info;


        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

    }


    public static void addInfoCache(int cur_id, String logo, String cur_name, String symbol,String description, String date_launched, String website) throws Exception {

        String addUserDataSQL =
                """
                     INSERT INTO infoCache(cur_id, logo, cur_name, symbol, description, date_launched, website) VALUES (?,?,?,?,?,?,?);
                """;

        try (Connection conn = DriverManager.getConnection(cachedbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(addUserDataSQL)) {
            preparedStatement.setInt(1, cur_id);
            preparedStatement.setString(2, logo);
            preparedStatement.setString(3, cur_name);
            preparedStatement.setString(4, symbol);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, date_launched);
            preparedStatement.setString(7, website);

            preparedStatement.executeUpdate();
            System.out.println("Added infoCache");

        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void delAllCache() throws Exception {

        String addUserDataSQL =
                """
                     DELETE FROM infoCache ;
                """;

        try (Connection conn = DriverManager.getConnection(cachedbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(addUserDataSQL)) {
            preparedStatement.executeUpdate();

            System.out.println("DELETE  ALL infoCache");


        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }


}

