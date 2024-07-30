import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectionExample {

    // Configuración de la conexión
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fiestacumpleanios"; //Fiestacumpleanios se cambia
    private static final String USER = "root";
    private static final String PASS = "admin";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Registrar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Abrir la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Ejecutar una consulta
            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql = "select * from cliente"; //CAMBIAR ESTUDIANTE, PERSONA, SECRETARIA
            // Extraer datos del ResultSet
            try (ResultSet rs = stmt.executeQuery(sql)) {
                // Extraer datos del ResultSet
                while (rs.next()) {
                    // Recuperar por nombre de columna
                    int id = rs.getInt("Cl_Ci");
                    String nombre = rs.getString("Cl_Nombres");
                    String apellido = rs.getString("Cl_Email");
                    
                    // Mostrar valores
                    System.out.print("ID: " + id);
                    System.out.print(", Nombre: " + nombre);
                    System.out.println(", Correo: " + apellido);
                }
                // Limpiar entorno
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException se) {
            // Manejar errores para JDBC

        }
        // Manejar errores para Class.forName
         finally {
            // Bloque finally utilizado para cerrar recursos
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nada podemos hacer
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
            }
        }
        System.out.println("Adiós!");
    }
}