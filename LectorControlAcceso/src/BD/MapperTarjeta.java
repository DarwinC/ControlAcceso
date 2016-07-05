/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.IModel;
import Modelo.Tarjeta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Darwin
 */
public class MapperTarjeta extends BDConsultas{

    public boolean VerificaDuplicado(IModel imodel){
        try{
            String consultaSQL="SELECT * FROM tarjeta WHERE codigo = ?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Tarjeta t=(Tarjeta)imodel;
            pst.setString(1, t.codigo());
            ResultSet rs=this.ConsultaSQL(pst);
            boolean duplicado=false;
            if(rs.next()){
                duplicado=true;
            }
            this.CierraConexion();
            return duplicado;
            
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally{
            this.CierraConexion();
        }
    }
    
    public boolean Add(IModel imodel) {
        try{
            String consultaSQL="INSERT INTO tarjeta (codigo)VALUES(?)";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Tarjeta t=(Tarjeta)imodel;
            pst.setString(1, t.codigo());
            int resultado=this.ConsultaSQLupdate(pst);
            return resultado==1;
            
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally{
            this.CierraConexion();
        }
    }

    public boolean Update(IModel imodel) {
        try{
            String consultaSQL="UPDATE tarjeta SET codigo=? WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Tarjeta t=(Tarjeta)imodel;
            pst.setString(1, t.codigo());
            pst.setInt(2, t.id());
            int resultado=this.ConsultaSQLupdate(pst);
            this.CierraConexion();
            return resultado==1;
            
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally{
            this.CierraConexion();
        }
    }

    public boolean Delete(IModel imodel) {
        try{
            String consultaSQL="DELETE FROM tarjeta WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Tarjeta t=(Tarjeta)imodel;
            pst.setInt(1, t.id());
            int resultado=this.ConsultaSQLupdate(pst);
            this.CierraConexion();
            return resultado==1;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally{
            this.CierraConexion();
        }
    }

    public Tarjeta GetTarjeta(Tarjeta t){
        return (Tarjeta)this.Get(t);
    }
    
    private IModel Get(IModel imodel) {
        try{
            Tarjeta t=(Tarjeta)imodel;
            String consultaSQL="SELECT * FROM tarjeta WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, t.id());
            ResultSet rs=this.ConsultaSQL(pst);
            t=null;
            if(rs.next()){
                t=(Tarjeta)this.CargarObjeto(rs);
            }
            this.CierraConexion();
            return t;
            
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }
    
    public Tarjeta GetByNumeroSerie(String nro_tarjeta){
        try{
            String consultaSQL="SELECT * FROM tarjeta WHERE codigo=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setString(1, nro_tarjeta);
            ResultSet rs=this.ConsultaSQL(pst);
            Tarjeta t=null;
            if(rs.next()){
                t=(Tarjeta)this.CargarObjeto(rs);
            }
            this.CierraConexion();
            return t;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }
    
    public int GetLastId(){
        try{
            String consultaSQL="SELECT MAX(id) as maximo_id FROM tarjeta";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            ResultSet rs=this.ConsultaSQL(pst);
            int id=0;
            if(rs.next()){
                id=rs.getInt("maximo_id");
            }
            return id;
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }finally{
            this.CierraConexion();
        }
    }

    public ArrayList<Tarjeta> GetTarjetas(){
        ArrayList<Tarjeta> alt=new ArrayList<Tarjeta>();
        for(IModel im:this.GetAll()){
            alt.add((Tarjeta)im);
        }
        return alt;
    }
    
    private ArrayList<IModel> GetAll() {
        ArrayList<IModel> lstsec=new ArrayList<IModel>();
        try{
            String consultaSQL="SELECT * FROM tarjeta";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            ResultSet rs=this.ConsultaSQL(pst);
            while(rs.next()){
                lstsec.add(this.CargarObjeto(rs));
            }
            this.CierraConexion();
            return lstsec;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }

    public ArrayList<Tarjeta> GetTarjetasSinUsuarioAsociado(){
        ArrayList<Tarjeta> lsttarjetas=new ArrayList<Tarjeta>();
        try{
            String consultaSQL="SELECT tarjeta.id,tarjeta.codigo FROM tarjeta WHERE tarjeta.id NOT IN (SELECT usuario.tarjeta_id FROM usuario WHERE usuario.tarjeta_id >0)";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            ResultSet rs=this.ConsultaSQL(pst);
            while(rs.next()){
                lsttarjetas.add((Tarjeta)this.CargarObjeto(rs));
            }
            this.CierraConexion();
            return lsttarjetas;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }
    
    @Override
    protected IModel CargarObjeto(ResultSet rs) {
        try{
            Tarjeta t=new Tarjeta();
            t.id(rs.getInt("id"));
            t.codigo(rs.getString("codigo"));
            return t;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    
}
