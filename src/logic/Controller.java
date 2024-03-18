package logic;

import com.google.gson.reflect.TypeToken;
import model.Worker;
import persistence.Archivo;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;


public class Controller {

    private ArrayList<Worker> result;
    public Controller(){
        result = new ArrayList<>();
    }

    public ArrayList<Worker> getResult() {
        return result;
    }



    public Worker addWorker(Worker worker){
        Gson gson = new Gson();

            loadWorker();
            result.add(worker);
            try (PrintWriter out = new PrintWriter(new FileWriter("./Resources/archive.json"))) {
                String jsonString = "[";
                int count = 1;
                for(Worker w:result){
                    if(count == result.size()){
                        jsonString += gson.toJson(w);
                    }else{
                        jsonString += gson.toJson(w)+",";
                    }
                    count++;
                }
                jsonString +="]";
                out.write(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }


        return worker;
    }
    public void loadWorker(){
        Gson gson = new Gson();
        String content = readFile();
        Type type = new TypeToken<List<Worker>>(){}.getType();
        result = gson.fromJson(content,type);
    }

    public void createTextPlain(){
        Archivo a = new Archivo();
        System.out.println(result.size());
        for(Worker w:result){
            System.out.println(w.toString());
            a.AgregarContenidoArchivo(w.getName()+","+w.getCode()+","+w.getlastname()+","+w.getSalary()+","+w.getJob().getName()+","+w.getJob().gethourValue());
            System.out.println("añadido");

        }

    }
    public String readFile(){
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(this.getClass().getResourceAsStream("/Resources/archive.json"), Charset.defaultCharset()));{
                String line = null;
                StringBuilder output = new StringBuilder();
                while((line = input.readLine()) != null){
                    output.append(line);
                }
                return output.toString();
            }
        }catch (IOException e){
            e.getMessage();
        }
        return null;
    }
}
