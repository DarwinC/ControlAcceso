/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import ModeloDatos.BaseDatos;
import Utilidades.ManejaArchivos;
import java.sql.Connection;
import java.sql.DriverManager;
import net.ucanaccess.jdbc.UcanaccessDriver;
/**
 *
 * @author Darwin
 */
public abstract class BD {
    
    protected Connection con=null;
    
//    private String getCadenaConexion(){
//        return this.cadenaConexion;
//    }

    public void CierraConexion(){
        try{
            if(con!=null){
            con.close();
            }
        }catch (Exception ex){
            System.out.println("Error en cierre de conexion bd: " + ex.getMessage());
        }
    }

    //private Connection getConexion(){
    public void getConexion(){
        //ACA TIENE QUE HABER UNA CLASE QUE CARGUE LOS DATOS DE UN ARCHIVO DE TEXTO CIFRADO
        ManejaArchivos ma=new ManejaArchivos();
        
        ///////////////////////////////////////////////////////////////////
        
        String rutaArchivoCfg="\\configuracion_bd.ini"; //harcode !!!
        
        //////////////////////////////////////////////////////////////////
        
        BaseDatos bd=ma.GetConfiguracionBD(rutaArchivoCfg);
        if(bd.getMotorBD().equals("Access")){
            this.con=this.getConexionAccess(bd.getServidor(), bd.getNombreBD(), bd.getUsuario(), bd.getClave());
        }
        else if(bd.getMotorBD().equals("MySQL")){
            //System.out.println("Cargo la MySQL: "+ bd.getServidor() + bd.getPuerto() + bd.getNombreBD() + bd.getUsuario() + bd.getClave());
            this.con=this.getConexionMySQL(bd.getServidor(),bd.getPuerto(), bd.getNombreBD(), bd.getUsuario(), bd.getClave());
        }
    }
    
    private Connection getConexionAccess(String servidor,String basedatos,String usuario,String password){
        this.con=null;
        try{
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver").newInstance();
            
        }catch(Exception e){
          e.printStackTrace(); // 
            //System.out.println(e.getMessage()); //mensaje de error
            return null; // no se pudo cargar el controlador
        }
        try{
            String DSN="jdbc:ucanaccess://"+servidor;
            System.out.println(servidor);
            //String DSN="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+ basedatos; 
            //this.con = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=c:/BaseDatos/base.mdb,"root" , "password");
            this.con = DriverManager.getConnection( DSN,usuario , password);
            return this.con;
        }catch (Exception e){
            e.printStackTrace();
            
            return null; ////-> mensaje no se pudo conectar a la base de  datos
        }
    }
    
    private Connection getConexionMySQL(String servidor,String puerto,String basedatos,String usuario,String password){
        
        this.con=null;
        if(puerto.trim().isEmpty()){puerto="3306";}
        //puerto || ( puerto = 3306 );
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception e){
          e.printStackTrace(); // 
            //System.out.println(e.getMessage()); //mensaje de error
            return null; // no se pudo cargar el driver
        }
        try{
            this.con = DriverManager.getConnection("jdbc:mysql://"+servidor+":"+puerto+"/"+basedatos, usuario, password);
            //this.con = DriverManager.getConnection("jdbc:mysql://"+ servidor +":"+ puerto +"//"+ basedatos,usuario ,password);
       //     this.con = DriverManager.getConnection("jdbc:mysql://mi_servidor:3306/mi_base_de_datos","root" ,"pswd");
            return this.con;
        }catch(Exception e){
            e.printStackTrace();
            return null; //-> mensaje no se pudo conectar a la base de datos;
        }
    }
}
