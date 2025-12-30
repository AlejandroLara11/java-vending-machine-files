package maquina_snacks_files.service;

import maquina_snacks_files.domain.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SnackServiceFiles implements ServiceSnacksI{

    private final String FILE_NAME = "Snacks.txt";
    private final List<Snack> snacks = new ArrayList<>();

    public SnackServiceFiles() {
        //check if archivo exists
        var archivo = new File(FILE_NAME);
        var exists = false;
        try {
            exists = archivo.exists();
            if (exists) {
                //this.snacks = obtainSnacks();
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
            output.println(snack);
            output.close();
        }
        catch (Exception e){
            System.out.println("Unexpected error at add snack: "  + e.getMessage());
        }
    }

    @Override
    public void showSnacks() {

    }

    @Override
    public List<Snack> getSnacks() {
        return List.of();
    }
}
