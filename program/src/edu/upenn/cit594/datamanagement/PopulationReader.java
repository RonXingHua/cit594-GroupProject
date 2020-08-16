package edu.upenn.cit594.datamanagement;


import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PopulationReader {
	
	protected String filename;
	protected Logger logger;
	
	public PopulationReader(String filename, Logger logger) {
		this.filename = filename;
		this.logger = logger;
	}
	
	
    public List<Population> getAllPopulation() {
    	
        List<Population> populations = new ArrayList<Population>();
        File populationInputFile = new File(filename);

        Scanner myScanner = null;
        try{
            myScanner = new Scanner(populationInputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //logging
        logger.logFileOpen(filename);

        if(myScanner != null) {
            while (myScanner.hasNextLine()) {
                String rawPopulation = myScanner.nextLine();
                if (rawPopulation.isEmpty()) {
                    continue;
                }

                // Parse one line in CSV file
                String rawPopulation_split[] = rawPopulation.split("\\s");

                // Parse all fields for Population
                Integer zip = parseInt(rawPopulation_split[0]);
                Integer zipPopulation = parseInt(rawPopulation_split[1]);


                // Population object
                Population myPopulation = new Population(zip, zipPopulation);

                // Add Population info into List
                populations.add(myPopulation);
            }
        }

        return populations;
    }
}
