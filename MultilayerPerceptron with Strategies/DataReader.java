
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class DataReader{
    
    List<Data> dataList;
    List<Data> normalizedData;
    List<Data> trainingData;
    List<Data> testData;
    int normalization = 0;

    public DataReader(){
        this.dataList = new ArrayList<>();
        this.normalizedData = new ArrayList<>();
        this.trainingData = new ArrayList<>();
        this.testData = new ArrayList<>();
        readData();
        normalization();
        criarBases();
    }

    public void readData(){

        File sW = new File("mammographic_masses_data.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(sW))){
            String line;
           
            while((line = br.readLine())!= null){

                double[] input = new double[5];
                double[] output = new double[1];

                String[] aSplit = line.split(",");
                int[] dataValue = new int[aSplit.length];
                for (int i = 0; i < aSplit.length; i++) {
                    if(aSplit[i].equals("?")) aSplit[i] = "0";
                    dataValue[i] = Integer.parseInt(aSplit[i]);
                }
                for(int i = 0; i<dataValue.length-1;i++){
                    input[i] = dataValue[i];
                }

                if(dataValue[5] == 0){
                    output[0] = 0;
                    //output[0] = 0.005;
                }else if(dataValue[5] == 1){
                    //output[0] = 0.995;
                    output[0] = 1;
                }

                Data data = new Data(input,output);
                dataList.add(data);
            }
            
            br.close();
        }catch(IOException e){
            System.out.println(e);
        }
    
    }

    public void normalization(){

        double[] minInput = new double[this.dataList.get(0).input.length];
        double[] maxInput = new double[this.dataList.get(0).input.length];
    
        for(int i=0; i<minInput.length; i++){
            minInput[i] = this.dataList.get(0).input[i];
            maxInput[i] = this.dataList.get(0).input[i];
        }

        for(int i=1; i<this.dataList.size(); i++){
            for (int j = 0; j < maxInput.length; j++) {
                if(this.dataList.get(i).input[j] > maxInput[j]) maxInput[j] = this.dataList.get(i).input[j];
                
                if(this.dataList.get(i).input[j] < minInput[j]) minInput[j] = this.dataList.get(i).input[j];
            }
        }

        for(int i=0; i<this.dataList.size(); i++){
            double[] newInput = new double[5];
            for (int j = 0; j < maxInput.length; j++) {
                newInput[j] = (this.dataList.get(i).input[j] - minInput[j]) / (maxInput[j] - minInput[j]); 
            }
            Data newData = new Data(newInput, this.dataList.get(i).output);
            normalizedData.add(newData);
        }

        if(normalization==0){
            normalizedData = dataList;
        }

    }

    public void criarBases(){

        List<Data> subBaseA = new ArrayList();
        List<Data> subBaseB = new ArrayList();
        List<Data> subBaseC = new ArrayList();

        for (int i = 0; i < dataList.size(); i++) {
            if(i < ((normalizedData.size()*30)/100)){
                subBaseA.add(normalizedData.get(i));
            }else if(i>=((normalizedData.size()*30)/100) && i<(((normalizedData.size()*30)/100)*2)){
                subBaseB.add(normalizedData.get(i));
            }else{
                subBaseC.add(normalizedData.get(i));
            }
        }
        
        Collections.shuffle(subBaseA);
        Collections.shuffle(subBaseB);
        Collections.shuffle(subBaseC);

        for (int i = 0; i < subBaseA.size(); i++) {
            int aux = (subBaseA.size()*70)/100;
            if(i<aux){
                this.trainingData.add(subBaseA.get(i));
            }else{
                this.testData.add(subBaseA.get(i));
            }
        }

        for (int i = 0; i < subBaseB.size(); i++) {
            if(i<(subBaseB.size()*70)/100 ){
                this.trainingData.add(subBaseB.get(i));
            }else{
                this.testData.add(subBaseB.get(i));
            }
        }

        for (int i = 0; i < subBaseC.size(); i++) {
            if(i<(subBaseC.size()*70)/100 ){
                this.trainingData.add(subBaseC.get(i));
            }else{
                this.testData.add(subBaseC.get(i));
            }
        }
    }   
}
