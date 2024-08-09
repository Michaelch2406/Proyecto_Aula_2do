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
        System.out.println("Ingrese el código de la solicitud que desea editar:\n");
        String codigoSol = es.nextLine();
        Solicitud s=new Solicitud();
        Fechas f=new Fechas();
        
        f.fechaSol(s);
        String asunto = "";
        String razon = "";
        boolean asuntoValido = false;

        while (!asuntoValido) {
            System.out.println("Seleccione el nuevo Asunto: ");
            System.out.println("1. Certificado de Notas");
            System.out.println("2. Certificado de Asistencia");
            System.out.println("3. Certificado de Matrícula");

            int asuntoOpcion = es.nextInt();
            es.nextLine(); // Consumir el salto de línea residual

            switch (asuntoOpcion) {
                case 1 -> {
                    asunto = "Certificado de Notas";
                    asuntoValido = true;

                    // Mini menú para Certificado de Notas
                    System.out.println("Elija el motivo nuevo para la emisión del Certificado de Notas:");
                    System.out.println("1. Presentación en otra institución educativa");
                    System.out.println("2. Aplicación a beca");
                    System.out.println("3. Respaldo personal");
                    System.out.println("4. Postulación a estudios de posgrado");
                    System.out.println("5. Reconocimiento de estudios en el extranjero");

                    int razonOpcion = es.nextInt();
                    es.nextLine(); // Consumir el salto de línea residual

                    switch (razonOpcion) {
                        case 1 ->
                            razon = "Presentación en otra institución educativa";
                        case 2 ->
                            razon = "Aplicación a beca";
                        case 3 ->
                            razon = "Respaldo personal";
                        case 4 ->
                            razon = "Postulación a estudios de posgrado";
                        case 5 ->
                            razon = "Reconocimiento de estudios en el extranjero";
                        default ->
                            System.out.println("Opción no válida. Por favor, seleccione una razón válida.");
                    }
                }
                case 2 -> {
                    asunto = "Certificado de Asistencia";
                    asuntoValido = true;

                    // Mini menú para Certificado de Asistencia
                    System.out.println("Elija el motivo nuevo para la emisión del Certificado de Asistencia:");
                    System.out.println("1. Requisito para empleo");
                    System.out.println("2. Justificación ante instituciones académicas");
                    System.out.println("3. Respaldo histórico de asistencia");
                    System.out.println("4. Transferencia de créditos académicos a otra universidad");
                    System.out.println("5. Participación en programas de apoyo institucional");

                    int razonOpcion = es.nextInt();
                    es.nextLine(); // Consumir el salto de línea residual

                    switch (razonOpcion) {
                        case 1 ->
                            razon = "Requisito para empleo";
                        case 2 ->
                            razon = "Justificación ante instituciones académicas";
                        case 3 ->
                            razon = "Respaldo histórico de asistencia";
                        case 4 ->
                            razon = "Transferencia de créditos académicos a otra universidad";
                        case 5 ->
                            razon = "Participación en programas de apoyo institucional";
                        default ->
                            System.out.println("Opción no válida. Por favor, seleccione una razón válida.");
                    }
                }
                case 3 -> {
                    asunto = "Certificado de Matrícula";
                    asuntoValido = true;

                    // Mini menú para Certificado de Matrícula
                    System.out.println("Elija el motivo nuevo para la emisión del Certificado de Matrícula:");
                    System.out.println("1. Requisito para solicitud de crédito estudiantil");
                    System.out.println("2. Requisito para seguro médico");
                    System.out.println("3. Trámites migratorios");
                    System.out.println("4. Inscripción en actividades extracurriculares");
                    System.out.println("5. Revalidación de matrícula en otra institución educativa");

                    int razonOpcion = es.nextInt();
                    es.nextLine(); // Consumir el salto de línea residual

                    switch (razonOpcion) {
                        case 1 ->
                            razon = "Requisito para solicitud de crédito estudiantil";
                        case 2 ->
                            razon = "Requisito para seguro médico";
                        case 3 ->
                            razon = "Trámites migratorios";
                        case 4 ->
                            razon = "Inscripción en actividades extracurriculares";
                        case 5 ->
                            razon = "Revalidación de matrícula en otra institución educativa";
                        default ->
                            System.out.println("Opción no válida. Por favor, seleccione una razón válida.");
                    }
                }
                default ->
                    System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y 3.");
            }
        }


        // Aquí se debería realizar la lógica para actualizar la solicitud con el nuevo asunto y razon
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets

            String consultaSQL = "UPDATE solicitudes SET Sol_Asunto='" + asunto + "', Sol_Razon='" + razon + "' WHERE Sol_Codigo='" + codigoSol + "';";
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
