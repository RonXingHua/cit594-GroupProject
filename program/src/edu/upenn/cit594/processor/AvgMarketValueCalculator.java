package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;

public class AvgMarketValueCalculator implements AvgCalculator {
	
	public Double calculate(Map<Integer, List<Property>> propertyByZip, Integer zip) {
		Double ttlMarketValue = 0.00;
		int count = 0;
		

		List<Property> properties = propertyByZip.get(zip);
		if (properties == null) return 0.00;
		for(Property p: properties) {
			Double marketValue = p.getMarketValue();
			if(marketValue != null) {
				ttlMarketValue += marketValue;
				count++;
			}		
		}
		
		Double avgMarketValueForZip = ttlMarketValue/count;
		return avgMarketValueForZip;
	}

}
