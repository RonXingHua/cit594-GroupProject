package edu.upenn.cit594.logging;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    // Using Singleton
    private static Logger instance = new Logger();

    public static Logger getInstance(){
        return instance;
    }

    public void logString (String logFileName, String loggingContent){
        try{
            FileWriter file = new FileWriter (logFileName, true);

            file.append(loggingContent + "\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logProgramStart(
            String PVInputFileFormat,
            String PVInputFileName,
            String propertyInputFileName,
            String populationInputFileName,
            String logFileName){
        try{
            FileWriter file = new FileWriter (logFileName, true);

            // logging content is current time and runtime arguments
            String currentTime = String.valueOf(System.currentTimeMillis());
            file.append(currentTime + "\\s" + PVInputFileFormat + PVInputFileName
                    + propertyInputFileName + populationInputFileName + logFileName + "\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logFileOpen( String logFileName,  String openedFileName){
        try{
            FileWriter file = new FileWriter (logFileName, true);

            // logging content is state and original tweet
            String currentTime = String.valueOf(System.currentTimeMillis());
            file.append(currentTime + "\\s" + openedFileName + "\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logUserChoice( String logFileName, int userChoice){
        try{
            FileWriter file = new FileWriter (logFileName, true);

            // logging content is state and original tweet
            String currentTime = String.valueOf(System.currentTimeMillis());
            file.append(currentTime + "\\s" + "User Choice:" + userChoice + "\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logUserZip( String logFileName, int zip){
        try{
            FileWriter file = new FileWriter (logFileName, true);

            // logging content is state and original tweet
            String currentTime = String.valueOf(System.currentTimeMillis());
            file.append(currentTime + "\\s" + "Zip:" + zip + "\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
