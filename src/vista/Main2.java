/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.AdministradorControlador;
import Controlador.EstudianteControlador;
import Controlador.LogeoControlador;
import Controlador.PersonaControlador;
import Controlador.SecretariaControlador;
import Controlador.SolicitudControlador;
import Controlador.TurnoControlador;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Persona;
import modelo.Secretaria;
import modelo.Solicitud;
import modelo.Turno;

/**
 *
 * @author LENOVO
 */
public class Main2 {

    public static void main(String[] args) {
        LogeoControlador lc = new LogeoControlador();
        Scanner es = new Scanner(System.in);
        int i = 1;

        do {
            lc.limpiarPantalla();
            System.out.println("BIENVENIDO AL SISTEMA");
            System.out.println("Seleccione una opción:\n"
                    + "1. Iniciar Sesión\n"
                    + "2. Registrarse como Estudiante\n"
                    + "0. Salir");
            int mainOption = es.nextInt();
            es.nextLine(); // Consumir el salto de línea residual

            switch (mainOption) {// 1
                case 1 ->
                    lc.iniciarSesion(es);
                case 2 ->
                    lc.registrarEstudiante(es);
                case 0 -> {
                    i = 0;
                    System.out.println("Gracias por usar el sistema!");
                }
                default ->
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (i == 1);
    }

    public static void menuEstudiante(Scanner es, int logeado) {
        LogeoControlador lc = new LogeoControlador();
        int i = 1;
        do {
            lc.limpiarPantalla();
            System.out.println("*----------MENU ESTUDIANTE----------*");
            System.out.println("""
                               Elija la opción que necesite:
                               1. Crear Solicitud
                               2. Consultar Solicitud
                               3. Editar Solicitud
                               0. Salir""");
            int op1 = es.nextInt();
            es.nextLine(); // Consumir el salto de línea residual
            EstudianteControlador estC = new EstudianteControlador();
            SolicitudControlador sc = new SolicitudControlador();
            Solicitud s = new Solicitud();
            switch (op1) {
                case 1 ->
                    sc.crearSolicitud(es, logeado);
                case 2 ->
                    sc.consultarSolicitudesMain(logeado);

                case 3 -> {
                    sc.consultarSolicitudesMain(logeado);
                    estC.editarSolicitud();
                }
                case 0 -> {
                    i = 0;
                    System.out.println("Saliendo al menú principal...");
                }
                default ->
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
            System.out.println(""); // Línea en blanco para separación
            System.out.println("Presione Enter para continuar...");
            es.nextLine(); // Pausa esperando que el usuario presione Enter

        } while (i == 1);
    }

    public static void menuSecretaria(Scanner es, int logeado) {
        int i = 1;
        do {
            LogeoControlador lc = new LogeoControlador();
            EstudianteControlador ec = new EstudianteControlador();
            SolicitudControlador sc = new SolicitudControlador();
            lc.limpiarPantalla();
            System.out.println("*----------MENÚ SECRETARIA----------*");
            System.out.println("""
                               Elija la opción que necesite:
                               1. Consultar Estudiantes
                               2. Revisar Solicitudes
                               0. Salir""");
            int op1 = es.nextInt();
            es.nextLine(); // Consumir el salto de línea residual

            switch (op1) {
                case 1 ->
                    ec.consultarEstudiantesMain();
                case 2 ->
                    sc.revisarSolicitudesMain(es);
                case 0 -> {
                    i = 0;
                    System.out.println("Gracias por usar el servicio!");
                }
                default ->
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (i == 1);
    }

    public static void menuAdministrador(Scanner es, int logeado) {
        int i = 1;
        do {
            AdministradorControlador adm = new AdministradorControlador();
            LogeoControlador lg = new LogeoControlador();
            lg.limpiarPantalla();
            System.out.println("BIENVENIDO");
            System.out.println("*----------MENÚ ADMINISTRADOR----------*");
            System.out.println("""
                               Elija la opción que necesite:
                               1. Registrar secretarias
                               2. Consultar secretarias
                               0. Salir""");
            int op1 = es.nextInt();
            es.nextLine(); // Consumir el salto de línea residual

            switch (op1) {
                case 1 ->
                    lg.registrarSecretaria(es);
                case 2 ->
                    adm.consultarSecretarias();
                case 0 -> {
                    i = 0;
                    System.out.println("Gracias por usar el servicio!");
                }
                default ->
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (i == 1);
    }
}
