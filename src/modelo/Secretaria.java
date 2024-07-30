/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Secretaria extends Persona{
    private int idSecretaria;
    private String area;
    private int idPersona;

    public Secretaria() {
    }

    public Secretaria(int idSecretaria, String area, int idPersona, String nombre, String apellido, String cedula, String clave, String telefono, String correo, String direccion) {
        super(idPersona, nombre, apellido, cedula, clave, telefono, correo, direccion);
        this.idSecretaria = idSecretaria;
        this.area = area;
        this.idPersona = idPersona;
    }
    
    public int getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(int idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
    public String revisarSolicitud(){
        return "" ;
    }
}
