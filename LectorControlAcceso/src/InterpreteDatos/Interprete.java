/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteDatos;

import Modelo.Registro;
import Modelo.Tarjeta;
import java.util.Calendar;

/**
 *
 * @author Darwin
 */
public class Interprete {
    
    
    
    public Registro InterpreteRegistro(String dato){
        System.out.println("entra al interprete");        
        // este procedimiento esta hecho para un formato de registro especifico
        // 
        // este es el formato que da el dispositivo:
        // "01521511 17/07/12 08:20 S   001     ";
        //  tarjeta  |  fecha  |  hora | entrada/salida
        try{
//            String nro_tarjeta="";
//            String fecha="";
//            String hora="";
//            String estado="";
//            
//            nro_tarjeta=dato.substring(0, dato.indexOf(" ")).trim();
//            fecha=dato.substring((nro_tarjeta.length()+1),dato.indexOf(" ", nro_tarjeta.length()+1));
//            hora=dato.substring(((nro_tarjeta+fecha).length()+2), dato.indexOf(" ",((nro_tarjeta+fecha).length()+2)));
//            estado=dato.substring(((nro_tarjeta+fecha+hora).length()+3), dato.indexOf(" ",((nro_tarjeta+fecha+hora).length()+3)));
//
//            String[] fechas=fecha.split("/");
//            Integer anio=Integer.parseInt("20"+fechas[2]);
//            Integer mes=Integer.parseInt(fechas[1])-1;
//            Integer dia=Integer.parseInt(fechas[0]);
//            
//            String[] horas=hora.split(":");
//            Integer hrs=Integer.parseInt(horas[0]);
//            Integer minuto=Integer.parseInt(horas[1]);
//            Calendar fechahora = new GregorianCalendar(anio,mes,dia,hrs,minuto,0);
        
            
            
            // EN ESTE CASO SOLO SE RECIBE EL CODIGO DE LA TARJETA
            
        Calendar fechahora=Calendar.getInstance();
        String nro_tarjeta=dato.trim();
        //String estado="";
        
            Registro r=new Registro();
            Tarjeta t=new Tarjeta(nro_tarjeta);
            //r.codigo(nro_tarjeta);
            r.tarjeta(t);
            r.fecha_hora(fechahora);
            //r.setEstado(estado);

            return r;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
