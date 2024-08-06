/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Administrador extends Persona{
    private int idAdministrador;
    private String cargo;
    private String institucion;
    private int idPersona;

    public Administrador() {
    }

    public Administrador(int idAdministrador, String cargo, String institucion, int idPersona, String nombre, String apellido, String cedula, String clave, String telefono, String correo, String direccion) {
        super(idPersona, nombre, apellido, cedula, clave, telefono, correo, direccion);
        this.idAdministrador = idAdministrador;
        this.cargo = cargo;
        this.institucion = institucion;
        this.idPersona = idPersona;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
}
