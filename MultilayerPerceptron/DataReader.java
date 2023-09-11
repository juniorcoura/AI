
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataReader{
    
    List<Data> dataList;

    public DataReader(){
        this.dataList = new ArrayList<>();
        readData();
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
}
