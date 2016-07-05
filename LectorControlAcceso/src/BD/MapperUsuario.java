/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.IModel;
import Modelo.Sector;
import Modelo.Tarjeta;
import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Darwin
 */
public class MapperUsuario extends BDConsultas{

    public boolean Add(IModel imodel) {
        try{
            
            String consultaSQL="INSERT INTO usuario (nombre,documento,foto,fecha_alta)VALUES(?,?,?,?)";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            
            Usuario u=(Usuario)imodel;
            
            pst.setString(1,u.nombre());
            pst.setString(2,u.documento());
            pst.setString(3,u.foto());
            
            
            //String fechahorastring=String.valueOf(fechahora.get(Calendar.YEAR))+"-"+String.valueOf(fechahora.get(Calendar.MONTH)+1)+"-"+String.valueOf(fechahora.get(Calendar.DAY_OF_MONTH))+" "+String.valueOf(fechahora.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(fechahora.get(Calendar.MINUTE))+":"+String.valueOf(fechahora.get(Calendar.SECOND));
            pst.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Calendar.getInstance().getTimeInMillis())));            
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
            
            String consultaSQL="UPDATE usuario SET nombre=?,documento=?,foto=? WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            
            Usuario u=(Usuario)imodel;
            
            pst.setString(1,u.nombre());
            pst.setString(2,u.documento());
            pst.setString(3,u.foto());
            pst.setInt(4, u.id());
            int resultado=this.ConsultaSQLupdate(pst);
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
            String consultaSQL="DELETE FROM usuario WHERE id=?";
            Usuario u=(Usuario)imodel;
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, u.id());
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

    public boolean AsignarTarjeta(Usuario u){
        try{
            String consultaSQL="UPDATE usuario SET tarjeta_id=? WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, u.tarjeta().id());
            pst.setInt(2, u.id());
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
    
    public boolean RemoverTarjeta(Usuario u){
        try{
            String consultaSQL="UPDATE usuario SET tarjeta_id=? WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setNull(1, Types.INTEGER);
            pst.setInt(2, u.id());
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

    public Usuario GetUsuario(Usuario u){
        return (Usuario)this.Get(u);
    }
    
    private IModel Get(Usuario u) {
        try{
            String consultaSQL="SELECT * FROM usuario WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            
            pst.setInt(1,u.id());
            ResultSet rs=this.ConsultaSQL(pst);
            if(rs.next()){
            u = (Usuario)this.CargarObjeto(rs);
            }
            this.CierraConexion();
            return u;
        
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }

    public Usuario GetByTarjeta(Tarjeta t){
        try{
            String consultaSQL="SELECT * FROM usuario WHERE tarjeta_id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, t.id());
            ResultSet rs=this.ConsultaSQL(pst);
            Usuario u=null;
            if(rs.next()){
                u=(Usuario)this.CargarObjeto(rs);
            }
            this.CierraConexion();
            return u;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }
    
    public ArrayList<Usuario> GetByNombre(String nombre){
        ArrayList<Usuario> lstu=new ArrayList<Usuario>();
        try{
            String consultaSQL="SELECT * FROM usuario WHERE nombre like ?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setString(1, nombre);
            ResultSet rs=this.ConsultaSQL(pst);
            while(rs.next()){
                lstu.add((Usuario)this.CargarObjeto(rs));
            }
            this.CierraConexion();
            return lstu;
        
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }

    public ArrayList<Usuario> GetUsuarios(){
        ArrayList<Usuario> alu=new ArrayList<Usuario>();
        for(IModel im:this.GetAll()){
            alu.add((Usuario)im);
        }
        return alu;
    }
    
    private ArrayList<IModel> GetAll() {
        ArrayList<IModel> lstu=new ArrayList<IModel>();
        try{
            String consultaSQL="SELECT * FROM usuario";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            ResultSet rs=this.ConsultaSQL(pst);
            while(rs.next()){
                System.out.println(rs.getString("nombre"));
                lstu.add(this.CargarObjeto(rs));
            }
            this.CierraConexion();
            return lstu;
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
            Usuario u=new Usuario();
            u.id(rs.getInt("id"));
            u.nombre(rs.getString("nombre"));
            Tarjeta t=null;
            
            if(rs.getObject("tarjeta_id")!=null){
                System.out.println("Encontro tarjeta: " + rs.getInt("tarjeta_id"));
                MapperTarjeta mt=new MapperTarjeta();
                t=new Tarjeta();
                t.id(rs.getInt("tarjeta_id"));
                t=(Tarjeta)mt.GetTarjeta(t);
            }
            u.tarjeta(t);
            MapperSector ms=new MapperSector();
            u.setSectores(ms.GetSectoresByUsuario(u));
            u.documento(rs.getString("documento"));
            u.foto(rs.getString("foto"));
            Calendar fecha_alta=Calendar.getInstance();
            fecha_alta.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("fecha_alta")));
            u.fecha_alta(fecha_alta);

            return u;

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean AsignarSectores(Usuario u){
        
        try{
            String consultaSQL="DELETE FROM usuario_has_sector WHERE usuario_id=?";
            
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, u.id());
            this.ConsultaSQLupdate(pst);
            
            consultaSQL="INSERT INTO usuario_has_sector (usuario_id,sector_id)VALUES(?,?)";
            pst=this.PrepareStatement(consultaSQL);
            int totalinsertados=0;
            for(Sector s:u.getSectores()){
                System.out.println("usuario " + u.id() + " sector: " + s.id());
                pst.setInt(1, u.id());
                pst.setInt(2, s.id());
                if((this.ConsultaSQLupdate(pst)==1)){
                    totalinsertados+=1;
                }
            }
            this.CierraConexion();
            
            return (u.getSectores().size()==totalinsertados);
            
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }finally{
            this.CierraConexion();
        }
    }
}
