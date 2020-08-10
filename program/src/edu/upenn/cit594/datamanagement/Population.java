package edu.upenn.cit594.datamanagement;

public class Population {
    private Integer zip;
    private Integer zipPopulation;

    public Population(Integer zip, int zipPopulation) {
        this.zip = zip;
        this.zipPopulation = zipPopulation;
    }

    public Integer getZip() {
        return zip;
    }

    public Integer getZipPopulation() {
        return zipPopulation;
    }
}
