//package vista;
//import Controlador.EstudianteControlador;
//import Controlador.PersonaControlador;
//import Controlador.SecretariaControlador;
//import Controlador.SolicitudControlador;
//import java.text.SimpleDateFormat;
//import java.util.Scanner;
//import modelo.Estudiante;
//import modelo.Solicitud;
//import java.util.Date;
//import modelo.Persona;
//import modelo.Secretaria;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner es = new Scanner(System.in);
//        int i = 1;
//        do {
//            System.out.println("BIENVENIDO AL SISTEMA");
//            System.out.println("Elija la opción que Usted requiera ejecutar:\n"
//                    + "1.Crear Estudiante\n"
//                    + "2.Crear Secretaria\n"
//                    + "3.Crear Solicitud\n"
//                    + "4.Consultar Solicitud\n"
//                    + "5.Eliminar\n"
//                    + "6.Consultar Personas\n"
//                    + "0.Salir");
//            int op1 = es.nextInt();
//            es.nextLine(); // Agregar esto para consumir el salto de línea residual
//            if (op1 == 1) {
//                System.out.println("Ingrese los siguientes datos estudiante...");
//                Persona p = new Persona();
//                System.out.println("Ingrese su Nombre: ");
//                p.setNombre(es.nextLine());
//                System.out.println("Ingrese su Apellidos: ");
//                p.setApellido(es.nextLine());
//                System.out.println("Ingrese el número de cédula: ");
//                p.setCedula(es.nextLine());
//                System.out.println("Ingrese su contraseña: ");
//                p.setClave(es.nextLine());
//                System.out.println("Ingrese su teléfono: ");
//                p.setTelefono(es.nextLine());
//                System.out.println("Ingrese su correo Electrónico: ");
//                p.setCorreo(es.nextLine());
//                System.out.println("Ingrese su Dirección: ");
//                p.setDireccion(es.nextLine());
//
//                //CONTROLADOR
//                PersonaControlador pc = new PersonaControlador();
//                pc.crearPersona(p);
//                
//                int idPersona=pc.buscarIdPersona(p.getCedula());
//                    System.out.println("----------------"+idPersona);
//                //MODELO
//                
//                Estudiante est = new Estudiante();
//                es.nextLine(); 
//                System.out.println("Ingrese su código de estudiante: ");
//                est.setCodigoEst(es.nextLine());
//                System.out.println("Ingrese su carrera: ");
//                est.setCarrera(es.nextLine());
//                System.out.println("Ingrese su nivel de carrera: ");
//                est.setNivel(es.nextLine());
//                est.setIdPersona(idPersona);
//
//                EstudianteControlador estC = new EstudianteControlador();
//                estC.crearEstudiante(est); 
//            }else if (op1==2){
//                System.out.println("Ingrese los siguientes datos informativos");
//                Persona p = new Persona();
//                System.out.println("Ingrese su Nombre: ");
//                p.setNombre(es.nextLine());
//                System.out.println("Ingrese su Apellidos: ");
//                p.setApellido(es.nextLine());
//                System.out.println("Ingrese el número de cédula: ");
//                p.setCedula(es.nextLine());
//                System.out.println("Ingrese su contraseña: ");
//                p.setClave(es.nextLine());
//                System.out.println("Ingrese su teléfono: ");
//                p.setTelefono(es.nextLine());
//                System.out.println("Ingrese su correo Electrónico: ");
//                p.setCorreo(es.nextLine());
//                System.out.println("Ingrese su Dirección: ");
//                p.setDireccion(es.nextLine());
//
//                //CONTROLADOR
//                PersonaControlador pc = new PersonaControlador();
//                pc.crearPersona(p);
//                
//                int idPersona=pc.buscarIdPersona(p.getCedula());
//                    System.out.println("----------------"+idPersona);
//                //MODELO
//                System.out.println("Ingrese datos de Secretaria...");
//                Secretaria se = new Secretaria();
//                System.out.println("Ingrese su código de secretaria: ");
//                se.setCodigoSec(es.next());
//                System.out.println("Ingrese su área: ");
//                se.setArea(es.next());
//                se.setIdPersona(idPersona);
//
//                SecretariaControlador secC = new SecretariaControlador();
//                secC.crearSecretaria(se); 
//            } else if (op1 == 3) {
//                
//                Solicitud s = new Solicitud();
//                SolicitudControlador sc = new SolicitudControlador();
//                //pc.consultarSolicitud();
//                System.out.println(sc.consultarSolicitud());
//                System.out.println("Seleccione la cédula de la persona para la cual desea crear la solicitud");
//                s.setCedula(es.nextLine());
//                System.out.println("Ingrese los datos para la solicitud");
//                es.nextLine();
//                Date todayDate = new Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                String fechaActual = sdf.format(todayDate);
//                s.setFecha(fechaActual);
//                System.out.println("Ingrese el Asunto: ");
//                s.setAsunto(es.nextLine());
//                System.out.println("Ingrese el Detalle de la solicitud: ");
//                s.setDetalle(es.nextLine());
//                System.out.println("Ingrese el estado de la solicitud: ");
//                s.setEstado(es.nextLine());
//                SolicitudControlador so = new SolicitudControlador();
//                so.crearSolicitud(s);
//                es.nextLine();
//            } else if (op1 == 4) {
//                Solicitud s = new Solicitud();
//                SolicitudControlador sc = new SolicitudControlador();
//                //pc.consultarSolicitud();
//                System.out.println(sc.consultarSolicitud());
//            }else if(op1==6){
//                
//            }else if (op1 == 0) {
//                i = 0;
//                System.out.println("Gracias por usar el servicio!");
//            }
//        } while (i == 1);
//    }
//}
