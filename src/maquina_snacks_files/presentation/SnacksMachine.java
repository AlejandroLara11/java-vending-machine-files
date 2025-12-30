package maquina_snacks_files.presentation;

import maquina_snacks_files.domain.Snack;
import maquina_snacks_files.service.ServiceSnacksI;
import maquina_snacks_files.service.SnackServiceFiles;
import maquina_snacks_files.service.SnackServiceList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnacksMachine {
    public static void main(String[] args) {SnacksMachine();
    }

    public static void SnacksMachine(){

        var flag_exit = false;
        var sc = new Scanner(System.in);
        ServiceSnacksI SnacksService = new SnackServiceFiles();
        List<Snack> products = new ArrayList<>();

        System.out.println("Snack Vending Machine");
        SnacksService.showSnacks();

        while(!flag_exit){
            try{
                var option = showMenu(sc);
                flag_exit = executeOption(option, sc, products, SnacksService);
            }catch(Exception e){
                System.out.println("Unexpected error: " + e.getMessage());
            }
            finally {
                System.out.println();
            }
        }
    }

    private static int showMenu(Scanner sc){
        System.out.print("-------Main Menu-------" +
                "\n1. Buy Snacks" +
                "\n2. Show ticket" +
                "\n3. Add Snack" +
                "\n4. Exit" +
                "\nChoose an option:\s");
        return Integer.parseInt(sc.nextLine());
    }

    private static boolean executeOption(int option, Scanner sc, List<Snack> products, ServiceSnacksI SnacksService){

        var exit = false;
        switch (option){
            case 1: buySnack(sc, products, SnacksService);
            break;
            case 2: showTicket(products);
            break;
            case 3: addSnack(sc, SnacksService);
            break;
            case 4:
                System.out.println("Thank you for using this application... ");
                exit = true;
            break;
            default:
                System.out.println("Invalid Option " +  option);
        }
        return exit;
    }

    private static void buySnack(Scanner sc, List<Snack> productos,  ServiceSnacksI SnacksService){
        System.out.print("Select an snack: (ID): ");
        int idSnack = Integer.parseInt(sc.nextLine());
        var snackFounded = false;
        for (var snack : SnacksService.getSnacks()){
            if(idSnack ==  snack.getIdSnack()) {
                productos.add(snack);
                snackFounded = true;
                System.out.println("Snack added!" + snack);
                break;
            }
        }
        if(!snackFounded){
            System.out.println("Snack not found!" + idSnack);
        }
    }

    private static void showTicket(List<Snack> products){
        var total = 0;
        var ticket = "\t***Ticket***";
        for(Snack snack : products){
            total += snack.getPrice();
            ticket += "\n -" + snack.getName() + "  $" + snack.getPrice();
        }
        System.out.print(ticket + "\n\tTotal: $" + total);
    }

    private static void addSnack(Scanner sc, ServiceSnacksI SnacksService){
        System.out.println("Enter Snack name: ");
        String name = sc.nextLine();
        System.out.println("Enter Snack price: ");
        var precio = Double.parseDouble(sc.nextLine());
        SnacksService.addSnack(new Snack(name, precio));
        System.out.println("Snack added!");
        SnacksService.showSnacks();
    }

}

