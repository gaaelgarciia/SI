package es.udc.sistemasinteligentes;

public class Nodo {
    private Estado estado;
    private Nodo padre;
    private Accion accion;
    private int coste;

    public Nodo(Estado estado, Nodo padre, Accion accion, int coste) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
        this.coste = coste;
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

    public int getCoste() {
        return coste;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "estado=" + estado +
                ", padre=" + padre +
                ", accion=" + accion +
                ", coste=" + coste +
                '}';
    }
}
