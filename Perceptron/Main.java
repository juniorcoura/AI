package Trab2;

public class Main {
    public static void main(String[] args) {
    

        Perceptron p = new Perceptron(5, 1, 0.001);
        DataReader data = new DataReader();
        for(int i = 0; i<100000;i++){
            double erroEpoca = 0;
            int erroClassificacao=0;

            for(int j=0;j<data.dataList.size();j++){
              
                
                double[] xBarra = data.dataList.get(j).input;
                double[] yBarra = data.dataList.get(j).output;
                double[] teta = p.treinar(xBarra, yBarra);
                double erroAmostra = 0;
                for (int k = 0; k < yBarra.length; k++) {
                    erroAmostra += Math.abs(yBarra[k]- threshold(teta[k]));
                    erroClassificacao += classification(erroAmostra);
                }
                erroEpoca += erroAmostra;

                
            }

            System.out.println("Epoca "+i+" | erro = "+ erroEpoca+"  | Erro de classificação = "+erroClassificacao);
        }


    }

    public static int classification(double classificacao){
        if(classificacao>0){
            return 1;
        }else{
            return 0;
        }
    }

    public static double threshold(double teta){

        if(teta>0.5){
            return 1;
        }else{
            return 0;
        }
        
    }
}
