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
public class Ficha implements Serializable{
 
    private String color;
   

    public Ficha(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    
}
