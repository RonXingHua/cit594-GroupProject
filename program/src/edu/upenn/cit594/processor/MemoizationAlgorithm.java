package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.PV;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.Reader;



public class MemoizationAlgorithm {
	
	protected Reader reader;
	protected PropertyReader propertyReader;
	protected List<PV> PVs;
	protected List<Property> properties;
	
	protected Map<Integer, List<PV>> PVByZip;
	protected Map<Integer, List<Property>> propertyByZip;
	
	
	
	public MemoizationAlgorithm(Reader reader, PropertyReader propertyReader) {
		this.reader = reader;
		this.propertyReader = propertyReader;

	}
	
	
	public Map<Integer, List<PV>> getPVByZip(){
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
		return PVByZip;
	}
	
	
	public Map<Integer, List<Property>> getPropertyByZip(){
		properties = propertyReader.getAllProperties();
		propertyByZip = new HashMap<>();

		for(Property p: properties) {
			Integer zip = p.getZip();
			if(zip == null) continue;
			if(propertyByZip.containsKey(zip)) {
				propertyByZip.get(zip).add(p);
			}
			else {
				List<Property> l = new ArrayList<>();
				l.add(p);
				propertyByZip.put(zip,l);
			}
		}
		return propertyByZip;
	}
	
	


}
