
public class Main {
    public static void main(String[] args) {
        DataReader data = new DataReader();

        
        MLP mlp = new MLP(5, 1, 3,  0.0001);
        for (int i = 0; i < 50000; i++) {
            double erroEpoca = 0;
            double erroClassificacao=0;

            double erroEpocaTeste = 0;
            double erroClassificacaoTeste = 0;
            for (int j = 0; j < data.trainingData.size(); j++) {
                double[] xBarra = data.trainingData.get(j).input;
                double[] yBarra = data.trainingData.get(j).output;
                
                
                double[] teta = mlp.treinar(xBarra, yBarra);
                double erroAmostra = 0;

                //calcula o erro de aproximação da amostra
                for (int k = 0; k < teta.length; k++) {                   
                    erroAmostra += Math.abs(yBarra[k]- teta[k]);
                }

                //calcula o erro de classificação da amostra
                double erroClassificacaoAmostra = 0;
                double[] ot = threshold(teta);
                double soma = 0;
                for (int k = 0; k < ot.length; k++) {
                    soma = soma+Math.abs(yBarra[k] - ot[k]);
                }

                if(soma > 0 ){
                    erroClassificacaoAmostra = 1;
                }

                erroEpoca += erroAmostra;
                erroClassificacao += erroClassificacaoAmostra;
            }

            for (int j = 0; j < data.testData.size(); j++) {

                double[] xBarra = data.testData.get(j).input;
                double[] yBarra = data.testData.get(j).output;
                double[] teta = mlp.teste(xBarra, yBarra);
                double erroAmostra = 0;

                //calcula o erro de aproximação da amostra
                for (int k = 0; k < teta.length; k++) {                   
                    erroAmostra += Math.abs(yBarra[k]- teta[k]);
                }

                //calcula o erro de classificação da amostra
                double erroClassificacaoAmostra = 0;
                double[] ot = threshold(teta);
                double soma = 0;
                for (int k = 0; k < ot.length; k++) {
                    soma = soma+Math.abs(yBarra[k] - ot[k]);
                }

                if(soma > 0 ){
                    erroClassificacaoAmostra = 1;
                }

                erroEpocaTeste += erroAmostra;
                erroClassificacaoTeste += erroClassificacaoAmostra;
            }

            
            System.out.println("Epoca "+i+" | Erro = "+ erroEpoca+"  | Erro de classificação = "+erroClassificacao + "||| Erro do Teste: "+erroEpocaTeste+"  | Erro de classificação = "+erroClassificacaoTeste );

        }  

    }

    public static double[] threshold(double[] teta){
        double[] aux = new double[teta.length];
        for (int i = 0; i < teta.length; i++) {
            if(teta[i]>=0.5){
                aux[i] = 1;
                //aux[i] = 0.995;
            }else{
                aux[i] = 0;
                //aux[i] = 0.005;
            }
        }
        return aux;
    }
}



