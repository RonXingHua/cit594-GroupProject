package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;

public class AvgLivableAreaCalculator implements AvgCalculator {
	
	public Double calculate(Map<Integer, List<Property>> propertyByZip, Integer zip) {
		Double ttlLivableArea = 0.00;
		int count = 0;
		
//		for(Property p: properties) {
//			Double livableArea = p.getTotLivableArea();
//			if(p.getZip().equals(zip) && livableArea != null) {
//				ttlLivableArea += livableArea;
//				count++;
//			}			
//		}
		
		List<Property> properties = propertyByZip.get(zip);
		if(properties == null) return 0.00;
		for(Property p: properties) {
			Double livableArea = p.getTotLivableArea();
			if(livableArea != null) {
				ttlLivableArea += livableArea;
				count++;
			}		
		}
		
		Double avgLivableAreaForZip = ttlLivableArea/count;
		return avgLivableAreaForZip;
	}

}
