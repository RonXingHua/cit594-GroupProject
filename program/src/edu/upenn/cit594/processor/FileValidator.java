package edu.upenn.cit594.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileValidator {
    // Check if the tweet file format is json or csv
    public static void formatValidate(String PVFileFormat){
        if ((!PVFileFormat.equals("json")) && (!PVFileFormat.equals("csv"))){
            System.out.println("Error: The Parking Violations input file format only accept either json or csv, please check, exiting.");
            System.exit(1);
        }
    }

    // Check if the tweet file exist and able to read
    public static void inputFileValidate(String inputFileName){
        File file = new File(inputFileName);
        boolean fileTest = file.exists() && file.canRead();
        if(!fileTest){
            System.out.println("Error: The input file doesn't exist or cannot be opened, please check, exiting.");
            System.exit(1);
        }
    }

    // Check if the log file exist
    public static void logfileValidate(String logfileName)  {
        try{
            File logfile = new File(logfileName);
            FileWriter fw = new FileWriter(logfileName,false);

            if (logfile.createNewFile()) {
                // create file if log file doesn't exist
            } else {
                // overwrite the logfile
                fw.write("");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
