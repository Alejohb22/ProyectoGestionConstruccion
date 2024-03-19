package logic;

import com.google.gson.reflect.TypeToken;
import model.Job;
import model.Material;
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
    private ArrayList<Material> resultMaterial;
    public Controller(){
        result = new ArrayList<>();
        resultJob = new ArrayList<>();
        resultMaterial = new ArrayList<>();
    }

    public ArrayList<Material> getResultMaterial() {
        return resultMaterial;
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
    public Material addMaterial(Material material){
        Gson gson = new Gson();

        loadMaterial();
        resultMaterial.add(material);
        try (PrintWriter out = new PrintWriter(new FileWriter("./Resources/materials.json"))) {
            String jsonString = "[";
            int count = 1;
            for(Material w:resultMaterial){
                if(count == resultMaterial.size()){
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


        return material;
    }

    public boolean deleteMaterial(String materialId) {
        loadMaterial(); // Cargar los materiales existentes
        Material materialToRemove = null;
        // Buscar el material por su ID
        for (Material material : resultMaterial) {
            if (material.getId().equals(materialId)) {
                materialToRemove = material; // Encontrar el material a eliminar
                break;
            }
        }
        if (materialToRemove != null) {
            resultMaterial.remove(materialToRemove); // Eliminar el material
            saveMaterial(); // Guardar los cambios
            return true; // Indicar que el material se eliminó correctamente
        } else {
            return false; // Indicar que el material no se encontró o no se pudo eliminar
        }
    }

    private void saveMaterial() {
        Gson gson = new Gson();
        try (PrintWriter out = new PrintWriter(new FileWriter("./Resources/materials.json"))) {
            String jsonString = gson.toJson(resultMaterial);
            out.write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMaterial(){
        Gson gson = new Gson();
        String content = readFileMaterial();
        Type type = new TypeToken<List<Material>>(){}.getType();
        resultMaterial = gson.fromJson(content,type);
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
    public String readFileMaterial(){
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(this.getClass().getResourceAsStream("/materials.json"), Charset.defaultCharset()));{
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

    public double calcularSalarioTrabajador(String idTrabajador, int numHorasTrabajadas) {
        loadWorker(); // Cargar datos de los trabajadores
        int pos = new Utilidades().buscarSecuencial(result, idTrabajador); // Buscar el trabajador por su ID
        if (pos != -1) {
            // Calcular el salario del trabajador en base a las horas trabajadas
            return result.get(pos).CalcularSalary(numHorasTrabajadas);
        } else {
            // El ID del trabajador no está registrado
            return -1; // Otra forma de manejar esto sería lanzar una excepción para indicar que el trabajador no existe
        }
    }

}