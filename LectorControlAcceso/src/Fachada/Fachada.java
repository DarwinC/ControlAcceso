/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Fachada;

import Modelo.Registro;
import Modelo.Sector;
import Logica.NegocioGeneral;
import Modelo.Tarjeta;
import Modelo.Usuario;
import ModeloDatos.BaseDatos;
import ModeloDatos.PuertoSerie;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author dcarrizo
 */
public class Fachada extends Observable{

    private static Fachada instancia=null;
    private NegocioGeneral instanciaServicio=null;

    private Fachada(){
        
        this.instanciaServicio=NegocioGeneral.getInstancia();
    }

    public static Fachada getInstancia(){
        if(instancia==null){
            instancia=new Fachada();
        }
        return instancia;
    }

//    public void setRutaBD(String rutabd){
//        this.instanciaServicio.setDirectorioBD(rutabd);
//    }
//
//    public String getRutaBD(){
//        return this.instanciaServicio.getDirectorioBD();
//    }
//
//    public void setRutaRegistro(String dirReg){
//        this.instanciaServicio.setDirectorioRegistro(dirReg);
//    }
//
//    public String getDirectorioRegistro(){
//        return this.instanciaServicio.getDirectorioRegistro();
//    }
    //***************************************************************
    // INICIO CARGA DE CONFIGURACIONES
    
    public void Inicio(){
        BaseDatos bd=this.CargarConfiguracionDB();
        PuertoSerie ps=this.CargarConfiguracionPuertoSerie();
        if(bd==null){
            //aviso de configuracion faltante
        }else{
            //aviso de carga de configuracion de base de datos y muestra parametros cargados
        }
        
        if(ps!=null){
            this.ConectarPuerto(ps.getPuerto(), ps.getBaurate(), ps.getDatabytesize(), ps.getStopbits(), ps.getParity());
            this.instanciaServicio.ActivarRegistroPuertoSerie(ps.getRegistro());
        }else{
            //aviso de configuracion faltante
        }
        
    }
    
    
    
    
    //***************************************************************
    
    //BASE DE DATOS
    
    public boolean GuardarConfiguracionBD(String servidor,String nombreBD,String usuario,String clave,String puerto,String motorBD){
        return this.instanciaServicio.GuardarCfgBD(servidor, nombreBD, usuario, clave, puerto, motorBD);
    }
    
    public BaseDatos CargarConfiguracionDB(){
        return this.instanciaServicio.CargarConfiguracionBaseDatos();
    }
    
    //***************************************************************

    // USUARIOS
    
    public boolean AgregaUsuario(String nombre, String documento, String foto){
        Usuario u=new Usuario();
        u.nombre(nombre);
        u.foto(foto);
        u.documento(documento);
        return this.instanciaServicio.AddUsuario(u);
    }
    
    public boolean ModificaUsuario(int id,String nombre, String documento, String foto){
        Usuario u=new Usuario();
        u.id(id);
        u.nombre(nombre);
        u.documento(documento);
        u.foto(foto);
        
        System.out.println("esto llega: " + u.id() + " " + u.nombre() + " " + u.documento() + " " + u.foto());
        
        return this.instanciaServicio.UpdateUsuario(u);
    }
    
    public boolean DeleteUsuario(Usuario u){
        return this.instanciaServicio.DeleteUsuario(u);
    }
    
    public Usuario GetUsuario(int id){
        Usuario u=new Usuario();
        u.id(id);
        return this.instanciaServicio.GetUsuario(u);
    }
    
    public Usuario GetUsuarioByTarjeta(Tarjeta t){
        return this.instanciaServicio.GetUsuarioTarjeta(t);
    }
    
    public DefaultListModel BuscaUsuarioByNombre(String nombre){
        return this.instanciaServicio.FindUsuarioByNombre(nombre);
    }
    
    public boolean ActualizarSectoresUsuario(Usuario u){
        return this.instanciaServicio.ActualizarSectoresUsuario(u);
    }

    public boolean AsignarTarjetaUsuario(Usuario u){
        return this.instanciaServicio.AsignarTarjetaUsuario(u);
    }
    
    public boolean RemoverTarjetaUsuario(Usuario u){
        return this.instanciaServicio.RemoverTarjetaUsuario(u);
    }
    
    public DefaultComboBoxModel GetListadoUsuarioCMB(){
        return this.instanciaServicio.GetUsuariosCMB();
    }
    
    //***************************************************************
    
    
//
//    public DefaultListModel getListado(int opt){
//        return this.instanciaServicio.getListadoActual(opt);
//    }
    
    //*******************************************************
    
    
    // SECTORES ******************************************
    
