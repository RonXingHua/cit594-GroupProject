package edu.upenn.cit594.data;

import edu.upenn.cit594.datamanagement.PV;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PVJSONReader <E> implements Reader{
    @Override
    public ArrayList readFromFile(File PVInputFileName) {
        ArrayList<PV> parkingViolations = new ArrayList<PV>();

        // create a parser
        JSONParser parser = new JSONParser();
        // open the file and get the array of JSON objects

        JSONArray tweets = null;
        try {
            tweets = (JSONArray)parser.parse(new FileReader(tweetFile));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // use an iterator to iterate over each element of the array
        Iterator iter = tweets.iterator();

        Map<String, Integer> summary = new HashMap<>();

        // iterate while there are more objects in array
        while (iter.hasNext()) {
            // get the next JSON object
            JSONObject jsonData = (JSONObject) iter.next();

            String rawLocations = jsonData.get("location").toString();
            String rawText = jsonData.get("text").toString();

            // Parse latitude and longitude in location
            String rawLocations_split[] = rawLocations.split(",");
            double tweetLatitude = Double.parseDouble(rawLocations_split[0].substring(1));
            double tweetLongitude = Double.parseDouble(rawLocations_split[1].substring(0, rawLocations_split[1].length() - 1));

            Geolocation geolocation = new Geolocation(tweetLatitude, tweetLongitude);
            String state = statesDataService.getStateByGeoLocation(geolocation);
            Tweet tweet = new Tweet(state, rawText);

            // If it's a flu tweet, it will be added into the summary which will be presented in the end
            boolean isLogged = processTweet(tweet);

            if (isLogged){
                summary.putIfAbsent(state, 0);
                summary.put(state, summary.get(state) + 1);
            }
        }

        return null;
    }
}
