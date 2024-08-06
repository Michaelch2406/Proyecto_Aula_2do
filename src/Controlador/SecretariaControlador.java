/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
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
    ConexionBDD conexion=new ConexionBDD();
    Connection connection=(Connection)conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado; 
     
     /**
         * Se busca verificar el rol de la persona con el usuario logeado
         * @param idPersona ID del usuario Logeado
         * @return 
         */
     public int verificarRolSecretaria(int idPersona){

            try {
                
                String consultaSQL="select Sec_Id from secretarias where Per_Id='"+idPersona+"';";
                ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);
                resultado=ejecutar.executeQuery();
                
                if(resultado.next()){
                    int idSecretaria=resultado.getInt("Sec_Id"); //Lista Estática
                    return idSecretaria;
                }else{
                    return 0;
                }
            } catch (SQLException e) {
                System.out.println("Por favor, comuníquese con el administrador"+e);
            }
            return 0;
    }   
}
