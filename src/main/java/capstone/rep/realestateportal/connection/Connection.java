/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.connection;

import java.sql.DriverManager;

/**
 *
 * @author Phong
 */
public class Connection {
    public static String username = "";
    public static String password = "";
    public static String url = "";
    public static java.sql.Connection conn = null;

    public static Connection dBContext = new Connection();

    public Connection() {
        initConfig();
    }

    public static void initConfig() {
        url = "jdbc:sqlserver://localhost:1433;databaseName=RealEstatePortalGIS";
        username = "sa";
        password = "123456";
    	
    	//db online
 //     url = "jdbc:sqlserver://database-1.czlokgka8gz9.us-east-1.rds.amazonaws.com:1433;databaseName=RealEstatePortalGIS";
 //     username = "admin";
  //    password = "clgt1234";
    }

    public java.sql.Connection getConnection() throws Exception {
        //  initConfig();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, username, password);
    }
}