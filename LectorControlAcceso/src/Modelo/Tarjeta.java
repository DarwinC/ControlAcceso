/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Darwin
 */
public class Tarjeta implements IModel{
    
    private int id=0;
    private String codigo="";
    
    //*******************
    
    public int id(){
        return this.id;
    }
    
    public void id(int id){
        this.id=id;
    }
    
    public String codigo(){
        return this.codigo;
    }
    
    public void codigo(String codigo){
        this.codigo=codigo;
    }
    
    public Tarjeta(){
        
    }
    
    public Tarjeta(String codigo){
        this.codigo=codigo;
    }
    
    @Override
    public String toString(){
        return this.codigo();
    }
}
