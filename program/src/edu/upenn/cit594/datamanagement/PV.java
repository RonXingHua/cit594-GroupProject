package edu.upenn.cit594.datamanagement;

import java.time.LocalDateTime;
import java.util.Date;

public class PV {
    private LocalDateTime timestamp;
    private int fine;
    private String description;
    private int anonymousID;
    private String state;
    private int uniqueID;
    private int zip;

    public PV(LocalDateTime timestamp,int fine, String description, int anonymousID, String state, int uniqueID, int zip){
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

    public int getFine() {
        return fine;
    }

    public String getDescription() {
        return description;
    }

    public int getAnonymousID() {
        return anonymousID;
    }

    public String getState() {
        return state;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public int getZip() {
        return zip;
    }
}
