package es.udc.sistemasinteligentes;

import java.util.ArrayList;

public class Nodo {
    private Estado estado;
    private Nodo padre;
    private Accion accion;

    public Nodo(Estado estado, Nodo padre, Accion accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
    }

    public Nodo[] reconstruirCamino() {
        ArrayList<Nodo> camino = new ArrayList<Nodo>();
        Nodo actual = this;
        while (actual.getPadre() != null) {
            camino.add(actual);
            actual = actual.getPadre();
        }
        return camino.toArray(new Nodo[0]);
    }

    public Nodo crearNodo(Estado estado, Nodo padre, Accion accion) {
        return new Nodo(estado, padre, accion);
    }

    public Estado getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Accion getAccion() {
        return accion;
    }


    @Override
    public String toString() {
        return "Nodo{" +
                "estado=" + estado +
                ", padre=" + padre +
                ", accion=" + accion +
                '}';
    }
}
