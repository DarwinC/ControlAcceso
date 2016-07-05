/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

import ModeloDatos.BaseDatos;
import ModeloDatos.PuertoSerie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author dcarrizo
 */
public class ManejaArchivos {
    
    private File CrearArchivoConfiguracion(String ruta){
        try{
            File f=new File("");
            f=new File(f.getAbsolutePath()+ ruta);
            if(!f.exists()){
                f.createNewFile();
            }
            return f;
        }catch(Exception ex){
            //aviso con descripcion del error
            return null;
        }
    }
    
    public boolean GuardarCfgBD(String servidor, String nombre_bd, String usuario, String clave, String puerto, String motor_bd,String rutaArchivoCfg){
        try{
            File f=this.CrearArchivoConfiguracion(rutaArchivoCfg);
            String datos=
                    "Servidor: " + servidor + System.getProperty("line.separator") 
                    +"NombreBD: " + nombre_bd + System.getProperty("line.separator")
                    +"Puerto: " + puerto + System.getProperty("line.separator")
                    +"Usuario: " + usuario + System.getProperty("line.separator")
                    +"Clave: " + clave + System.getProperty("line.separator")
                    +"MotorBD: " + motor_bd + System.getProperty("line.separator");
            PrintWriter writer = new PrintWriter(new FileWriter(f,false));
            writer.write(datos);
            writer.close();
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    
    public boolean GuardarCfgPuertoSerie(String puerto,Integer baurate,Integer databytesize,Integer stopbits,Integer parity,Boolean registrar,String rutaArchivoCfg){
        
        try{
            
            File f=this.CrearArchivoConfiguracion(rutaArchivoCfg);
            
            String datos=
                    "Puerto: " + puerto + System.getProperty("line.separator") 
                    +"Baurate: " + baurate.toString() + System.getProperty("line.separator")
                    +"DataByteSize: " + databytesize.toString() + System.getProperty("line.separator")
                    +"Stopbits: " + stopbits.toString() + System.getProperty("line.separator")
                    +"Parity: " + parity.toString() + System.getProperty("line.separator")
                    +"Registro: " + registrar.toString() + System.getProperty("line.separator");
            PrintWriter writer = new PrintWriter(new FileWriter(f,false));
            writer.write(datos);
            writer.close();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

        public BaseDatos GetConfiguracionBD(String rutaArchivoCfg){
        //HARDCODE DEL ARCHIVO DE CONFIGURACIÓN
        //SI NO HAY ARCHIVO DE CONFIGURACIÓN SE SOLICITA SE CARGUEN LOS DATOS
        File f=new File("");
        BaseDatos bd=new BaseDatos();
    
        try{
            f=new File(f.getAbsolutePath()+rutaArchivoCfg);
            if(!f.exists()){
                //RETORNO DE NULL DEMOSTRANDO QUE NO EXISTE EL ARCHIVO DE CONFIGURACION
                //CLASE DE MANEJO DE ERROR QUE DETERMINE EL ERROR
                return null;
            }else{
                //SI EXISTE EL ARCHIVO DE CONFIGURACION SE LEE
                //PENDIENTE ENCRIPTACION DEL ARCHIVO
                BufferedReader br = new BufferedReader( new FileReader(f) );
                String linea="";
                while(br.ready()){
                    linea=br.readLine();
                    String indicador=(linea.substring(0, linea.indexOf(":")).trim());
                            //leer todo lo demas hasta fin de linea y cortar los espacios con trim
                    String valor=linea.substring((linea.indexOf(":")+1)).trim();
                    if(indicador.equals("Servidor")){
                        bd.setServidor(valor.toString());
                    }
                    else if(indicador.equals("NombreBD")){
                        bd.setNombreBD(valor.toString());
                    }
                    else if(indicador.equals("Usuario")){
                        bd.setUsuario(valor.toString());
                    }
                    else if(indicador.equals("Clave")){
                        bd.setClave(valor.toString());
                    }
                    else if(indicador.equals("Puerto")){
                        bd.setPuerto(valor.toString());
                    }
                    else if(indicador.equals("MotorBD")){
                        bd.setMotorBD(valor.toString());
                    }
                }
                br.close();
            }
            return bd;
        }catch (Exception e){
            return null;
            // aviso del tipo de error
        }
    }
    
    public PuertoSerie GetConfiguracionPuertoSerie(String rutaArchivoCfg){
        //HARDCODE DEL ARCHIVO DE CONFIGURACIÓN
        //SI NO HAY ARCHIVO DE CONFIGURACIÓN SE SOLICITA SE CARGUEN LOS DATOS
        File f=new File("");
        PuertoSerie ps=new PuertoSerie();
    
        try{
            f=new File(f.getAbsolutePath()+rutaArchivoCfg);
            if(!f.exists()){
                //RETORNO DE NULL DEMOSTRANDO QUE NO EXISTE EL ARCHIVO DE CONFIGURACION
                //CLASE DE MANEJO DE ERROR QUE DETERMINE EL ERROR
                return null;
            }else{
                //SI EXISTE EL ARCHIVO DE CONFIGURACION SE LEE
                //PENDIENTE ENCRIPTACION DEL ARCHIVO
                BufferedReader br = new BufferedReader( new FileReader(f) );
                String linea="";
                while(br.ready()){
                    linea=br.readLine();
                    String indicador=(linea.substring(0, linea.indexOf(":")).trim());
                            //leer todo lo demas hasta fin de linea y cortar los espacios con trim
                    String valor=linea.substring((linea.indexOf(":")+1)).trim();
                    if(indicador.equals("Puerto")){
                        ps.setPuerto(valor.toString());
                    }    
                    else if(indicador.equals("Baurate")){
                        ps.setBaurate(Integer.parseInt(valor));
                    }
                    else if(indicador.equals("DataByteSize")){
                        ps.setDatabytesize(Integer.parseInt(valor));
                    }
                    else if(indicador.equals("Stopbits")){
                        ps.setStopbits(Integer.parseInt(valor));
                    }
                    else if(indicador.equals("Parity")){
                        ps.setParity(Integer.parseInt(valor));
                    }
                    else if(indicador.equals("Registro")){
                        ps.setRegistro(Boolean.valueOf(valor));
                        System.out.println(valor);
                        //System.out.println(System.getProperty("line.separator"));
                        //System.out.println(Boolean.parseBoolean(valor).toString());
                    }
                }
                br.close();
            }
            return ps;
        }catch (Exception e){
            return null;
            // aviso del tipo de error
        }
    }
//
//    public ArrayList<Registro> InsertDeDatos(String directorioArchivo){
//    try{
//        ConvertidorFecha cnvfh=new ConvertidorFecha();
//    ArrayList<Registro> colRegistros=new ArrayList<Registro>();
//    File f=new File(directorioArchivo);
//    BufferedReader br = new BufferedReader( new FileReader(f) );
//    String fecha="",hora="",linea="",nro_tarjeta="",estado="";
//    if(br.ready()){
//    do{
//        linea=br.readLine();
//        if((linea.indexOf(" ")>=0)&&(linea.trim().length()>0)){
//            //System.out.println("aca llego al insert");
//        nro_tarjeta=linea.substring(0, linea.indexOf(" ")).trim();
//        fecha=linea.substring((nro_tarjeta.length()+1),linea.indexOf(" ", nro_tarjeta.length()+1));
//        hora=linea.substring(((nro_tarjeta+fecha).length()+2), linea.indexOf(" ",((nro_tarjeta+fecha).length()+2)));
//        estado=linea.substring(((nro_tarjeta+fecha+hora).length()+3), linea.indexOf(" ",((nro_tarjeta+fecha+hora).length()+3)));
//        Registro r=new Registro();
//        r.setTarjeta(nro_tarjeta);
//        r.setFechaHora(cnvfh.FormatoFechaLong(fecha + " " + hora));
//        r.setEstado(estado);
//        colRegistros.add(r);
//        }
//    }while(br.ready());
//    }
//    br.close();
//    return colRegistros;
//    }catch (Exception ex){
//    return null;
//    }
//}
}