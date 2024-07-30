/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Persona {
    private int idPersona;
    private String nombre;
    private String apellido;
    private String cedula;
    private String clave;
    private String telefono;
    private String correo;
    private String direccion;

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String apellido, String cedula, String clave, String telefono, String correo, String direccion) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.clave = clave;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    public String  procesarSolicitud(){
        return "";
    }
}
