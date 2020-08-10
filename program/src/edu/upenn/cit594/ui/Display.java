package edu.upenn.cit594.ui;

import edu.upenn.cit594.logging.Logger;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Display {
    public int getUserOption(){
        System.out.println("Kindly select an option (0 to 6) to proceed:");
        System.out.println("[0]: Exit");
        System.out.println("[1]: Total population for all ZIP Codes");
        System.out.println("[2]: Total parking fines per capita for each ZIP Code");
        System.out.println("[3]: Average market value for residences in a specified ZIP Code");
        System.out.println("[4]: Average total liable area for residences in a specified ZIP Code");
        System.out.println("[5]: Total residential market value per capita for a specified ZIP Code");
        System.out.println("[6]: Total residential market value per capita for area which has the most parking violations for METER EXPIRED ");
        System.out.print("Your have chosen: ");

        Scanner sc = new Scanner(System.in);
        int option = parseInt(sc.nextLine());

        // logging
        Logger logger = Logger.getInstance();
        logger.logUserOption(option);

        // input check
        if(option != 0 && option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6){
            System.out.println("Error: The input must be an integer between 0 and 6 inclusive. Please check, exiting.");
            System.exit(1);
        }

        return option;

    }

    public int getUserZip(){
        System.out.println("Kindly input a ZIP code : ");

        Scanner sc = new Scanner(System.in);
        int zip = parseInt(sc.nextLine());

        // logging
        Logger logger = Logger.getInstance();
        logger.logUserZip(zip);

        return zip;
    }

    public void displayInfo(String displayingContent){
        System.out.println(displayingContent);
    }
}
