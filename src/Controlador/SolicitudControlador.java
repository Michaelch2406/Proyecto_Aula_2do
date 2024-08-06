/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
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

            String consultaSQL = "INSERT INTO solicitudes(Sol_Fecha,Sol_Codigo,Sol_Asunto,Sol_Detalle,Sol_Estado,Per_Id) VALUES ('" + s.getFecha() + "','" + s.getCodigoSol() + "','" + s.getAsunto() + "','" + s.getDetalle() + "','" + s.getEstado() + "','" + s.getIdPersona() + "');";
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
        LogeoControlador lc=new LogeoControlador();
        lc.limpiarPantalla();
        Solicitud s = new Solicitud();
        SolicitudControlador so = new SolicitudControlador();
        s.setIdPersona(logeado);
        es.nextLine();
        System.out.println("Ingrese los datos para la solicitud");
        es.nextLine();
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = sdf.format(todayDate);
        s.setFecha(fechaActual);
        String codigoSolicitud = so.generarCodigoSolicitud();
        s.setCodigoSol(codigoSolicitud);

        String asunto = "";
        boolean asuntoValido = false;

        while (!asuntoValido) {
            System.out.println("Ingrese el Asunto: ");
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

        s.setAsunto(asunto);
        System.out.println("Ingrese el Detalle de la solicitud: ");
        s.setDetalle(es.nextLine());
        s.setEstado("Pendiente");

        so.crearSolicitud(s);
    }
    
    public void consultarSolicitudesMain(int logeado) {
        SolicitudControlador soc = new SolicitudControlador();
        ArrayList<Solicitud> listaSolicitudes = soc.consultarSolicitudes(logeado);
        for (Solicitud l : listaSolicitudes) {
            System.out.println(l.imprimir());
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

    public ArrayList<Solicitud> consultarSolicitudes(int idPersona) {
        ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
// Usamos StringBuilder para construir la cadena de resultados

        try {
            String consultaSQL = "SELECT * FROM solicitudes WHERE Sol_Estado='Pendiente' AND Per_Id="+ idPersona + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);

            resultado = ejecutar.executeQuery();

            while (resultado.next()) {
                Solicitud s = new Solicitud();
                s.setCodigoSol(resultado.getString("Sol_Codigo"));
                s.setFecha(resultado.getString("Sol_Fecha"));
                s.setAsunto(resultado.getString("Sol_Asunto"));
                s.setDetalle(resultado.getString("Sol_Detalle"));
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

    public String consultarSolicitud() {
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
                        .append(" Fecha: ").append(solFecha).append("\n") // Directamente añadimos la fecha como cadena
                        .append(" Asunto: ").append(solAsunto).append("\n")
                        .append(" Detalle: ").append(solDetalle).append("\n")
                        .append(" Estado: ").append(solEstado).append("\n")
                        .append(" Id Persona: ").append(idPersona)
                        .append("\n"); // Agrega un salto de línea entre registros
            }
        } catch (SQLException e) {
            System.out.println("ERROR en la consulta de solicitudes: " + e.getMessage());
            return "Error en la consulta: " + e.getMessage(); // Retorna un mensaje de error si ocurre alguno
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

        return resultado.toString(); // Retorna la cadena completa de resultados
    }

    public ArrayList<Solicitud> consultarSolicitudEst(int idPersona) {
        ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String consultaSQL = "select * from solicitudes where Per_Id='" + idPersona + "';";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Solicitud solicitud = new Solicitud();
                solicitud.setCodigoSol(resultSet.getString("Sol_Codigo"));
                solicitud.setFecha(resultSet.getString("Sol_Fecha"));
                solicitud.setAsunto(resultSet.getString("Sol_Asunto"));
                solicitud.setDetalle(resultSet.getString("Sol_Detalle"));
                solicitud.setEstado(resultSet.getString("Sol_Estado"));
                // Agregar la solicitud a la lista
                listaSolicitudes.add(solicitud);
            }
        } catch (SQLException e) {
            System.out.println("Por favor, comuníquese con el administrador" + e);
        }
        return listaSolicitudes;
    }
    
    public void revisarSolicitudesMain(Scanner es) {
        Solicitud so = new Solicitud();
        SolicitudControlador scr = new SolicitudControlador();
        ArrayList<Solicitud> listaSolicitudes = scr.revisarSolicitud();

        // Verificar si hay solicitudes pendientes
        if (listaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        // Mostrar todas las solicitudes pendientes
        for (Solicitud solicitud : listaSolicitudes) {
            System.out.println(solicitud.revisarSol());
        }

        // Solicitar al usuario que seleccione el código de la solicitud
        System.out.println("Seleccione el código de la solicitud que desea actualizar:");
        String seleccion = es.nextLine();

        // Buscar la solicitud seleccionada
        Solicitud solicitudSeleccionada = null;
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
        scr.actualizarSolicitud(solicitudSeleccionada);

        if (estado.equals("Aprobado") || estado.equals("Aceptado")) {
            Turno t = new Turno();
            Date todayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = sdf.format(todayDate);
            t.setFecha(fechaActual);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            t.setHora(dateFormat.format(date));
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
            String consultaSQL = "SELECT Sol_Id, Sol_Codigo, Sol_Fecha, Sol_Asunto, Sol_Detalle, Sol_Estado, Per_Id FROM solicitudes WHERE Sol_Estado = 'Pendiente'";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Solicitud solicitud = new Solicitud();
                solicitud.setIdSolicitud(resultSet.getInt("Sol_Id"));
                solicitud.setCodigoSol(resultSet.getString("Sol_Codigo"));
                solicitud.setFecha(resultSet.getString("Sol_Fecha"));
                solicitud.setAsunto(resultSet.getString("Sol_Asunto"));
                solicitud.setDetalle(resultSet.getString("Sol_Detalle"));
                solicitud.setEstado(resultSet.getString("Sol_Estado"));
                // NO SE DEBE MOSTRAR?
                solicitud.setIdPersona(resultSet.getInt("Per_Id"));

                // Agregar la solicitud a la lista
                listaSolicitudes.add(solicitud);
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
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL = "UPDATE solicitudes SET Sol_Estado='" + s.getEstado() + "' WHERE Sol_Codigo='" + s.getCodigoSol() + "';";

            //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
            //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
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

    public void editarSolicitudEst(Solicitud s) {
        try {
            // Verificar si la solicitud no está aprobada o aceptada
            String consultaSQL = "UPDATE solicitudes SET Sol_Asunto = ?,Sol_Detalle = ?,Sol_Estado = ? WHERE Sol_Codigo = ? AND NOT (Sol_Estado = 'Aprobado' OR Sol_Estado = 'Aceptado');";
            PreparedStatement ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, s.getAsunto());
            ejecutar.setString(1, s.getDetalle());
            ejecutar.setString(2, s.getEstado());
            ejecutar.setString(3, s.getCodigoSol());

            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("La solicitud ha sido actualizada con éxito.");
            } else {
                System.out.println("No se pudo actualizar la solicitud. La solicitud ya está aprobada o aceptada, o el código es incorrecto.");
            }

            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador, gracias: " + e.getMessage());
        }
    }

}
