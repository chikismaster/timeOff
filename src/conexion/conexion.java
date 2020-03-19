package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelos.login;

public class conexion {
    
    private static Connection conn;
    //conexion local
    /*
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/db_timeoff";
    */
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "uwxe4zfnuzulaykh";
    private static final String password = "nOwtgWhZa16iA9deHx4D";
    private static final String url = "jdbc:mysql://b7sis9pkpmxjxql3njhz-mysql.services.clever-cloud.com/b7sis9pkpmxjxql3njhz";
    public static boolean cam = false;
    
    public conexion() {
        conn = null;
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null){
                System.out.println(".");
                cam = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "encienda la base de datos.. "+e);
            cam = false;           
        }
    }
    
    public Connection getConnection(){
            return conn;
    }
    public boolean sijalo(){
        
    
        return cam;
    }
}
