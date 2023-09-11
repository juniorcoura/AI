
public class Main {
    public static void main(String[] args) {
        DataReader data = new DataReader();

        
        MLP p = new MLP(5, 1, 2,  0.001);
        for (int i = 0; i < 100000; i++) {
            double erroEpoca = 0;
            double erroClassificacao=0;
            for (int j = 0; j < data.dataList.size(); j++) {

                double[] xBarra = data.dataList.get(j).input;
                double[] yBarra = data.dataList.get(j).output;
                double[] teta = p.treinar(xBarra, yBarra);
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

            System.out.println("Epoca "+i+" | Erro = "+ erroEpoca+"  | Erro de classificação = "+erroClassificacao);

        }  

    }

    public static double[] threshold(double[] teta){
        double[] aux = new double[teta.length];
        for (int i = 0; i < teta.length; i++) {
            if(teta[i]>=0.5){
                aux[i] = 1;
            }else{
                aux[i] = 0;
            }
        }
        return aux;
    }
}



