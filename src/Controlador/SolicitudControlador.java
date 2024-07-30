/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Estudiante;
import modelo.Solicitud;

/**
 *
 * @author LENOVO
 */
public class SolicitudControlador {
    private Estudiante estudiante; //CON CADA CLASE
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion=new ConexionBDD();
    Connection connection=(Connection)conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado; 
    
    public void crearSolicitud(Solicitud s){
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL="INSERT INTO solicitudes(Sol_Fecha,Sol_Asunto,Sol_Detalle,Sol_Estado,Per_Id) VALUES ('"+s.getFecha()+"','"+s.getAsunto()+"','"+s.getDetalle()+"','"+s.getEstado()+"','"+s.getIdPersona()+"');";
                    //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
                    ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
                    //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
                    int res=ejecutar.executeUpdate(); 
                    if(res>0){
                        System.out.println("La solicitud ha sido creada con éxito");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }else{
                        System.out.println("Favor ingrese correctamente los datos solicitados: ");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }
                    
        } catch (Exception e) { //Captura el error el (e)
            System.out.println("Comuníquese con el Administrador, gracias!!:"+e);
        } //Captura el error y permite que la consola se siga ejecutando
    } 
    public String consultarSolicitud(){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder resultado = new StringBuilder(); // Usamos StringBuilder para construir la cadena de resultados

        try {
            String consultaSQL = "SELECT * FROM solicitudes";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int solId = resultSet.getInt("Sol_Id");
                String solFecha = resultSet.getString("Sol_Fecha"); // Ahora tratamos la fecha como un String
                String solAsunto = resultSet.getString("Sol_Asunto");
                String solDetalle = resultSet.getString("Sol_Detalle");
                String solEstado = resultSet.getString("Sol_Estado");
                int idPersona = resultSet.getInt("Per_Id");

                // Construyendo la cadena de resultados
                resultado.append("ID: ").append(solId).append("\n") //Append lista de strings
                         .append(" Fecha: ").append(solFecha).append("\n")  // Directamente añadimos la fecha como cadena
                         .append(" Asunto: ").append(solAsunto).append("\n") 
                         .append(" Detalle: ").append(solDetalle).append("\n") 
                         .append(" Estado: ").append(solEstado).append("\n") 
                         .append(" Id Persona: ").append(idPersona)
                        
                         .append("\n"); // Agrega un salto de línea entre registros
            }
        } catch (Exception e) {
            System.out.println("ERROR en la consulta de solicitudes: " + e.getMessage());
            return "Error en la consulta: "+ e.getMessage() ; // Retorna un mensaje de error si ocurre alguno
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception ex) {
                System.out.println("ERROR al cerrar los recursos: " + ex.getMessage());
            }
        }

        return resultado.toString(); // Retorna la cadena completa de resultados
    }
    
    public String consultarSolicitudEst(int idPersona){
        StringBuilder resultado2 = new StringBuilder(); // Usamos StringBuilder para construir la cadena de resultados    
        try {
                
                String consultaSQL="select * from solicitudes where Per_Id='"+idPersona+"';";
                ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);
                resultado=ejecutar.executeQuery();

                    while (resultado.next()) {
                int solId = resultado.getInt("Sol_Id");
                String solFecha = resultado.getString("Sol_Fecha"); // Ahora tratamos la fecha como un String
                String solAsunto = resultado.getString("Sol_Asunto");
                String solDetalle = resultado.getString("Sol_Detalle");
                String solEstado = resultado.getString("Sol_Estado");
                int idPersona1 = resultado.getInt("Per_Id");

                // Construyendo la cadena de resultados
                resultado2.append("ID: ").append(solId).append("\n") //Append lista de strings
                         .append(" Fecha: ").append(solFecha).append("\n")  // Directamente añadimos la fecha como cadena
                         .append(" Asunto: ").append(solAsunto).append("\n") 
                         .append(" Detalle: ").append(solDetalle).append("\n") 
                         .append(" Estado: ").append(solEstado).append("\n") 
                         .append(" Id Persona: ").append(idPersona1)
                        
                         .append("\n"); // Agrega un salto de línea entre registros
                }
            } catch (Exception e) {
                System.out.println("Por favor, comuníquese con el administrador"+e);
            }
            return resultado2.toString();
    }
    
    
    public String revisarSolicitud(){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder resultado = new StringBuilder(); // Usamos StringBuilder para construir la cadena de resultados

        try {
            String consultaSQL = "SELECT * FROM solicitudes where Sol_Estado='Pendiente'";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int solId = resultSet.getInt("Sol_Id");
                String solFecha = resultSet.getString("Sol_Fecha"); // Ahora tratamos la fecha como un String
                String solAsunto = resultSet.getString("Sol_Asunto");
                String solDetalle = resultSet.getString("Sol_Detalle");
                String solEstado = resultSet.getString("Sol_Estado");
                int idPersona = resultSet.getInt("Per_Id");

                // Construyendo la cadena de resultados
                resultado.append("ID: ").append(solId).append("\n") //Append lista de strings
                         .append(" Fecha: ").append(solFecha).append("\n")  // Directamente añadimos la fecha como cadena
                         .append(" Asunto: ").append(solAsunto).append("\n") 
                         .append(" Detalle: ").append(solDetalle).append("\n") 
                         .append(" Estado: ").append(solEstado).append("\n") 
                         .append(" Id Persona: ").append(idPersona)
                        
                         .append("\n"); // Agrega un salto de línea entre registros
            }
        } catch (Exception e) {
            System.out.println("ERROR en la consulta de solicitudes: " + e.getMessage());
            return "Error en la consulta: "+ e.getMessage() ; // Retorna un mensaje de error si ocurre alguno
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception ex) {
                System.out.println("ERROR al cerrar los recursos: " + ex.getMessage());
            }
        }

        return resultado.toString(); // Retorna la cadena completa de resultados
    }
    public void actualizarSolicitud(Solicitud s){
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL="UPDATE solicitudes SET Sol_Estado='"+s.getEstado()+"' WHERE Sol_Id='"+s.getIdSolicitud()+"';";
                    //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
                    ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
                    //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
                    int res=ejecutar.executeUpdate(); 
                    if(res>0){
                        System.out.println("La solicitud ha sido actualizada con éxito");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }else{
                        System.out.println("Favor ingrese correctamente los datos solicitados: ");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }
                    
        } catch (Exception e) { //Captura el error el (e)
            System.out.println("Comuníquese con el Administrador, gracias!!:"+e);
        } //Captura el error y permite que la consola se siga ejecutando
    } 

    public void eliminarSolicitud(String detalle){
        try {
           
            String consultaSQL="DELETE FROM solicitudes WHERE Sol_Detalle=?;";
            ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);        
            ejecutar.setString(1, detalle);
            int res=ejecutar.executeUpdate();
            if(res>0){
                System.out.println("Acción exitosa");
                ejecutar.close();
            }else{
                System.out.println("El usuario no existe");
                ejecutar.close();
            }       
        } catch (Exception e) {
            System.out.println(""+e);
        }
    
    
    }

}
