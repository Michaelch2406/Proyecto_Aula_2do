package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDD {
    Connection conexion;

    public Connection conectar() {
        try {
<<<<<<< Updated upstream
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tur_resp_solicitudes_secretariag?autoReconnect=true&useSSL=false",
                "root",
                "0984315441k"
            );
            System.out.println("CONECTADO");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR DE CONEXION A LA BASE DE DATOS: " + e);
=======
            //Manera de Conexión a la Base de Datos
            Class.forName("com.mysql.jdbc.Driver");
            //Parámetros de conexión url/usuario/clave en mysql
            conexion=DriverManager.getConnection("jdbc:mysql://localhost/tur_resp_solicitudes_secretariag?autoReconnect=true&useSSL=false","root","0984315441k");
            //System.out.println("CONECTADO"); 
        } catch (ClassNotFoundException | SQLException e)//CAPTURAR ERRORES 
        {
             System.out.println("ERROR DE CONEXION A LA BASE DE DATOS"+ e);
>>>>>>> Stashed changes
        }
        return conexion;
    }
}
