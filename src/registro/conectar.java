/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registro;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Vinni
 */
public class conectar {

    public Connection conectarse() {
        try {
            String conexion = "jdbc:derby://localhost:1527/pruebaDB";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connection = DriverManager.getConnection(conexion,"adminDB","claveDB");
            return connection;
        } catch (Exception ex) {
            System.out.println("mal"+ex.getMessage());
        }
        return null;
        
    }
    
}
