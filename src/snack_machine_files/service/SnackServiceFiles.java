package snack_machine_files.service;

import snack_machine_files.domain.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SnackServiceFiles implements ServiceSnacksI{

    private final String FILE_NAME = "Snacks.txt";
    private List<Snack> snacks = new ArrayList<>();

    public SnackServiceFiles() {
        //check if archivo exists
        var archivo = new File(FILE_NAME);
        var exists = false;
        try {
            exists = archivo.exists();
            if (exists) {
                this.snacks = obtainSnacks();
            }else{
                var output = new PrintWriter(new FileWriter(FILE_NAME));
                output.close();
                System.out.println("File " + FILE_NAME + " created");
            }
        }catch (Exception e){
            System.out.println("Unexpected error at creating file: "  + e.getMessage());
        }
        //if file not exists, we need create the file and add some products
        if(!exists){
            addInitialSnacks();
        }
    }

    private void addInitialSnacks(){
        this.addSnack(new Snack("chips", 40));
        this.addSnack(new Snack("soda", 30));
        this.addSnack(new Snack("sandwich", 90));
    }

    @Override
    public void addSnack(Snack snack) {
        //1. add the snack to list
        this.snacks.add(snack);
        //2. add the snack to file
        this.addSnackToFile(snack);
    }

    private void addSnackToFile(Snack snack){
        boolean anexar =  false;
        var archivo =  new File(FILE_NAME);

        try {
            anexar = archivo.exists();
            var output =  new PrintWriter(new FileWriter(FILE_NAME, anexar));
            output.println(snack.writeSnack());
            output.close();
        }
        catch (Exception e){
            System.out.println("Unexpected error at add snack: "  + e.getMessage());
        }
    }

    private List<Snack> obtainSnacks(){
        var snacks = new ArrayList<Snack>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(FILE_NAME));
            for(String linea : lineas){
                String[] lineaSplited = linea.split(",");
                var id_snack = lineaSplited[0];
                var name_snack = lineaSplited[1];
                var price_snack = lineaSplited[2];
                var snack = new Snack(name_snack, Double.parseDouble(price_snack));
                snacks.add(snack);
            }
        }catch (Exception e){
            System.out.println("Unexpected error at obtain snacks: "  + e.getMessage());
        }
        return snacks;
    }

    @Override
    public void showSnacks() {
        System.out.println("-----Snack Inventory-----");
        var inventory = "";
        for(var snack : this.snacks){
            inventory += snack + "\n";
        }
        System.out.println(inventory);
    }

    @Override
    public List<Snack> getSnacks() {
        return List.of();
    }
}