    public boolean AgregaSector(String nombre,String descripcion){
        Sector s=new Sector();
        s.nombre(nombre);
        s.descripcion(descripcion);
        return this.instanciaServicio.AddSector(s);
    }
    
    public boolean ModificaSector(int id, String nombre, String descripcion){
        Sector s=new Sector();
        s.id(id);
        s.nombre(nombre);
        s.descripcion(descripcion);
        return this.instanciaServicio.UpdateSector(s);
    }
    
    public boolean BorrarSector(Sector s){
        return this.instanciaServicio.DeleteSector(s);
    }
    
    public DefaultListModel BuscaSectorPorNombre(String nombre){
        return this.instanciaServicio.FindSectorByNombre(nombre);
    }
    
    public DefaultListModel GetListadoSectoresSinAsignarAlUsuario(Usuario u){
        return this.instanciaServicio.GetListSectoresSinAsignarAlUsuario(u);
    }
    
    public DefaultListModel GetSectoresUsuario(Usuario u){
        return this.instanciaServicio.GetListSectoresDeUsuario(u);
    }
    //**************************************************************
    
    // TARJETA
    
    public boolean AgregaTarjeta(Tarjeta t){
        return this.instanciaServicio.AddTarjeta(t);
    }
    
    public boolean ModificaTarjeta(Tarjeta t){
        return this.instanciaServicio.UpdateTarjeta(t);
    }
    
    public boolean BorraTarjeta(int id){
        Tarjeta tarjeta=new Tarjeta();
        tarjeta.id(id);
        return this.instanciaServicio.DeleteTarjeta(tarjeta);
    }
    
    public Tarjeta GetTarjeta(int id){
        Tarjeta tarjeta=new Tarjeta();
        tarjeta.id(id);
        return this.instanciaServicio.GetTarjeta(tarjeta);
    }
    
    public boolean VerificarCodigoDuplicadoTarjeta(Tarjeta t){
        return this.instanciaServicio.VerificarTarjetaDuplicada(t);
    }
    
    public Tarjeta BuscaTarjetaPorNumeroSerie(String numero_serie){
        return this.instanciaServicio.GetTarjetaByNumeroSerie(numero_serie);
    }
    
    public DefaultListModel GetListadoTarjetasLibres(){
        return this.instanciaServicio.GetTarjetasLibres();
    }
    
    public DefaultComboBoxModel GetListadoTarjetaCMB(){
        return this.instanciaServicio.GetListadoTarjetaCMB();
    }
    
    //*******************************************************************
    
    // REGISTROS ********************************************************
    
    
    public boolean AgregaRegistro(Calendar fecha_hora, Tarjeta tarjeta){
        Registro registro=new Registro();
        registro.fecha_hora(fecha_hora);
        //registro.codigo(codigo);
        registro.tarjeta(tarjeta);
        
        return this.instanciaServicio.AddRegistro(registro);
    }
    
    public boolean ModificaRegistro(int id, Calendar fecha_hora, Tarjeta tarjeta){
        Registro registro=new Registro();
        registro.id(id);
        registro.fecha_hora(fecha_hora);
        //registro.codigo(codigo);
        registro.tarjeta(tarjeta);
        
        return this.instanciaServicio.UpdateRegistro(registro);
    }
    
    public boolean BorraRegistro(int id){
        Registro registro=new Registro();
        registro.id(id);
        
        return this.instanciaServicio.DeleteRegistro(registro);
        
    }
    
    public DefaultListModel ConsultaRegistros(Calendar fecha_desde,Calendar fecha_hasta, Usuario u,Tarjeta t){
        return this.instanciaServicio.GetRegistrosBy(fecha_desde,fecha_hasta,u,t);
    }
    
    public DefaultListModel ConsultaRegistroTarjetasFuera(){
        return this.instanciaServicio.GetListadoTarjetasFuera();
    }
    
    public DefaultListModel ConsultaRegistroTarjetasDentro(){
        return this.instanciaServicio.GetListadoTarjetasDentro();
    }
    
    public boolean ExportarExcel(String ruta,ListModel dlm){
        return this.instanciaServicio.ExportarExcel(ruta,dlm);
    }
    // ****************************************************
    
    // ******* PUERTO SERIAL *********************
    
    public boolean GuardarConfiguracionPuertoSerie(String puerto,Integer baurate,Integer databytesize,Integer stopbits,Integer parity,Boolean registrar){
        return this.instanciaServicio.GuardarCfgPuertoSerie(puerto,baurate,databytesize,stopbits,parity,registrar);
    }
    
    public PuertoSerie CargarConfiguracionPuertoSerie(){
        return this.instanciaServicio.CargarConfiguracionPuertoSerie();
    }
    
