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

    public void setResult(ArrayList<Worker> result) {
        this.result = result;
    }

    public Worker addWorker(Worker worker){
        loadWorker();
        System.out.println("aaaaa");
        if(result.size()==0){
            System.out.println("aaaaa");
            try (PrintWriter out = new PrintWriter(new FileWriter("./resources/archive.json"))) {
                String jsonString = "[\n";
                System.out.println(result.size());
                for(Worker w:result){
                    jsonString += w.toString();
                }
                jsonString+= worker.toString()+"\n]";

                out.write(jsonString);
                System.out.println(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try (PrintWriter out = new PrintWriter(new FileWriter("./resources/archive.json"))) {
                String jsonString = "[\n";
                jsonString+= worker.toString()+"\n]";

                out.write(jsonString);
                System.out.println(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return worker;
    }
    public List<Worker> loadWorker(){
        Gson gson = new Gson();


        String content = readFile();
        Type type = new TypeToken<List<Worker>>(){}.getType();
        result = gson.fromJson(content,type);

        return result;
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
                    new InputStreamReader(this.getClass().getResourceAsStream("./resources/archive.json"), Charset.defaultCharset()));{
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
