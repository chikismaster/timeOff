package pv;

import conexion.conexion;
import java.sql.Connection;
import modelos.login;
import modelos.error_db;


public class Pv {
    
    public static void main(String[] args) {
        
      conexion con = new conexion();
      
      boolean chi = con.sijalo();
      
        if (chi) {
            System.out.println("..");
            login lo = new login();
            lo.setVisible(true);
        }else{
            error_db ed = new error_db();
            ed.setVisible(true);
            System.out.println("no tonto");
            //System.exit(0);
        }
      
    }
    
}
