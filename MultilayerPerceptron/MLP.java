import java.util.Random;

public class MLP {

    double[][] wH, wO;
    int qtdIn, qtdH, qtdOut;
    double ni;

    //qtdH = quantidade de neurônios na camada intermediária.
    //qtdIn = entradas ( x ) ; qtdOut = saídas ( y )
    public MLP(int qtdIn, int qtdOut, int qtdH, double ni){
        this.wH = new double[qtdIn+1][qtdH];
        this.wO = new double[qtdH+1][qtdOut];

        this.qtdIn = qtdIn;
        this.qtdH = qtdH;
        this.qtdOut = qtdOut;
        this.ni = ni;

        randMat(this.wH);
        randMat(this.wO);
    }

    public double[] treinar(double[] xIn, double[] y){
            
            //acrescenta o bias
            double[] x = new double[xIn.length+1];
            for (int i = 0; i < xIn.length; i++) {
                x[i] = xIn[i];
            }
            x[x.length-1] = 1;

            //calcula a saída da camada H
            double[] h = new double[this.qtdH+1]; 
            for(int i=0; i<h.length-1; i++){
                for (int j = 0; j < x.length; j++) {
                    h[i] += x[j] *wH[j][i];
                }
                h[i] = 1/(1+ Math.exp(-h[i]));
            }
            h[this.qtdH] = 1;
            //

            //calcula a saída da camada out
            double[] out = new double[this.qtdOut];
            for (int i = 0; i < out.length; i++) {
                for (int j = 0; j < h.length; j++) {
                    out[i] += h[j]*wO[j][i];
                }
                out[i] = 1/(1+ Math.exp(-out[i]));
            }

            //calcula os deltas da camada out
            double[] deltaTeta = new double[this.qtdOut];
            for (int i = 0; i < deltaTeta.length; i++) {
                deltaTeta[i] = out[i]*(1-out[i])*(y[i]-out[i]);
            }

            double soma;
            double[] deltaH = new double[this.qtdH];
            for (int i = 0; i < deltaH.length; i++) {
                soma = 0;
                for (int j = 0; j < deltaTeta.length; j++) {
                    soma += deltaTeta[j]*wO[i][j];
                }
                deltaH[i] = h[i]*(1-h[i])*soma;
            }

            for (int i = 0; i < h.length; i++) {
                for (int j = 0; j < out.length; j++) {
                  wO[i][j] = wO[i][j] + this.ni * deltaTeta[j] * h[i];  
                }
            }

            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < h.length-1; j++) {
                  wH[i][j] = wH[i][j] + this.ni * deltaH[j] * x[i];  
                }
            }

            return out;
    }

    public void randMat(double[][] w){
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[0].length; j++) {
                w[i][j] = ((Math.random()* 0.3) - 0.3);
            }
        }
    }
}
