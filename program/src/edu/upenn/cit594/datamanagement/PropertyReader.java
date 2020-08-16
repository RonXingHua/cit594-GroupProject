package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class PropertyReader {
	
	protected String filename;
	protected Logger logger;
	
	public PropertyReader(String filename, Logger logger) {
		this.filename = filename;
		this.logger = logger;
	}

    public List<Property> getAllProperties() {
    	
        List<Property> properties = new ArrayList<Property>();
        File propertyInputFile = new File(filename);

        Scanner myScanner = null;
        try{
            myScanner = new Scanner(propertyInputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //logging
        logger.logFileOpen(filename);

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
            int indexTotLivableArea = fields.indexOf("total_livable_area");
            int indexZip = fields.indexOf("zip_code");

            while (myScanner.hasNextLine()) {
                String rawProperty = myScanner.nextLine();
                if (rawProperty.isEmpty()) {
                    continue;
                }

                // Parse one line in CSV file ignoring commas in quotes
                String rawProperty_split[] = rawProperty.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // Parse all fields for Property

                Double propertyMarketValue,propertyTotLivableArea;
                // Whenever parse to Double encounter error, make it a null value
                try{
                    propertyMarketValue = parseDouble(rawProperty_split[indexMarketValue]);
                }catch (Exception e) {
                    propertyMarketValue = null;
                }
                try{
                    propertyTotLivableArea = parseDouble(rawProperty_split[indexTotLivableArea]);
                }catch (Exception e) {
                    propertyTotLivableArea = null;
                }

                // ignore the entry with empty zip code
                if(rawProperty_split[indexZip].isEmpty()){
                    continue;
                }

                //only consider the first five digits of zip
                Integer propertyZip;
                try{
                    propertyZip = parseInt(rawProperty_split[indexZip].substring(0,5));
                }catch (Exception e) {
                    continue;
                }


                // Property object
                Property myProperty = new Property(propertyMarketValue, propertyTotLivableArea, propertyZip);

                // Add Property info into List
                properties.add(myProperty);
            }
        }

        return properties;
    }
}
