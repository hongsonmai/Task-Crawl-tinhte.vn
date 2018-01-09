/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulti;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Son
 */
public class DBUlti {

    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection != null) {
            return connection;
        } else {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:49769;databasename=TinhTe","sa","1234567");
            return connection;
        }
    }
    
//    private static URL url;
//
//    public static URL getUrl() throws MalformedURLException, IOException {
//        url = new URL("https://tinhte.vn/");
//        URLConnection urlC = url.openConnection();
//        urlC.connect();
//        return url;
//    }

}
