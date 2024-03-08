package es.udc.sistemasinteligentes;

import java.util.*;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {
    public EstrategiaBusquedaGrafo() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        /*
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        //Queue<Nodo> frontera = new LinkedList<>(); //Creation of the frontier anchura
        Stack<Nodo> frontera = new Stack<>(); //profundidad
        Nodo node = new Nodo(estadoActual, null, null, null); //Creation of the node
        frontera.add(node); //Add the node to the frontier
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (true) {
            if (!frontera.isEmpty()) {
                // node = frontera.poll(); anchura
                node = frontera.pop(); //profundidad
                estadoActual = node.getEstado();
                if (!p.esMeta(estadoActual)) {
                    System.out.println((i++) + " - " + estadoActual + " no es meta");
                    Accion[] accionesDisponibles = p.acciones(node.getEstado());
                    boolean modificado = false;
                    for (Accion acc : accionesDisponibles) {
                        Estado sc = p.result(estadoActual, acc);
                        System.out.println((i++) + " - RESULT(" + estadoActual + "," + acc + ")=" + sc);
                        if (!explorados.contains(estadoActual) && !frontera.contains(node)) {
                            frontera.add(node); //Add the node to the frontier
                            estadoActual = sc;
                            System.out.println((i++) + " - " + sc + " NO explorado");
                            explorados.add(estadoActual);
                            modificado = true;
                            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                            node = node.crearNodo(estadoActual, node, acc, null);
                        } else {
                            System.out.println((i++) + " - " + sc + " ya explorado o en la frontera");
                            break;
                        }
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
        */
        //Queue<Nodo> frontera = new LinkedList<>(); //Creation of the frontier anchura
        Stack<Nodo> frontera = new Stack<>(); //profundidad
        ArrayList<Estado> explorados = new ArrayList<Estado>();

        Estado estadoActual = p.getEstadoInicial();

        Nodo node = new Nodo(estadoActual, null, null, null); //Creation of the node
        frontera.add(node); //Add the node to the frontier

        int i = 1;
        int nCreados = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (true) {
            if (frontera.isEmpty()) //EMPTY?(F)
                throw new Exception("No se ha podido encontrar una solución");
            // node = frontera.poll(); anchura
            node = frontera.pop(); //profundidad //N = POP(F)
            estadoActual = node.getEstado(); //S = N.estado
            if (!p.esMeta(estadoActual)) { //S es meta?
                System.out.println((i++) + " - " + estadoActual + " no es meta");
                explorados.add(estadoActual); //INSERT(N, E)
                Nodo[] sucesores = new Nodo[0];
                sucesores = node.sucesores(p, estadoActual, null); //H = sucesores(N)
                nCreados += sucesores.length;
                for (Nodo sucesor : sucesores) { //Para cada Nh en H
                    Estado sh = sucesor.getEstado(); //Sh es Nh.estado
                    System.out.println((i++) + " - RESULT(" + estadoActual + "," + sucesor.getAccion() + ")=" + sh);
                    if (!explorados.contains(sh) && !frontera.contains(sucesor)) { // Si Nh.estado no en E ni en F
                        System.out.println((i++) + " - " + sh + " NO explorado");
                        frontera.add(sucesor); //Add the node to the frontier
                        System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    } else { //Sh en E? SI
                        System.out.println((i++) + " - " + sh + " ya explorado");
                    }
                }
            } else {
                break;
            }
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        System.out.println("Nodos expandidos: " + explorados.size());
        System.out.println("Nodos creados: " + nCreados);
        return node.reconstruirCamino();
    }

}
