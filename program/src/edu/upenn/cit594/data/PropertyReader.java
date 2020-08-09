package edu.upenn.cit594.data;


import edu.upenn.cit594.datamanagement.Property;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class PropertyReader <E> implements Reader {
    @Override
    public ArrayList<Property> readFromFile(File propertyInputFileName) {
        ArrayList<Property> properties = new ArrayList<Property>();

        Scanner myScanner = null;
        try{
            myScanner = new Scanner(propertyInputFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
                int propertyMarketValue = parseInt(rawProperty_split[indexMarketValue]);
                int propertyTotLiableArea = parseInt(rawProperty_split[indexTotLiableArea]);
                //only consider the first five digits of zip
                int propertyZip = parseInt(rawProperty_split[indexZip].substring(0,4));


                // Property object
                Property myProperty = new Property(propertyMarketValue, propertyTotLiableArea, propertyZip);

                // Add Property info into List
                properties.add(myProperty);
            }
        }

        return properties;
    }
}
