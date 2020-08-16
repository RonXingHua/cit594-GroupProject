package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.PV;
import edu.upenn.cit594.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class PVCSVReader implements Reader {

    protected String filename;
    protected Logger logger;
    
    public PVCSVReader(String filename, Logger logger) {
    	this.filename = filename;
    	this.logger = logger;
    }
    
    public List<PV> getAllPVs() {  	
        List<PV> parkingViolations = new ArrayList<PV>();
        
        Scanner myScanner = null;

        try {
        	File PVInputFile = new File(filename);  	
            myScanner = new Scanner(PVInputFile);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //logging
        logger.logFileOpen(filename);

        if(myScanner != null) {
            while (myScanner.hasNextLine()) {
                String rawPV = myScanner.nextLine();
                if (rawPV.isEmpty()) {
                    continue;
                }

                // Parse one line in CSV file
                String rawPV_split[] = rawPV.split(",",-1);

                // Parse all fields for PV
                DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss'Z'");
                LocalDateTime PVDateTime = LocalDateTime.parse(rawPV_split[0], formatter);

                int PVFine = parseInt(rawPV_split[1]);
                String PVDescription = rawPV_split[2];
                String PVAnonymousID = rawPV_split[3];
                String PVState = rawPV_split[4];
                String PVUniqueID = rawPV_split[5];

                // ignore the entry with empty zip code
                if(rawPV_split[6].isEmpty()){
                    continue;
                }

                Integer PVZip = parseInt(rawPV_split[6]);

                // PV object
                PV myPV = new PV(PVDateTime,PVFine, PVDescription,PVAnonymousID,PVState,PVUniqueID,PVZip);

                // Add PV info into List
                parkingViolations.add(myPV);
            }
        }

        return parkingViolations;
    }


}
