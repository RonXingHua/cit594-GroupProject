package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.PopulationReader;

import java.util.List;

public class PopulationProcessor {
    protected static PopulationReader populationReader;
    protected List<Population> populations;

    public PopulationProcessor(PopulationReader populationReader) {
        this.populationReader = populationReader;
    }

    public Integer getTtlPopulation() {
        populations = populationReader.getAllPopulation();
        Integer ttlPopulation = 0;
        for(Population p: populations) {
            ttlPopulation += p.getZipPopulation();
        }
        return ttlPopulation;
    }
}
