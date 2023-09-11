import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NRainhasInd implements Individuo<NRainhasInd> {

    Random rand = new Random();
    public int nRainhas;
    public int[] dna;
    double txMutacao = 0.15;
    int avaliacao;

    public NRainhasInd(int nRainhas){
        this.nRainhas = nRainhas;
        dna = new int[nRainhas];
        this.avaliacao = -1;

        for(int i=0; i<nRainhas;i++){
            dna[i] = rand.nextInt(nRainhas);
        }
    }

    public int avaliar(){   
        int count = 0 ;   
        if(this.avaliacao<0){
            
            for (int i = 0; i <nRainhas-1; i++) {
                for(int j = 1; j<nRainhas;j++){
                    if(i!=j){
                        if(this.dna[i] == this.dna[j] ){
                            count++;
                        }else if(this.dna[j]-(j-i) == this.dna[i] || this.dna[j]+(j-i) == this.dna[i]){
                            count++;
                        }
                    }            
                }
            }
            this.avaliacao = count;
        }
        
        
        return this.avaliacao; 
    }
    
    //Cria dois filhos a partir da recombinação dos pais
    public List<Individuo> recombinar(Individuo ind){


        List<Individuo> filhos = new ArrayList<>();
        int cut = rand.nextInt(nRainhas);
 
        int aux = nRainhas/3;
        NRainhasInd filho = new NRainhasInd(this.nRainhas);

        if(ind instanceof NRainhasInd){

        NRainhasInd nR = (NRainhasInd)ind;
        
        
        for (int i = 0;i<this.nRainhas; i++){
            if(i<cut){
                filho.dna[i] = this.dna[i];
            }else{
                filho.dna[i] = nR.dna[i];
            }
        }
        filhos.add(filho);
        
        
        NRainhasInd filhoDois = new NRainhasInd(this.nRainhas);
     

        filhoDois.dna = nR.dna;

       
        for (int i = 0; i < aux; i++) {
            if(cut>=nRainhas){
                filhoDois.dna[nRainhas%cut] = this.dna[nRainhas%cut];
            }else{
                filhoDois.dna[cut] = this.dna[cut];
            }  
            cut++;
        }

        filhos.add(filhoDois);
    }

        return filhos;
    }
    
    //Gera um mutande a partir dos genes de um indivíuo
    public NRainhasInd mutar(){
        NRainhasInd mut = new NRainhasInd(this.nRainhas);
        
        //Gera aleatóriamente qual posição receberá um novo gene
        for (int i = 0;i<this.nRainhas; i++){
            double r = rand.nextDouble(); 
            
            if(r>this.txMutacao){
                mut.dna[i] = this.dna[i];
            }else{
                mut.dna[i] = rand.nextInt(nRainhas);
                
                }
            }
            return mut;
        }
    } 

