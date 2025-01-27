/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Estudiante;
import modelo.Persona;
import modelo.Secretaria;
import modelo.Solicitud;
/**
 *
 * @author LENOVO
 */
public class PersonaControlador {
    private Estudiante estudiante; //CON CADA CLASE
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion=new ConexionBDD();
    Connection connection=(Connection)conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado; 
    
    //INSERTAR FILAS EN UNA TABLA
    public void crearPersona(Persona p){
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL="INSERT INTO personas(Per_Nombre,Per_Apellido,Per_Cedula,Per_Clave,Per_Telefono,Per_Correo,Per_Direccion) VALUES ('"+p.getNombre()+"','"+p.getApellido()+"','"+p.getCedula()+"','"+p.getClave()+"','"+p.getTelefono()+"','"+p.getCorreo()+"','"+p.getDireccion()+"');";
                    //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
                    ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
                    //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
                    int res=ejecutar.executeUpdate(); 
                    if(res>0){
                        System.out.println("La persona ha sido creada con éxito");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }else{
                        System.out.println("Favor ingrese correctamente los datos solicitados: ");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }
                    
        } catch (Exception e) { //Captura el error el (e)
            System.out.println("Por favor, Comuníquese con el Administrador, gracias!!"+e);
        } //Captura el error y permite que la consola se siga ejecutando
        
 
    }
    public int buscarIdPersona(String cedula){
            try {
                String consultaSQL="select Per_Id from personas where Per_Cedula='"+cedula+"';";
                ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);
                resultado=ejecutar.executeQuery();
                if(resultado.next()){
                    int idPersona=resultado.getInt("Per_Id"); //Lista Estática
                    return idPersona;
                }else{
                    System.out.println("Ingrese una cédula válida");
                }
            } catch (Exception e) {
                System.out.println("Por favor, comuníquese con el administrador"+e);
            }
            return 0;
    }
        
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
            } catch (Exception e) {
                System.out.println("Por favor, comuníquese con el administrador"+e);
            }
            return 0;
    }    
    public void eliminarPersonas(String cedula){
        try {
            //String consultaSQL="delete from persona where cedula='"+cedula+"';";
            String consultaSQL="delete from personas where cedula=?;";
            ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);        
            ejecutar.setString(1, cedula);
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
    
    public int login(String cedula, String clave){
        int res=0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder resultado = new StringBuilder(); // Usamos StringBuilder para construir la cadena de resultados

        try {
            String consultaSQL = "SELECT * FROM personas where Per_Cedula='"+cedula+"';";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String contrasenia= resultSet.getString("Per_Clave");
                int idPersona = resultSet.getInt("Per_Id");
                if(contrasenia.equals(clave)){
                    res=idPersona;
                }else{
                    res= 0;
                }

            }
        } catch (Exception e) {
            System.out.println("ERROR en la consulta de personas: " + e.getMessage());
            return res; // Retorna un mensaje de error si ocurre alguno
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception ex) {
                System.out.println("ERROR al cerrar los recursos: " + ex.getMessage());
            }
        }

        return res;
    }
    
public Persona buscardatosPersona(String cedula) {
        Persona p = new Persona();
        try {
            String consultaSQL = "select nombres,apellidos,cedula,usuario from persona where cedula='" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                p.setNombre(resultado.getString("nombres"));
                p.setApellido(resultado.getString("apellidos"));
                p.setCedula(resultado.getString("cedula"));
                resultado.close();
                return p;
            } else {
                System.out.println("Ingrese una cédula válida");
            }
        } catch (Exception e) {
            System.out.println("Comuníquese con el administrador" + e);
        }
        return null;
    }    
}
