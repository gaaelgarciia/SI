package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.EstrategiaBusquedaGrafo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ProblemaCuadradoMagico;
import es.udc.sistemasinteligentes.Nodo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class MainEj2a {

    public static void main(String[] args) throws Exception {
        int[][] cuadrado = new int[][]{
                {9,9,2},
                {3,5,0},
                {0,1,0},
        };
        ProblemaCuadradoMagico.EstadoCuadradoMagico cuadradoMagico = new ProblemaCuadradoMagico.EstadoCuadradoMagico(3, cuadrado);
        ProblemaBusqueda problema = new ProblemaCuadradoMagico(cuadradoMagico);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaGrafo();

        Nodo[] solucion = buscador.soluciona(problema);
        ArrayList<Nodo> listaNodos = new ArrayList<>(Arrays.asList(solucion));
        Collections.reverse(listaNodos);
        for(Nodo i : listaNodos)
            System.out.println(i.toString());

    }
}
