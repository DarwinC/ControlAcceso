/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.IModel;
import Modelo.Registro;
import Modelo.Tarjeta;
import Modelo.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Darwin
 */
public class MapperRegistro extends BDConsultas{

    public boolean Add(IModel imodel) {
        try{
            String consultaSQL="INSERT INTO registro (tarjeta_id,fecha_hora,estado)VALUES(?,?,?)";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Registro r=(Registro)imodel;
            pst.setInt(1, r.tarjeta().id());
            pst.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.fecha_hora().getTime()));
            String estado=this.GetUltimoEstado(r.tarjeta());
            System.out.println(estado);
            
            pst.setString(3, estado);
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
            
            String consultaSQL="UPDATE registro SET fecha_hora=?,estado=? WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Registro r=(Registro)imodel;
            
            //pst.setString(1, r.codigo());
            String fechaHoraString=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(r.fecha_hora().getTimeInMillis()));
            pst.setString(1, fechaHoraString);
            pst.setString(2, r.getEstado());
            pst.setInt(3, r.id());
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
            String consultaSQL="DELETE FROM registro WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            Registro r=(Registro)imodel;
            pst.setInt(1, r.id());
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

    public Registro GetRegistro(Registro r){
        return (Registro)this.Get(r);
    }
    
    private IModel Get(Registro r) {
        try{
            String consultaSQL="SELECT * FROM registro WHERE id=?";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, r.id());
            r=null;
            r=(Registro)this.CargarObjeto(this.ConsultaSQL(pst));
            this.CierraConexion();
            return r;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }

    public ArrayList<Registro> GetRegistros(){
        ArrayList<Registro> alr=new ArrayList<Registro>();
        for(IModel im:this.GetAll()){
            alr.add((Registro)im);
        }
        return alr;
    }
    
    private ArrayList<IModel> GetAll() {
        ArrayList<IModel> lstsec=new ArrayList<IModel>();
        try{
            String consultaSQL="SELECT * FROM registro LIMIT 500";
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
    
    public ArrayList<Registro> GetAllBy(Calendar fecha_desde,Calendar fecha_hasta, Usuario u, Tarjeta t){ // entre fechas
        ArrayList<Registro> lstsec=new ArrayList<Registro>();
        try{
            //? OR fecha_hora
            //? OR fecha_hora
            String consultaSQL=""
                    + "SELECT * "
                    + "FROM registro "
                    + "WHERE "
                    + "(fecha_hora>=?)"
                    + "AND (fecha_hora<=?) ";
                    
            if(t!=null){
                consultaSQL+=" AND tarjeta_id=?";
            }
            if(u!=null){
                consultaSQL+= " AND tarjeta_id IN(SELECT u.tarjeta_id FROM usuario as u WHERE u.id=?)";
            }
            consultaSQL+=" ORDER BY fecha_hora";
            //System.out.println(consultaSQL);
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            
            String fechaHoraString="";
            if(fecha_desde!=null){ //&&!fecha_desde.isLenient()
                fechaHoraString=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(fecha_desde.getTimeInMillis()));
            }else{
                fechaHoraString=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(0));
            }
            
            pst.setString(1, fechaHoraString);
            //pst.setString(2, fechaHoraString);
            
            //System.out.println("Fecha desde: "+ fechaHoraString);
            
            if(fecha_hasta!=null){ //&&!fecha_desde.isLenient()
                fechaHoraString=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(fecha_hasta.getTimeInMillis()));
            }else{
                
                fechaHoraString=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
            }
            pst.setString(2, fechaHoraString);
            //System.out.println("Fecha hasta: "+ fechaHoraString);
            //pst.setString(4, fechaHoraString);
            
            
            if(t!=null)pst.setInt(3, t.id());
            
            if(u!=null){
                if(t==null){
                    pst.setInt(3, u.id());
                }else{
                    pst.setInt(4, u.id());
                }
            }
            
            
            ResultSet rs=this.ConsultaSQL(pst);
            
            while(rs.next()){
                lstsec.add((Registro)this.CargarObjeto(rs));
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
    
//    public ArrayList<Registro> GetListadoTarjetasFuera(){
//        ArrayList<Registro> lstsec=new ArrayList<Registro>();
//        try{
//            String consultaSQL="SELECT * FROM registro WHERE estado ='S' OR estado IS NULL AND id IN (SELECT MAX(fecha_hora) FROM registro)";
//            PreparedStatement pst=this.PrepareStatement(consultaSQL);
//            ResultSet rs=this.ConsultaSQL(pst);
//            while(rs.next()){
//                lstsec.add((Registro)this.CargarObjeto(rs));
//            }
//            return lstsec;
//        }catch (Exception ex){
//            ex.printStackTrace();
//            return null;
//        }
//    }
//    
//    public ArrayList<Registro> GetListadoTarjetasDentro(){
//        ArrayList<Registro> lstsec=new ArrayList<Registro>();
//        try{
//            String consultaSQL="SELECT MAX(fecha_hora) as fecha_hora,id,estado,tarjeta_id FROM registro WHERE estado ='E' GROUP BY tarjeta_id";
//            PreparedStatement pst=this.PrepareStatement(consultaSQL);
//            ResultSet rs=this.ConsultaSQL(pst);
//            while(rs.next()){
//                lstsec.add((Registro)this.CargarObjeto(rs));
//            }
//            return lstsec;
//        }catch (Exception ex){
//            ex.printStackTrace();
//            return null;
//        }
//    }
    
    public ArrayList<Registro> GetFuera(){
        ArrayList<Registro> lstsalida=new ArrayList<Registro>();
        try{
            String consultaSQL="SELECT r.* from registro r where fecha_hora=(select max(s.fecha_hora) from registro s WHERE r.tarjeta_id=s.tarjeta_id) AND r.estado='S' OR r.estado = 'NULL'" ;
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            ResultSet rs=this.ConsultaSQL(pst);
            while(rs.next()){
                lstsalida.add((Registro)this.CargarObjeto(rs));
            }
            this.CierraConexion();
            return lstsalida;
            
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }
    
    public ArrayList<Registro> GetDentro(){
    ArrayList<Registro> lstentrada=new ArrayList<Registro>();
    try{
        String consultaSQL="SELECT r.* from registro r where fecha_hora=(select max(s.fecha_hora) from registro s WHERE r.tarjeta_id=s.tarjeta_id) AND r.estado='E'";
        PreparedStatement pst=this.PrepareStatement(consultaSQL);
        ResultSet rs=this.ConsultaSQL(pst);
        while(rs.next()){
            lstentrada.add((Registro)this.CargarObjeto(rs));
        }
        this.CierraConexion();
        return lstentrada;

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.CierraConexion();
        }
    }
    
    public String GetUltimoEstado(Tarjeta t){// siempre es parte de otro proceso que cierra la conexion
        try{
            String consultaSQL="SELECT * FROM registro WHERE r1.fecha_hora IN (SELECT MAX(r.fecha_hora) FROM registro r WHERE r.tarjeta_id=?)";
            PreparedStatement pst=this.PrepareStatement(consultaSQL);
            pst.setInt(1, t.id());
            ResultSet rs=this.ConsultaSQL(pst);
            String estado=null;
            if(rs.next()){
                estado=rs.getString("estado");
                if(estado!=null){
                    if(estado.trim().compareTo("E")==0){estado="S";}else{estado="E";}
                }else{
                    estado="E";
                }
            }
            
            return estado;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected IModel CargarObjeto(ResultSet rs) {
        try{
            Registro r=new Registro();
            r.id(rs.getInt("id"));
            Calendar cal=new GregorianCalendar();
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("fecha_hora")));
            r.fecha_hora(cal);
            
            MapperTarjeta mt=new MapperTarjeta();
            Tarjeta t=new Tarjeta();
            t.id(rs.getInt("tarjeta_id"));
            r.tarjeta((Tarjeta)mt.GetTarjeta(t));
            r.setEstado(rs.getString("estado"));
            //r.codigo(rs.getString("codigo").trim());
            return r;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
