package es.udc.sistemasinteligentes;

import java.util.Arrays;
import java.util.Objects;

public class ProblemaCuadradoMagico extends ProblemaBusqueda{

    public static class EstadoCuadradoMagico extends Estado{
        private final int n;
        private final int[][] cuadrado;

        public EstadoCuadradoMagico(int n, int[][] cuadrado) {
            this.n = n;
            this.cuadrado = cuadrado;
        }
        public int getMaxN(){
            return this.n*(this.n*this.n+1)/2;
        }

        public int getN(){
            return n;
        }

        public int[][] getCuadrado() {
            return cuadrado;
        }
        public int getDiagonal1(){
            int diagonal1 = 0;
            for (int i = 0; i < n; i++) {
                diagonal1 += cuadrado[i][i];
            }
            return diagonal1;
        }
        public int getDiagonal2(){
            int diagonal2 = 0;
            for (int i = 0; i < n; i++) {
                diagonal2 += cuadrado[i][n-i-1];
            }
            return diagonal2;
        }
        public int getFila(int i){
            int fila = 0;
            for (int j = 0; j < n; j++) {
                fila += cuadrado[i][j];
            }
            return fila;
        }
        public int getColumna(int i){
            int columna = 0;
            for (int j = 0; j < n; j++) {
                columna += cuadrado[j][i];
            }
            return columna;
        }

        public int getElemCuadrado(int i, int j){
            return cuadrado[i][j];
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (int i = 0; i < n; i++) {
                sb.append("| ");
                for (int j = 0; j < n; j++) {
                    sb.append(cuadrado[i][j]);
                    sb.append(" ");
                }
                sb.append(" |\n");
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof EstadoCuadradoMagico)) return false;
            return n == ((EstadoCuadradoMagico) obj).n && Arrays.deepEquals(cuadrado, ((EstadoCuadradoMagico) obj).cuadrado);
        }

        @Override
        public int hashCode() {
            return Objects.hash(n) * 31 + Arrays.deepHashCode(cuadrado);
        }
    }

    public static class AccionCuadrado extends Accion{
        int x, y, valor;
        public AccionCuadrado(int x, int y, int valor){
            this.x = x;
            this.y = y;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return "Poner " + valor + " en (" + x + "," + y + ")";
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;
            for(int i = 0; i < estado.getN(); i++){
                if(estado.getFila(i) == estado.getMaxN() || estado.getFila(i) + this.valor > estado.getMaxN()) return false;
                if(estado.getColumna(i) == estado.getMaxN() || estado.getColumna(i) + this.valor > estado.getMaxN()) return false;
            }
            if(this.x == this.y
                    && (estado.getDiagonal1() == estado.getMaxN() || estado.getDiagonal1() + this.valor > estado.getMaxN())) return false;
            if((this.x == this.y || this.x == estado.getN()-this.x-1)
                    && (estado.getDiagonal2() == estado.getMaxN() || estado.getDiagonal2() + this.valor > estado.getMaxN())) return false;
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;
            int[][] cuadrado = new int[estado.getN()][estado.getN()];
            for (int i = 0; i < estado.getN(); i++) {
                for (int j = 0; j < estado.getN(); j++) {
                    cuadrado[i][j] = estado.getElemCuadrado(i, j);
                }
            }
            cuadrado[this.x][this.y] = this.valor;
            return new EstadoCuadradoMagico(estado.getN(), cuadrado);
        }
    }
    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;
         int diagonal1 = 0, diagonal2 = 0, sumatorio = ((EstadoCuadradoMagico) es).getMaxN();
         for (int i = 0; i < estado.getN(); i++) {
             int fila = 0, columna = 0;
             fila += estado.getFila(i);
             columna += estado.getColumna(i);
             if(fila !=  sumatorio|| columna != sumatorio) return false;
         }
        diagonal1 += estado.getDiagonal1();
        diagonal2 += estado.getDiagonal2();
        return diagonal1 == sumatorio && diagonal2 == sumatorio;
    }

    @Override
    public Accion[] acciones(Estado es) {
        return new Accion[0];
    }
}
