package pv;

import conexion.conexion;
import java.sql.Connection;
import modelos.login;


public class Pv {
    
    public static void main(String[] args) {
        
      conexion con = new conexion();
      
      boolean chi = con.sijalo();
      
        if (chi) {
            System.out.println("si jaloo bebe");
            login lo = new login();
            lo.setVisible(true);
        }else{
            System.out.println("no tonto");
            System.exit(0);
        }
      
    }
    
}
