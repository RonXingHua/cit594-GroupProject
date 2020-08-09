package edu.upenn.cit594.data;

import edu.upenn.cit594.datamanagement.PV;
import edu.upenn.cit594.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PVCSVReader <E> implements Reader {
    @Override
    public ArrayList<PV> readFromFile(String PVInputFileName) {
        Logger logger = Logger.getInstance();
        ArrayList<PV> parkingViolations = new ArrayList<PV>();
        File PVInputFile = new File(PVInputFileName);

        Scanner myScanner = null;

        try {
            myScanner = new Scanner(PVInputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //logging
        logger.logFileOpen(PVInputFileName);

        if(myScanner != null) {
            while (myScanner.hasNextLine()) {
                String rawPV = myScanner.nextLine();
                if (rawPV.isEmpty()) {
                    continue;
                }

                // Parse one line in CSV file
                String rawPV_split[] = rawPV.split(",");

                // Parse all fields for PV
                DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("uuuu-MM-ddTHH:mm:ssZ");
                LocalDateTime PVDateTime = LocalDateTime.parse(rawPV_split[0], formatter);

                int PVFine = parseInt(rawPV_split[1]);
                String PVDescription = rawPV_split[2];
                int PVAnonymousID = parseInt(rawPV_split[3]);
                String PVState = rawPV_split[4];
                int PVUniqueID = parseInt(rawPV_split[5]);
                int PVZip = parseInt(rawPV_split[6]);

                // PV object
                PV myPV = new PV(PVDateTime,PVFine, PVDescription,PVAnonymousID,PVState,PVUniqueID,PVZip);

                // Add PV info into List
                parkingViolations.add(myPV);
            }
        }

        return parkingViolations;
    }


}
