/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Turno{
    private int idTurno;
    private String codigo;
    private String fecha;
    private String hora;
    private String retiro;
    private int idSolicitud;

    public Turno() {
    }

    public Turno(int idTurno, String codigo, String fecha, String hora, String retiro, int idSolicitud) {
        this.idTurno = idTurno;
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.retiro = retiro;
        this.idSolicitud = idSolicitud;
    }


    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getRetiro() {
        return retiro;
    }

    public void setRetiro(String retiro) {
        this.retiro = retiro;
    }


    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public String consultarTurnoEst(){
        return "*----------- CONSULTA TURNO -------------*" + "\n" + 
                "Codigo: " + getCodigo()+ "\n" + 
                "Fecha de retiro: O APROBACIÓN?" + getFecha()+ "\n" + 
                "Hora de retiro: "+ getHora()+ "\n" + 
                "Retiro: "+ getRetiro(); 
    }
    
}
