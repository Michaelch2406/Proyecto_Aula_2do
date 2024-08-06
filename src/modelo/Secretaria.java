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
    private String codigoSec;
    private String area;
    private int idPersona;

    public Secretaria() {
    }

    public Secretaria(int idSecretaria, String codigoSec, String area, int idPersona, String nombre, String apellido, String cedula, String clave, String telefono, String correo, String direccion) {
        super(idPersona, nombre, apellido, cedula, clave, telefono, correo, direccion);
        this.idSecretaria = idSecretaria;
        this.codigoSec = codigoSec;
        this.area = area;
        this.idPersona = idPersona;
    }


    public int getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(int idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public String getCodigoSec() {
        return codigoSec;
    }

    public void setCodigoSec(String codigoSec) {
        this.codigoSec = codigoSec;
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
     public String imprimir(){
        return "*----------- Datos -------------*" + "\n" + 
                "Código: " + getCodigoSec()+ "\n" +
                "Nombre: " + getNombre()+ "\n" + 
                "Apellido: "+ getApellido() + "\n" + 
                "Cédula: "+ getCedula() + "\n" + 
                "Carrera: " + getArea();
    }
}
