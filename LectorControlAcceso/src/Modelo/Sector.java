/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author dcarrizo
 */
public class Sector implements IModel{

    private int id=0;
    private String nombre="sin_definir";
    private String descripcion="sin_definir";

    public void id(int id){
        this.id=id;
    }
    public int id(){
        return this.id;
    }

    public void nombre(String nombre){
        this.nombre=nombre;
    }
    public String nombre(){
        return this.nombre;
    }

    public void descripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public String descripcion(){
        return this.descripcion;
    }

    public Sector(){

    }

    @Override
    public String toString(){
        return this.nombre;
    }
}
