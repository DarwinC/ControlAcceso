/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Registro;
import Modelo.Usuario;
import Modelo.Sector;
import BD.MapperRegistro;
import BD.MapperSector;
import BD.MapperTarjeta;
import BD.MapperUsuario;
import InterpreteDatos.Interprete;
import Modelo.IModel;
import Modelo.Tarjeta;
import ModeloDatos.BaseDatos;
import ModeloDatos.PuertoSerie;
import Utilidades.ComunicacionSerieParalelo;
import Utilidades.ExportarExcel;
import Utilidades.ManejaArchivos;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
/**
 * @author dcarrizo
 */
public class NegocioGeneral {

    //INSTANCIA DE SERVICIO GENERAL SINGLETON
    private static NegocioGeneral instancia=null;
    private final ComunicacionSerieParalelo csp=new ComunicacionSerieParalelo();
    private boolean registroPuertoSerie=false;
    
    private NegocioGeneral(){
    }

    public static NegocioGeneral getInstancia(){
        if(instancia==null){
            instancia=new NegocioGeneral();
        }
        return instancia;
    }
    
    
    //****************************************************
    //BASE DE DATOS
    
    public boolean GuardarCfgBD(String servidor,String nombreBD,String usuario,String clave,String puerto,String motorBD){
        ManejaArchivos ma=new ManejaArchivos();
        String rutaArchivoCfg="\\configuracion_bd.ini";
        return ma.GuardarCfgBD(servidor, nombreBD, usuario, clave, puerto, motorBD, rutaArchivoCfg);
    }
    
    public BaseDatos CargarConfiguracionBaseDatos(){
        ManejaArchivos ma=new ManejaArchivos();
        String rutaArchivoCfg="\\configuracion_bd.ini";
        return ma.GetConfiguracionBD(rutaArchivoCfg);
    }
    
    
//*************************************************
    // **************************************************
    
    // PUERTOS SERIE PARALELO
    
    public boolean IniciarComunicacionPuertoSerie(String puerto,Integer baurate,Integer databytesize,Integer stopbits,Integer parity){
        //csp=new ComunicacionSerieParalelo();
        csp.setPuerto(puerto);
        csp.setBauRate(baurate);
        csp.setDataByteSize(databytesize);
        csp.setParity(parity);
        csp.setStopBits(stopbits);
        return csp.InstanciaPuerto();
    }
    
    public boolean DesconectarPuerto(){
        return this.csp.CerrarPuerto();
    }
    
    public void EnviarDatos(String datos){
        this.csp.EnviarDatos(datos);
    }
    
    public String RecibirDatos(){
        return this.csp.LeerDatos();
    }
    
    public boolean GuardarCfgPuertoSerie(String puerto,Integer baurate,Integer databytesize,Integer stopbits,Integer parity,Boolean registrar){
        ManejaArchivos ma=new ManejaArchivos();
        //Asignar valor a variable registrar
        this.registroPuertoSerie=registrar;
        String rutaArchivoCfg="\\configuracion_puerto_serie.ini";
        return ma.GuardarCfgPuertoSerie(puerto,baurate,databytesize,stopbits,parity,registrar,rutaArchivoCfg);
    }
    
    public PuertoSerie CargarConfiguracionPuertoSerie(){
        ManejaArchivos ma=new ManejaArchivos();
        String rutaArchivoCfg="\\configuracion_puerto_serie.ini";
        return ma.GetConfiguracionPuertoSerie(rutaArchivoCfg);
    }
    
    public void ActivarRegistroPuertoSerie(Boolean activar){
        this.registroPuertoSerie=activar;
    }
    
    public void DesactivarRegistroPuertoSerie(){
        this.registroPuertoSerie=false;
    }
    
    public boolean RegistroPuertoSerie(){
        return this.registroPuertoSerie;
    }
    
    public boolean EstadoConexionPuertoSerie(){
        return this.csp.getEstadoConexion();
    }
    //listados
    
    public DefaultListModel GetListadoPuertoSerieLibres(){
        
        DefaultListModel dlm=new DefaultListModel();
        
        for(String puertos: csp.getPuertosLibres()){
            dlm.addElement(puertos);
        }
        
        return dlm;
    }
    
    public DefaultComboBoxModel GetListadoComboPuertoSerieLibres(){
        
        DefaultComboBoxModel cmb=new DefaultComboBoxModel();
        ArrayList<String> lp=csp.getPuertosLibres();
        for(String puertos: lp){
            cmb.addElement(puertos);
        }
        return cmb;
    }
    
