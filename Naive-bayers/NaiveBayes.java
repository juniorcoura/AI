import java.util.List;

public class NaiveBayes {

    private double[][] mediaPonderada;
    private double[][] desvioPadrao;
    private int elementosC1;
    private int elementosC2;
    

    public NaiveBayes(int numClasses, int numEntradas){
        this.mediaPonderada = new double[numClasses][numEntradas];
        this.desvioPadrao = new double[numClasses][numEntradas];
        this.elementosC1 = 0;
        this.elementosC2 = 0;
    }

    public int[] nb(List<Data> trainingData,List<Data> testData, int numEntradas, int numClasses){
        calcularMedia(trainingData, numEntradas, numClasses);
        int[] erroClassificacao = new int[2];
        int classificacao = 0;

        System.out.println(trainingData.size());
        System.out.println(testData.size());

        for (Data data : testData) {
            double c1=1;
            double c2=1;
            
            for (int i = 0; i < numEntradas; i++) {
                c1 = c1 * ((1/desvioPadrao[0][i]*Math.sqrt(2*Math.PI)*Math.exp((-1/2)*Math.pow((data.input[i]-mediaPonderada[0][i])/desvioPadrao[0][i],2))));
                c2 = c2 * ((1/desvioPadrao[1][i]*Math.sqrt(2*Math.PI)*Math.exp((-1/2)*Math.pow((data.input[i]-mediaPonderada[1][i])/desvioPadrao[1][i],2))));
            }
            if(c1*(elementosC1/trainingData.size()) > c2*(elementosC2/trainingData.size())){
                classificacao = 0;
            }else{
                classificacao = 1;
            }

            if(classificacao != data.output[0]) erroClassificacao[0]++;
        }

        for (Data data : trainingData) {
            double c1=1;
            double c2=1;
            
            for (int i = 0; i < numEntradas; i++) {
                c1 = c1 * ((1/desvioPadrao[0][i]*Math.sqrt(2*Math.PI)*Math.exp((-1/2)*Math.pow((data.input[i]-mediaPonderada[0][i])/desvioPadrao[0][i],2))));
                c2 = c2 * ((1/desvioPadrao[1][i]*Math.sqrt(2*Math.PI)*Math.exp((-1/2)*Math.pow((data.input[i]-mediaPonderada[1][i])/desvioPadrao[1][i],2))));
            }
            if(c1*(elementosC1/trainingData.size()) > c2*(elementosC2/trainingData.size())){
                classificacao = 0;
            }else{
                classificacao = 1;
            }

            if(classificacao != data.output[0]) erroClassificacao[1]++;
        }

        
        
        return erroClassificacao;
    }

    public void calcularMedia(List<Data> trainingData, int numEntradas, int numClasses){
    
        int[] numElementosClasses = new int[2];

        double[][] somaTotal = new double[numClasses][numEntradas];

        for (int j = 0; j < trainingData.size(); j++) {
            if(trainingData.get(j).output[0] == 0){
                numElementosClasses[0] += 1;
            }else{
                numElementosClasses[1] += 1;
            }
            for (int i = 0; i < numEntradas; i++) { 
                if(trainingData.get(j).output[0] == 0){
                    somaTotal[0][i] += trainingData.get(j).input[i];
                }else {
                    somaTotal[1][i] += trainingData.get(j).input[i];

                }
            }
        }

        for (int i = 0; i < numClasses; i++) {
            for (int j = 0; j < numEntradas; j++) {
                if(i==0){
                    mediaPonderada[i][j] = somaTotal[i][j]/numElementosClasses[0];
                }else{
                    mediaPonderada[i][j] = somaTotal[i][j]/numElementosClasses[1];
                }
                
            }
        }

        //desvio padrão
        double[][] aux = new double[numClasses][numEntradas];
        for (int j = 0; j < trainingData.size(); j++) {
            for (int i = 0; i < numEntradas; i++) { 
                if(trainingData.get(j).output[0] == 0){
                    aux[0][i] += Math.pow(trainingData.get(i).input[i] - mediaPonderada[0][i], 2);
                }else {
                    aux[1][i] += Math.pow(trainingData.get(i).input[i] - mediaPonderada[1][i], 2);
                }
            }
        }

        for (int i = 0; i < numClasses; i++) {
            for (int j = 0; j < numEntradas; j++) {
                if(i==0){
                    aux[i][j] = Math.sqrt(aux[0][j]/numElementosClasses[0]);
                }else{
                    aux[i][j] = Math.sqrt(aux[1][j]/numElementosClasses[1]);
                }
            }
        }

        elementosC1 = numElementosClasses[0];
        elementosC2 = numElementosClasses[1];

        desvioPadrao = aux;
        
    }

}
