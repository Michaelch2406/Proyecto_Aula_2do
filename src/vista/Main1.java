///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package vista;
//
//import Controlador.EstudianteControlador;
//import Controlador.PersonaControlador;
//import Controlador.SecretariaControlador;
//import Controlador.SolicitudControlador;
//import Controlador.TurnoControlador;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Scanner;
//import modelo.Estudiante;
//import modelo.Solicitud;
//import java.util.Date;
//import modelo.Secretaria;
//
//import modelo.Persona;
//import modelo.Turno;
//
///**
// *
// * @author LENOVO
// */
//public class Main1 {
//
//    public static void main(String[] args) {
//        Scanner es = new Scanner(System.in);
//        int i = 1;
//        //do{
//        Persona p = new Persona();
//        System.out.println("Login Usuario");
//        System.out.println("Ingrese Cédula:");
//        p.setCedula(es.nextLine());
//        System.out.println("Ingrese la contraseña:");
//        p.setClave(es.nextLine());
//        PersonaControlador pc = new PersonaControlador();
//        EstudianteControlador eC = new EstudianteControlador();
//        SecretariaControlador secC = new SecretariaControlador();
//        int logeado = pc.login(p.getCedula(), p.getClave());
//        if (logeado != 0) {
//            int idestudiante = eC.verificarRolEstudiante(logeado);
//            int idsecretaria = secC.verificarRolSecretaria(logeado);
//
//            String rol = "";
//
//            if (idestudiante != 0) {
//                rol = "Estudiante";
//
//            } else if (idsecretaria != 0) {
//                rol = "Secretaria";
//            }
//
//            //System.out.println("Contraseña Correcta" +logeado+" "+idestudiante);
//            if (rol.equals("Estudiante")) {
//                do {
//                    System.out.println("Elija la opción que necesite:\n"
//                            + "1.Crear Solicitud\n"
//                            + "2.Consultar Solicitud\n"
//                            + "3.Editar Solicitud\n" //Hasta que la secretaria no lo revise
//                            + "0.Salir");
//                    int op1 = es.nextInt();
//                    es.nextLine(); // Agregar esto para consumir el salto de línea residual
//                    if (op1 == 1) {
//                        Solicitud s = new Solicitud();
//                        SolicitudControlador so = new SolicitudControlador();
//                        s.setIdPersona(logeado);
//                        System.out.println("Ingrese los datos para la solicitud");
//                        es.nextLine();
//                        Date todayDate = new Date();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String fechaActual = sdf.format(todayDate);
//                        s.setFecha(fechaActual);
//                        String codigoSolicitud = so.generarCodigoSolicitud();
//                        s.setCodigoSol(codigoSolicitud);
//
//                        System.out.println("Ingrese el Asunto: ");
//                        System.out.println("1.- Certificado de Notas");
//                        System.out.println("2.- Certificado de Asistencia");
//                        System.out.println("3.- Certificado de Matrícula");
//
//                        int asuntoOpcion = es.nextInt();
//                        es.nextLine(); // Consumir el salto de línea residual
//
//                        String asunto;
//                        switch (asuntoOpcion) {
//                            case 1 ->
//                                asunto = "Certificado de Notas";
//                            case 2 ->
//                                asunto = "Certificado de Asistencia";
//                            case 3 ->
//                                asunto = "Certificado de Matrícula";
//                            default -> {
//                                System.out.println("Opción no válida. No se seleccionó ningún asunto.");
//                                asunto = ""; // Dejar vacío o manejar de otra manera según tu lógica
//                            }
//                        }
//
//                        s.setAsunto(asunto);
//                        System.out.println("Ingrese el Detalle de la solicitud: ");
//                        s.setDetalle(es.nextLine());
//                        s.setEstado("Pendiente");
//
//                        so.crearSolicitud(s);
//
//                    } else if (op1 == 2) {
//                        SolicitudControlador soc = new SolicitudControlador();
//                        ArrayList<Solicitud> listaSolicitudes = soc.consultarSolicitudes();
//                        for (Solicitud l : listaSolicitudes) {
//                            System.out.println(l.imprimir());
//                        }
////                        SolicitudControlador sc = new SolicitudControlador();
////                        //pc.consultarSolicitud();
////                        System.out.println(sc.consultarSolicitudEst(logeado));
//                    } else if (op1 == 3) {
//                        Solicitud s=new Solicitud();
//                        SolicitudControlador sc=new SolicitudControlador();
//                        sc.editarSolicitudEst(s);
//
//                    } else if (op1 == 0) {
//                        i = 0;
//                        System.out.println("Gracias por usar el servicio!");
//                    }
//                } while (i == 1);
//
//            } else if (rol.equals("Secretaria")) {
//                do {
//                    System.out.println("Elija la opción que necesite:\n"
//                            + "1.Registrar Estudiante\n"
//                            + "2.Registrar Secretaria\n"
//                            + "3.Consultar Estudiantes\n"
//                            + "4.Revisar Solicitudes\n"
//                            + "0.Salir");
//                    int op1 = es.nextInt();
//                    es.nextLine(); // Agregar esto para consumir el salto de línea residual
//                    if (op1 == 1) {
//                        es.nextLine();
//                        System.out.println("Ingrese los siguientes datos estudiante...");
//                        Persona pe = new Persona();
//                        System.out.println("Ingrese su Nombre: ");
//                        pe.setNombre(es.nextLine());
//                        System.out.println("Ingrese su Apellidos: ");
//                        pe.setApellido(es.nextLine());
//                        System.out.println("Ingrese el número de cédula: ");
//                        pe.setCedula(es.nextLine());
//                        System.out.println("Ingrese su contraseña: ");
//                        pe.setClave(es.nextLine());
//                        System.out.println("Ingrese su teléfono: ");
//                        pe.setTelefono(es.nextLine());
//                        System.out.println("Ingrese su correo Electrónico: ");
//                        pe.setCorreo(es.nextLine());
//                        System.out.println("Ingrese su Dirección: ");
//                        pe.setDireccion(es.nextLine());
//
//                        //CONTROLADOR
//                        PersonaControlador pcr = new PersonaControlador();
//                        pcr.crearPersona(pe);
//
//                        int idPersona = pc.buscarIdPersona(pe.getCedula());
//                        System.out.println("----------------" + idPersona);
//                        //MODELO
//                        es.nextLine();
//                        Estudiante est = new Estudiante();
//                        System.out.println("Ingrese su carrera: ");
//                        est.setCarrera(es.nextLine());
//                        System.out.println("Ingrese su nivel de carrera: ");
//                        est.setNivel(es.nextLine());
//                        est.setIdPersona(idPersona);
//
//                        EstudianteControlador estC = new EstudianteControlador();
//                        estC.crearEstudiante(est);
//                    } else if (op1 == 2) {
//                        System.out.println("Ingrese los siguientes datos informativos");
//                        Persona pe = new Persona();
//                        System.out.println("Ingrese su Nombre: ");
//                        pe.setNombre(es.nextLine());
//                        System.out.println("Ingrese su Apellidos: ");
//                        pe.setApellido(es.nextLine());
//                        System.out.println("Ingrese el número de cédula: ");
//                        pe.setCedula(es.nextLine());
//                        System.out.println("Ingrese su contraseña: ");
//                        pe.setClave(es.nextLine());
//                        System.out.println("Ingrese su teléfono: ");
//                        pe.setTelefono(es.nextLine());
//                        System.out.println("Ingrese su correo Electrónico: ");
//                        pe.setCorreo(es.nextLine());
//                        System.out.println("Ingrese su Dirección: ");
//                        pe.setDireccion(es.nextLine());
//
//                        //CONTROLADOR
//                        PersonaControlador pcr = new PersonaControlador();
//                        pcr.crearPersona(p);
//
//                        int idPersona = pcr.buscarIdPersona(p.getCedula());
//                        System.out.println("----------------" + idPersona);
//                        //MODELO
//                        System.out.println("Ingrese datos de Secretaria...");
//                        Secretaria se = new Secretaria();
//                        System.out.println("Ingrese su área: ");
//                        se.setArea(es.next());
//                        se.setIdPersona(idPersona);
//
//                        SecretariaControlador estC = new SecretariaControlador();
//                        estC.crearSecretaria(se);
//                    } else if (op1 == 3) {
//
//                        EstudianteControlador estCr = new EstudianteControlador();
//                        ArrayList<Estudiante> listaEstudiante = estCr.consultarEstudiantes();
//
//                        for (Estudiante e : listaEstudiante) {
//
//                            System.out.println(e.imprimir());
//                        }
//
//                    } else if (op1 == 4) {
//
//                        Solicitud so = new Solicitud();
//                        SolicitudControlador scr = new SolicitudControlador();
//                        ArrayList<Solicitud> listaSolicitudes = scr.revisarSolicitud();
//
//                        // Verificar si hay solicitudes pendientes
//                        if (listaSolicitudes.isEmpty()) {
//                            System.out.println("No hay solicitudes pendientes.");
//                            return;
//                        }
//
//                        // Mostrar todas las solicitudes pendientes
//                        for (Solicitud solicitud : listaSolicitudes) {
//                            System.out.println(solicitud.revisarSol());
//                        }
//
//                        // Seleccionar el número de la solicitud
//                        System.out.println("Seleccione el número de la solicitud que desea actualizar:");
//                        /**
//                         * CORREGIR EL nextInt(); ya que es un String "S0001"
//                         */
//                        int seleccion = es.nextInt();
//                        es.nextLine(); // Consumir el salto de línea residual
//
//                        if (seleccion < 1 || seleccion > listaSolicitudes.size()) {
//                            System.out.println("Selección no válida.");
//                            return;
//                        }
//
//                        // Obtener la solicitud seleccionada
//                        Solicitud solicitudSeleccionada = listaSolicitudes.get(seleccion - 1);
//                        System.out.println("Indique el estado de la solicitud:");
//                        System.out.println("1. Aceptar");
//                        System.out.println("2. Rechazar");
//                        // HIZO EL SISTEMA DE APACHE CON SWITCH
//                        int opcion = es.nextInt();
//                        String estado;
//
//                        switch (opcion) {
//                            case 1 ->
//                                estado = "Aprobado";
//                            case 2 ->
//                                estado = "Rechazado";
//                            default -> {
//                                System.out.println("Opción no válida. Se rechazará la solicitud por defecto.");
//                                estado = "Rechazado";
//                            }
//                        }
//                        solicitudSeleccionada.setEstado(estado);
//                        scr.actualizarSolicitud(solicitudSeleccionada);
//                        es.nextLine();
//                        so.setEstado(es.nextLine());
//                        scr.actualizarSolicitud(so);
//                        if (so.getEstado().equalsIgnoreCase("Aprobado")
//                                || so.getEstado().equalsIgnoreCase("Aceptado")) {
//                            Turno t = new Turno();
//                            Date todayDate = new Date();
//                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                            String fechaActual = sdf.format(todayDate);
//                            t.setFecha(fechaActual);
//                            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//                            Date date = new Date();
//                            t.setHora(dateFormat.format(date));
//                            t.setRetiro(so.getEstado());
//                            t.setIdSolicitud(so.getIdSolicitud());
//                            TurnoControlador tC = new TurnoControlador();
//                            tC.crearTurno(t);
//                        }
//
//                    } else if (op1 == 0) {
//                        i = 0;
//                        System.out.println("Gracias por usar el servicio!");
//                    }
//
//                } while (i == 1);
//            } else {
//                System.out.println("Usuario no asignado, solicite a Secretaria que le asigne un rol");
//            }
//        } else {
//            System.out.println("Contraseña Incorrecta");
//        }
//    }
//    //}while (i==1);
//}
