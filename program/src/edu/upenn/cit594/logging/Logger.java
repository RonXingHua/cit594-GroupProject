package edu.upenn.cit594.logging;

import java.io.File;

import java.io.PrintWriter;

public class Logger {

    // Using Singleton
    private PrintWriter out;

    private Logger(String filename){
        try{
            out = new PrintWriter(new File(filename));
        }catch(Exception e){}
    }
    private static Logger instance = null;

    public static Logger getInstance(){
        return instance;
    }
    
    public static Logger getInstance(String logFileName){
        if (instance == null){
            instance = new Logger(logFileName);
        }
        return instance;
    }

    public void logString ( String loggingContent){
        out.println(loggingContent);
        out.flush();
    }

    public void logProgramStart(
            String PVInputFileFormat,
            String PVInputFileName,
            String propertyInputFileName,
            String populationInputFileName,
            String logFileName){


        String currentTime = String.valueOf(System.currentTimeMillis());
        out.println(currentTime + " " + PVInputFileFormat + " " + PVInputFileName + " " + propertyInputFileName + " " + populationInputFileName + " " + logFileName);
        out.flush();
    }

    public void logFileOpen( String openedFileName){

        String currentTime = String.valueOf(System.currentTimeMillis());
        out.println(currentTime + " " + openedFileName);
        out.flush();

    }

    public void logUserOption( int userChoice){

        String currentTime = String.valueOf(System.currentTimeMillis());

        out.println(currentTime + " " + "User Choice:" + userChoice);
        out.flush();

    }

    public void logUserZip( int zip){

        String currentTime = String.valueOf(System.currentTimeMillis());
        out.println(currentTime + " " + "Zip:" + zip);
        out.flush();

    }
}