    public DefaultListModel GetListadoBaudiosRate(){
        DefaultListModel dlm=new DefaultListModel();
        ArrayList<Integer> lb=csp.getBaudiosRate();
        for(int br: lb){
            dlm.addElement(br);
        }
        
        return dlm;
    }
    
    public DefaultComboBoxModel GetListadoComboBaudiosRate(){
        
        DefaultComboBoxModel cmb=new DefaultComboBoxModel();
        ArrayList<Integer> lb=csp.getBaudiosRate();
        for(int br: lb){
            cmb.addElement(br);
        }
        return cmb;
    }
    
    public DefaultComboBoxModel GetListadoComboDataSizeBytes(){
        DefaultComboBoxModel cmb=new DefaultComboBoxModel();
        cmb.addElement(5);
        cmb.addElement(6);
        cmb.addElement(7);
        cmb.addElement(8);
        return cmb;
    }
    
    public DefaultComboBoxModel GetListadoComboParity(){
        DefaultComboBoxModel cmb=new DefaultComboBoxModel(); 
        cmb.insertElementAt("NONE",0);
        cmb.insertElementAt("ODD",1);
        cmb.insertElementAt("EVEN", 2);
        cmb.insertElementAt("MARK",3);
        cmb.insertElementAt("SPACE",4);
        return cmb;
    }
    
    public DefaultComboBoxModel GetListadoComboStopBits(){
        DefaultComboBoxModel cmb=new DefaultComboBoxModel();
        cmb.insertElementAt("1",0);
        cmb.insertElementAt("1.5",1);
        cmb.insertElementAt("2",2);
        return cmb;
    }
    
//**************************************************
    
// USUARIOS

    public boolean AddUsuario(Usuario user){
        MapperUsuario mu=new MapperUsuario();
        if(!user.nombre().trim().isEmpty()){
            return mu.Add(user);
        }
        return false;
    }
    
    public boolean UpdateUsuario(Usuario user){
        MapperUsuario mu=new MapperUsuario();
        if(!user.nombre().trim().isEmpty()){
            System.out.println("ACA EN NEGOCIO");
            return mu.Update(user);
        }
        return false;
    }
    
    public boolean DeleteUsuario(Usuario user){
        MapperUsuario mu=new MapperUsuario();
        return mu.Delete(user);
    }
    
    public Usuario GetUsuario(Usuario u){
        MapperUsuario mu=new MapperUsuario();
        return (Usuario)mu.Get(u);
    }
    
    public Usuario GetUsuarioTarjeta(Tarjeta t){
        MapperUsuario mu=new MapperUsuario();
        return (Usuario)mu.GetByTarjeta(t);
    }

    public ArrayList<Usuario> GetUsuarios(){
        MapperUsuario mu=new MapperUsuario();
        ArrayList<Usuario> alu=new ArrayList<Usuario>();
        ArrayList<IModel> lu=mu.GetAll();
        for(IModel im: lu){
            alu.add((Usuario)im);
        }
        return alu;
    }
    
    public DefaultListModel FindUsuarioByNombre(String nombre){
        MapperUsuario mu=new MapperUsuario();
        DefaultListModel dlm=new DefaultListModel();
        ArrayList<Usuario> lu=mu.GetByNombre(nombre);
        for(Usuario u: lu){
            dlm.addElement(u);
        }
        return dlm;
    }
    
    public boolean ActualizarSectoresUsuario(Usuario u){
        MapperUsuario mu=new MapperUsuario();
        return mu.AsignarSectores(u);
    }
    
    public boolean AsignarTarjetaUsuario(Usuario u){
        MapperUsuario mu=new MapperUsuario();
        return mu.AsignarTarjeta(u);
    }
    
    public boolean RemoverTarjetaUsuario(Usuario u){
        MapperUsuario mu=new MapperUsuario();
        return mu.RemoverTarjeta(u);
    }
    
    public DefaultComboBoxModel GetUsuariosCMB(){
        MapperUsuario mu=new MapperUsuario();
        DefaultComboBoxModel dcmb=new DefaultComboBoxModel();
        ArrayList<IModel> lu=mu.GetAll();
        for(IModel im:lu){
            dcmb.addElement((Usuario)im);
        }
        return dcmb;
    }
    
    //******************************************
    
    
    // SECTORES
    
    public boolean AddSector(Sector sec){
        MapperSector ms=new MapperSector();
        return ms.Add(sec);
    }
    
