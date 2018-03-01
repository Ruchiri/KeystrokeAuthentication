/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keystrokeauthentication;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Database {
    private static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
   
    private static final String JDBC_URL="jdbc:derby:Keystroke;create=true";
    
    private Connection con;
    public Database(){
        try {
            this.con=DriverManager.getConnection(JDBC_URL);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void createTable(){
        try {
            DatabaseMetaData dbm = con.getMetaData();
       
            ResultSet table1 = dbm.getTables(null, null, "Text", null);
            if (table1.next()) {
                getCon().createStatement().execute("create table Text(Username varchar(20),Password varchar(50) not null,primary key(Username))");
            }
            
            ResultSet table2 = dbm.getTables(null, null, "Average", null);
            if (table2.next()) {
                getCon().createStatement().execute("create table Average(Username varchar(20),Value double not null,primary key(Username))");
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }
}
