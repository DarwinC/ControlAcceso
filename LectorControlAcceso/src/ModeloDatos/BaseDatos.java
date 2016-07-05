/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDatos;

/**
 *
 * @author Darwin
 */
public class BaseDatos {
    
    String servidor="";
    String puerto="";
    String nombreBD="";
    String usuario="";
    String clave="";
    String motorBD="";

    public void BaseDatos(){
        
    }
    
    public void BaseDatos(String servidor,String nombreBD, String usuario, String clave, String puerto,String motorbd){
        this.servidor=servidor;
        this.nombreBD=nombreBD;
        this.usuario=usuario;
        this.clave=clave;
        this.puerto=puerto;
        this.motorBD=motorbd;
    }
    
    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public String getMotorBD(){
        return this.motorBD;
    }
    
    public void setMotorBD(String motorbd){
        this.motorBD=motorbd;
    }
}
