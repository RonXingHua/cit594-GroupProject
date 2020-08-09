package edu.upenn.cit594.data;

import edu.upenn.cit594.datamanagement.Population;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PopulationReader <E> implements Reader {
    @Override
    public ArrayList<Population> readFromFile(File populationInputFileName) {
        ArrayList<Population> populations = new ArrayList<Population>();

        Scanner myScanner = null;
        try{
            myScanner = new Scanner(populationInputFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(myScanner != null) {
            while (myScanner.hasNextLine()) {
                String rawPopulation = myScanner.nextLine();
                if (rawPopulation.isEmpty()) {
                    continue;
                }

                // Parse one line in CSV file
                String rawPopulation_split[] = rawPopulation.split("\\s");

                // Parse all fields for Population
                int zip = parseInt(rawPopulation_split[0]);
                int zipPopulation = parseInt(rawPopulation_split[1]);


                // Population object
                Population myPopulation = new Population(zip, zipPopulation);

                // Add Population info into List
                populations.add(myPopulation);
            }
        }

        return populations;
    }
}
