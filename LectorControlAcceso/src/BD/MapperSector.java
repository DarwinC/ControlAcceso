/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.IModel;
import Modelo.Sector;
import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Darwin
 */
public class MapperSector extends BDConsultas{

    public boolean Add(IModel imodel) {
        try{
            String consultaSQL="INSERT INTO sector (nombre,descripcion)VALUES(?,?)";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Sector s=(Sector)imodel;
            pst.setString(1, s.nombre());
            pst.setString(2, s.descripcion());
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

    public boolean Update(IModel imodel) {
        try{
            String consultaSQL="UPDATE sector SET nombre=?,descripcion=? WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Sector s=(Sector)imodel;
            System.out.println(s.id() + " - " + s.nombre() + " - " + s.descripcion() );
            pst.setString(1, s.nombre());
            pst.setString(2, s.descripcion());
            pst.setInt(3, s.id());
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
            System.out.println("llega a borrar sector");
            String consultaSQL="DELETE FROM sector WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Sector s=(Sector)imodel;
            pst.setInt(1, s.id());
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

    public IModel Get(IModel imodel) {
        try{
            Sector s=(Sector)imodel;
            String consultaSQL="SELECT * FROM sector WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, s.id());
            s=null;
            s=(Sector)this.CargarObjeto(this.ConsultaSQL(pst));
            this.CierraConexion();
            return s;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }

    public ArrayList<IModel> GetAll() {
        ArrayList<IModel> lstsec=new ArrayList<IModel>();
        try{
            String consultaSQL="SELECT * FROM sector";
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
    
    public ArrayList<IModel> FindSectorByNombre(String nombre){
        ArrayList<IModel> lstsec=new ArrayList<IModel>();
        try{
            String consultaSQL="SELECT * FROM sector WHERE nombre like ?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setString(1, nombre);
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
    
    public ArrayList<Sector> GetSectoresByUsuario(Usuario u){
        ArrayList<Sector> lstsec=new ArrayList<Sector>();
        try{
            String consultaSQL="SELECT * FROM sector WHERE id IN(SELECT sector_id FROM usuario_has_sector WHERE usuario_id=?)";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, u.id());
            ResultSet rs=this.ConsultaSQL(pst);
            while(rs.next()){
                lstsec.add((Sector)this.CargarObjeto(rs));
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

    public ArrayList<Sector> GetSectoresSinAsignarAlUsuario(Usuario u){
        ArrayList<Sector> lstsec=new ArrayList<Sector>();
        try{
            String consultaSQL="SELECT * FROM sector WHERE id NOT IN(SELECT sector_id FROM usuario_has_sector WHERE usuario_id=?)";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, u.id());
            ResultSet rs=this.ConsultaSQL(pst);
            while(rs.next()){
                lstsec.add((Sector)this.CargarObjeto(rs));
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
    
    @Override
    protected IModel CargarObjeto(ResultSet rs) {
        try{
            Sector s=new Sector();
            s.id(rs.getInt("id"));
            s.nombre(rs.getString("nombre"));
            s.descripcion(rs.getString("descripcion"));
            return s;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    
}
