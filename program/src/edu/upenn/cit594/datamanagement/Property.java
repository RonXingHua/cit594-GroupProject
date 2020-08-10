package edu.upenn.cit594.datamanagement;

public class Property {
    private Double marketValue;
    private Double totLiableArea;
    private Integer zip;

    public Property(Double marketValue, Double totLiableArea, Integer zip) {
        // null pointer check - 1
        if(marketValue == null){
            this.marketValue = null;
        }else{
            this.marketValue = marketValue;
        }

        // null pointer check - 2
        if(totLiableArea == null){
            this.totLiableArea = null;
        }else{
            this.totLiableArea = totLiableArea;
        }

        this.zip = zip;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public Double getTotLiableArea() {
        return totLiableArea;
    }

    public Integer getZip() {
        return zip;
    }
}