    public boolean UpdateSector(Sector sec){
        MapperSector ms=new MapperSector();
        return ms.Update(sec);
    }
    
    public boolean DeleteSector(Sector sec){
        MapperSector ms=new MapperSector();
        return ms.Delete(sec);
    }
    
    public Sector GetSector(Sector s){
        MapperSector ms=new MapperSector();
        return (Sector)ms.Get(s);
    }
    
    public ArrayList<Sector> GetSectores(){
        MapperSector ms=new MapperSector();
        ArrayList<Sector> als=new ArrayList<Sector>();
        ArrayList<IModel> ls=ms.GetAll();
        for(IModel im: ls){
            als.add((Sector)im);
        }
        return als;
        
    }
    
    public DefaultListModel FindSectorByNombre(String nombre){
        MapperSector ms=new MapperSector();
        DefaultListModel dlm=new DefaultListModel();
        ArrayList<IModel> ls=ms.FindSectorByNombre(nombre);
        for(IModel im: ls){
            dlm.addElement((Sector)im);
        }
        return dlm;
    }
    
    public DefaultListModel GetListSectoresDeUsuario(Usuario u){
        MapperSector ms=new MapperSector();
        DefaultListModel dlm=new DefaultListModel();
        ArrayList<Sector> ls=ms.GetSectoresByUsuario(u);
        for(Sector s:ls){
            dlm.addElement(s);
        }
        
        return dlm;
    }
    
    public DefaultListModel GetListSectoresSinAsignarAlUsuario(Usuario u){
        MapperSector ms=new MapperSector();
        DefaultListModel dlm=new DefaultListModel();
        ArrayList<Sector> ls=ms.GetSectoresSinAsignarAlUsuario(u);
        for(Sector s:ls){
            dlm.addElement(s);
        }
        
        return dlm;
    }
    
    //*********************************************
    
    // TARJETAS
    
    public boolean AddTarjeta(Tarjeta t){
        MapperTarjeta mt=new MapperTarjeta();
        return mt.Add(t);
    }
    
    public boolean UpdateTarjeta(Tarjeta t){
        MapperTarjeta mt=new MapperTarjeta();
        return mt.Update(t);
    }
    
    public boolean DeleteTarjeta(Tarjeta t){
        MapperTarjeta mt=new MapperTarjeta();
        return mt.Delete(t);
    }
    
        public int GetUltimoIdTarjeta(){
        MapperTarjeta mt=new MapperTarjeta();
        return mt.GetLastId();
    }
    
    public Tarjeta GetTarjeta(Tarjeta t){
        MapperTarjeta mt=new MapperTarjeta();
        return (Tarjeta)mt.Get(t);
    }
    
    public boolean VerificarTarjetaDuplicada(String codigo){
        MapperTarjeta mt=new MapperTarjeta();
        return mt.GetByNumeroSerie(codigo)==null;
    }
    
    public Tarjeta GetTarjetaByNumeroSerie(String numero_serie){
        MapperTarjeta mt=new MapperTarjeta();
        return (Tarjeta)mt.GetByNumeroSerie(numero_serie);
    }

    public ArrayList<Tarjeta> GetTarjetas(){
        MapperTarjeta mt=new MapperTarjeta();
        ArrayList<Tarjeta> alt=new ArrayList<Tarjeta>();
        ArrayList<IModel> lt=mt.GetAll();
        for(IModel im: lt){
            alt.add((Tarjeta)im);
        }
        return alt;
    }    
    
    public DefaultComboBoxModel GetListadoTarjetaCMB(){
        DefaultComboBoxModel dcmb=new DefaultComboBoxModel();
        MapperTarjeta mt=new MapperTarjeta();
        ArrayList<IModel> lt=mt.GetAll();
        for(IModel im:lt){
            dcmb.addElement((Tarjeta)im);
        }
        return dcmb;
    }
    
    //***********************************************
    
    // REGISTROS
    
    public boolean AddRegistro(Registro r){
        MapperRegistro mr=new MapperRegistro();
        return mr.Add(r);
    }
    
    public boolean UpdateRegistro(Registro r){
        MapperRegistro mr=new MapperRegistro();
        return mr.Update(r);
    }
    
    public boolean DeleteRegistro(Registro r){
        MapperRegistro mr=new MapperRegistro();
        return mr.Delete(r);
    }
    
    private Registro GetRegistro(Registro r){
        MapperRegistro mr=new MapperRegistro();
        return (Registro)mr.Get(r);
    }

