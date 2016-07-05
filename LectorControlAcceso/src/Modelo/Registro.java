/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Darwin
 */
public class Registro implements IModel{
    
    private int id;
    private Tarjeta tarjeta;
    //private String codigo;
    private Calendar fecha_hora;
    private String estado; // 'E' -> Entro | 'S' -> Salio
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int id() {
        return this.id;
    }

    public void id(int id) {
        this.id=id;
    }
    
    public Tarjeta tarjeta(){
        return this.tarjeta;
    }
    
    public void tarjeta(Tarjeta tarjeta){
        this.tarjeta=tarjeta;
    }
    
//    public String codigo(){
//        return this.codigo;
//    }
//    
//    public void codigo(String codigo){
//        this.codigo=codigo;
//    }
    
    public Calendar fecha_hora(){
        return this.fecha_hora;
    }
    
    public void fecha_hora(Calendar fecha_hora){
        this.fecha_hora=fecha_hora;
    }
    
    @Override
    public String toString(){
        return ("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(this.fecha_hora.getTimeInMillis()))+ " - Tarjeta: " + this.tarjeta.codigo());
    }
    
}
