/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Estudiante;

/**
 *
 * @author LENOVO
 */
public class EstudianteControlador {
    private Estudiante estudiante; //CON CADA CLASE
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion=new ConexionBDD();
    Connection connection=(Connection)conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado; 

    public void crearEstudiante(Estudiante es){
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL="INSERT INTO estudiantes(Est_Carrera,Est_Nivel,Per_Id) VALUES ('"+es.getCarrera()+"','"+es.getNivel()+"','"+es.getIdPersona()+"');";
                    //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
                    ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
                    //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
                    int res=ejecutar.executeUpdate(); 
                    if(res>0){
                        System.out.println("El estudiante ha sido creado con éxito");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }else{
                        System.out.println("Favor ingrese correctamente los datos solicitados: ");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }
                    
        } catch (Exception e) { //Captura el error el (e)
            System.out.println("Por favor, Comuníquese con el Administrador, gracias!!"+e);
        } //Captura el error y permite que la consola se siga ejecutando
    }   
    public String consultarEstudiantes(){
        StringBuilder resultado2 = new StringBuilder(); // Usamos StringBuilder para construir la cadena de resultados    
        try {
                
                String consultaSQL="select * from estudiantes;";
                ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);
                resultado=ejecutar.executeQuery();

                    while (resultado.next()) {
                int estId = resultado.getInt("Est_Id");
                String estCarrera = resultado.getString("Est_Carrera"); // Ahora tratamos la fecha como un String
                String estNivel = resultado.getString("Est_Nivel");
                int idPersona1 = resultado.getInt("Per_Id");

                // Construyendo la cadena de resultados
                resultado2.append("ID: ").append(estId).append("\n") //Append lista de strings
                         .append(" Carrera: ").append(estCarrera).append("\n")  // Directamente añadimos la fecha como cadena
                         .append(" Nivel: ").append(estNivel).append("\n") 
                         .append(" Id Persona: ").append(idPersona1)
                        
                         .append("\n"); // Agrega un salto de línea entre registros
                }
            } catch (Exception e) {
                System.out.println("Por favor, comuníquese con el administrador"+e);
            }
            return resultado2.toString();
    }
    public int verificarRolEstudiante(int idPersona){

            try {
                
                String consultaSQL="select Est_Id from estudiantes where Per_Id='"+idPersona+"';";
                ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);
                resultado=ejecutar.executeQuery();
                
                if(resultado.next()){
                    int idEstudiante=resultado.getInt("Est_Id"); //Lista Estática
                    return idEstudiante;
                }else{
                    return 0;
                }
            } catch (Exception e) {
                System.out.println("Por favor, comuníquese con el administrador"+e);
            }
            return 0;
    }  
    public void eliminarEstudiantes(String carrera){
        try {
            //String consultaSQL="delete from persona where cedula='"+cedula+"';";
            String consultaSQL="delete from estudiantes where Est_Carrera=?;";
            ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);        
            ejecutar.setString(1, carrera);
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
