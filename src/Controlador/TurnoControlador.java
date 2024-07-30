/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Estudiante;
import modelo.Turno;

/**
 *
 * @author LENOVO
 */
public class TurnoControlador {
    private Estudiante estudiante; //CON CADA CLASE
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion=new ConexionBDD();
    Connection connection=(Connection)conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado; 
    public void crearTurno(Turno t){
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL="INSERT INTO turnos(Tur_Fecha,Tur_Hora,Tur_Estado,Sol_Id) VALUES ('"+t.getFecha()+"','"+t.getHora()+"','"+t.getEstado()+"','"+t.getIdSolicitud()+"');";
                    //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
                    ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
                    //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
                    int res=ejecutar.executeUpdate(); 
                    if(res>0){
                        System.out.println("El turno ha sido creado con éxito");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }else{
                        System.out.println("Favor ingrese correctamente los datos solicitados: ");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }
                    
        } catch (Exception e) { //Captura el error el (e)
            System.out.println("Por favor, Comuníquese con el Administrador, gracias!!"+e);
        } //Captura el error y permite que la consola se siga ejecutando
    }   
}
