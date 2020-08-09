package edu.upenn.cit594.datamanagement;

public class Property {
    private int marketValue;
    private int totLiableArea;
    private int zip;

    public Property(int marketValue, int totLiableArea, int zip) {
        this.marketValue = marketValue;
        this.totLiableArea = totLiableArea;
        this.zip = zip;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public int getTotLiableArea() {
        return totLiableArea;
    }

    public int getZip() {
        return zip;
    }
}
