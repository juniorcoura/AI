package Trab2;

import java.util.Random;

public class Perceptron {
 
    private int qtdIn, qtdOut;
    private double[][] w;
    private double ni;

    public Perceptron(int qtdIn, int qtdOut, double ni){
        this.qtdIn = qtdIn;
        this.qtdOut = qtdOut;
        this.w = new double[qtdIn+1][qtdOut];
        
        Random rand = new Random();

        for (int i = 0; i < this.qtdIn+1 ; i++) {
            for (int j = 0; j < this.qtdOut ; j++) {
                w[i][j] = (rand.nextDouble() * 0.6) - 0.3; 
            }
        }
        
        this.ni = ni;
    }

    public double[] treinar(double[] xIn, double[] yBarra){
        double[] xBarra = concat(xIn, new double[]{1});
        double[] teta = new double[yBarra.length];
        for (int i = 0; i < yBarra.length; i++) {
            double uj = 0;
            for (int j = 0; j < xBarra.length; j++) {
                uj += xBarra[j]*this.w[j][i];        
            }
            teta[i] = 1/(1+ Math.exp(-uj));
        } 

        for (int j = 0; j < yBarra.length; j++) {
            for (int i = 0; i < xBarra.length; i++) {
                this.w[i][j] += this.ni * (yBarra[j]-teta[j])*xBarra[i]; 
            }
        }
        return teta;
    }

    public double[] concat(double[] vetUm, double[] vetDois){
        double[] vetAux = new double[vetUm.length + vetDois.length];
        System.arraycopy(vetUm, 0, vetAux, 0, vetUm.length);
        System.arraycopy(vetDois, 0, vetAux, vetUm.length, vetDois.length);
        return vetAux;
    }

}
