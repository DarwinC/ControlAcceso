/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Darwin
 */
public class LogRegistros {
    
    private String pathlog="\\log_puerto_serie.txt"; //ruta por defecto pero modificable

    public String getPathlog() {
        return pathlog;
    }

    public void setPathlog(String pathlog) {
        this.pathlog = pathlog;
    }
    
    public LogRegistros(){
    }
    
    public LogRegistros(String pathlog){
        this.pathlog=pathlog;
    }
    
    public void RegistrarEnLog(String registro){
        
        //aca se escribe en un archivo de texto plano
        //fecha hora y dato que se recibio del puerto serie, 
        //así se libera la memoria y no se pierde registro

        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            File f=new File("");
            f=new File(f.getAbsolutePath()+this.pathlog);
            
            if(!f.exists()){f.createNewFile();}
            
            Date date=new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            String fechahora=hourdateFormat.format(date);
            fichero = new FileWriter(f,true);
            BufferedWriter bw=new BufferedWriter(fichero);
            pw = new PrintWriter(bw);
            
            registro=registro+" - fecha sistema: "+fechahora + System.getProperty("line.separator");
            
            pw.append(registro);
            pw.close();
            bw.close();

        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
              pw.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}
