package Utilidades;

import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author dcarrizo
 */

public class ExportarExcel {

    public ExportarExcel(){

    }

        public boolean CreaExcel(ArrayList<ArrayList<String>> array,String ruta){
        String contenidoCelda="";
            // Se crea el libro
            HSSFWorkbook libro = new HSSFWorkbook();
            
            // Creamos un estilo pora la fila de los titulos
            HSSFCellStyle style = libro.createCellStyle();
            HSSFFont fuente = libro.createFont();
            fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setFont(fuente);
         // se crea hoja
            HSSFSheet hoja = libro.createSheet();
            //se recorre el array
            HSSFRow filatitulos = hoja.createRow(0);
            
            filatitulos.createCell(0).setCellValue("Tarjeta");
            filatitulos.createCell(1).setCellValue("Usuario");
            filatitulos.createCell(2).setCellValue("Sectores");
            filatitulos.createCell(3).setCellValue("Fecha");
            filatitulos.createCell(4).setCellValue("Estado");
            
            filatitulos.getCell(0).setCellStyle(style);// setRowStyle(style);
            filatitulos.getCell(1).setCellStyle(style);
            filatitulos.getCell(2).setCellStyle(style);
            filatitulos.getCell(3).setCellStyle(style);
            filatitulos.getCell(4).setCellStyle(style);
            
            for(int i=0;i<array.size();i++){
                HSSFRow fila = hoja.createRow(i+1);
                for(int n=0;n<array.get(i).size();n++){
                    contenidoCelda=array.get(i).get(n);
                    fila.createCell(n).setCellValue(contenidoCelda);
                }
            }
        // Se salva el libro.
        try {
//            System.out.println("RUTA ARCHIVO: " + ruta_archivo);
            FileOutputStream archivo=new FileOutputStream(ruta);
            libro.write(archivo);
            archivo.close();
            return true;
        } catch (Exception e) {
  //          e.printStackTrace();
            return false;
        }
    }
}