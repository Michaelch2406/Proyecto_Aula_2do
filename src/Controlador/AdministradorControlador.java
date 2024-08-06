/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Administrador;
import modelo.Estudiante;
import modelo.Secretaria;

/**
 *
 * @author LENOVO
 */
public class AdministradorControlador {

    private Administrador administrador; //CON CADA CLASE
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado;

    public void crearSecretaria(Secretaria se) {
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL = "INSERT INTO secretarias(Sec_Codigo,Sec_Area,Per_Id) VALUES ('" + generarCodigoSecretaria() + "','" + se.getArea() + "','" + se.getIdPersona() + "');";
            //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
            //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("La secretaria ha sido creada con éxito");
                ejecutar.close(); //Siempre cierro mi conlsuta
            } else {
                System.out.println("Favor ingrese correctamente los datos solicitados: ");
                ejecutar.close(); //Siempre cierro mi conlsuta
            }

        } catch (SQLException e) { //Captura el error el (e)
            System.out.println("Comuníquese con el Administrador, GRACIAS!!:" + e);
        } //Captura el error y permite que la consola se siga ejecutando
    }

    public String generarCodigoSecretaria() {
        try {
            String consultaSQL = "SELECT Sec_Codigo FROM secretarias ORDER BY Sec_Id DESC LIMIT 1;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();

            if (resultado.next()) {
                String ultimoCodigo = resultado.getString("Sec_Codigo");
                int numeroActual = Integer.parseInt(ultimoCodigo.substring(3));
                int nuevoNumero = numeroActual + 1;
                return String.format("SEC%03d", nuevoNumero);
            } else {
                return "SEC001";
            }
        } catch (SQLException e) {
            System.out.println("Error al generar el código de secretaria: " + e);
        }
        return null;
    }
    
     public void consultarSecretarias() {
        ArrayList<Secretaria> listaSecretaria = new ArrayList<>();
        try {

            String consultaSQL = "select * FROM personas join secretarias on personas.Per_Id=secretarias.Per_Id ;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);

            resultado = ejecutar.executeQuery();

            while (resultado.next()) {

                Secretaria e = new Secretaria();
                e.setIdSecretaria(resultado.getInt("Sec_Id"));
                e.setCodigoSec(resultado.getString("Sec_Codigo"));
                e.setArea(resultado.getString("Sec_Area"));
                e.setIdPersona(resultado.getInt("Per_Id"));
                e.setNombre(resultado.getString("Per_Nombre"));
                e.setApellido(resultado.getString("Per_Apellido"));
                e.setCedula(resultado.getString("Per_Cedula"));

                e.setTelefono(resultado.getString("Per_Telefono"));
                e.setCorreo(resultado.getString("Per_Correo"));
                e.setDireccion(resultado.getString("Per_Direccion"));
                listaSecretaria.add(e);
            }
            resultado.close();
            for (Secretaria e : listaSecretaria) {
            System.out.println(e.imprimir());
        }

        } catch (SQLException e) {
            System.out.println("Por favor, comuníquese con el administrador" + e);
        }
    }

    public int verificarRolAdministrador(int idPersona){

            try {
                
                String consultaSQL="select Adm_Id from administrador where Per_Id='"+idPersona+"';";
                ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);
                resultado=ejecutar.executeQuery();
                
                if(resultado.next()){
                    int idAdministrador=resultado.getInt("Adm_Id"); //Lista Estática
                    return idAdministrador;
                }else{
                    return 0;
                }
            } catch (SQLException e) {
                System.out.println("Por favor, comuníquese con el administrador"+e);
            }
            return 0;
    }   
}
