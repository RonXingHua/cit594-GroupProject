package edu.upenn.cit594.datamanagement;

public class Population {
    private String zip;
    private Integer zipPopulation;

    public Population(String zip, int zipPopulation) {
        this.zip = zip;
        this.zipPopulation = zipPopulation;
    }

    public String getZip() {
        return zip;
    }

    public Integer getZipPopulation() {
        return zipPopulation;
    }
}
