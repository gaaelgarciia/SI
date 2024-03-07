package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {
    public EstrategiaBusquedaGrafo() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        //Queue<Nodo> frontera = new LinkedList<>(); //Creation of the frontier
        Stack<Nodo> frontera = new Stack<>();
        Nodo node = new Nodo(estadoActual, null, null); //Creation of the node
        frontera.add(node); //Add the node to the frontier
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (true) {
            if (!frontera.isEmpty()) {
                // node = frontera.poll();
                node = frontera.pop();
                estadoActual = node.getEstado();
                if (!p.esMeta(estadoActual)) {
                    System.out.println((i++) + " - " + estadoActual + " no es meta");
                    Accion[] accionesDisponibles = p.acciones(estadoActual);
                    boolean modificado = false;
                    for (Accion acc : accionesDisponibles) {
                        Estado sc = p.result(estadoActual, acc);
                        System.out.println((i++) + " - RESULT(" + estadoActual + "," + acc + ")=" + sc);
                        if (!explorados.contains(sc)) {
                            estadoActual = sc;
                            System.out.println((i++) + " - " + sc + " NO explorado");
                            explorados.add(estadoActual);
                            modificado = true;
                            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                            node = node.crearNodo(estadoActual, node, acc);
                            if (!frontera.contains(node)) frontera.add(node); //Add the node to the frontier
                            else System.out.println((i++) + " - " + sc + " ya en la frontera");
                            break;
                        } else
                            System.out.println((i++) + " - " + sc + " ya explorado");
                    }
                    if (!modificado) throw new Exception("No se ha podido encontrar una solución");
                } else {
                    break;
                }
            } else {
                throw new Exception("No se ha podido encontrar una solución");
            }
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return node.reconstruirCamino();
    }
}