    public boolean ConectarPuerto(String puerto,Integer baurate,Integer bytesize,Integer stopbits,Integer parity){
        return this.instanciaServicio.IniciarComunicacionPuertoSerie(puerto,baurate,bytesize,stopbits,parity);
    }
    
    public boolean DesconectarPuerto(){
        return this.instanciaServicio.DesconectarPuerto();
    }
    
    public void EnviarDatosAPuertoSerie(String datos){
        this.instanciaServicio.EnviarDatos(datos);
    }
    
    public String RecibeDatos(){
        return this.instanciaServicio.RecibirDatos();
    }
    
    public void MostrarDatosRecibidosPuertoSerie(String datos){
        this.setChanged();
        this.notifyObservers(datos);
    }
    
    public void MostrarTarjetaMonitor(Tarjeta t){
        this.setChanged();
        System.out.println("Esta es la clase que nos da t: " + t.getClass().getName());
        this.notifyObservers(t);
    }
    
    public boolean Registrar(){
        return this.instanciaServicio.RegistroPuertoSerie();
    }
    
    public boolean EstadoConexionPuertoSerie(){
        return this.instanciaServicio.EstadoConexionPuertoSerie();
    }
    
    public void ProcesarDatos(String datos){
        
        this.MostrarDatosRecibidosPuertoSerie(datos);
        Tarjeta t=this.instanciaServicio.ProcesarRegistro(datos);
        if(t!=null){
            this.MostrarTarjetaMonitor(t);
        }
    }
    
    // listados
    
    public DefaultListModel GetPuertoSerieLibres(){
        return this.instanciaServicio.GetListadoPuertoSerieLibres();
    }
    
    public DefaultListModel GetListadoBaudiosRate(){
        return this.instanciaServicio.GetListadoBaudiosRate();
    }
    
    public DefaultComboBoxModel GetListadoComboBaudiosRate(){
        return this.instanciaServicio.GetListadoComboBaudiosRate();
    }
    
    public DefaultComboBoxModel GetListadoComboDataSizeBytes(){
        return this.instanciaServicio.GetListadoComboDataSizeBytes();
    }
    
    public DefaultComboBoxModel GetListadoComboParity(){
        return this.instanciaServicio.GetListadoComboParity();
    }
    
    public DefaultComboBoxModel GetListadoComboStopBits(){
        return this.instanciaServicio.GetListadoComboStopBits();
    }
    
    public DefaultComboBoxModel GetListadoComboPuertosLibres(){
        return this.instanciaServicio.GetListadoComboPuertoSerieLibres();
    }
//
//    public boolean getEstadoInicio(){
//        return this.instanciaServicio.getEstadoInicio();
//    }

//    public void ActualizaBD(){
//        this.instanciaServicio.ActualizaDatosTempFase1();
//    }

//    public boolean ActualizaHistoricoBD(){
//        return this.instanciaServicio.ActualizaHistorico(false);
//    }
//    
//    public boolean ForzarActualizacionHistorico(){
//        return this.instanciaServicio.ActualizaHistorico(true);
//    }
//
//    public void Guardar(){
//        this.instanciaServicio.Guardar();
//    }
//
//    public void GuardarSectores(){
//        this.instanciaServicio.GuardarColSectores();
//    }
//
//    public void GuardarUsuarios(){
//        this.instanciaServicio.GuardarColUsuarios();
//    }

    

//    public boolean SectorEnUso(Sector s){
//        return this.instanciaServicio.SectorEnUso(s);
//    }

    
    // LISTADOS
    
    public DefaultListModel getListaUsuarios(){
        return this.instanciaServicio.getListadoUsuarios();
    }
    
    public DefaultListModel getListadoSectores(){
        return this.instanciaServicio.getListaSectores();
    }

    public DefaultComboBoxModel getListadoSectoresCMB(){
        return this.instanciaServicio.getListaSectoresCMB();
    }

    public DefaultListModel getListadoTarjetas(){
        return this.instanciaServicio.getListadoTarjetas();
    }
    

    // CODIGOS X
    
    public void Msgbox(String msj){
        JOptionPane.showMessageDialog(null, msj, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

//    public DefaultListModel Consulta(int filtro_int,String filtro,Date fechaini,Date fechafin,int estado){
//        return this.instanciaServicio.ConsultaSQL(filtro_int,filtro,fechaini,fechafin,estado);
//    }

//    public boolean ExportaExcel(String rutaExcel, ListModel dlm){
//        return this.instanciaServicio.ExportarExcel(rutaExcel,dlm);
//    }

//    public boolean BorraRegistroHistorico(ArrayList<Registro> idreghist){
//        return this.instanciaServicio.BorrarRegistroHistorico(idreghist);
//    }
//
//    public boolean RecargaDatos(){
//        return this.instanciaServicio.CargaDatos();
//    }

}