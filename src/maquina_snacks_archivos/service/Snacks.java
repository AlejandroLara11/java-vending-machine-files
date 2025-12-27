package maquina_snacks_archivos.service;

import maquina_snacks_archivos.domain.Snack;

import java.util.ArrayList;
import java.util.List;

public class Snacks {
    public static final List<Snack> snacks;

    //bloque estatico inicializador
    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("chips", 20));
        snacks.add(new Snack("soda", 25));
        snacks.add(new Snack("sandwich", 40));
    }

    public static void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public static void mostrarSnacks(){
        System.out.println("AVAILABLE SNACKS: ");
        for (Snack elemento : snacks) {
            System.out.println(elemento);
        }
    }

    public static List<Snack> getSnacks(){
        return snacks;
    }

}
