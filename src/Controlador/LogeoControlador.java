/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelo.Estudiante;
import modelo.Persona;
import modelo.Secretaria;
import vista.Main2;
import static vista.Main2.menuEstudiante;
import static vista.Main2.menuSecretaria;
import static vista.Main2.menuAdministrador;

/**
 *
 * @author LENOVO
 */
public class LogeoControlador {

    public void iniciarSesion(Scanner es) {
        Persona p = new Persona();
        limpiarPantalla();
        System.out.println("Login Usuario");

        // Validar la cédula
        String cedula;
        boolean cedulaValida = false;

        do {
            System.out.println("Ingrese Cédula:");
            cedula = es.nextLine();

            if (verificarCedula(cedula)) {
                cedulaValida = true;
                p.setCedula(cedula);
            } else {
                System.out.println("Cédula inválida. La cédula debe tener exactamente 10 dígitos.");
            }
        } while (!cedulaValida);

        System.out.println("Ingrese la contraseña:");
        p.setClave(es.nextLine());

        PersonaControlador pc = new PersonaControlador();
        EstudianteControlador eC = new EstudianteControlador();
        SecretariaControlador secC = new SecretariaControlador();
        AdministradorControlador adm = new AdministradorControlador();
        int logeado = pc.login(p.getCedula(), p.getClave());

        if (logeado != 0) {
            int idestudiante = eC.verificarRolEstudiante(logeado);
            int idsecretaria = secC.verificarRolSecretaria(logeado);
            int idadministrador = adm.verificarRolAdministrador(logeado);

            String rol = "";

            if (idestudiante != 0) {
                rol = "Estudiante";
            } else if (idsecretaria != 0) {
                rol = "Secretaria";
            } else if (idadministrador != 0) {
                rol = "Administrador";
            }

            switch (rol) {
                case "Estudiante" ->
                    menuEstudiante(es, logeado);
                case "Secretaria" ->
                    menuSecretaria(es, logeado);
                case "Administrador" ->
                    menuAdministrador(es, logeado);
                default ->
                    System.out.println("Usuario no asignado, solicite a Secretaria que le asigne un rol");
            }
        } else {
            System.out.println("Contraseña o Cédula Incorrecta");
        }
    }

//    public void registrarse(Scanner es) {
//        int option = -1;
//        do {
//            System.out.println("Seleccione una opción:\n"
//                    + "1. Registrarse como Estudiante\n"
//                    + "2. Registrarse como Secretaria\n"
//                    + "0. Regresar");
//            option = es.nextInt();
//            es.nextLine(); // Consumir el salto de línea residual
//
//            switch (option) {
//                case 1 ->
//                    registrarEstudiante(es);
//                case 0 ->
//                    System.out.println("Regresando al menú principal...");
//                default ->
//                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
//            }
//        } while (option != 0);
//    }
    public void registrarEstudiante(Scanner es) {
        LogeoControlador lc = new LogeoControlador();
        boolean cedulaValida = false;
        boolean telefonoValido = false;
        limpiarPantalla();
        System.out.println("Ingrese los siguientes datos para registrarse como estudiante...");
        es.nextLine();
        Persona pe = new Persona();
        System.out.println("Ingrese su Nombre: ");
        pe.setNombre(es.nextLine());
        System.out.println("Ingrese su Apellido: ");
        pe.setApellido(es.nextLine());
        do {
            System.out.println("Ingrese el número de cédula: ");
            String cedula = es.nextLine();

            if (lc.verificarCedula(cedula)) {
                cedulaValida = true;
                pe.setCedula(cedula);
            } else {
                System.out.println("Cédula inválida. La cédula debe tener exactamente 10 dígitos. Por favor, intente nuevamente.");
            }
        } while (!cedulaValida);

        System.out.println("Ingrese su contraseña: ");
        pe.setClave(es.nextLine());
        do {
        System.out.println("Ingrese su teléfono: ");
        String telefono = es.nextLine();

        if (lc.verificarTelefono(telefono)) {
            telefonoValido = true;
            pe.setTelefono(telefono);
        } else {
            System.out.println("Teléfono inválido. El teléfono debe tener exactamente 10 dígitos. Por favor, intente nuevamente.");
        }
    } while (!telefonoValido);
        System.out.println("Ingrese su correo Electrónico: ");
        pe.setCorreo(es.nextLine());
        System.out.println("Ingrese su Dirección: ");
        pe.setDireccion(es.nextLine());

        // CONTROLADOR
        PersonaControlador pc = new PersonaControlador();
        pc.crearPersona(pe);

        int idPersona = pc.buscarIdPersona(pe.getCedula());

        Estudiante est = new Estudiante();
        System.out.println("Ingrese el código de estudiante");

        String carrera1 = "TECNOLOGÍA SUPERIOR EN DESARROLLO DE SOFTWARE";
        String carrera2 = "TECNOLOGÍA SUPERIOR EN REDES Y TELECOMUNICACIONES";
        String carrera3 = "TECNOLOGÍA SUPERIOR EN ELECTRÓNICA";
        String carrera4 = "TECNOLOGÍA SUPERIOR EN AUTOMATIZACIÓN E INSTRUMENTACIÓN";
        String carrera5 = "TECNOLOGÍA SUPERIOR EN ELECTRICIDAD";
        String carrera6 = "TECNOLOGÍA SUPERIOR EN MECÁNICA INDUSTRIAL";
        String carrera7 = "TECNOLOGÍA SUPERIOR EN MECÁNICA AUTOMOTRIZ";
        String carrera8 = "TECNOLOGÍA SUPERIOR EN ENTRENAMIENTO DEPORTIVO";
        String carrera9 = "TECNOLOGÍA SUPERIOR EN PROCESAMIENTO DE ALIMENTOS";
        String carrera10 = "TECNOLOGÍA SUPERIOR EN QUÍMICA";
        String carrera11 = "TECNOLOGÍA SUPERIOR EN BIOTECNOLOGÍA";
        String carrera12 = "TECNOLOGÍA SUPERIOR EN CONTROL DE INCENDIOS Y OPERACIONES DE RESCATE";
        // Selección de carrera
        System.out.println("Seleccione su carrera: ");
        System.out.println("1. " + carrera1);
        System.out.println("2. " + carrera2);
        System.out.println("3. " + carrera3);
        System.out.println("4. " + carrera4);
        System.out.println("5. " + carrera5);
        System.out.println("6. " + carrera6);
        System.out.println("7. " + carrera7);
        System.out.println("8. " + carrera8);
        System.out.println("9. " + carrera9);
        System.out.println("10. " + carrera10);
        System.out.println("11. " + carrera11);
        System.out.println("12. " + carrera12);

        int carreraOpcion = es.nextInt();
        es.nextLine(); // Consumir el salto de línea residual

        String carrera;
        switch (carreraOpcion) {
            case 1 ->
                carrera = carrera1;
            case 2 ->
                carrera = carrera2;
            case 3 ->
                carrera = carrera3;
            case 4 ->
                carrera = carrera4;
            case 5 ->
                carrera = carrera5;
            case 6 ->
                carrera = carrera6;
            case 7 ->
                carrera = carrera7;
            case 8 ->
                carrera = carrera8;
            case 9 ->
                carrera = carrera9;
            case 10 ->
                carrera = carrera10;
            case 11 ->
                carrera = carrera11;
            case 12 ->
                carrera = carrera12;
            default -> {
                System.out.println("Opción no válida. Por favor, intente nuevamente.");
                return; // Si la opción no es válida, se termina la ejecución del método.
            }
        }

        est.setCarrera(carrera);

        // Selección del nivel de carrera
        int nivel;
        do {
            System.out.println("Ingrese su nivel de carrera: ");
            System.out.println("1. Primero");
            System.out.println("2. Segundo");
            System.out.println("3. Tercero");
            System.out.println("4. Cuarto");
            System.out.println("5. Quinto");

            nivel = es.nextInt();
            es.nextLine(); // Consumir el salto de línea residual

            if (nivel < 1 || nivel > 5) {
                System.out.println("Nivel no válido. Por favor, seleccione un número entre 1 y 5.");
            }
        } while (nivel < 1 || nivel > 5);

        est.setNivel(nivel);
        est.setIdPersona(idPersona);

        EstudianteControlador estC = new EstudianteControlador();
        estC.crearEstudiante(est);
        System.out.println("Registro de estudiante completado exitosamente.");
    }

