package edu.upenn.cit594.data;

import java.time.LocalDateTime;
import java.util.Date;

public class PV {
    private LocalDateTime timestamp;
    private Integer fine;
    private String description;
    private String anonymousID;
    private String state;
    private String uniqueID;
    private Integer zip;

    public PV(LocalDateTime timestamp,Integer fine, String description, String anonymousID, 
    		String state, String uniqueID, Integer zip){
        this.timestamp = timestamp;
        this.fine = fine;
        this.description = description;
        this.anonymousID = anonymousID;
        this.state = state;
        this.uniqueID = uniqueID;
        this.zip = zip;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getFine() {
        return fine;
    }

    public String getDescription() {
        return description;
    }

    public String getAnonymousID() {
        return anonymousID;
    }

    public String getState() {
        return state;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public Integer getZip() {
        return zip;
    }
}
