/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juego4enlinea.controlador;

import com.juego4enlinea.controlador.util.JsfUtil;
import com.juego4enlinea.modelo.grafo.Arista;
import com.juego4enlinea.modelo.grafo.Ficha;
import com.juego4enlinea.modelo.grafo.Grafo;
import com.juego4enlinea.modelo.grafo.Vertice;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;


/**
 *
 * @author yordano
 */
@Named(value = "tablero")
@ApplicationScoped
public class Tablero implements Serializable{
    
    private int ancho=7;
    private int alto = ancho -1;
    private int total = ancho * alto;
    private DefaultDiagramModel model;
    private Grafo tablero = new Grafo();

    /**
     * Creates a new instance of Tablero
     */
    public Tablero() {
    }
    @PostConstruct
    public void pintarTablero() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        int x = 18;
        int y = 5;
        String color = "Negra";
        String styleColor = "ui-diagram-element-ficha-negra";
        for (int i = 1; i <= alto; i++ ) {
            for (int j = 1 ; j <= ancho ; j++) {
                tablero.adicionarVertice(new Ficha(color));
                Element ceo = new Element(tablero.getVertices().size(), x + "em", y + "em");
                ceo.setDraggable(false);
                ceo.setStyleClass(styleColor);
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.CENTER));
                model.addElement(ceo);
                x = x + 5;
            }
            y = y + 5;
            x = 18;
        }
       llenarAristas();
       StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:1}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);

        //recorrer aristas
        for (Arista arista : tablero.getAristas()) {
            Element origen = model.getElements().get(arista.getOrigen() - 1);
            Element destino = model.getElements().get(arista.getDestino() - 1);
            model.connect(new Connection(origen.getEndPoints().get(0), destino.getEndPoints().get(0)));
        }
    }
     
    private EndPoint createEndPoint(EndPointAnchor anchor) {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setStyle("{fillStyle:'#404a4e'}");
        endPoint.setHoverStyle("{fillStyle:'#20282b'}");         
        return endPoint;
    }
    
    public void llenarAristas() {        
        //Crear aristas        
        for (Vertice vert : tablero.getVertices()) {
            if(vert.getCodigo() % ancho != 0){
                tablero.adicionarArista(vert.getCodigo(), vert.getCodigo() + 1, 0);
            }
            if(vert.getCodigo() + ancho <= total){                    
                tablero.adicionarArista(vert.getCodigo(), vert.getCodigo() + ancho, 0);                                
                        
            if(vert.getCodigo() % ancho != 0 ){
                tablero.adicionarArista(vert.getCodigo(), vert.getCodigo() + ancho + 1, 0);
                tablero.adicionarArista(vert.getCodigo() + 1, vert.getCodigo() + ancho, 0);
            }
            }           
        }
    }
  
    

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }
    
    public void simularJugada(int num)
    {
        if(tablero.getVertices().get(num -1).getFicha().getColor().compareTo("Negra")==0){
            int aux=0;
            for (int i = num; i <= total; i = i+ancho) {
                if(tablero.getVertices().get(i -1).getFicha().getColor().compareTo("Negra")==0){
                    aux = i;
                }
            }
            Element elem1=model.getElements().get(aux -1);       
            elem1.setStyleClass("ui-diagram-element-ficha");
            tablero.getVertices().get(aux -1).getFicha().setColor("Azul");  
        }
        else{
            System.out.println("columna "+ num + " llena" +", haga otra jugada");
        }
        
    }
    
    
}


/*else{
                Element elem1=model.getElements().get(aux-1);       
                elem1.setStyleClass("ui-diagram-element-ficha-azul");
                tablero.getVertices().get(aux-1).getFicha().setColor("Azul");
                
            }*/
        
        /*
        
        if(tablero.getVertices().get(num -1).getFicha().getColor().compareTo("Negra")==0){
            
        Element elem1=model.getElements().get(num -1);       
        elem1.setStyleClass("ui-diagram-element-ficha-azul");
        tablero.getVertices().get(num -1).getFicha().setColor("Azul");
            
        }else{
            Element elem1=model.getElements().get(11 -1);       
            elem1.setStyleClass("ui-diagram-element-ficha-azul");
            tablero.getVertices().get(11 -1).getFicha().setColor("Azul");
        }
        
        
        for (int i = 0; i < 10; i++) {
            
        }*/