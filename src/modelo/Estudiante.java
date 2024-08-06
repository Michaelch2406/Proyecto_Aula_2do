/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Estudiante extends Persona{
    private int idEstudiante;
    private String codigoEst;
    private String carrera;
    private int nivel;
    private int idPersona;

    public Estudiante(int idEstudiante, String codigoEst, String carrera, int nivel,int idPersona, String nombre, String apellido, String cedula, String clave, String telefono, String correo, String direccion) {
        super(idPersona, nombre, apellido, cedula, clave, telefono, correo, direccion);
        this.idEstudiante = idEstudiante;
        this.codigoEst = codigoEst;
        this.carrera = carrera;
        this.nivel = nivel;
        this.idPersona = idPersona;
    }




    public Estudiante() {
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getCodigoEst() {
        return codigoEst;
    }

    public void setCodigoEst(String codigoEst) {
        this.codigoEst = codigoEst;
    }


    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
        
    public String imprimir(){
        return "*----------- Datos -------------*" + "\n" + 
                "Código: " + getCodigoEst()+ "\n" +
                "Nombre: " + getNombre()+ "\n" + 
                "Apellido: "+ getApellido() + "\n" + 
                "Cédula: "+ getCedula() + "\n" + 
                "Teléfono: "+ getTelefono() + "\n" +
                "Correo Electrónico: "+ getCorreo() + "\n" + 
                "Dirección: "+ getDireccion() + "\n" +
                "Carrera: " + getCarrera() + "\n" +
                "Nivel: " + getNivel(); 
    }
}
