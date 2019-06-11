/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juego4enlinea.controlador;

import com.juego4enlinea.modelo.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yordano
 */
@Named(value = "plantillaController")
@ViewScoped
public class PlantillaController implements Serializable{
    
    public void verificarSesion(){
        
        try {
          Usuario us   = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
          
          if(us == null){
              FacesContext.getCurrentInstance().getExternalContext().redirect("./../index.xhtml");
          }
        
        } catch (Exception e) {
            //implementaciones del no guardar errores
        }
    }
    
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    /**
     * Creates a new instance of PlantillaController
     */
    public PlantillaController() {
    }
    
}
