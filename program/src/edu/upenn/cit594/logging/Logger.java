package edu.upenn.cit594.logging;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    // Using Singleton
    private static Logger instance = new Logger();

    public static Logger getInstance(){
        return instance;
    }

    public void log (String fileName, String loggingContent){
        try{
            FileWriter file = new FileWriter (fileName, true);

            // logging content is state and original tweet
            file.append(loggingContent);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
