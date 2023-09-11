package DixonPriceFunction;

public class Main {
    public static void main(String[] args) {
         
        int nPopulation = 20;
        int elitism = 2;
        int roleta = nPopulation-elitism;
        int dimensao = 4;// numero de genes

        IndFactory factoryDiPrice = new DiPriceFunctionIndFacotory(dimensao);
        Individuo melhor;
        int tipoFuncao = 2;

        FGA fga = new FGA();
        melhor = fga.execute(factoryDiPrice, nPopulation, elitism, 2000, roleta, tipoFuncao, dimensao);

        DiPriceFunctionInd melhorInd  = (DiPriceFunctionInd)melhor;
        if(melhorInd != null){
            System.out.print("Genes do melhor indivíduo: "+"[");
            for (int i = 0; i < melhorInd.dna.length; i++) {
                System.out.print(melhorInd.dna[i]+" ");
            }
            System.out.print("]");
        }
    }
}
