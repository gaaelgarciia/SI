package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.EstrategiaBusquedaGrafo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ProblemaCuadradoMagico;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);
        ProblemaCuadradoMagico cuadradoMagico = new ProblemaCuadradoMagico(new ProblemaCuadradoMagico.EstadoCuadradoMagico(3, new int[][]{{0, 0, 0}, {9, 5, 1}, {4, 3, 8}}));

        EstrategiaBusqueda buscador = new EstrategiaBusquedaGrafo();
        System.out.println(buscador.soluciona(cuadradoMagico));
    }
}
