/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errores;

import java.util.ArrayList;

/**
 *
 * @author Darwin
 */
public class ColectorErrores {
    
    private static ColectorErrores instancia;
    
    private boolean registraErrores=false;
    
    private ArrayList<String> mensajesError;
    //array colector de errores
    //
    public void mensajesError(String msjErr){
        this.mensajesError.add(msjErr);
    }
    
    public void limpiarMensajesError(){
        if(this.registraErrores){
            //guarda los msj en un archivo de log de errores.
        }
        
        this.mensajesError.clear();
        
    }
    
    public void registraErrores(boolean activar){
        this.registraErrores=activar;
    }
    
    public boolean registraErrores(){
        return this.registraErrores;
    }
    
    private ColectorErrores(){
        //inicializa el array
        this.mensajesError=new ArrayList<String>();
    }
    
    public static ColectorErrores getInstancia(){
        if(instancia==null){
            instancia=new ColectorErrores();
        }
        return instancia;
    }
}
