/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juego4enlinea.modelo.grafo;

import java.io.Serializable;

/**
 *
 * @author yordano
 */
public class Vertice implements Serializable{
    
    private int codigo;
    private Ficha ficha;

    public Vertice(int codigo, Ficha ficha) {
        this.codigo = codigo;
        this.ficha = ficha;
    }

    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
    
      
      
    
}
