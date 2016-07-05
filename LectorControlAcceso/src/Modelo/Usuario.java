/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Modelo.Sector;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author dcarrizo
 */
public class Usuario implements IModel{

    private int id=0;
    private Tarjeta tarjeta=null;
    private String nombre="visitante";
    private String documento="";
    private ArrayList<Sector> sectores;
    private String foto="";
    private Calendar fecha_alta;
    
    public String documento() {
        return documento;
    }

    public void documento(String documento) {
        this.documento = documento;
    }

    public void id(int id){
        this.id=id;
    }
    public int id(){
        return this.id;
    }

    public void tarjeta(Tarjeta tarjeta){
        this.tarjeta=tarjeta;
    }
    public Tarjeta tarjeta(){
        return this.tarjeta;
    }
    public void nombre(String nombre){
        this.nombre=nombre;
    }
    public String nombre(){
        return this.nombre;
    }

    public void addSector(Sector sector){
        this.sectores.add(sector);
    }
    
    public void removeSector(Sector sector){
        this.sectores.remove(sector);
    }
    
    public ArrayList<Sector> getSectores(){
        return this.sectores;
    }
    
    public void setSectores(ArrayList<Sector> sectores){
        this.sectores=sectores;
    }

    public void foto(String pathfoto){
        this.foto=pathfoto;
    }
    public String foto(){
        return this.foto;
    }

    public void fecha_alta(Calendar fecha_alta){
        this.fecha_alta=fecha_alta;
    }
    
    public Calendar fecha_alta(){
        return this.fecha_alta;
    }
    
    @Override
    public String toString(){
         return this.nombre;
    }

    public Usuario(){
        this.InicializaSectores();
    }

    public Usuario(int id,Tarjeta tarjeta,String nombreu,ArrayList<Sector> sectores){
        this.id=id;
        this.tarjeta=tarjeta;
        this.nombre=nombreu;
        this.sectores=sectores;
    }
    
    private void InicializaSectores(){
        this.sectores=new ArrayList<Sector>();
    }
}