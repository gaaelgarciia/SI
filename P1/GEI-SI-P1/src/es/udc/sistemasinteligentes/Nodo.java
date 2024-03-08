package es.udc.sistemasinteligentes;

import java.util.ArrayList;

public class Nodo implements Comparable<Nodo>{
    private Estado estado;
    private Nodo padre;
    private Accion accion;
    private float coste;
    private float f;

    public Nodo(Estado estado, Nodo padre, Accion accion, Heuristica heuristica) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
        if (padre != null) {
            this.coste = padre.getCoste() + accion.getCoste();
            if(heuristica != null)
                this.f = this.coste + heuristica.evalua(estado);
        } else {
            this.coste = 0;
        }
    }

    public Nodo[] reconstruirCamino() {
        ArrayList<Nodo> camino = new ArrayList<Nodo>();
        Nodo actual = this;
        while (actual != null) {
            camino.add(actual);
            actual = actual.getPadre();
        }
        return camino.toArray(new Nodo[0]);
    }

    public Nodo crearNodo(Estado estado, Nodo padre, Accion accion, Heuristica h) {
        return new Nodo(estado, padre, accion, h);
    }

    public Nodo[] sucesores(ProblemaBusqueda p, Estado estado, Heuristica h){
        ArrayList<Nodo> sucesores = new ArrayList<>();
        Accion[] acciones = p.acciones(estado);
        for (Accion accion : acciones) {
            Estado sc = p.result(estado, accion);
            sucesores.add(new Nodo(sc, this, accion, h));
        }
        return sucesores.toArray(new Nodo[0]);
    }
    public float getCoste() {
        return coste;
    }
    public float getF() {
        return f;
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
    @Override
    public int compareTo(Nodo nodo) {
        return nodo.f < this.f ? 1 : -1;
    }
}
