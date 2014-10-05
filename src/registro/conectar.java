/**
 *
 * @author Juan Camilo Fernández
 * Programa que permite establecer una conexión con la base de datos
 */
package registro;

import java.sql.Connection;
import java.sql.DriverManager;

//La clase conectar permite establecer la conexión con la DB Derby
public class conectar {
//El metodo conectar no recibe parámetros y retorna un objeto de tipo Connection
    public Connection conectar() {
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
