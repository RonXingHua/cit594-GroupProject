package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.PV;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.Reader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PVProcessor {

    protected List<PV> PVs;
    protected Map<Integer, List<PV>> PVByZip;



    public PVProcessor(Reader reader) {

            PVs = reader.getAllPVs();
            PVByZip = new HashMap<>();

            for(PV pv: PVs) {
                Integer zip = pv.getZip();
                if(zip == null) continue;
                if(PVByZip.containsKey(zip)) {
                    PVByZip.get(zip).add(pv);
                }
                else {
                    List<PV> l = new ArrayList<>();
                    l.add(pv);
                    PVByZip.put(zip, l);
                }
            }

    }

    public Map<Integer, List<PV>> getPVByZip(){
        return PVByZip;
    }

    public Map<Integer, String> getTtlFinesPerCapitaForZip() {

        List<Population> populations = PopulationProcessor.populationReader.getAllPopulation();
        Map<Integer, String> ttlFinesPerCapitaForZip = new HashMap<>();

        Map<Integer, List<PV>> PVByZip = this.getPVByZip();
        for (Map.Entry<Integer, List<PV>> entry: PVByZip.entrySet()) {
            Integer zip = entry.getKey();
            Double ttlFines = 0.0000;
            Integer zipPopulation = null;

            for(Population p: populations) {
                if(!p.getZip().equals(zip)) continue;  //if this zip is not in the population, skip

                zipPopulation = p.getZipPopulation();
                if(zipPopulation.equals(0)) break; //if the zip has 0 population, loop for next zip

                List<PV> PVs = entry.getValue();
                for(PV pv: PVs) {
                    if(pv.getFine() == (int) pv.getFine() && pv.getFine() != null
                            && pv.getState().equals("PA")) {
                        ttlFines += pv.getFine();
                    }
                }
                if(ttlFines == 0) break;  //if total fine for this zip is 0, loop for next zip
                String ttlFinesPerCapita = truncateFourDigit(ttlFines/zipPopulation);
                ttlFinesPerCapitaForZip.put(zip, ttlFinesPerCapita);
                break;  //no need to loop for remaining populations
            }
        }

        return ttlFinesPerCapitaForZip;
    }

    //helper method for #2: truncate a Double value to four decimal
    private String truncateFourDigit(Double value) {
        Double truncatedValue = Math.floor(value*10000)/10000;
        DecimalFormat df = new DecimalFormat("0.0000");
        String result = df.format(truncatedValue);
        return result;
    }

}
