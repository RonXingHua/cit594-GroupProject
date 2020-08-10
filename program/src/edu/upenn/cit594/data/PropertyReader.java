package edu.upenn.cit594.data;


import edu.upenn.cit594.datamanagement.Property;
import edu.upenn.cit594.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class PropertyReader <E> implements Reader {
    @Override
    public ArrayList<Property> readFromFile(String propertyInputFileName) {
        Logger logger = Logger.getInstance();
        ArrayList<Property> properties = new ArrayList<Property>();
        File propertyInputFile = new File(propertyInputFileName);

        Scanner myScanner = null;
        try{
            myScanner = new Scanner(propertyInputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //logging
        logger.logFileOpen(propertyInputFileName);

        if(myScanner != null) {
            // Determine fields in the first row
            String rawFields = myScanner.nextLine();
            if (rawFields.isEmpty()) {
                System.exit(1);
            }
            String rawFields_split[] = rawFields.split(",");

            // Put all fields into one arraylist and search for respective fields
            ArrayList<String> fields = new ArrayList<String>();
            for(int i=0; i < rawFields_split.length; i++){
                fields.add(i,rawFields_split[i]);
            }

            int indexMarketValue = fields.indexOf("market_value");
            int indexTotLiableArea = fields.indexOf("total_livable_area");
            int indexZip = fields.indexOf("zip_code");

            while (myScanner.hasNextLine()) {
                String rawProperty = myScanner.nextLine();
                if (rawProperty.isEmpty()) {
                    continue;
                }

                // Parse one line in CSV file ignoring commas in quotes
                String rawProperty_split[] = rawProperty.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // Parse all fields for Property

                Double propertyMarketValue,propertyTotLiableArea;
                // Whenever parse to Double encounter error, make it a null value
                try{
                    propertyMarketValue = parseDouble(rawProperty_split[indexMarketValue]);
                }catch (Exception e) {
                    propertyMarketValue = null;
                }
                try{
                    propertyTotLiableArea = parseDouble(rawProperty_split[indexMarketValue]);
                }catch (Exception e) {
                    propertyTotLiableArea = null;
                }

                // ignore the entry with empty zip code
                if(rawProperty_split[indexZip].isEmpty()){
                    continue;
                }

                //only consider the first five digits of zip
                String propertyZip;
                try{
                    propertyZip = rawProperty_split[indexZip].substring(0,4);
                }catch (Exception e) {
                    continue;
                }


                // Property object
                Property myProperty = new Property(propertyMarketValue, propertyTotLiableArea, propertyZip);

                // Add Property info into List
                properties.add(myProperty);
            }
        }

        return properties;
    }
}
