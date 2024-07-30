/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Turno {
    private int idTurno;
    private String fecha;
    private String hora;
    private String estado;
    private int idSolicitud;

    public Turno() {
    }

    public Turno(int idTurno, String fecha, String hora, String estado, int idSolicitud) {
        this.idTurno = idTurno;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.idSolicitud = idSolicitud;
    }



    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    
}
