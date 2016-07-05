/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dcarrizo
 */
public class ConvertidorFecha {

    public ConvertidorFecha(){
    }

    public String FormatoAccess(long fecha){
        Date f=new Date(fecha);
        String fechastr="";
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        fechastr=sdf.format(f);
        return fechastr;
    }

    public long Formato_Long_AMPM(String fechavar){//distingue formato dd/MM/yyyy HH:mm:ss a.m./p.m

        String fecha="",horavar="";
        fecha=fechavar.substring(0,fechavar.indexOf(" "));
        horavar=fechavar.substring(fechavar.indexOf(" ")+1);
        int dia=(Integer.parseInt(fecha.substring(0, fecha.indexOf("/"))));
        int mes=Integer.parseInt(fecha.substring(fecha.indexOf("/")+1,fecha.lastIndexOf("/")));
        int anio=Integer.parseInt(fecha.substring(fecha.lastIndexOf("/")+1));
        int hora=Integer.parseInt(horavar.substring(0,horavar.indexOf(":")));
        int minutos=Integer.parseInt(horavar.substring(horavar.indexOf(":")+1,horavar.lastIndexOf(":")));
        int segundos=Integer.parseInt(horavar.substring(horavar.lastIndexOf(":")+1));
        Date d=new Date((anio-1900),(mes-1),dia,hora,minutos,segundos);
        return d.getTime();
    }

    public long FormatoFechaLong(String fecha){//exclusivo para archivos registros de colgate

        //aca convierto los formatos de fecha dd/mm/yy HH:mm a long entendible
        String dia=fecha.substring(0, fecha.indexOf("/"));
        String mes=fecha.substring(fecha.indexOf("/")+1,fecha.lastIndexOf("/"));
        String anio=fecha.substring(fecha.lastIndexOf("/")+1,fecha.indexOf(" "));
        if(anio.length()<3){
            anio="20"+anio;
        }else{
            anio="2"+anio;
        }
        String hora=fecha.substring(fecha.indexOf(" ")+1,fecha.indexOf(":"));
        String minutos=fecha.substring(fecha.indexOf(":")+1);
        String segundos="00";
        Date fechaprocesada=new Date(anio+"/"+mes+"/"+dia+" "+hora+":"+minutos+":"+segundos);
        return fechaprocesada.getTime();
    }

        public String FormatoFechaString(String fecha){

        //aca convierto los formatos de fecha dd/mm/yy HH:mm a long entendible
        String dia=fecha.substring(0, fecha.indexOf("/"));
        String mes=fecha.substring(fecha.indexOf("/")+1,fecha.lastIndexOf("/"));
        String anio=fecha.substring(fecha.lastIndexOf("/")+1,fecha.indexOf(" "));
        if(anio.length()<3){
            anio="20"+anio;
        }else{
            anio="2"+anio;
        }
        String hora=fecha.substring(fecha.indexOf(" ")+1,fecha.indexOf(":"));
        String minutos=fecha.substring(fecha.indexOf(":")+1);
        String segundos="00";
        Date fechaprocesada=new Date(anio+"/"+mes+"/"+dia+" "+hora+":"+minutos+":"+segundos);
        return fechaprocesada.toString();
    }

}