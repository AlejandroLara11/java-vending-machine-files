package snack_machine_files.domain;

import java.io.Serializable;
import java.util.Objects;

//implements serializable para hacerlo javabean
public class Snack implements Serializable {
    static int contadorSnacks = 0;
    private int idSnack;
    private String name;
    private double price;


    public Snack(){
        this.idSnack = ++contadorSnacks;
    }

    public Snack(String name, double price){
        this();
        this.name = name;
        this.price = price;
    }

    public static int getContadorSnacks() {
        return contadorSnacks;
    }

    public int getIdSnack() {
        return idSnack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Snack: "+ "ID = " + idSnack + ", name = " +  name + ", price = " + price;
    }

    public String writeSnack(){
        return idSnack + "," + name + "," + price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && price == snack.price && Objects.equals(name, snack.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, name, price);
    }
}