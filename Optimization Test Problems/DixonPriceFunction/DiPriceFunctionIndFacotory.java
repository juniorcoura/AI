package DixonPriceFunction;

public class DiPriceFunctionIndFacotory implements IndFactory<DiPriceFunctionInd> {

        private int dimensao;
        public DiPriceFunctionIndFacotory(int dimensao){
            this.dimensao = dimensao;
        }
    
        public DiPriceFunctionInd getNewIndividuo(){
            return new DiPriceFunctionInd(this.dimensao);
        }
}