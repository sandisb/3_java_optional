package com.company;

import java.io.*;
import java.util.*;

import static java.lang.System.*;

class Main {

    //List of possible choices for the user
    public static void list_choices() {
        out.println("1 - add an item");
        out.println("2 - list items");
        out.println("3 - delete an item");
        out.println("4 - save list to file");
        out.println("5 - load list from file");
        out.println("6 - exit");
        out.println("");
        out.print("Enter your choice: ");

    }

    //Function to add new item to the shopping list
    public static void add_an_item(List<String> shopping_list, Scanner scan){
        out.println("");
        out.println("Current list contains " + shopping_list);
        out.println("");
        out.print("Enter item to add: ");
        String added_item = (scan.next());
        shopping_list.add(added_item);
        out.print(added_item + " has been added. " + "Add more? (yes/no): ");
        while (scan.next().startsWith("y")) {
            out.print("Enter item to add: ");
            added_item = (scan.next());
            shopping_list.add(added_item);
            out.print(added_item + " has been added. " + "Add more? (yes/no): ");
        }
    }

    //Function to list existing items in the shopping list
    public static void list_items(List<String> shopping_list){
        out.println("");
        out.println("Current list contains " + shopping_list);
        out.println("");
    }

    //Function to delete an item from the shopping list
    public static void delete_an_item(List<String> shopping_list, Scanner scan){
        out.println("");
        out.println("Current list contains " + shopping_list);
        out.println("");
        out.print("Enter item to remove: ");
        String deleted_item = (scan.next());
        if (shopping_list.contains(deleted_item)) {
            shopping_list.remove(deleted_item);
            out.print(deleted_item + " has been removed. " + "Remove more? (yes/no): ");
        } else {
                out.print(deleted_item + " is not in the list. " + "Do you want to continue to remove? (yes/no): ");
        }
        while (scan.next().startsWith("y")) {
            out.print("Enter item to remove: ");
            deleted_item = (scan.next());
            if (shopping_list.contains(deleted_item)) {
                shopping_list.remove(deleted_item);
                out.print(deleted_item + " has been removed. " + "Remove more? (yes/no): ");
            } else {
                out.print(deleted_item + " is not in the list. " + "Do you want to continue to remove? (yes/no): ");
            }
        }
    }

    //Function to save the shopping list into text file
    public static void save_to_file(List<String> shopping_list,String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < shopping_list.size(); i++) {
            writer.write(shopping_list.get(i) +'\n');
        }
        writer.close();
    }

    //Function to load shopping list from the file
    public static void load_from_file(List<String> shopping_list ,String fileName) throws IOException {
        shopping_list.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                shopping_list.add(line);
                reader.close();
                out.println("");
                out.println("Current list contains " + shopping_list);
                out.println("");
            }
        } catch (FileNotFoundException e) {
            out.println("");
            System.out.println("File does not exist! Please create '" + fileName + "' and try again!");
            out.println("");
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> shopping_list = new ArrayList<String>();
        Scanner scan = new Scanner(in);

        out.println("This is a shopping list application. Available choices:");
        list_choices();

        //Informing user that int needs to be entered
        while (!scan.hasNextInt()) {
            out.println("Please enter a number!");
            scan.nextLine();
        }

        int choice = scan.nextInt();
        while (choice != 6) {
            switch (choice) {
                case 1:
                    add_an_item(shopping_list, scan);
                    break;
                case 2:
                    list_items(shopping_list);
                    break;
                case 3:
                    delete_an_item(shopping_list, scan);
                    break;
                case 4:
                    save_to_file(shopping_list, "shopping_list.txt");
                    break;
                case 5:
                    load_from_file(shopping_list, "shopping_list.txt");
                    break;
                default:
                    out.println("Wrong choice, here are available choices ");
            }
            list_choices();
            choice = scan.nextInt();
        }
    }
}