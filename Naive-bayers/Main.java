public class Main{
    public static void main(String[] args) {
       
        DataReader data = new DataReader();

        int numClasses = 2;
        int numEntradas = data.dataList.get(0).input.length;
        NaiveBayes nb = new NaiveBayes(numClasses, numEntradas);
        int[] erroClassificacao = nb.nb(data.trainingData, data.testData, numEntradas, numClasses);
        System.out.println("Erro de Classificação do Treino: "+ erroClassificacao[1]);
        System.out.println("Erro de Classificação do Teste: "+ erroClassificacao[0]);
    }
}