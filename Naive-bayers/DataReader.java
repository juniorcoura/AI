
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class DataReader{
    
    List<Data> dataList;
    List<Data> trainingData;
    List<Data> testData;

    public DataReader(){
        this.dataList = new ArrayList<>();
        this.trainingData = new ArrayList<>();
        this.testData = new ArrayList<>();
        readData();
        criarClasses();
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
                output[0] = dataValue[5];

                Data data = new Data(input,output);
                dataList.add(data);
            }
            
            br.close();
        }catch(IOException e){
            System.out.println(e);
        }
    
    }

    public void criarClasses(){

        List<Data> classeUm = new ArrayList();
        List<Data> classeDois = new ArrayList();

        for (int i = 0; i < dataList.size(); i++){
            if(this.dataList.get(i).output[0] == 0){
                classeUm.add(this.dataList.get(i));
            }else{
                classeDois.add(this.dataList.get(i));
            }
        }
        
        Collections.shuffle(classeUm);
        Collections.shuffle(classeDois);

        for (int i = 0; i < classeUm.size(); i++) {
            int aux = (classeUm.size()*70)/100;
            if(i<aux){
                this.trainingData.add(classeUm.get(i));
            }else{
                this.testData.add(classeUm.get(i));
            }
        }
 
        for (int i = 0; i < classeDois.size(); i++) {
            int aux = (classeDois.size()*70)/100;
            if(i<aux){
                this.trainingData.add(classeDois.get(i));
            }else{
                this.testData.add(classeDois.get(i));
            }
        }

    }   
}
