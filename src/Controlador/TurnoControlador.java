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
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Solicitud;
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
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado;

    public void crearTurno(Turno t) {
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL = "INSERT INTO turnos(Tur_Codigo,Tur_Fecha,Tur_Hora,Tur_Retiro,Sol_Id) VALUES ('" + generarCodigoTurno() + "','" + t.getFecha() + "','" + t.getHora() + "','" + t.getRetiro() + "','" + t.getIdSolicitud() + "');";
            //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
            //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("El turno ha sido creado con éxito");
                ejecutar.close(); //Siempre cierro mi conlsuta
            } else {
                System.out.println("Favor ingrese correctamente los datos solicitados: ");
                ejecutar.close(); //Siempre cierro mi conlsuta
            }

        } catch (SQLException e) { //Captura el error el (e)
            System.out.println("Por favor, Comuníquese con el Administrador, gracias!!" + e);
        } //Captura el error y permite que la consola se siga ejecutando
    }

    public void consultarTurnoMain(int idEstudiante) {
        SolicitudControlador sc = new SolicitudControlador();
        ArrayList<Solicitud> solicitudes = sc.obtenerSolicitudesPorEstudiante(idEstudiante);

        if (solicitudes.isEmpty()) {
            System.out.println("No existen solicitudes disponibles.");
            return;
        }

        String consultaSQL = "SELECT * FROM turnos WHERE Tur_Retiro='Ir a retirar' AND Sol_Id=?";
        boolean hayTurnos = false;

        try (PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(consultaSQL)) {
            for (Solicitud s : solicitudes) {
                stmt.setInt(1, s.getIdSolicitud());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("*----------CONSULTA----------*");
                        System.out.printf("Código: %s%n", rs.getString("Tur_Codigo"));
                        System.out.printf("Fecha de retiro: %s%n", rs.getString("Tur_Fecha"));
                        System.out.printf("Hora de aprobación: %s%n", rs.getString("Tur_Hora"));
                        System.out.printf("Retiro: %s%n", rs.getString("Tur_Retiro"));
                        System.out.println("*----------------------------*");
                        hayTurnos = true;
                    }
                }
            }

            if (!hayTurnos) {
                System.out.println("No existen turnos disponibles.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los turnos: " + e.getMessage());
        }
    }

    public ArrayList<Turno> consultarTurno(int idSolicitud) {
        ArrayList<Turno> listaTurnos = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String consultaSQL = "SELECT * FROM turnos WHERE Tur_Retiro='Ir a retirar' AND Sol_Id=?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            preparedStatement.setInt(1, idSolicitud);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Turno t = new Turno();
                t.setCodigo(resultSet.getString("Tur_Codigo"));
                t.setFecha(resultSet.getString("Tur_Fecha"));
                t.setHora(resultSet.getString("Tur_Hora"));
                t.setRetiro(resultSet.getString("Tur_Retiro"));
                listaTurnos.add(t);
            }
            resultSet.close();
            return listaTurnos;
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
        return listaTurnos;
    }

    public void actualizarTurnosMain(Scanner es) {
        // Mostrar información de todos los turnos con el nombre del estudiante
        mostrarTurnosConEstudiantes();

        // Solicitar al usuario que seleccione el código del turno que desea actualizar
        System.out.println("Seleccione el código del turno que desea actualizar:");
        String seleccion = es.nextLine();

        // Buscar el turno seleccionado
        Turno turnoseleccionado = null;
        ArrayList<Turno> listaTurno = actualizarTurno();
        for (Turno t : listaTurno) {
            if (t.getCodigo().equals(seleccion)) {
                turnoseleccionado = t;
                break;
            }
        }

        // Verificar si se encontró el turno
        if (turnoseleccionado == null) {
            System.out.println("Código de turno no válido.");
            return;
        }

        // Actualizar la fecha y hora del turno y marcarlo como retirado
        Fechas f = new Fechas();
        f.fechaTurno(turnoseleccionado);
        f.horaTurno(turnoseleccionado);
        turnoseleccionado.setRetiro("Retirado");

        // Actualizar el turno en la base de datos
        actualizarTurno(turnoseleccionado);
    }

    public void mostrarTurnosConEstudiantes() {
        try {
            // Consulta SQL que une las tablas turnos, solicitudes, estudiantes y personas
            String consultaSQL = "SELECT t.Tur_Codigo, t.Tur_Fecha, t.Tur_Hora, t.Tur_Retiro, p.Per_Nombre, p.Per_Apellido "
                    + "FROM turnos t "
                    + "JOIN solicitudes s ON t.Sol_Id = s.Sol_Id "
                    + "JOIN estudiantes e ON s.Per_Id = e.Per_Id "
                    + "JOIN personas p ON e.Per_Id = p.Per_Id "
                    + "WHERE t.Tur_Retiro = 'Ir a retirar';";

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            // Mostrar la información de los turnos y los estudiantes
                System.out.println("*--------------CONSULTA DE TURNOS---------------*");
            while (resultado.next()) {
                String codigo = resultado.getString("Tur_Codigo");
                String fecha = resultado.getString("Tur_Fecha");
                String hora = resultado.getString("Tur_Hora");
                String retiro = resultado.getString("Tur_Retiro");
                String nombreEstudiante = resultado.getString("Per_Nombre");
                String apellidoEstudiante = resultado.getString("Per_Apellido");

                System.out.println("Código del Turno: " + codigo);
                System.out.println("Fecha: " + fecha);
                System.out.println("Hora: " + hora);
                System.out.println("Retiro: " + retiro);
                System.out.println("Estudiante: " + nombreEstudiante + " " + apellidoEstudiante);
                System.out.println("*-----------------------------------------------*");
            }

            resultado.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Error al consultar los turnos: " + e.getMessage());
        }
    }

    public ArrayList<Turno> actualizarTurno() {
        ArrayList<Turno> listaTurnos = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String consultaSQL = "SELECT * FROM turnos WHERE Tur_Retiro = 'Ir a retirar'";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Turno s = new Turno();
                s.setIdTurno(resultSet.getInt("Tur_Id"));
                s.setCodigo(resultSet.getString("Tur_Codigo"));
                s.setFecha(resultSet.getString("Tur_Fecha"));
                s.setHora(resultSet.getString("Tur_Hora"));
                s.setRetiro(resultSet.getString("Tur_Retiro"));
                s.setIdSolicitud(resultSet.getInt("Sol_Id"));

                // Agregar la s a la lista
                listaTurnos.add(s);
            }
        } catch (SQLException e) {
            System.out.println("ERROR en la consulta de turnos: " + e.getMessage());
        }

        return listaTurnos; // Retorna la lista de solicitudes pendientes
    }

    public void mostrarTur() {
        try {
            // Consulta SQL que une las tablas solicitudes y personas
            String consultaSQL = "SELECT * FROM turnos WHERE Tur_Retiro='Ir a retirar';";

            Statement statement = (Statement) connection.createStatement();
            ResultSet resultado = statement.executeQuery(consultaSQL);

            // Iterar sobre los resultados y mostrar los datos deseados
            while (resultado.next()) {
                String turCodigo = resultado.getString("Tur_Codigo");
                String turFecha = resultado.getString("Tur_Fecha");
                String turHora = resultado.getString("Tur_Hora");
                String turRetiro = resultado.getString("Tur_Retiro");

                // Mostrar la información incluyendo nombre y apellido
                System.out.println("--------------CONSULTA--------------");
                System.out.println("Código del Turno: " + turCodigo);
                System.out.println("Fecha de turno: " + turFecha);
                System.out.println("Hora: " + turHora);
                System.out.println("Fecha de solicitud: " + turRetiro);
                System.out.println("------------------------------------");
            }

            resultado.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error en la revisión de turnos: " + e.getMessage());
        }
    }

    public void actualizarTurno(Turno t) {
        try {
            // Consulta SQL para actualizar fecha, hora y retiro del turno
            String consultaSQL = "UPDATE turnos SET Tur_Fecha='" + t.getFecha()
                    + "', Tur_Hora='" + t.getHora()
                    + "', Tur_Retiro='" + t.getRetiro()
                    + "' WHERE Tur_Codigo='" + t.getCodigo() + "';";

            // Ejecutar la consulta
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            int res = ejecutar.executeUpdate();

            if (res > 0) {
                System.out.println("El turno ha sido actualizado con éxito");
            } else {
                System.out.println("Favor ingrese correctamente los datos solicitados.");
            }

            ejecutar.close(); // Cerrar la consulta siempre

        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador, gracias!!: " + e);
        }
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
