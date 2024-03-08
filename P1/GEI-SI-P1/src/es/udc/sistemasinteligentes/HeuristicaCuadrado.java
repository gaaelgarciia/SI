package es.udc.sistemasinteligentes;
import es.udc.sistemasinteligentes.ProblemaCuadradoMagico.EstadoCuadradoMagico;
public class HeuristicaCuadrado extends Heuristica {
    @Override
    public float evalua(Estado e) {
        EstadoCuadradoMagico estado = (EstadoCuadradoMagico) e;
        int n = estado.getN();
        int maxN = estado.getMaxN();
        int suma = 0;
        return score(n, maxN, suma, estado);
    }

    private int score(int n, int maxN, int suma, EstadoCuadradoMagico estado) {
        for (int i = 0; i < n; i++) {
            if(estado.getFila(i) > maxN)
                suma += 1000;
            else if(estado.getFila(i) == maxN)
                suma += 0;
            else{
                suma += Math.abs(maxN - estado.getFila(i));
            }

            if(estado.getColumna(i) > maxN)
                suma += 1000;
            else if(estado.getColumna(i) == maxN)
                suma += 0;
            else{
                suma += Math.abs(maxN - estado.getColumna(i));
            }
        }
        if(estado.getDiagonal1() > maxN)
            suma += 1000;
        else if(estado.getDiagonal1() == maxN)
            suma += 0;
        else{
            suma += Math.abs(maxN - estado.getDiagonal1());
        }

        if(estado.getDiagonal2() > maxN)
            suma += 1000;
        else if(estado.getDiagonal2() == maxN)
            suma += 0;
        else{
            suma += Math.abs(maxN - estado.getDiagonal2());
        }
        return suma;
    }

}
