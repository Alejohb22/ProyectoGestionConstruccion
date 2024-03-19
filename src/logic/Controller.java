package logic;

import com.google.gson.reflect.TypeToken;
import model.Job;
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
    private ArrayList<Job> resultJob;
    public Controller(){
        result = new ArrayList<>();
        resultJob = new ArrayList<>();
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

    public Job addJob(Job job){
        Gson gson = new Gson();

        loadWorker();
        resultJob.add(job);
        try (PrintWriter out = new PrintWriter(new FileWriter("./Resources/roles.json"))) {
            String jsonString = "[";
            int count = 1;
            for(Job w:resultJob){
                if(count == resultJob.size()){
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


        return job;
    }
    public void loadWorker(){
        Gson gson = new Gson();
        String content = readFile();
        Type type = new TypeToken<List<Worker>>(){}.getType();
        result = gson.fromJson(content,type);
    }

    public void loadRol(){
        Gson gson = new Gson();
        String content = readFileRol();
        Type type = new TypeToken<List<Job>>(){}.getType();
        resultJob = gson.fromJson(content,type);
    }

    public ArrayList<Job> getResultJob() {
        return resultJob;
    }

    public void createTextPlain(){
        Archivo a = new Archivo();
        System.out.println(result.size());
        for(Worker w:result){
            System.out.println(w.toString());
            a.AgregarContenidoArchivo(w.getName()+","+w.getCode()+","+w.getLastName()+","+w.getSalary()+","+w.getJob().getName()+","+w.getJob().gethourValue());
            System.out.println("añadido");

        }

    }
    public String readFile(){
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(this.getClass().getResourceAsStream("/archive.json"), Charset.defaultCharset()));{
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
    public String readFileRol(){
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(this.getClass().getResourceAsStream("/roles.json"), Charset.defaultCharset()));{
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