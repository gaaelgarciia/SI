package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainEj2b {
    public static void main(String[] args) throws Exception {
        int[][] cuadrado = new int[][]{
                {2,0,0},
                {0,0,0},
                {0,0,0},
        };
        ProblemaCuadradoMagico.EstadoCuadradoMagico cuadradoMagico = new ProblemaCuadradoMagico.EstadoCuadradoMagico(3, cuadrado);
        ProblemaBusqueda problema = new ProblemaCuadradoMagico(cuadradoMagico);

        EstrategiaBusquedaInformada buscador = new EstrategiaBusquedaAEstrella();

        Nodo[] solucion = buscador.soluciona(problema, new HeuristicaCuadrado());
        ArrayList<Nodo> listaNodos = new ArrayList<>(Arrays.asList(solucion));
        Collections.reverse(listaNodos);
        for(Nodo i : listaNodos)
            System.out.println(i.toString());

    }
}