    public void registrarSecretaria(Scanner es) {
        limpiarPantalla();
        LogeoControlador lc = new LogeoControlador();
        boolean cedulaValida = false;
        boolean telefonoValido = false;
        System.out.println("Ingrese los siguientes datos para registrarse como secretaria...");
        Persona pe = new Persona();
        es.nextLine();
        System.out.println("Ingrese su Nombre: ");
        pe.setNombre(es.nextLine());
        System.out.println("Ingrese su Apellido: ");
        pe.setApellido(es.nextLine());
        do {
            System.out.println("Ingrese el número de cédula: ");
            String cedula = es.nextLine();

            if (lc.verificarCedula(cedula)) {
                cedulaValida = true;
                pe.setCedula(cedula);
            } else {
                System.out.println("Cédula inválida. La cédula debe tener exactamente 10 dígitos. Por favor, intente nuevamente.");
            }
        } while (!cedulaValida);

        System.out.println("Ingrese su contraseña: ");
        pe.setClave(es.nextLine());
        do {
        System.out.println("Ingrese su teléfono: ");
        String telefono = es.nextLine();

        if (lc.verificarTelefono(telefono)) {
            telefonoValido = true;
            pe.setTelefono(telefono);
        } else {
            System.out.println("Teléfono inválido. El teléfono debe tener exactamente 10 dígitos. Por favor, intente nuevamente.");
        }
    } while (!telefonoValido);
        System.out.println("Ingrese su correo Electrónico: ");
        pe.setCorreo(es.nextLine());
        System.out.println("Ingrese su Dirección: ");
        pe.setDireccion(es.nextLine());

        // CONTROLADOR
        PersonaControlador pc = new PersonaControlador();
        pc.crearPersona(pe);

        int idPersona = pc.buscarIdPersona(pe.getCedula());

        Secretaria sec = new Secretaria();
        System.out.println("Ingrese su área: ");
        sec.setArea(es.nextLine());
        sec.setIdPersona(idPersona);

        AdministradorControlador admC = new AdministradorControlador();
        admC.crearSecretaria(sec);
        System.out.println("Registro de secretaria completado exitosamente.");
    }

    public void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
        }
    }

    public boolean verificarCedula(String cedula) {
        // Expresión regular para encontrar exactamente 10 dígitos
        String regex = "\\b\\d{10}\\b";

        // Compilar la expresión regular
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cedula);

        // Verificar si la cédula coincide con la expresión regular
        return matcher.matches();
    }
    public boolean verificarTelefono(String telefono) {
    // Expresión regular para encontrar exactamente 10 dígitos
    String regex = "^09\\d{8}$";

    // Compilar la expresión regular
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(telefono);

    // Verificar si coincide
    return matcher.matches();
}

}
