package edu.upenn.cit594.data;

import edu.upenn.cit594.datamanagement.Population;
import edu.upenn.cit594.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PopulationReader <E> implements Reader {
    @Override
    public ArrayList<Population> readFromFile(String populationInputFileName) {
        Logger logger = Logger.getInstance();
        ArrayList<Population> populations = new ArrayList<Population>();
        File populationInputFile = new File(populationInputFileName);

        Scanner myScanner = null;
        try{
            myScanner = new Scanner(populationInputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //logging
        logger.logFileOpen(populationInputFileName);

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
