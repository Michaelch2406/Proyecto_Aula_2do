/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.EstudianteControlador;
import Controlador.PersonaControlador;
import Controlador.SecretariaControlador;
import Controlador.SolicitudControlador;
import Controlador.TurnoControlador;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Solicitud;
import java.util.Date;
import modelo.Persona;
import modelo.Secretaria;

import modelo.Persona;
import modelo.Turno;

/**
 *
 * @author LENOVO
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner es = new Scanner(System.in);
        int i = 1;
        //do{
        Persona p = new Persona();
        System.out.println("Login Usuario");
        System.out.println("Ingrese Cédula:");
        p.setCedula(es.nextLine());
        System.out.println("Ingrese la contraseña:");
        p.setClave(es.nextLine());
        PersonaControlador pc = new PersonaControlador();
        EstudianteControlador eC = new EstudianteControlador();
        int logeado = pc.login(p.getCedula(), p.getClave());
        if (logeado != 0) {
            int idestudiante = eC.verificarRolEstudiante(logeado);
            int idsecretaria = pc.verificarRolSecretaria(logeado);

            String rol = "";

            if (idestudiante != 0) {
                rol = "Estudiante";

            } else if (idsecretaria != 0) {
                rol = "Secretaria";
            }

            //System.out.println("Contraseña Correcta" +logeado+" "+idestudiante);
            if (rol.equals("Estudiante")) {
                do {
                    System.out.println("Elija la opción que necesite:\n"
                            + "1.Crear Solicitud\n"
                            + "2.Consultar Solicitud\n"
                            + "0.Salir");
                    int op1 = es.nextInt();
                    es.nextLine(); // Agregar esto para consumir el salto de línea residual
                    if (op1 == 1) {
                        Solicitud s = new Solicitud();
                        s.setIdPersona(logeado);
                        System.out.println("Ingrese los datos para la solicitud");
                        es.nextLine();
                        Date todayDate = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaActual = sdf.format(todayDate);
                        s.setFecha(fechaActual);
                        System.out.println("Ingrese el Asunto: ");
                        s.setAsunto(es.nextLine());
                        System.out.println("Ingrese el Detalle de la solicitud: ");
                        s.setDetalle(es.nextLine());
                        s.setEstado("Pendiente");
                        SolicitudControlador so = new SolicitudControlador();
                        so.crearSolicitud(s);

                    } else if (op1 == 2) {
                        SolicitudControlador sc = new SolicitudControlador();
                        //pc.consultarSolicitud();
                        System.out.println(sc.consultarSolicitudEst(logeado));
                    } else if (op1 == 0) {
                        i = 0;
                        System.out.println("Gracias por usar el servicio!");
                    }
                } while (i == 1);

            } else if (rol.equals("Secretaria")) {
                do {
                    System.out.println("Elija la opción que necesite:\n"
                            + "1.Crear Estudiante\n"
                            + "2.Consultar Estudiantes\n"
                            + "3.Revisar Solicitudes\n"
                            + "4.Revisar Solicitudes\n"
                            + "0.Salir");
                    int op1 = es.nextInt();
                    es.nextLine(); // Agregar esto para consumir el salto de línea residual
                    if (op1 == 1) {
                        es.nextLine();
                        System.out.println("Ingrese los siguientes datos estudiante...");
                        Persona pe = new Persona();
                        System.out.println("Ingrese su Nombre: ");
                        pe.setNombre(es.nextLine());
                        System.out.println("Ingrese su Apellidos: ");
                        pe.setApellido(es.nextLine());
                        System.out.println("Ingrese el número de cédula: ");
                        pe.setCedula(es.nextLine());
                        System.out.println("Ingrese su contraseña: ");
                        pe.setClave(es.nextLine());
                        System.out.println("Ingrese su teléfono: ");
                        pe.setTelefono(es.nextLine());
                        System.out.println("Ingrese su correo Electrónico: ");
                        pe.setCorreo(es.nextLine());
                        System.out.println("Ingrese su Dirección: ");
                        pe.setDireccion(es.nextLine());

                        //CONTROLADOR
                        PersonaControlador pcr = new PersonaControlador();
                        pcr.crearPersona(pe);

                        int idPersona = pc.buscarIdPersona(pe.getCedula());
                        System.out.println("----------------" + idPersona);
                        //MODELO
                        es.nextLine();
                        Estudiante est = new Estudiante();
                        System.out.println("Ingrese su carrera: ");
                        est.setCarrera(es.nextLine());
                        System.out.println("Ingrese su nivel de carrera: ");
                        est.setNivel(es.nextLine());
                        est.setIdPersona(idPersona);

                        EstudianteControlador estC = new EstudianteControlador();
                        estC.crearEstudiante(est);
                    } else if (op1 == 2) {
                        EstudianteControlador estCr = new EstudianteControlador();
                        System.out.println(estCr.consultarEstudiantes());
                    } else if (op1 == 3) {

                        Solicitud so = new Solicitud();
                        SolicitudControlador scr = new SolicitudControlador();
                        System.out.println(scr.revisarSolicitud());
                        System.out.println("Seleccione el ID de la solicitud");
                        so.setIdSolicitud(es.nextInt());
                        System.out.println("Indique el estado de la solicitud:");
                        es.nextLine();
                        so.setEstado(es.nextLine());
                        scr.actualizarSolicitud(so);
                        if (so.getEstado().equalsIgnoreCase("Aprobado")
                                || so.getEstado().equalsIgnoreCase("Aceptado")) {
                            Turno t = new Turno();
                            Date todayDate = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            String fechaActual = sdf.format(todayDate);
                            t.setFecha(fechaActual);
                            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                            Date date = new Date();
                            t.setHora(dateFormat.format(date));
                            t.setEstado(so.getEstado());
                            t.setIdSolicitud(so.getIdSolicitud());
                            TurnoControlador tC = new TurnoControlador();
                            tC.crearTurno(t);
                        }
                    } else if (op1 == 4) {
                        System.out.println("Ingrese el detalle que desea eliminar:");
                        String  s= es.next();
                        SolicitudControlador estc = new SolicitudControlador();
                        estc.eliminarSolicitud(s);
                    } else if (op1 == 0) {
                        i = 0;
                        System.out.println("Gracias por usar el servicio!");
                    }

                } while (i == 1);
            } else {
                System.out.println("Usuario no asignado, solicite a Secretaria que le asigne un rol");
            }
        } else {
            System.out.println("Contraseña Incorrecta");
        }
    }
    //}while (i==1);
}
