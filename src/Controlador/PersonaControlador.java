/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection; // Para la versión 5.1.49
import com.mysql.jdbc.PreparedStatement; // Para la versión 5.1.49
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Persona;
import modelo.Estudiante;

public class PersonaControlador {
    private Estudiante estudiante;
    ConexionBDD conexion = new ConexionBDD();
    Connection connection; // Solo declara la variable aquí

    public PersonaControlador() {
        // Inicializa la conexión en el constructor
        connection = (Connection) conexion.conectar();
    }

    public void crearPersona(Persona p) {
        try {
            String consultaSQL = "INSERT INTO personas(Per_Nombre, Per_Apellido, Per_Cedula, Per_Clave, Per_Telefono, Per_Correo, Per_Direccion) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, p.getNombre());
            ejecutar.setString(2, p.getApellido());
            ejecutar.setString(3, p.getCedula());
            ejecutar.setString(4, p.getClave());
            ejecutar.setString(5, p.getTelefono());
            ejecutar.setString(6, p.getCorreo());
            ejecutar.setString(7, p.getDireccion());

            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("La persona ha sido creada con éxito");
            } else {
                System.out.println("Favor ingrese correctamente los datos solicitados: ");
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("Por favor, comuníquese con el Administrador, gracias!!" + e);
        }
    }

    public int buscarIdPersona(String cedula) {
        try {
            String consultaSQL = "SELECT Per_Id FROM personas WHERE Per_Cedula = ?;";
            PreparedStatement ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, cedula);
            ResultSet resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                int idPersona = resultado.getInt("Per_Id");
                return idPersona;
            } else {
                System.out.println("Ingrese una cédula válida");
            }
        } catch (SQLException e) {
            System.out.println("Por favor, comuníquese con el administrador" + e);
        }
        return 0;
    }

    public int buscarDatosPersona(int idPersona) {
        try {
            String consultaSQL = "SELECT * FROM personas WHERE Per_Id = ?;";
            PreparedStatement ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setInt(1, idPersona);
            ResultSet resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                Persona p = new Persona();
                p.setNombre(resultado.getString("Per_Nombre"));
                p.setApellido(resultado.getString("Per_Apellido"));
                p.setCedula(resultado.getString("Per_Cedula"));
                p.setTelefono(resultado.getString("Per_Telefono"));
                p.setCorreo(resultado.getString("Per_Correo"));
                p.setDireccion(resultado.getString("Per_Direccion"));

                return idPersona;
            } else {
                System.out.println("Ingrese una cédula válida");
            }
        } catch (SQLException e) {
            System.out.println("Por favor, comuníquese con el administrador" + e);
        }
        return 0;
    }

    public int login(String cedula, String clave) {
        int res = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String consultaSQL = "SELECT * FROM personas WHERE Per_Cedula = ?;";
            preparedStatement = (PreparedStatement) connection.prepareStatement(consultaSQL);
            preparedStatement.setString(1, cedula);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String contrasenia = resultSet.getString("Per_Clave");
                int idPersona = resultSet.getInt("Per_Id");
                if (contrasenia.equals(clave)) {
                    res = idPersona;
                } else {
                    res = 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR en la consulta de personas: " + e.getMessage());
            return res;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar los recursos: " + ex.getMessage());
            }
        }

        return res;
    }

    public Persona buscardatosPersona(String cedula) {
        Persona p = new Persona();
        try {
            String consultaSQL = "SELECT Per_Nombre, Per_Apellido, Per_Cedula, Per_Telefono, Per_Correo, Per_Direccion FROM personas WHERE Per_Cedula = ?;";
            PreparedStatement ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, cedula);
            ResultSet resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                p.setNombre(resultado.getString("Per_Nombre"));
                p.setApellido(resultado.getString("Per_Apellido"));
                p.setCedula(resultado.getString("Per_Cedula"));
                p.setTelefono(resultado.getString("Per_Telefono"));
                p.setCorreo(resultado.getString("Per_Correo"));
                p.setDireccion(resultado.getString("Per_Direccion"));
                resultado.close();
                return p;
            } else {
                System.out.println("Ingrese una cédula válida");
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el administrador" + e);
        }
        return null;
    }
}
