/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Fachada.Fachada;
import Log.LogRegistros;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class ComunicacionSerieParalelo {

    private String puerto="";
    private Integer baurate=0;
    private Integer databytesize=8;//8 - 7
    private Integer stopbits=0; //0 - 2
    private Integer parity=0;//E - N - O - S
    static SerialPort puertoSerial;
    static String datosrecibidos="";
    
    public void setPuerto(String p){
        this.puerto=p;
    }

    public String getPuerto(){
        return this.puerto;
    }

    public void setBauRate(Integer br){
        this.baurate=br;
    }

    public Integer getBauRate(){
        return this.baurate;
    }

    public void setDataByteSize(Integer btsz){
        this.databytesize=btsz;
    }

    public Integer getDataByteSize(){
        return this.databytesize;
    }

    public void setStopBits(Integer stpb){
        this.stopbits=stpb;
    }

    public Integer getStopBits(){
        return this.stopbits;
    }

    public void setParity(Integer par){
        this.parity=par;// aca funcion q verifique
    }

    public Integer getParity(){
        return this.parity;
    }

    public ComunicacionSerieParalelo(){

    }

    public Boolean getEstadoConexion(){
        try{
            return (puertoSerial.isCTS());
        }catch (Exception ex){
            return false;
        }
    }
    
    public ArrayList<String> getPuertosLibres(){
        ArrayList<String> listaNombrePuertos=new ArrayList<String>();
        String[] portNames = SerialPortList.getPortNames();
        for(String puertos: portNames){
            listaNombrePuertos.add(puertos);
        }
        return listaNombrePuertos;
    }

    public void CargarParametros(String puerto,Integer baurate,Integer databytesize,Integer stopbits,Integer parity,Boolean registrar){
        this.puerto=puerto;
        this.baurate=baurate;
        this.databytesize=databytesize;
        switch(stopbits){
            case 0:
                stopbits=SerialPort.STOPBITS_1;
                break;
            case 1:
                stopbits=SerialPort.STOPBITS_1_5;
                break;
            case 2:
                stopbits=SerialPort.STOPBITS_2;
                break;
        }
        this.stopbits=stopbits;
        this.parity=parity;
    }
    
    public void InstanciaPuertoSinEventInterface(){
//        serialPort = new SerialPort(this.puerto);
//        try {
//            //*Abrimos el puerto
//            serialPort.openPort();
//            //*We expose the settings. You can also use this line - serialPort.setParams(9600, 8, 1, 0);
////            serialPort.setParams(SerialPort.BAUDRATE_9600,
////                                 SerialPort.DATABITS_8,
////                                 SerialPort.STOPBITS_1,
////                                 SerialPort.PARITY_NONE);
//            serialPort.setParams(this.baurate, this.databytesize, this.stopbits, this.parity);
//            //*Escribimos datos en el puerto
//            this.puertoSerial=serialPort;
//
//
//
//          //  serialPort.writeBytes("Test");
//
//            //*Lee datos de 10 bytes. cuidado con el método readBytes(), si el número de bytes de entrada en el buffer
//            //*es menor de lo que se necesita, entonces el metodo quedará en espera hasta que se complete el total.
//            //*Es mejor usarlo en conjunto con la interfaz SerialPortEventListener.
//
//         //   byte[] buffer = serialPort.readBytes(10);
//            //*Cerrando el puerto
//        //    serialPort.closePort();
//        }
//        catch (SerialPortException ex) {
//            System.out.println(ex);
//        }
//
    }

        public boolean InstanciaPuerto(){
        puertoSerial = new SerialPort(this.puerto);
        try {
            //*Abrimos el puerto
            puertoSerial.openPort();
            //*We expose the settings. You can also use this line - serialPort.setParams(9600, 8, 1, 0);
//            serialPort.setParams(SerialPort.BAUDRATE_9600,
//                                 SerialPort.DATABITS_8,
//                                 SerialPort.STOPBITS_1,
//                                 SerialPort.PARITY_NONE);
            puertoSerial.setParams(this.baurate, this.databytesize, this.stopbits, this.parity);
            //
            //Preparamos la mascara. En una mascara, necesitamos especificar
            //el tipo de evento que queremos "seguir o escuchar".
            //Por ejemplo si queremos saber que viene en los datos,
            //por lo tanto la mascara debe tener el siguiente valor: MASK_RXCHAR.
            //Si nosotros, por ejemplo, todavía necesitamos saber acerca de los
            //cambios de estado de las líneas de CTS(clear to send) y DSR(data set ready),
            //la máscara tiene que tener este aspecto:
            //SerialPort.MASK_RXCHAR + + SerialPort.MASK_CTS SerialPort.MASK_DSR;
            int mask = SerialPort.MASK_RXCHAR;
            //int mask = SerialPort.MASK_TXEMPTY;
            //Asignamos la mascara preparada
            puertoSerial.setEventsMask(mask);
           // puertoSerial.setFlowControlMode(mask);
            //Agregamos una interfaz atravez de la cual recibiremos información de los eventos
            SerialPortReader spr=new SerialPortReader();
            
            puertoSerial.addEventListener(spr);
            return true;
        }
        catch (SerialPortException ex) {
                        //aviso con descripcion del error
            return false;
        }

    }
        
        static class SerialPortReader implements SerialPortEventListener {
        private static LogRegistros log;
        private Fachada modelo=Fachada.getInstancia();
        
        public SerialPortReader(){
            log=new LogRegistros("\\log_puerto_serie.txt");
        }
        
        public void serialEvent(SerialPortEvent event) {
            //Tipo de objeto SerialPortEvent lleva información sobre el evento que se produjo y su valor.
            //Por ejemplo, si los datos provienen de un método event.getEventValue() devuelve el número de bytes en el buffer de entrada.
            
            if(event.isRXCHAR()&& event.getEventValue() > 0){
                
                //*if(event.getEventValue() == 10){
                    try {
                        datosrecibidos = puertoSerial.readString(event.getEventValue());
                        //datosrecibidos += puertoSerial.readString(event.getEventValue());
                        System.out.println("Received response: " + datosrecibidos);
                        
                        if(!datosrecibidos.isEmpty()){
                            modelo.ProcesarDatos(datosrecibidos);
                        }
                        if(modelo.Registrar()){log.RegistrarEnLog(datosrecibidos);}
                        
                        //puertoSerial.closePort();//Close serial port
                        //*byte buffer[] = puertoSerial.readBytes(10);
                        //ACA MANDAMOS LOS DATOS LEIDOS A ALGUN LADO
                    }
                    catch (SerialPortException ex) {
                        System.out.println(ex);
                    }
                //*}
            }
            //Si el estado de la línea CTS (clear to send) ha cambiado,
            //entonces el método event.getEventValue() devuelve 1
            //si la línea está activa y 0 si está apagada.
            else if(event.isCTS()){
                if(event.getEventValue() == 1){
                    System.out.println("CTS - ON");
                }
                else {
                    System.out.println("CTS - OFF");
                }
            }
            else if(event.isDSR()){
                if(event.getEventValue() == 1){
                    System.out.println("DSR - ON");
                }
                else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    }
        
    public void EnviarDatos(String dato){
        try {
            puertoSerial.writeString(dato);
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }
    
    public String LeerDatos(){
        return datosrecibidos;
    }

    public byte[] LeerDatosBytes(){
        try {
            byte[] buffer = puertoSerial.readBytes(10);
            return buffer;
        } catch (SerialPortException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean CerrarPuerto(){
        try {
            puertoSerial.removeEventListener();
            return puertoSerial.closePort();
        } catch (SerialPortException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // listado de BaudiosRate
    public ArrayList<Integer> getBaudiosRate(){
        //hardcode mejorarlo con bd
      ArrayList<Integer> lstBaudios=new ArrayList<Integer>();
      lstBaudios.add(9600);
      lstBaudios.add(57600);
      lstBaudios.add(4800);
      lstBaudios.add(460800);
      lstBaudios.add(38400);
      lstBaudios.add(2400);
      lstBaudios.add(19200);
      lstBaudios.add(1200);
      lstBaudios.add(115200);
      return lstBaudios;
    }
}
