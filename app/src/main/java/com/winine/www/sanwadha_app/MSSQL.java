package com.winine.www.sanwadha_app;

import android.app.Application;
import android.telecom.Connection;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.net.ssl.SNIServerName;

/**
 * Created by Yasintha on 7/9/2017.
 */

public class MSSQL extends Application {
    java.sql.Connection conn = null;
    String url="";
    String DBname="sanwadhaDB";
    String username ="yasintha";
    String password ="perera@123";
    String serverName="sanwadha-server.database.windows.net";
    Statement st ;
    ResultSet rs;


    public void open(){
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            url="jdbc:sqlserver://sanwadha-server.database.windows.net:1433;database=sanwadhaDB;user=yasintha@sanwadha-server;password={perera@123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            conn= DriverManager.getConnection(url);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error Y",Toast.LENGTH_LONG).show();
        }
    }









































}
