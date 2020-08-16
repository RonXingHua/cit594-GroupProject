package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.PVJSONReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.PVCSVReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.FileValidator;
import edu.upenn.cit594.processor.MemoizationAlgorithm;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.Display;


public class Main {
	
    public static void main(String[] args) {
    	
        // Read command line arguments
        if (args.length != 5){
            System.out.println("Error: The program requires exactly 5 arguments, please check, exiting.");
            System.exit(1);
        }      
        String PVInputFileFormat = args[0];
        String PVInputFileName = args[1];
        String propertyInputFileName = args[2];
        String populationInputFileName = args[3];
        String logFileName = args[4];        
        
        
        // logging and validate input files
        Logger logger = Logger.getInstance(logFileName);
        logger.logProgramStart(PVInputFileFormat,PVInputFileName,propertyInputFileName,
        		populationInputFileName,logFileName);

        FileValidator.formatValidate(PVInputFileFormat);
        FileValidator.inputFileValidate(PVInputFileName);
        FileValidator.inputFileValidate(propertyInputFileName);
        FileValidator.inputFileValidate(populationInputFileName);
        FileValidator.logfileValidate(logFileName);
           
        
        //build instances
        Reader reader = null;		
		if(PVInputFileFormat.equals("csv")){
			reader = new PVCSVReader(PVInputFileName, logger);			
		}
		if(PVInputFileFormat.equals("json")){
			reader = new PVJSONReader(PVInputFileName, logger);			
		}			
		PropertyReader propertyReader = new PropertyReader(propertyInputFileName, logger);		
		PopulationReader populationReader = new PopulationReader(populationInputFileName, logger);		
		MemoizationAlgorithm memoization = new MemoizationAlgorithm(reader, propertyReader);		
		Processor processor = new Processor(reader, propertyReader, populationReader, logger, memoization);		
		Display display = new Display(processor, logger);
	
		//run
		display.start();
		

    }
}		
		
        // Get user input
//        Display myDisplay = new Display();
//        int userOption = myDisplay.getUserOption();
//        System.out.println("user selected option: " + userOption);


        // test reading file
        // PV
//        PVCSVReader myCSVReader = new  PVCSVReader();
//        PVJSONReader myJSONReader = new  PVJSONReader();
//        ArrayList<PV> PVs = myCSVReader.readFromFile(PVInputFileName);
//        System.out.println(PVInputFileName+ " Content:");

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
//        PopulationReader myPopulationReader = new  PopulationReader();
//        ArrayList<Population>  Populations = myPopulationReader.readFromFile(populationInputFileName);
//        for (Population po: Populations){
//            System.out.println(po.getZip()+" "+po.getZipPopulation());
//        }


        // Processing



