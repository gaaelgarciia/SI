package es.udc.sistemasinteligentes;

import java.util.*;

public class EstrategiaBusquedaAEstrella implements EstrategiaBusquedaInformada {

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        Queue<Nodo> frontera = new PriorityQueue<>(); //Creation of the frontier anchura
        ArrayList<Estado> explorados = new ArrayList<Estado>();

        Estado estadoActual = p.getEstadoInicial();

        Nodo node = new Nodo(estadoActual, null, null, h); //Creation of the node
        frontera.add(node); //Add the node to the frontier

        int i = 1, nCreados = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (true) {
            if (frontera.isEmpty())
                throw new Exception("No se ha podido encontrar una solución");//EMPTY?(F)
            node = frontera.remove(); //N = POP(F)
            estadoActual = node.getEstado(); //S = N.estado
            if (!p.esMeta(estadoActual)) { //S es meta?
                System.out.println((i++) + " - " + estadoActual + " no es meta");
                explorados.add(estadoActual); //INSERT(N, E)
                Nodo[] sucesores = new Nodo[0];
                sucesores = node.sucesores(p, estadoActual, h); //H = sucesores(N)
                nCreados += sucesores.length;
                for (Nodo sucesor : sucesores) { //Para cada Nh en H
                    Estado sh = sucesor.getEstado(); //Sh es Nh.estado
                    System.out.println((i++) + " - RESULT(" + estadoActual + "," + sucesor.getAccion() + ")=" + sh);
                    if (!explorados.contains(sh)) { //Sh en E? NO
                        System.out.println((i++) + " - " + sh + " NO explorado");
                        if(!frontera.contains(sucesor)) //Sh en F? NO
                            frontera.add(sucesor); //Add the node to the frontier
                        else{ //Sh en F? SI
                            if(sucesor.getF() < node.getF()){ //Nh.f < Nf.f? SI
                                frontera.remove(node);
                                frontera.add(sucesor);
                            } //NO
                        }
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
