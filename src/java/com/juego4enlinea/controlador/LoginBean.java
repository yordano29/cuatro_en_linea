/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juego4enlinea.controlador;

import com.juego4enlinea.controlador.util.FacesUtils;
import com.juego4enlinea.controlador.util.JsfUtil;
import com.juego4enlinea.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author yordano
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{
    
    private Usuario usuario;    
    @EJB
    private UsuarioFacade usuarioFacade;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String ingresar()
    {
     
        Usuario usuarioEncontrado=usuarioFacade.find(usuario.getCorreo());
        if (usuarioEncontrado != null)            
        {
            if (usuario.getContrasena().compareTo(usuarioEncontrado.getContrasena()) == 0) {
            
                //alamacenar en la sesion de 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioEncontrado);
                ControladorJuego4linea contDamas = (ControladorJuego4linea) FacesUtils.getManagedBean("controladorJuego4linea");
                contDamas.setUsuario(usuarioEncontrado);
                if(usuarioEncontrado.getRol().getCodigo()==1){
                    return "ingresar";
                }
                else
                {
                    return "ingresarJugador";
                }
                
            }
            JsfUtil.addErrorMessage("Contraseña errada");
        } else {
            JsfUtil.addErrorMessage("El correo ingresado no existe");
        } 
   
        return null;
    }

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        
    }
    
    @PostConstruct
    private void inicializar()
    {
        usuario = new Usuario();
    }
    
}
