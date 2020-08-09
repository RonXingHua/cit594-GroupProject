package edu.upenn.cit594.datamanagement;

public class Population {
    private int zip;
    private int zipPopulation;

    public Population(int zip, int zipPopulation) {
        this.zip = zip;
        this.zipPopulation = zipPopulation;
    }

    public int getZip() {
        return zip;
    }

    public int getZipPopulation() {
        return zipPopulation;
    }
}
