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
public class Main {

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
            int mainOption = lc.leerOpcion(es);

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
            System.out.println("*--------------BIENVENIDO AL--------------*");
            System.out.println("              MENÚ ESTUDIANTE");
            System.out.println("""
                               Elija la opción que necesite:
                               1. Crear Solicitud
                               2. Consultar Solicitud
                               3. Editar Solicitud
                               4. Consultar Turno //EN PROCESO FALTA DIA DE RETIRO, Y QUE SE ACTUALICE CUANDO VAYA A RETIRAR COMO RETIRADO
                               0. Salir""");
            int op1 = lc.leerOpcion(es);
            EstudianteControlador estC = new EstudianteControlador();
            SolicitudControlador sc = new SolicitudControlador();
            TurnoControlador tc = new TurnoControlador();
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
                case 4 -> {
                    tc.consultarTurnoMain(logeado);
                    tc.consultarTurno(logeado);
                }
                case 0 -> {
                    i = 0;
                    System.out.println("Saliendo al menú principal...");
                }
                default ->
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }

        } while (i == 1);
    }

    public static void menuSecretaria(Scanner es, int logeado) {
        int i = 1;
        do {
            LogeoControlador lc = new LogeoControlador();
            EstudianteControlador ec = new EstudianteControlador();
            SolicitudControlador sc = new SolicitudControlador();
            TurnoControlador tc=new TurnoControlador();
            lc.limpiarPantalla();
            System.out.println("*--------------BIENVENIDO AL--------------*");
            System.out.println("              MENÚ SECRETARIA");
            System.out.println("""
                               Elija la opción que necesite:
                               1. Consultar Estudiantes
                               2. Revisar Solicitudes
                               3. Consultar Solicitudes 
                               4. Actualizar Turno //EN PROCESO... CUANDO EL ESTUDIANTE FUE A RETIRAR SU CERTIFICADO PONER RETIRADO 
                               0. Salir""");
            int op1 = lc.leerOpcion(es);
            
            switch (op1) {
                case 1 ->
                    ec.consultarEstudiantesMain();
                case 2 ->
                    sc.revisarSolicitudesMain(es);
                case 3 ->
                    sc.consultarSolEst();
                case 4 ->
                    tc.consultarTurnoMain(logeado);
                    //FALTA ACTUALIZAR TURNO A RETIRADO
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
            SecretariaControlador sec = new SecretariaControlador();
            EstudianteControlador ec = new EstudianteControlador();
            SolicitudControlador sc=new SolicitudControlador();
            LogeoControlador lg = new LogeoControlador();
            lg.limpiarPantalla();
            System.out.println("*--------------BIENVENIDO AL--------------*");
            System.out.println("             MENÚ ADMINISTRADOR");
            System.out.println("""
                               Elija la opción que necesite:
                               1. Registrar secretarias
                               2. Consultar secretarias
                               3. Consultar estudiantes
                               4. Registrar estudiantes
                               5. Eliminar estudiantes
                               6. Eliminar secretarias
                               7. Revisar solicitudes //EN PROCESO...
                               0. Salir""");
            int op1 = lg.leerOpcion(es);

            switch (op1) {
                case 1 ->
                    lg.registrarSecretaria(es);
                case 2 ->
                    adm.consultarSecretarias();
                case 3 ->
                    ec.consultarEstudiantesMain();
                case 4 ->
                    lg.registrarEstudiante(es);
                case 5 ->
                    adm.eliminarEst(es);
                case 6 ->
                    sec.eliminarSec(es);
                case 7 ->
                    sc.revisarSolicitudesMain(es);
                case 0 -> {
                    i = 0;
                    System.out.println("Saliendo al menú principal...");
                }
                default ->
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
            LogeoControlador.pause();
        } while (i == 1);
    }
}
