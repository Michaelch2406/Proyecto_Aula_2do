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
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Persona;
import modelo.Solicitud;

/**
 *
 * @author LENOVO
 */
public class EstudianteControlador {

    private Estudiante estudiante; //CON CADA CLASE
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado;

    public void crearEstudiante(Estudiante es) {
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL = "INSERT INTO estudiantes(Est_Codigo,Est_Carrera,Est_Nivel,Per_Id) VALUES ('" + generarCodigoEstudiante() + "','" + es.getCarrera() + "','" + es.getNivel() + "','" + es.getIdPersona() + "');";
            //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
            //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("El estudiante ha sido creado con éxito");
                ejecutar.close(); //Siempre cierro mi conlsuta
            } else {
                System.out.println("Favor ingrese correctamente los datos solicitados: ");
                ejecutar.close(); //Siempre cierro mi conlsuta
            }

        } catch (SQLException e) { //Captura el error el (e)
            System.out.println("Por favor, Comuníquese con el Administrador, gracias!!" + e);
        } //Captura el error y permite que la consola se siga ejecutando
    }

    public String generarCodigoEstudiante() {
        try {
            String consultaSQL = "SELECT Est_Codigo FROM estudiantes ORDER BY Est_Id DESC LIMIT 1;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();

            if (resultado.next()) {
                String ultimoCodigo = resultado.getString("Est_Codigo");
                int numeroActual = Integer.parseInt(ultimoCodigo.substring(4));
                int nuevoNumero = numeroActual + 1;
                return String.format("EST%03d", nuevoNumero);
            } else {
                return "EST001";
            }
        } catch (SQLException e) {
            System.out.println("Error al generar el código de estudiante: " + e);
        }
        return null;
    }

    public void consultarEstudiantesMain() {
        EstudianteControlador estCr = new EstudianteControlador();
        ArrayList<Estudiante> listaEstudiante = estCr.consultarEstudiantes();

        for (Estudiante e : listaEstudiante) {
            System.out.println(e.imprimir());
        }
    }

    public ArrayList<Estudiante> consultarEstudiantes() {
        ArrayList<Estudiante> listaEstudiante = new ArrayList<>();
        try {

            String consultaSQL = "select * FROM personas join estudiantes on personas.Per_Id=estudiantes.Per_Id ;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);

            resultado = ejecutar.executeQuery();

            while (resultado.next()) {

                Estudiante e = new Estudiante();
                e.setCodigoEst(resultado.getString("Est_Codigo"));
                e.setCarrera(resultado.getString("Est_Carrera"));
                e.setNivel(resultado.getInt("Est_Nivel"));
                e.setIdPersona(resultado.getInt("Per_Id"));
                e.setNombre(resultado.getString("Per_Nombre"));
                e.setApellido(resultado.getString("Per_Apellido"));
                e.setCedula(resultado.getString("Per_Cedula"));
                e.setTelefono(resultado.getString("Per_Telefono"));
                e.setCorreo(resultado.getString("Per_Correo"));
                e.setDireccion(resultado.getString("Per_Direccion"));
                listaEstudiante.add(e);
            }
            resultado.close();
            return listaEstudiante;

        } catch (SQLException e) {
            System.out.println("Por favor, comuníquese con el administrador" + e);
        }
        return listaEstudiante;
    }

    public int verificarRolEstudiante(int idPersona) {

        try {

            String consultaSQL = "select Est_Id from estudiantes where Per_Id='" + idPersona + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();

            if (resultado.next()) {
                int idEstudiante = resultado.getInt("Est_Id"); //Lista Estática
                return idEstudiante;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Por favor, comuníquese con el administrador" + e);
        }
        return 0;
    }

    public void editarSolicitud() {
        Scanner es = new Scanner(System.in);
        int i = 1;

        System.out.println("Ingrese el código de la solicitud que desea editar:\n");
        String codigoSol = es.nextLine();
        String asunto = "";
        boolean asuntoValido = false;

        while (!asuntoValido) {
            System.out.println("Ingrese el nuevo Asunto: ");
            System.out.println("1. Certificado de Notas");
            System.out.println("2. Certificado de Asistencia");
            System.out.println("3. Certificado de Matrícula");

            int asuntoOpcion = es.nextInt();
            es.nextLine(); // Consumir el salto de línea residual

            switch (asuntoOpcion) {
                case 1 -> {
                    asunto = "Certificado de Notas";
                    asuntoValido = true;
                }
                case 2 -> {
                    asunto = "Certificado de Asistencia";
                    asuntoValido = true;
                }
                case 3 -> {
                    asunto = "Certificado de Matrícula";
                    asuntoValido = true;
                }
                default ->
                    System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y 3.");
            }
        }
        System.out.println("Ingrese el nuevo Detalle de la solicitud: ");
        String detalleSol = es.nextLine();

        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets

            String consultaSQL = "UPDATE solicitudes SET Sol_Asunto='" + asunto + "', Sol_Detalle='"+detalleSol+"' WHERE Sol_Codigo='" + codigoSol + "';";
            //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
            //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("La solicitud ha sido actualizada con éxito");
                ejecutar.close(); //Siempre cierro mi conlsuta
            } else {
                System.out.println("Favor ingrese correctamente los datos solicitados...");
                ejecutar.close(); //Siempre cierro mi conlsuta
            }

        } catch (SQLException e) { //Captura el error el (e)
            System.out.println("Comuníquese con el Administrador, gracias!!:" + e);
        } //Captura el error y permite que la consola se siga ejecutando
    }
}
