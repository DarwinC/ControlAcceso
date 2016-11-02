/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;
import Modelo.IModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author dcarrizo
 */
abstract class BDConsultas extends BD {
    
   // private static BDConsultas instancia;

    public PreparedStatement PrepareStatement(String sqlcon){
        try{
            this.getConexion();
            return this.con.prepareStatement(sqlcon);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
  
    public int ConsultaSQLupdate(PreparedStatement pst){
        try{
            //System.out.println(sqlUpdate);
            //PreparedStatement pst=this.PrepareStatement(sqlUpdate);
            return pst.executeUpdate();
            //return pst.executeUpdate(sqlUpdate);
        }catch (Exception e){
            e.printStackTrace();
            //System.out.println(e.getMessage());
            return 0;
        }
    }
   
    public ResultSet ConsultaSQL(PreparedStatement pst){
        try{
        //PreparedStatement st = this.PrepareStatement(sqlcon);
        ResultSet rs=pst.executeQuery();
        if(rs==null){
            System.out.println("esta en null");
        }
        return rs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    protected abstract IModel CargarObjeto(ResultSet rs);
}