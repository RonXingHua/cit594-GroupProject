package edu.upenn.cit594;


import edu.upenn.cit594.data.PVCSVReader;
import edu.upenn.cit594.data.PVJSONReader;
import edu.upenn.cit594.data.PopulationReader;
import edu.upenn.cit594.data.PropertyReader;
import edu.upenn.cit594.datamanagement.PV;
import edu.upenn.cit594.datamanagement.Population;
import edu.upenn.cit594.datamanagement.Property;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.FileValidator;
import edu.upenn.cit594.ui.Display;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Read commandline arguments
        if (args.length != 5){
            System.out.println("Error: The program requires exactly 5 arguments, please check, exiting.");
            System.exit(1);
        }

        // Parse the runtime arguments
        String PVInputFileFormat = args[0].toLowerCase();
        String PVInputFileName = args[1];
        String propertyInputFileName = args[2];
        String populationInputFileName = args[3];
        String logFileName = args[4];

        // Validate input files
        FileValidator.formatValidate(PVInputFileFormat);
        FileValidator.inputFileValidate(PVInputFileName);
        FileValidator.inputFileValidate(propertyInputFileName);
        FileValidator.inputFileValidate(populationInputFileName);
        FileValidator.logfileValidate(logFileName);

        // logging
        Logger logger = Logger.getInstance(logFileName);
        logger.logProgramStart(PVInputFileFormat,PVInputFileName,propertyInputFileName,populationInputFileName,logFileName);

        // Get user input
//        Display myDisplay = new Display();
//        int userOption = myDisplay.getUserInput();
//        System.out.println("user selected option: " + userOption);


        // test reading file
        // PV
//        PVCSVReader myCSVReader = new  PVCSVReader();
//        PVJSONReader myJSONReader = new  PVJSONReader();
//        ArrayList<PV> PVs = myCSVReader.readFromFile(PVInputFileName);
//        System.out.println(PVInputFileName+ " Content:");
//
//        for (PV pv: PVs){
//            System.out.println(pv.getTimestamp()+" "+pv.getFine()+" "+pv.getDescription()+" " +pv.getAnonymousID()+" "+pv.getState()+" "+pv.getUniqueID()+" "+pv.getZip());
//        }

        // Property
//        PropertyReader myPropertyReader = new  PropertyReader();
//        ArrayList<Property> Properties = myPropertyReader.readFromFile(propertyInputFileName);
//        System.out.println(propertyInputFileName+ " Content:");
//
//        for (Property pp: Properties){
//            System.out.println(pp.getMarketValue()+" "+pp.getTotLiableArea()+" "+pp.getZip());
//        }

        // Population
        PopulationReader myPopulationReader = new  PopulationReader();
        ArrayList<Population>  Populations = myPopulationReader.readFromFile(populationInputFileName);
        for (Population po: Populations){
            System.out.println(po.getZip()+" "+po.getZipPopulation());
        }



        // Processing



    }
}
