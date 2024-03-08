package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainEj2b {
    public static void main(String[] args) throws Exception {
        int[][] cuadrado = new int[][]{
                {2,0,15,9},
                {14,0,5,0},
                {11,13,0,6},
                {7,1,0,16},
        };
        ProblemaCuadradoMagico.EstadoCuadradoMagico cuadradoMagico = new ProblemaCuadradoMagico.EstadoCuadradoMagico(4, cuadrado);
        ProblemaBusqueda problema = new ProblemaCuadradoMagico(cuadradoMagico);

        EstrategiaBusquedaInformada buscador = new EstrategiaBusquedaAEstrella();

        Nodo[] solucion = buscador.soluciona(problema, new HeuristicaCuadrado());
        ArrayList<Nodo> listaNodos = new ArrayList<>(Arrays.asList(solucion));
        Collections.reverse(listaNodos);
        for(Nodo i : listaNodos)
            System.out.println(i.toString());

    }
}
