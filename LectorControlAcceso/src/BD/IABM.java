/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelo.IModel;
import java.util.ArrayList;

/**
 *
 * @author Darwin
 */
public interface IABM {
    boolean Add(IModel imodel);
    boolean Update(IModel imodel);
    boolean Delete(IModel imodel);
    IModel Get(IModel imodel);
    ArrayList<IModel> GetAll();
}
