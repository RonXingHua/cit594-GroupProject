package edu.upenn.cit594.datamanagement;


import edu.upenn.cit594.data.PV;
import edu.upenn.cit594.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Integer.parseInt;

public class PVJSONReader implements Reader {
	
	protected String filename;
	protected Logger logger;
	
	public PVJSONReader(String filename, Logger logger) {
		this.filename = filename;
		this.logger = logger;
	}

    public List<PV> getAllPVs() {
        List<PV> parkingViolations = new ArrayList<PV>();

        // create a parser
        JSONParser parser = new JSONParser();
        
        // open the file and get the array of JSON objects
        JSONArray PVJSON = null;
        try {
            PVJSON = (JSONArray)parser.parse(new FileReader(filename));
        } catch (Exception e) {
            return parkingViolations;
        } 

        //logging
        logger.logFileOpen(filename);

        // use an iterator to iterate over each element of the array
        Iterator iter = PVJSON.iterator();

        // iterate while there are more objects in array
        while (iter.hasNext()) {
            // get the next JSON object
            JSONObject jsonData = (JSONObject) iter.next();

            // Parse all fields for PV
            String rawDatetime = jsonData.get("date").toString();
            DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss'Z'");
            LocalDateTime PVDateTime = LocalDateTime.parse(rawDatetime, formatter);

            Integer PVFine = parseInt(jsonData.get("fine").toString());
            String PVDescription = jsonData.get("violation").toString();
            String PVAnonymousID = jsonData.get("plate_id").toString();
            String PVState = jsonData.get("state").toString();
            String PVUniqueID = jsonData.get("ticket_number").toString();

            // ignore the entry with empty zip code
            if(jsonData.get("zip_code").toString().isEmpty()){
                continue;
            }
            Integer PVZip = parseInt(jsonData.get("zip_code").toString());


            // PV object
            PV myPV = new PV(PVDateTime,PVFine, PVDescription,PVAnonymousID,PVState,PVUniqueID,PVZip);

            // Add PV info into List
            parkingViolations.add(myPV);
        }

        return parkingViolations;
    }
}
