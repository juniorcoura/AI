package DixonPriceFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiPriceFunctionInd implements Individuo<DiPriceFunctionInd> {

    Random rand = new Random();
    public int dimensao;
    public double[] dna;
    double txMutacao = 0.15 ;
    double avaliacao;
    boolean verified;

    //
    public DiPriceFunctionInd(int dimensao){
        this.dimensao = dimensao;
        dna = new double[dimensao];
        this.verified = false;

        double max = 10;
        double min = -10;
        for(int i=0; i<dimensao;i++){
            this.dna[i] = min + (rand.nextDouble()*(max-min)); 
        }
    }
    //

    //
    public double avaliar() {       
        if(!this.verified){
            double result;
            result = Math.pow( this.dna[0]-1 , 2);
            for (int i = 1; i < this.dimensao; i++) {
                double xi = this.dna[i];
                result += (i+1) * Math.pow((2*xi*xi) - this.dna[i-1], 2);
            }
            this.avaliacao = result;
            this.verified = true;
        }
        return this.avaliacao;    
    }
    //

    //
    public List<Individuo> recombinar(Individuo ind, int tipo) {
          
        List<Individuo> filhos = new ArrayList<>();
            
        if(ind instanceof DiPriceFunctionInd){

            DiPriceFunctionInd filho = new DiPriceFunctionInd(this.dimensao);
            DiPriceFunctionInd filhoDois = new DiPriceFunctionInd(this.dimensao);

            DiPriceFunctionInd nR = (DiPriceFunctionInd)ind;
            if(tipo==1){//função aritmética

                for (int i = 0;i<this.dimensao; i++){
                    filho.dna[i] = (this.dna[i]*0.67) + (nR.dna[i]*0.33);
                    filhoDois.dna[i] = (nR.dna[i]*0.67) + (this.dna[i]*0.33);
                }
    
            }else{//função bnx-alfa

                for (int i = 0; i < this.dimensao; i++) {
                    double alfa = rand.nextGaussian();
                    filho.dna[i] = this.dna[i] + alfa*(Math.abs(this.dna[i]-nR.dna[i]));
                }

                for (int i = 0; i < this.dimensao; i++) {
                    double alfa = rand.nextGaussian();
                    filhoDois.dna[i] = nR.dna[i] + alfa*(Math.abs(this.dna[i]-nR.dna[i]));
                }
            }
            
            filhos.add(filho);
            filhos.add(filhoDois);        
        }
        return filhos;
    }
    //

    //
    public Individuo mutar(Individuo p){

        DiPriceFunctionInd mut = new DiPriceFunctionInd(this.dimensao);
        boolean controlMut = false;
        DiPriceFunctionInd nP = (DiPriceFunctionInd)p;
        
        if(p instanceof DiPriceFunctionInd){            
            for (int i = 0;i<this.dimensao; i++){
                double r = rand.nextDouble(); 
                if(r<=txMutacao){
                    mut.dna[i] = nP.dna[i] + (rand.nextGaussian());
                    controlMut=true;
                }else{
                    mut.dna[i] = nP.dna[i]; 
                }
            }      
        }
        if(!controlMut){
            int aux = rand.nextInt(dimensao);
            double alfa = rand.nextGaussian();
            nP.dna[aux] = nP.dna[aux] + (alfa);
            return nP;   
        }else{
            return mut;
        }
    }
    //
}