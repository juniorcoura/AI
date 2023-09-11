public class Main {
    public static void main(String[] args) {
        
        
        int nPopulation = 20;
        int elitism = 4;
        int roleta = 16;

        IndFactory factory = new NRainhasIndFactory(4);
        Individuo melhor;

        FGA fga = new FGA();

        melhor = fga.execute(factory, nPopulation, elitism, 1000, roleta);

        NRainhasInd melhorRainha  = (NRainhasInd)melhor;
        if(melhorRainha != null){
        System.out.print("Genes do melhor indivíduo: "+"[");
        for (int i = 0; i < melhorRainha.dna.length; i++) {
            System.out.print(melhorRainha.dna[i]+" ");
        }
        System.out.print("]");
        }
    }
}
