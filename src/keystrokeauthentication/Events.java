
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keystrokeauthentication;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author HP
 */
class Events {
    
    private static ArrayList<Long> pressedKeys;
    private static ArrayList<Long> releasedKeys;
    private static ArrayList<Long> prKeysAvg;
    public Events(){
       pressedKeys=new ArrayList<>();
       releasedKeys=new ArrayList<>();
       prKeysAvg=new ArrayList<>();
    }
    
    //validate user
    public  boolean checkUser(String username,double average) throws ClassNotFoundException {
            Connection connection=null;
            PreparedStatement statement1=null;
            ResultSet result1=null;
            boolean valid=false;
            String query="select Value from Average where Username=?";
            try {
                Database db=new Database();
                connection=db.getCon();
                statement1=connection.prepareStatement(query);
                statement1.setString(1,username);
                result1=statement1.executeQuery();
                double average2 = 0;
                while(result1.next()){
                    average2=result1.getDouble("Value");
                    
                }
                int variance=(int)Math.abs((average-average2)*100/average);
                System.out.println(variance);
                connection.close();
                if(variance<=5){
                    valid=true;
                }
                } catch (SQLException ex) {
                Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            }
        return valid;
    }
  
    //store the key pressed times in all situations
    public void press(KeyEvent evt, String username){
        Long time=System.currentTimeMillis();
        pressedKeys.add(time);
      
    }
    //store the key released times in all situations
    public void release(KeyEvent ke,String username) {
        Long time=System.currentTimeMillis();
        releasedKeys.add(time);
        
    }
    
    public double Average(String username){
        for(int i = 0;i<pressedKeys.size()-1;i++){
            prKeysAvg.add(releasedKeys.get(i)-pressedKeys.get(i));
        }
        double average=0;
        for(Long avg:prKeysAvg){
            average+=avg;
        }
        return average/prKeysAvg.size();
       
         
    }
}
