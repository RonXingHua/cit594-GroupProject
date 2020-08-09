package edu.upenn.cit594.data;

import edu.upenn.cit594.datamanagement.PV;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Integer.parseInt;

public class PVJSONReader <E> implements Reader{
    @Override
    public ArrayList readFromFile(File PVInputFileName) {
        ArrayList<PV> parkingViolations = new ArrayList<PV>();

        // create a parser
        JSONParser parser = new JSONParser();
        // open the file and get the array of JSON objects

        JSONArray PVJSON = null;
        try {
            PVJSON = (JSONArray)parser.parse(new FileReader(PVInputFileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // use an iterator to iterate over each element of the array
        Iterator iter = PVJSON.iterator();

        Map<String, Integer> summary = new HashMap<>();

        // iterate while there are more objects in array
        while (iter.hasNext()) {
            // get the next JSON object
            JSONObject jsonData = (JSONObject) iter.next();

            // Parse all fields for PV
            String rawDatetime = jsonData.get("date").toString();
            DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("uuuu-MM-ddTHH:mm:ssZ");
            LocalDateTime PVDateTime = LocalDateTime.parse(rawDatetime, formatter);

            int PVFine = parseInt(jsonData.get("fine").toString());
            String PVDescription = jsonData.get("text").toString();
            int PVAnonymousID = parseInt(jsonData.get("plate_id").toString());
            String PVState = jsonData.get("state").toString();
            int PVUniqueID = parseInt(jsonData.get("ticket_number").toString());
            int PVZip = parseInt(jsonData.get("zip_code").toString());


            // PV object
            PV myPV = new PV(PVDateTime,PVFine, PVDescription,PVAnonymousID,PVState,PVUniqueID,PVZip);

            // Add PV info into List
            parkingViolations.add(myPV);
        }

        return parkingViolations;
    }
}
