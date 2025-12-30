package maquina_snacks_files.service;

import maquina_snacks_files.domain.Snack;

import java.util.ArrayList;
import java.util.List;

public class SnackServiceList implements ServiceSnacksI{
    public static final List<Snack> snacks;

    //bloque estatico inicializador
    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("chips", 20));
        snacks.add(new Snack("soda", 25));
        snacks.add(new Snack("sandwich", 40));
    }

    public void addSnack(Snack snack){
        snacks.add(snack);
    }

    public void showSnacks(){
        System.out.println("AVAILABLE SNACKS: ");
        for (Snack elemento : snacks) {
            System.out.println(elemento);
        }
    }

    public List<Snack> getSnacks(){
        return snacks;
    }

}
