package DixonPriceFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FGA {
    public Individuo execute(IndFactory factory, int nPop, int elitismo, int nGen, int roleta, int tipo,int d ){
        
        //double globalMinimum =  (-d*(d+4)*(d-1)/6);
        Random rand = new Random();
        Individuo melhor = null;

        List<Individuo> popIni = new ArrayList<>();
        

        for (int i = 0; i < nPop; i++) {
            popIni.add(factory.getNewIndividuo());
        }

        
        for (int g = 0; g < nGen; g++){

            List<Individuo> newPop = new ArrayList<>(nPop);

            List<Individuo> filhos = new ArrayList<>(nPop);
            List<Individuo> aux = new ArrayList<>(nPop);
            aux.addAll(popIni);
            for (int i = 0; i < popIni.size()/2; i++) {
                int index = rand.nextInt(aux.size());             
                Individuo p1 = aux.get(index);
                             
                index = rand.nextInt(aux.size());
                Individuo p2 = aux.get(index);

                aux.remove(p1);
                aux.remove(p2);

                filhos.addAll(p1.recombinar(p2,tipo));
            }

            List<Individuo> mutantes = new ArrayList<>(nPop);
            for (int index = 0; index < nPop; index++) {
                Individuo p = popIni.get(index);
                mutantes.add(p.mutar(p));
            }

            List<Individuo> join = new ArrayList<>(nPop*3);
            join.addAll(popIni);
            join.addAll(filhos);
            join.addAll(mutantes);

            
            newPop.clear();
            System.out.print("\n");

            newPop.addAll(elitismo(join, elitismo));

            join.removeAll(newPop);
            newPop.addAll(roleta(join, roleta));
            
            System.out.println("Melhor individuo da "+ (g+1) +"ª geração tem " +newPop.get(0).avaliar()+" como menor minimo");

                if(newPop.get(0).avaliar() == 0){
                    System.out.println("Minimo global: 0");
                    melhor = newPop.get(0);
                    break;
                }      
            
            
            popIni = newPop;
            join.clear();            
        }
        
        return melhor;
    }

    public List<Individuo> elitismo(List<Individuo> join, int elite){
        //lista organizada de individuos
        List<Individuo> aux = new ArrayList<Individuo>(elite);
               
        for (int i = 0; i < join.size()-1; i++) {
            for (int j = 0; j < (join.size()-i)-1; j++) {
                if(join.get(j).avaliar() > join.get(j+1).avaliar()){
                    Individuo x = join.get(j);
                    join.set(j, join.get(j+1));
                    join.set(j+1,x);                    
                }
            }
        }
        for (int i = 0; i < elite; i++) {
            aux.add(join.get(i));           
        }           
        return aux;
    }

    public List<Individuo> roleta(List<Individuo> indRoleta, int numInd){

        Random rand = new Random();
        List<Individuo> aux = new ArrayList<>(indRoleta.size());
        double count;

        for(int i=0; i<numInd;i++){
            count=0;
            for (int j = 0; j < indRoleta.size(); j++) {
                count += (1/indRoleta.get(j).avaliar());
            }    
            double mult = rand.nextDouble();
            mult = mult*count;
            count = 0;
            for (int k = 0; k < indRoleta.size(); k++) {
                count += (1/indRoleta.get(k).avaliar());
                if(count>mult){
                    aux.add(indRoleta.get(k));
                    indRoleta.remove(indRoleta.get(k));     
                    break;
                }
            }
        }

        return aux;
    }
}


