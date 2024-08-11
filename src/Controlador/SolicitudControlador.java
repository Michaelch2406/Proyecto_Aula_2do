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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Solicitud;
import modelo.Turno;

/**
 *
 * @author LENOVO
 */
public class SolicitudControlador {

    private Estudiante estudiante; //CON CADA CLASE
    //conexión 
    //HACER CON CADA CONTROLADOR
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado;

    public void crearSolicitud(Solicitud s) {
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets

            String consultaSQL = "INSERT INTO solicitudes(Sol_Fecha,Sol_Codigo,Sol_Asunto,Sol_Razon,Sol_Estado,Per_Id) VALUES ('" + s.getFecha() + "','" + s.getCodigoSol() + "','" + s.getAsunto() + "','" + s.getRazon() + "','" + s.getEstado() + "','" + s.getIdPersona() + "');";
            //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
            //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("La solicitud ha sido creada con éxito");
                ejecutar.close(); //Siempre cierro mi conlsuta
            } else {
                System.out.println("Favor ingrese correctamente los datos solicitados: ");
                ejecutar.close(); //Siempre cierro mi conlsuta
            }

        } catch (SQLException e) { //Captura el error el (e)
            System.out.println("Comuníquese con el Administrador, gracias!!:" + e);
        } //Captura el error y permite que la consola se siga ejecutando
    }

    public void crearSolicitud(Scanner es, int logeado) {
        LogeoControlador lc = new LogeoControlador();
        lc.limpiarPantalla();
        Solicitud s = new Solicitud();
        Fechas f = new Fechas();
        s.setIdPersona(logeado);
        System.out.println("Ingrese los datos para la solicitud");
        f.fechaSol(s);
        String codigoSolicitud = generarCodigoSolicitud();
        s.setCodigoSol(codigoSolicitud);

        String asunto = "";
        String detalle = "";
        boolean asuntoValido = false;

        while (!asuntoValido) {
            System.out.println("Seleccione el tipo de Certificado que desea solicitar: ");
            System.out.println("1. Certificado de Notas");
            System.out.println("2. Certificado de Asistencia");
            System.out.println("3. Certificado de Matrícula");

            int asuntoOpcion = es.nextInt();

            switch (asuntoOpcion) {
                case 1 -> {
                    asunto = "Certificado de Notas";
                    asuntoValido = true;

                    // Mini menú para Certificado de Notas
                    System.out.println("Elija el motivo para la emisión del Certificado de Notas:");
                    System.out.println("1. Presentación en otra institución educativa");
                    System.out.println("2. Aplicación a beca");
                    System.out.println("3. Respaldo personal");
                    System.out.println("4. Postulación a estudios de posgrado");
                    System.out.println("5. Reconocimiento de estudios en el extranjero");

                    int razonOpcion = es.nextInt();

                    switch (razonOpcion) {
                        case 1 ->
                            detalle = "Presentación en otra institución educativa";
                        case 2 ->
                            detalle = "Aplicación a beca";
                        case 3 ->
                            detalle = "Respaldo personal";
                        case 4 ->
                            detalle = "Postulación a estudios de posgrado";
                        case 5 ->
                            detalle = "Reconocimiento de estudios en el extranjero";
                        default ->
                            System.out.println("Opción no válida. Por favor, seleccione una razón válida.");
                    }
                }
                case 2 -> {
                    asunto = "Certificado de Asistencia";
                    asuntoValido = true;

                    // Mini menú para Certificado de Asistencia
                    System.out.println("Elija el motivo para la emisión del Certificado de Asistencia:");
                    System.out.println("1. Requisito para empleo");
                    System.out.println("2. Justificación ante instituciones académicas");
                    System.out.println("3. Respaldo histórico de asistencia");
                    System.out.println("4. Transferencia de créditos académicos a otra universidad");
                    System.out.println("5. Participación en programas de apoyo institucional");

                    int razonOpcion = es.nextInt();

                    switch (razonOpcion) {
                        case 1 ->
                            detalle = "Requisito para empleo";
                        case 2 ->
                            detalle = "Justificación ante instituciones académicas";
                        case 3 ->
                            detalle = "Respaldo histórico de asistencia";
                        case 4 ->
                            detalle = "Transferencia de créditos académicos a otra universidad";
                        case 5 ->
                            detalle = "Participación en programas de apoyo institucional";
                        default ->
                            System.out.println("Opción no válida. Por favor, seleccione una razón válida.");
                    }
                }
                case 3 -> {
                    asunto = "Certificado de Matrícula";
                    asuntoValido = true;

                    // Mini menú para Certificado de Matrícula
                    System.out.println("Elija el motivo para la emisión del Certificado de Matrícula:");
                    System.out.println("1. Requisito para solicitud de crédito estudiantil");
                    System.out.println("2. Requisito para seguro médico");
                    System.out.println("3. Trámites migratorios");
                    System.out.println("4. Inscripción en actividades extracurriculares");
                    System.out.println("5. Revalidación de matrícula en otra institución educativa");

                    int razonOpcion = es.nextInt();

                    switch (razonOpcion) {
                        case 1 ->
                            detalle = "Requisito para solicitud de crédito estudiantil";
                        case 2 ->
                            detalle = "Requisito para seguro médico";
                        case 3 ->
                            detalle = "Trámites migratorios";
                        case 4 ->
                            detalle = "Inscripción en actividades extracurriculares";
                        case 5 ->
                            detalle = "Revalidación de matrícula en otra institución educativa";
                        default ->
                            System.out.println("Opción no válida. Por favor, seleccione una razón válida.");
                    }
                }
                default ->
                    System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y 3.");
            }
        }

        s.setAsunto(asunto);
        s.setRazon(detalle);
        s.setEstado("Pendiente");

        crearSolicitud(s);
    }

    public void revisarSolEst() {
        try {
            // Consulta SQL que une las tablas solicitudes y personas, filtrando solo las solicitudes pendientes
            String consultaSQL = "SELECT solicitudes.Sol_Codigo, solicitudes.Sol_Asunto, solicitudes.Sol_Razon, solicitudes.Sol_Fecha, solicitudes.Sol_Estado, personas.Per_Nombre, personas.Per_Apellido "
                    + "FROM solicitudes "
                    + "JOIN personas ON solicitudes.Per_Id = personas.Per_Id "
                    + "WHERE solicitudes.Sol_Estado = 'Pendiente';";

            Statement statement = (Statement) connection.createStatement();
            ResultSet resultado = statement.executeQuery(consultaSQL);

            // Iterar sobre los resultados y mostrar los datos deseados
            while (resultado.next()) {
                String solCodigo = resultado.getString("Sol_Codigo");
                String solAsunto = resultado.getString("Sol_Asunto");
                String solRazon = resultado.getString("Sol_Razon");
                String solFecha = resultado.getString("Sol_Fecha");
                String solEstado = resultado.getString("Sol_Estado");
                String nombre = resultado.getString("Per_Nombre");
                String apellido = resultado.getString("Per_Apellido");

                // Mostrar la información incluyendo nombre y apellido
                System.out.println("--------------REVISIÓN--------------");
                System.out.println("Código de Solicitud: " + solCodigo);
                System.out.println("Asunto: " + solAsunto);
                System.out.println("Detalle: " + solRazon);
                System.out.println("Fecha: " + solFecha);
                System.out.println("Estado: " + solEstado);
                System.out.println("Nombre: " + nombre + " " + apellido);
                System.out.println("------------------------------------");
            }

            resultado.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error en la revisión de las solicitudes: " + e.getMessage());
        }
    }

    public void consultarSolEst() {
        try {
            // Consulta SQL que une las tablas solicitudes y personas
            String consultaSQL = "SELECT solicitudes.Sol_Codigo, solicitudes.Sol_Asunto, solicitudes.Sol_Razon, solicitudes.Sol_Fecha, solicitudes.Sol_Estado, personas.Per_Nombre, personas.Per_Apellido FROM solicitudes JOIN personas ON solicitudes.Per_Id = personas.Per_Id;";

            Statement statement = (Statement) connection.createStatement();
            ResultSet resultado = statement.executeQuery(consultaSQL);

            // Iterar sobre los resultados y mostrar los datos deseados
            while (resultado.next()) {
                String solCodigo = resultado.getString("Sol_Codigo");
                String solAsunto = resultado.getString("Sol_Asunto");
                String solRazon = resultado.getString("Sol_Razon");
                String solFecha = resultado.getString("Sol_Fecha");
                String solEstado = resultado.getString("Sol_Estado");
                String nombre = resultado.getString("Per_Nombre");
                String apellido = resultado.getString("Per_Apellido");

                // Mostrar la información incluyendo nombre y apellido
                System.out.println("--------------CONSULTA--------------");
                System.out.println("Código de Solicitud: " + solCodigo);
                System.out.println("Asunto: " + solAsunto);
                System.out.println("Detalle: " + solRazon);
                System.out.println("Fecha: " + solFecha);
                System.out.println("Fecha: " + solEstado);
                System.out.println("Estudiante: " + nombre + " " + apellido);
                System.out.println("------------------------------------");
            }

            resultado.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar las solicitudes: " + e.getMessage());
        }
    }

    public String generarCodigoSolicitud() {
        try {
            String consultaSQL = "SELECT Sol_Codigo FROM solicitudes ORDER BY Sol_Id DESC LIMIT 1;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();

            if (resultado.next()) {
                String ultimoCodigo = resultado.getString("Sol_Codigo");
                int numeroActual = Integer.parseInt(ultimoCodigo.substring(1));
                int nuevoNumero = numeroActual + 1;
                return String.format("S%04d", nuevoNumero);
            } else {
                return "S0001";
            }
        } catch (SQLException e) {
            System.out.println("Error al generar el código de solicitud: " + e);
        }
        return null; // Devuelve null en caso de error
    }

    public void consultarSolicitudesMain(int logeado) {
        ArrayList<Solicitud> listaSolicitudes = consultarSolicitudes(logeado);
        for (Solicitud l : listaSolicitudes) {
            System.out.println(l.imprimir());
        }
    }

    public ArrayList<Solicitud> consultarSolicitudes(int idPersona) {
        ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String consultaSQL = "SELECT * FROM solicitudes WHERE Sol_Estado='Pendiente' AND Per_Id=" + idPersona + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);

            resultado = ejecutar.executeQuery();

            while (resultado.next()) {
                Solicitud s = new Solicitud();
                s.setCodigoSol(resultado.getString("Sol_Codigo"));
                s.setFecha(resultado.getString("Sol_Fecha"));
                s.setAsunto(resultado.getString("Sol_Asunto"));
                s.setRazon(resultado.getString("Sol_Razon"));
                s.setEstado(resultado.getString("Sol_Estado"));
                listaSolicitudes.add(s);
            }
            resultado.close();
            return listaSolicitudes;
        } catch (SQLException e) {
            System.out.println("ERROR en la consulta de solicitudes: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar los recursos: " + ex.getMessage());
            }
        }
        return null;

    }

    public void revisarSolicitudesMain(Scanner es) {

        // Mostrar la información detallada de todas las solicitudes incluyendo nombre y apellido del estudiante
        revisarSolEst();

        // Solicitar al usuario que seleccione el código de la solicitud que desea actualizar
        System.out.println("Seleccione el código de la solicitud que desea actualizar:");
        String seleccion = es.nextLine();

        // Buscar la solicitud seleccionada
        Solicitud solicitudSeleccionada = null;
        ArrayList<Solicitud> listaSolicitudes = revisarSolicitud();
        for (Solicitud solicitud : listaSolicitudes) {
            if (solicitud.getCodigoSol().equals(seleccion)) {
                solicitudSeleccionada = solicitud;
                break;
            }
        }

        // Verificar si se encontró la solicitud
        if (solicitudSeleccionada == null) {
            System.out.println("Código de solicitud no válido.");
            return;
        }

        // Actualizar el estado de la solicitud
        String estado = "";
        boolean estadoValido = false;

        while (!estadoValido) {
            System.out.println("Indique el estado de la solicitud:");
            System.out.println("1. Aceptar");
            System.out.println("2. Rechazar");

            int opcion = es.nextInt();

            switch (opcion) {
                case 1 -> {
                    estado = "Aprobado";
                    estadoValido = true;
                }
                case 2 -> {
                    estado = "Rechazado";
                    estadoValido = true;
                }
                default ->
                    System.out.println("Opción no válida. Por favor, seleccione 1 para Aceptar o 2 para Rechazar.");
            }
        }

        solicitudSeleccionada.setEstado(estado);
        actualizarSolicitud(solicitudSeleccionada);

        if (estado.equals("Aprobado") || estado.equals("Aceptado")) {
            Turno t = new Turno();
            Fechas f = new Fechas();
            f.fechaTurno(t);
            f.horaTurno(t);
            t.setRetiro("Ir a retirar");
            t.setIdSolicitud(solicitudSeleccionada.getIdSolicitud());
            TurnoControlador tC = new TurnoControlador();
            tC.crearTurno(t);
        }
    }

    public ArrayList<Solicitud> revisarSolicitud() {
        ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String consultaSQL = "SELECT Sol_Id, Sol_Codigo, Sol_Fecha, Sol_Asunto, Sol_Razon, Sol_Estado, Per_Id FROM solicitudes WHERE Sol_Estado = 'Pendiente'";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Solicitud s = new Solicitud();
                s.setIdSolicitud(resultSet.getInt("Sol_Id"));
                s.setCodigoSol(resultSet.getString("Sol_Codigo"));
                s.setFecha(resultSet.getString("Sol_Fecha"));
                s.setAsunto(resultSet.getString("Sol_Asunto"));
                s.setRazon(resultSet.getString("Sol_Razon"));
                s.setEstado(resultSet.getString("Sol_Estado"));
                s.setIdPersona(resultSet.getInt("Per_Id"));

                // Agregar la s a la lista
                listaSolicitudes.add(s);
            }
        } catch (SQLException e) {
            System.out.println("ERROR en la consulta de solicitudes: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar los recursos: " + ex.getMessage());
            }
        }

        return listaSolicitudes; // Retorna la lista de solicitudes pendientes
    }

    public void actualizarSolicitud(Solicitud s) {
        try {
            String consultaSQL = "UPDATE solicitudes SET Sol_Estado='" + s.getEstado() + "' WHERE Sol_Codigo='" + s.getCodigoSol() + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("La solicitud ha sido actualizada con éxito");
                ejecutar.close(); //Siempre cierro mi conlsuta
            } else {
                System.out.println("Favor ingrese correctamente los datos solicitados: ");
                ejecutar.close(); //Siempre cierro mi conlsuta
            }

        } catch (SQLException e) { //Captura el error el (e)
            System.out.println("Comuníquese con el Administrador, gracias!!:" + e);
        } //Captura el error y permite que la consola se siga ejecutando
    }

    /**
     * Método para obtener las solicitudes por estudiante
     * @param idEstudiante
     * @return 
     */
    public ArrayList<Solicitud> obtenerSolicitudesPorEstudiante(int idEstudiante) {
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        try {
            String consultaSQL = "SELECT * FROM solicitudes WHERE Per_Id=?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            preparedStatement.setInt(1, idEstudiante);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Solicitud solicitud = new Solicitud();
                solicitud.setIdSolicitud(resultSet.getInt("Sol_Id"));
                solicitud.setCodigoSol(resultSet.getString("Sol_Codigo"));
                solicitud.setFecha(resultSet.getString("Sol_Fecha"));
                solicitud.setAsunto(resultSet.getString("Sol_Asunto"));
                solicitud.setRazon(resultSet.getString("Sol_Razon"));
                solicitud.setEstado(resultSet.getString("Sol_Estado"));
                solicitudes.add(solicitud);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener solicitudes: " + e.getMessage());
        }

        return solicitudes;
    }
}
