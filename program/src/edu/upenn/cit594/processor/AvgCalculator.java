package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;

public interface AvgCalculator {
	
//	public Double calculate(List<Property> properties, Integer zip);
	public Double calculate(Map<Integer, List<Property>> propertyByZip, Integer zip);

}
