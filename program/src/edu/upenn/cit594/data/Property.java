package edu.upenn.cit594.data;

public class Property {
    private Double marketValue;
    private Double totalLivableArea;
    private Integer zip;

    public Property(Double marketValue, Double totalLivableArea, Integer zip) {
        // null pointer check - 1
        if(marketValue == null){
            this.marketValue = null;
        }else{
            this.marketValue = marketValue;
        }

        // null pointer check - 2
        if(totalLivableArea == null){
            this.totalLivableArea = null;
        }else{
            this.totalLivableArea = totalLivableArea;
        }

        this.zip = zip;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public Double getTotLivableArea() {
        return totalLivableArea;
    }

    public Integer getZip() {
        return zip;
    }
}
