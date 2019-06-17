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
    private int tablero;
    private int nivel;
    private int orientacion;

    public Ficha(String color, int tablero, int nivel) {
        this.color = color;
        this.tablero = tablero;
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
  
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTablero() {
        return tablero;
    }

    public void setTablero(int tablero) {
        this.tablero = tablero;
    }

    public int getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }
    
    

   
    
    
}
