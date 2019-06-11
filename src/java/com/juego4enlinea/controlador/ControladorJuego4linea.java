/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juego4enlinea.controlador;

import com.juego4enlinea.modelo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author yordano
 */
@Named(value = "controladorJuego4linea")
@SessionScoped
public class ControladorJuego4linea implements Serializable {
    
    private Usuario usuario;    
    @EJB
    private UsuarioFacade usuarioFacade;
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    

    /**
     * Creates a new instance of ControladorJuego4linea
     */
    public ControladorJuego4linea() {
    }
    
     @PostConstruct
    private void inicializar()
    {
        usuario = new Usuario();
        
    }
    
    
}
