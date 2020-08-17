package edu.upenn.cit594.processor;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.PV;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.logging.Logger;

public class PropertyProcessor {

	protected Logger logger;
	protected MemoizationAlgorithm memoization;
	protected Map<Integer, List<Property>> propertyByZip;
	protected Map<Integer, List<PV>> PVByZip;
	
	public PropertyProcessor(Reader reader, PropertyReader propertyReader,
			PopulationReader populationReader, Logger logger, MemoizationAlgorithm memoization) {

		this.logger = logger;
		this.memoization = memoization;

		// both property data and PV data are pre-processed so that only in the very first time the program is going to a bit longer to compute.
		// in the following tries, the result will be displayed instantaneously.
		propertyByZip = memoization.getPropertyByZip();
		PVByZip = memoization.getPVByZip();

	}
	
	//helper method for #2: truncate a Double value to four decimal
	private String truncateFourDigit(Double value) {  
		Double truncatedValue = Math.floor(value*10000)/10000;
		DecimalFormat df = new DecimalFormat("0.0000");	
		String result = df.format(truncatedValue);	
		return result;
	}
	
	//helper method for #3,4,5: truncate a Double value to integer
	private Integer truncateInt(double value) {  //for 3,4,5
		int intValue = (int) value;
		return intValue;
	}
	

	//#3+4
	public Double getAvgForZip(Integer zip, AvgCalculator avgCalculator) {
		return avgCalculator.calculate(propertyByZip, zip);
	}
	
	//#3
	public Integer getAvgMarketValueForZip(Integer zip) {
		return truncateInt(getAvgForZip(zip, new AvgMarketValueCalculator()));
	}
	
	//#4
	public Integer getAvgLivableAreaForZip(Integer zip)  {
		return truncateInt(getAvgForZip(zip, new AvgLivableAreaCalculator()));
	}
	
	//#5
	public Integer getTtlMarketValuePerCapitaForZip(Integer zip) {

		List<Population> populations = PopulationProcessor.populationReader.getAllPopulation();
		List<Property> properties = propertyByZip.get(zip);
		if(properties == null) return 0;
		
		Double ttlMarketValue = 0.00;		
		for(Property p: properties) {
			Double marketValue = p.getMarketValue();
			if(marketValue != null) {
				ttlMarketValue += marketValue;
			}			
		}
		
		Integer zipPopulation = -1;
		for(Population p: populations) {
			 if(p.getZip().equals(zip)) { 
				 zipPopulation = p.getZipPopulation();
				 if(zipPopulation.equals(0) || zipPopulation == null) return 0;  //if population is 0, return 0					 
				 Integer ttlMarketValuePerCapitaForZip = truncateInt(ttlMarketValue/zipPopulation);
				 return ttlMarketValuePerCapitaForZip;				 
			 }
		}
		return 0; //if the zip code is not listed in the population file, return 0	
	}
			
	
	//#6
	public Integer getTtlMarketValuePerCapitaForMostMeterExpZip() {

		Map<Integer, Integer> meterExpByZip = new HashMap<>();
		
		for (Map.Entry<Integer, List<PV>> entry: PVByZip.entrySet()) {
			Integer zip = entry.getKey();
			List<PV> PVs = entry.getValue();
			
			Integer count = 0;
			for(PV pv: PVs) {
				String description = pv.getDescription();
				if(description.equals("METER EXPIRED") || description.equals("METER EXPIRED CC")) {
					count++;
				}
			}
			meterExpByZip.put(zip, count);		
		}
			
		Integer maxZip = -1;
		Integer maxCount = -1;
		for (Map.Entry<Integer, Integer> entry: meterExpByZip.entrySet()) {
			if(entry.getValue() > maxCount) {
				maxZip = entry.getKey();
				maxCount = entry.getValue();
			}
		}			
		return getTtlMarketValuePerCapitaForZip(maxZip);
		
	}

	
	
}



//private Set<Integer> getAllZipFromPV() {
//	Set<Integer> allZipFromPV = new HashSet<>();
//	for(PV pv: PVs) {
//		Integer zip = pv.getZip();
//		if(allZipFromPV.contains(zip) || zip == null) continue;
//		else allZipFromPV.add(zip);
//	}
//	return allZipFromPV;
//}

//public Map<Integer, List<PV>> groupPVByZip() {
//	Map<Integer, List<PV>> PVByZip = new HashMap<>();
//	for(PV pv: PVs) {
//		Integer zip = pv.getZip();
//		if(zip == null) continue;
//		if(PVByZip.containsKey(zip)) {
//			PVByZip.get(zip).add(pv);
//		}
//		else {
//			List<PV> l = new ArrayList<>();
//			l.add(pv);
//			PVByZip.put(zip, l);
//		}
//	}
//	return PVByZip;
//}


//public Map<Integer, List<Property>> groupPropertyByZip() {
//	Map<Integer, List<Property>> propertyByZip = new HashMap<>();
//	for(Property p: properties) {
//		Integer zip = p.getZip();
//		if(zip == null) continue;
//		if(propertyByZip.containsKey(zip)) {
//			propertyByZip.get(zip).add(p);
//		}
//		else {
//			List<Property> l = new ArrayList<>();
//			l.add(p);
//			propertyByZip.put(zip,l);
//		}
//	}
//	return propertyByZip;
//}
