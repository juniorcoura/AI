public class NRainhasIndFactory implements IndFactory<NRainhasInd> {
    
    private int nRainhas;
    public NRainhasIndFactory(int nRainhas){
        this.nRainhas = nRainhas;
    }

    public NRainhasInd getNewIndividuo(){
        return new NRainhasInd(this.nRainhas);
    }
}
