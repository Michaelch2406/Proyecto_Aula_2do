/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Solicitud;
import modelo.Turno;

/**
 *
 * @author LENOVO
 */
public class Fechas {
    
    public void fechaSol(Solicitud s){
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = sdf.format(todayDate);
        s.setFecha(fechaActual);
    }
    public void fechaTurno(Turno t){
        Date todayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = sdf.format(todayDate);
            t.setFecha(fechaActual);
    }
    public void horaTurno(Turno t){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            t.setHora(dateFormat.format(date));
    }
    public void fechaTurnos(){
        Turno t=new Turno();
        Date todayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = sdf.format(todayDate);
            t.setFecha(fechaActual);
    }
}
