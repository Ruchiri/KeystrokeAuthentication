/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keystrokeauthentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class KeystrokeAuthentication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        Database db=new Database();
        db.createTable();
        MainFrame mf=new MainFrame();
        mf.setLocationRelativeTo(null);
        mf.setVisible(true);
        
        
    }
    
}
