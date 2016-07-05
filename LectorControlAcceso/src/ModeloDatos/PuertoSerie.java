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
public class PuertoSerie {
    
    private String puerto=""; // valor
    private Integer baurate=0; // valor
    private Integer databytesize=8;//8 - 7 valor
    private Integer stopbits=0; //0 - 1 - 2  combo index
    private Integer parity=0;//E - N - O - S combo index
    private Boolean registro=false;
    
    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public Integer getBaurate() {
        return baurate;
    }

    public void setBaurate(Integer baurate) {
        this.baurate = baurate;
    }

    public Integer getDatabytesize() {
        return databytesize;
    }

    public void setDatabytesize(Integer databytesize) {
        this.databytesize = databytesize;
    }

    public Integer getStopbits() {
        return stopbits;
    }

    public void setStopbits(Integer stopbits) {
        this.stopbits = stopbits;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }
    
    public Boolean getRegistro(){
        return this.registro;
    }
    
    public void setRegistro(Boolean registro){
        this.registro=registro;
    }
    
    public void PuertoSerie(){
        
    }
    
    public void PuertoSerie(String puerto,Integer baurate,Integer databytesize,Integer stopbits, Integer parity){
        this.puerto=puerto;
        this.baurate=baurate;
        this.databytesize=databytesize;
        this.stopbits=stopbits;
        this.parity=parity;
    }
    
}
