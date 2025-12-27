package maquina_snacks_archivos.presentation;

import maquina_snacks_archivos.domain.Snack;
import maquina_snacks_archivos.service.Snacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks(){

        var bandera_salida = false;
        var sc = new Scanner(System.in);
        List<Snack> productos = new ArrayList<>();

        System.out.println("Snack Vending Machine");
        Snacks.mostrarSnacks();

        while(!bandera_salida){
            try{
                var opcion = mostrarMenu(sc);
                bandera_salida = ejecutarOpcion(opcion, sc, productos);
            }catch(Exception e){
                System.out.println("Unexpected error: " + e.getMessage());
            }
            finally {
                System.out.println();
            }
        }
    }

    private static int mostrarMenu(Scanner sc){
        System.out.print("-------Main Menu-------" +
                "\n1. Buy Snacks" +
                "\n2. Show ticket" +
                "\n3. Add Snack" +
                "\n4. Exit" +
                "\nChoose an option:\s");
        return Integer.parseInt(sc.nextLine());
    }

    private static boolean ejecutarOpcion(int opcion, Scanner sc, List<Snack> productos){

        var salir = false;
        switch (opcion){
            case 1: comprarSnack(sc, productos);
            break;
            case 2: mostrarTicket(productos);
            break;
            case 3: agregarSnack(sc);
            break;
            case 4:
                System.out.println("Thank you for using this application... ");
                salir = true;
            break;
            default:
                System.out.println("Invalid Option " +  opcion);
        }
        return salir;
    }

    private static void comprarSnack(Scanner sc, List<Snack> productos){
        System.out.print("Select an snack: (ID): ");
        int idSnack = Integer.parseInt(sc.nextLine());
        var snackEncontrado = false;
        for (var snack : Snacks.getSnacks()){
            if(idSnack ==  snack.getIdSnack()) {
                productos.add(snack);
                snackEncontrado = true;
                System.out.println("Snack added!" + snack);
                break;
            }
        }
        if(!snackEncontrado){
            System.out.println("Snack not found!" + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos){
        var total = 0;
        var ticket = "\t***Ticket***";
        for(Snack snack : productos){
            total += snack.getPrice();
            ticket += "\n -" + snack.getName() + "  $" + snack.getPrice();
        }
        System.out.print(ticket + "\n\tTotal: $" + total);
    }

    private static void agregarSnack(Scanner sc){
        System.out.println("Enter Snack name: ");
        String name = sc.nextLine();
        System.out.println("Enter Snack price: ");
        var precio = Double.parseDouble(sc.nextLine());
        Snacks.agregarSnack(new Snack(name, precio));
        System.out.println("Snack added!");
        Snacks.mostrarSnacks();
    }

}