    public ArrayList<Registro> GetRegistros(){
        MapperRegistro mr=new MapperRegistro();
        ArrayList<Registro> alr=new ArrayList<Registro>();
        ArrayList<IModel> lr=mr.GetAll();
        for(IModel im: lr){
            alr.add((Registro)im);
        }
        return alr;
    }   
    
    public DefaultListModel GetRegistrosBy(Calendar fecha_desde,Calendar fecha_hasta, Usuario u, Tarjeta t){
        ArrayList<Registro> registros=null;
        DefaultListModel dlm=new DefaultListModel();
        MapperRegistro mr=new MapperRegistro();
        registros=mr.GetAllBy(fecha_desde, fecha_hasta, u, t);
        for(Registro r:registros){
            dlm.addElement(r);
        }
        
        return dlm;
    }
    
    
    public DefaultListModel GetListadoTarjetasFuera(){
        ArrayList<Registro> registros=null;
        DefaultListModel dlm=new DefaultListModel();
        MapperRegistro mr=new MapperRegistro();
        registros=mr.GetFuera();
        for(Registro r:registros){
            dlm.addElement(r);
        }
        return dlm;
    }
   
    public DefaultListModel GetListadoTarjetasDentro(){
        ArrayList<Registro> registros=null;
        DefaultListModel dlm=new DefaultListModel();
        MapperRegistro mr=new MapperRegistro();
        registros=mr.GetDentro();
        for(Registro r:registros){
            dlm.addElement(r);
        }
        return dlm;
    }
    
    public boolean ExportarExcel(String ruta,ListModel dlm){
        ExportarExcel expEx=new ExportarExcel();
        ArrayList<ArrayList<String>> array_array=new ArrayList<ArrayList<String>>();
        ArrayList<String> subarray=null;
        Registro r=null;
        int largo=dlm.getSize();
        
        for(int i=0;i<largo;i++){
            r=(Registro) dlm.getElementAt(i);
            subarray=new ArrayList<String>();
            subarray.add(r.tarjeta().codigo());
            Usuario u=this.GetUsuarioTarjeta(r.tarjeta());
            String usuario="Desconocido";
            String sectores="Sin asignar";
            if(u!=null){
                usuario=u.nombre();
                String separador="";
                if(u.getSectores()!=null){
                    if(u.getSectores().size()>1)separador="/";
                    sectores="";
                    for(Sector s:u.getSectores()){
                        sectores+=s.nombre()+separador;
                    }
                }
            }
            subarray.add(usuario);
            subarray.add(sectores);
            subarray.add(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(r.fecha_hora().getTimeInMillis())));
            subarray.add(r.getEstado());
            array_array.add(subarray);
        }
        return expEx.CreaExcel(array_array, ruta);
    }
    //******************************************************
    
    
    // LISTADOS
    
    public DefaultListModel getListaSectores(){
        DefaultListModel dlm=new DefaultListModel();
        ArrayList<Sector> ls=this.GetSectores();
        for(Sector s: ls){
            dlm.addElement(s);
        }
        return dlm;
    }

    public DefaultComboBoxModel getListaSectoresCMB(){
        DefaultComboBoxModel cmbm=new DefaultComboBoxModel();
        ArrayList<Sector> ls=this.GetSectores();
        for(Sector s: ls){
        cmbm.addElement(s);
        }
        return cmbm;
    }
    
    public DefaultListModel getListadoActual(int opcion){
        DefaultListModel dlm =new DefaultListModel();
        return dlm;
    }

    
    public DefaultListModel getListadoTarjetas(){
        DefaultListModel dlm=new DefaultListModel();
        ArrayList<Tarjeta> lt=this.GetTarjetas();
        for(Tarjeta t: lt){
            dlm.addElement(t);
        }
        return dlm;
    }
    
    public DefaultListModel GetTarjetasLibres(){
        DefaultListModel dlm=new DefaultListModel();
        MapperTarjeta mt=new MapperTarjeta();
        ArrayList<Tarjeta> lt=mt.GetTarjetasSinUsuarioAsociado();
        for(Tarjeta t: lt){
            dlm.addElement(t);
        }
        return dlm;
    }
    
    public DefaultListModel getListadoUsuarios(){
        DefaultListModel dlm=new DefaultListModel();
        ArrayList<Usuario> lu=this.GetUsuarios();
        for(Usuario u: lu){
            dlm.addElement(u);
        }
        return dlm;
    }

}