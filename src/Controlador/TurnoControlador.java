/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Estudiante;
import modelo.Turno;

/**
 *
 * @author LENOVO
 */
public class TurnoControlador {
    private Estudiante estudiante; //CON CADA CLASE
    private Turno turno;
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion=new ConexionBDD();
    Connection connection=(Connection)conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado; 
    public void crearTurno(Turno t){
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL="INSERT INTO turnos(Tur_Codigo,Tur_Fecha,Tur_Hora,Tur_Retiro,Sol_Id) VALUES ('"+generarCodigoTurno()+"','"+t.getFecha()+"','"+t.getHora()+"','"+t.getRetiro()+"','"+t.getIdSolicitud()+"');";
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
                    
        } catch (SQLException e) { //Captura el error el (e)
            System.out.println("Por favor, Comuníquese con el Administrador, gracias!!"+e);
        } //Captura el error y permite que la consola se siga ejecutando
    }
    public String generarCodigoTurno() {
        try {
            String consultaSQL = "SELECT Tur_Codigo FROM turnos ORDER BY Tur_Id DESC LIMIT 1;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();

            if (resultado.next()) {
                String ultimoCodigo = resultado.getString("Tur_Codigo");
                int numeroActual = Integer.parseInt(ultimoCodigo.substring(3));
                int nuevoNumero = numeroActual + 1;
                return String.format("T%04d", nuevoNumero);
            } else {
                return "T0001";
            }
        } catch (SQLException e) {
            System.out.println("Error al generar el código de estudiante: " + e);
        }
        return null;
    }
}
