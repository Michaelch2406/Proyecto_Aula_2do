/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Persona;
import modelo.Secretaria;
import modelo.Solicitud;

/**
 *
 * @author LENOVO
 */
public class SecretariaControlador {

    private Estudiante estudiante; //CON CADA CLASE
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado;

    

    /**
     * Se busca verificar el rol de la persona con el usuario logeado
     *
     * @param idPersona ID del usuario Logeado
     * @return
     */
    public int verificarRolSecretaria(int idPersona) {

        try {

            String consultaSQL = "select Sec_Id from secretarias where Per_Id='" + idPersona + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();

            if (resultado.next()) {
                int idSecretaria = resultado.getInt("Sec_Id"); //Lista Estática
                return idSecretaria;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Por favor, comuníquese con el administrador" + e);
        }
        return 0;
    }

    public void eliminarSec(Scanner es) {
        es.nextLine();
        AdministradorControlador adm = new AdministradorControlador();
        Persona p = new Persona();
        Secretaria s = new Secretaria();
        adm.consultarSecretarias();
        System.out.println("Elija el código de la secretaria para eliminar:");
        s.setCodigoSec(es.nextLine());

        System.out.println("¿Seguro/a quiere eliminar a la secretaria?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int opcion = 0;
        do {
            try {
                opcion = es.nextInt();
                es.nextLine(); // Consumir el salto de línea residual
                switch (opcion) {
                    case 1 ->
                        eliminarSecretarias(s);
                    case 2 -> {
                        System.out.println("Eliminación cancelada.");
                        return;
                    }
                    default ->
                        System.out.println("Opción no válida. Por favor, elija 1 para Sí o 2 para No.");
                }
            } catch (Exception ex) {
                System.out.println("Opción no válida. Por favor, ingrese un número.");
                es.nextLine(); // Limpiar el buffer de entrada
            }
        } while (opcion != 1 && opcion != 2);
    }

    /**
     * delete from persona where cedula='10035555';
     *
     * @param s
     */
    public void eliminarSecretarias(Secretaria s) {
        try {
            // Obtener el Per_Id del estudiante a eliminar
            String consultaIdPersona = "SELECT Per_Id FROM secretarias WHERE Sec_Codigo='" + s.getCodigoSec() + "';";
            Statement statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(consultaIdPersona);

            if (rs.next()) {
                int perId = rs.getInt("Per_Id");

                // Eliminar el estudiante de la tabla estudiantes
                String consultaSQL = "DELETE FROM secretarias WHERE Sec_Codigo='" + s.getCodigoSec() + "';";
                int resEstudiante = statement.executeUpdate(consultaSQL);

                // Eliminar la persona correspondiente de la tabla personas
                String consultaSQLPersona = "DELETE FROM personas WHERE Per_Id=" + perId + ";";
                int resPersona = statement.executeUpdate(consultaSQLPersona);

                if (resEstudiante > 0 && resPersona > 0) {
                    System.out.println("Secretaria eliminada exitosamente");
                } else {
                    System.out.println("No se pudo eliminar a la secretaria");
                }
            } else {
                System.out.println("La secretaria no existe");
            }

            // Cerrar recursos
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
