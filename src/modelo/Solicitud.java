/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Solicitud extends Persona{
    private int idSolicitud;
    private String fecha;
    private String asunto;
    private String detalle;
    private String estado;
    private int idPersona;
    

    public Solicitud() {
    }

    public Solicitud(int idSolicitud, String fecha, String asunto, String detalle, String estado, int idPersona) {
        this.idSolicitud = idSolicitud;
        this.fecha = fecha;
        this.asunto = asunto;
        this.detalle = detalle;
        this.estado = estado;
        this.idPersona = idPersona;
    }


    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    
    
}
