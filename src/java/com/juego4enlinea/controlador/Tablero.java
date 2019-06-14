/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juego4enlinea.controlador;

import com.juego4enlinea.controlador.util.JsfUtil;
import com.juego4enlinea.modelo.Jugador;
import com.juego4enlinea.modelo.Usuario;
import com.juego4enlinea.modelo.grafo.Arista;
import com.juego4enlinea.modelo.grafo.Ficha;
import com.juego4enlinea.modelo.grafo.Grafo;
import com.juego4enlinea.modelo.grafo.Vertice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class Tablero implements Serializable {

    private int ancho = 7;
    private int alto = ancho - 1;
    private int total = ancho * alto;
    private DefaultDiagramModel model;
    private Grafo tablero = new Grafo();
    private byte numeroJugadores = 4;
    private Jugador jugadorSeleccionado;
    private int tiempoTurno = 0;
    private boolean  estadoJuego=false;
    private Date fechaSistema;
    
    

    private List<Jugador> jugadores = new ArrayList<Jugador>();

    /**
     * Creates a new instance of Tablero
     */
    public Tablero() {
    }

    @PostConstruct
    public void pintarTablero() {
        //llenarVertices();
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        int iniX = 2;
        for (int cont = 0; cont < 6; cont++) {
            int x = iniX;
            int y = 5;
            String color = "Negra";
            String styleColor = "ui-diagram-element-ficha-negra";
            for (int i = 1; i <= alto; i++) {
                for (int j = 1; j <= ancho; j++) {
                    tablero.adicionarVertice(new Ficha(color, "T" + (cont + 1)));
                    Element ceo = new Element(tablero.getVertices().size(), x + "em", y + "em");
                    ceo.setDraggable(false);
                    ceo.setStyleClass(styleColor);
                    ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.CENTER));
                    model.addElement(ceo);
                    x = x + 5;
                }
                y = y + 5;
                x = iniX;
            }
            iniX = iniX + 40;
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
        for (int i = 1; i <= 6; i++) {
            for (Vertice vert : tablero.obtenerVerticesxTablero("T"+i)) {
                if (vert.getCodigo() % ancho != 0) {
                    tablero.adicionarArista(vert.getCodigo(), vert.getCodigo() + 1, 0);
                }
                if (vert.getCodigo() + ancho <= (total*i)) {
                    tablero.adicionarArista(vert.getCodigo(), vert.getCodigo() + ancho, 0);

                    if (vert.getCodigo() % ancho != 0) {
                        tablero.adicionarArista(vert.getCodigo(), vert.getCodigo() + ancho + 1, 0);
                        tablero.adicionarArista(vert.getCodigo() + 1, vert.getCodigo() + ancho, 0);
                    }
                }
            }
        }
    }

    public int getTiempoTurno() {
        return tiempoTurno;
    }

    public void setTiempoTurno(int tiempoTurno) {
        this.tiempoTurno = tiempoTurno;
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

    public byte getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(byte numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Jugador getJugadorSeleccionado() {
        return jugadorSeleccionado;
    }

    public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
        this.jugadorSeleccionado = jugadorSeleccionado;
    }

    public boolean isEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(boolean estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public Date getFechaSistema() {
        return new Date();
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    

    public void simularJugada(int num, Usuario usuario) {
        JsfUtil.addSuccessMessage("Jugó " + usuario.getNombre());

        if (tablero.getVertices().get(num - 1).getFicha().getColor().compareTo("Negra") == 0) {
            int aux = 0;
            for (int i = num; i <= total; i = i + ancho) {
                if (tablero.getVertices().get(i - 1).getFicha().getColor().compareTo("Negra") == 0) {
                    aux = i;
                }
            }
            Element elem1 = model.getElements().get(aux - 1);
            for (Jugador jugadores : jugadores) {
                if(usuario.getNombre().compareTo(jugadores.getUsuario().getNombre())==0){
                    elem1.setStyleClass("ui-diagram-element-ficha-"+ jugadores.getColor());
                    tablero.getVertices().get(aux - 1).getFicha().setColor("Azul");
                }
            }
            
            //elem1.setStyleClass("ui-diagram-element-ficha-"+ jugadores.get(0).getColor());
            //tablero.getVertices().get(aux - 1).getFicha().setColor("Azul");
        } else {
            //System.out.println("columna "+ num + " llena" +", haga otra jugada");
            JsfUtil.addErrorMessage("columna " + num + " llena" + ", haga otra jugada");
        }

    }

    public void adicionarJugador() {
        jugadores.add(jugadorSeleccionado);
        jugadorSeleccionado = new Jugador();
    }

    public void seleccionarJugador(Usuario usuario) {
        jugadorSeleccionado = new Jugador();
        jugadorSeleccionado.setUsuario(usuario);
        jugadorSeleccionado.setTiempo(tiempoTurno);
    }
    
    public void activarJuego()
    {
        estadoJuego=true;
        JsfUtil.addSuccessMessage("Se ha habilitado el juego");
    }
    
   
    /*public void simularJugada(int num, Usuario usuario) {
        JsfUtil.addSuccessMessage("Jugó " + usuario.getNombre());

        if (tablero.getVertices().get(num - 1).getFicha().getColor().compareTo("Negra") == 0) {
            int aux = 0;
            for (int i = num; i <= total; i = i + ancho) {
                if (tablero.getVertices().get(i - 1).getFicha().getColor().compareTo("Negra") == 0) {
                    aux = i;
                }
            }
            Element elem1 = model.getElements().get(aux - 1);
            elem1.setStyleClass("ui-diagram-element-ficha-rosado");
            tablero.getVertices().get(aux - 1).getFicha().setColor("Azul");
        } else {
            //System.out.println("columna "+ num + " llena" +", haga otra jugada");
            JsfUtil.addErrorMessage("columna " + num + " llena" + ", haga otra jugada");
        }

    }*/
}
