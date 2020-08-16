package edu.upenn.cit594.ui;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Display {
	
	protected Processor processor;
	protected Logger logger;
	protected Scanner in;
	
	public Display(Processor processor, Logger logger) {
		this.processor = processor;
		this.logger = logger;
	}
	
	
	public void start() {	
		
		System.out.println("Kindly select an option (0 to 6) to proceed:");
        System.out.println("[0]: Exit");
        System.out.println("[1]: Total population for all ZIP Codes");
        System.out.println("[2]: Total parking fines per capita for each ZIP Code");
        System.out.println("[3]: Average market value for residences in a specified ZIP Code");
        System.out.println("[4]: Average total liable area for residences in a specified ZIP Code");
        System.out.println("[5]: Total residential market value per capita for a specified ZIP Code");
        System.out.println("[6]: Total residential market value per capita for area which has the most "
        		+ "parking violations for METER EXPIRED ");
        System.out.print("You have chosen: ");
		

        in = new Scanner(System.in);       
        if(in.hasNextInt()) {       //if input is an integer
        	int option = in.nextInt();
    		logger.logUserOption(option);  // logging
    		 		
    		if(option != 0 && option != 1 && option != 2 && option != 3 && option != 4 
    				&& option != 5 && option != 6) {    //if input is not a valid integer
                System.out.println("Error: The input must be an integer between 0 and 6 inclusive. "
                		+ "Please check, exiting.");
                in.close();
                System.exit(1);
            }
    		
    		if(option == 0) System.exit(0);
    		else if(option == 1) doTtlPopulation();
    		else if(option == 2) doTtlFinesPerCapitaForZip();
    		else if(option == 3) doAvgMarketValueForZip();
    		else if(option == 4) doAvgLivableAreaForZip();
    		else if(option == 5) doTtlMarketValuePerCapitaForZip();
    		else if(option == 6) doTtlMarketValuePerCapitaForMostMeterExpZip();	
    		in.close();
        }
        else {   //if input in not an integer
        	System.out.println("Error: The input must be an integer between 0 and 6 inclusive. "
            		+ "Please check, exiting.");
        	in.close();
        	System.exit(1);
        }
	}

	
	//helper method to ask user input a zip code for #3,4,5 
	protected Integer getUserZip(){
        System.out.println("Kindly input a ZIP code : ");
        
        in = new Scanner(System.in);  //should not close in for this method, otherwise will throw exception
        Integer zip = in.nextInt();  
        logger.logUserZip(zip); //logging
        
        return zip;    
    }

	
	//#1
	protected void doTtlPopulation() {  
		Integer ttlPopulation = processor.getTtlPopulation();
		System.out.println(ttlPopulation.toString());
		
		start();
	}
	
	
	//#2
	protected void doTtlFinesPerCapitaForZip() {   
		Map<Integer, String> ttlFinesPerCapitaForZip = processor.getTtlFinesPerCapitaForZip();	
		List<Integer> zips = new ArrayList<>();
		
		//generate ArrayList of sorted zip
		for (HashMap.Entry<Integer, String> entry: ttlFinesPerCapitaForZip.entrySet()) {
			Integer zip = entry.getKey();
			zips.add(zip);		
		}
		Collections.sort(zips);
		
		//print out by sorted order (keys have sorted above)
		for(Integer zip: zips) {
			String value = ttlFinesPerCapitaForZip.get(zip);
			System.out.println(zip.toString() + " " + value);
		}
		
		start();
	}
	
	
	//#3
	protected void doAvgMarketValueForZip() {
		Integer zip = getUserZip();  //ask user to input a zip
		Integer result = processor.getAvgMarketValueForZip(zip);
		System.out.println(result.toString());
	    
		start();
	}
	
	//#4
	protected void doAvgLivableAreaForZip() {
		Integer zip = getUserZip();
		Integer result = processor.getAvgLivableAreaForZip(zip);
		System.out.println(result.toString());
		
		start();
	}
	
	//#5
	protected void doTtlMarketValuePerCapitaForZip() {
		Integer zip = getUserZip();
		Integer result = processor.getTtlMarketValuePerCapitaForZip(zip);
	    System.out.println(result.toString());
	    
		start();
	}
	
	//#6
	protected void doTtlMarketValuePerCapitaForMostMeterExpZip() {
		Integer result = processor.getTtlMarketValuePerCapitaForMostMeterExpZip();
		System.out.println(result.toString());
	
	    start();
	}

}







