package com.byplace.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
   private static Connection con;
   
   public static Connection dbConn() throws Exception {
      if(con == null) {
         String url = "jdbc:mariadb:///byplace";
         Class.forName("org.mariadb.jdbc.Driver");
         con = DriverManager.getConnection(url, "", "");
      }
      return con;
   }
   
}