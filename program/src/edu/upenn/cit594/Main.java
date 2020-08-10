package edu.upenn.cit594;


import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.FileValidator;
import edu.upenn.cit594.ui.Display;

public class Main {
    public static void main(String[] args) {
        // Read commandline arguments
        if (args.length != 5){
            System.out.println("Error: The program requires exactly 4 arguments, please check, exiting.");
            System.exit(1);
        }

        // Parse the runtime arguments
        String PVInputFileFormat = args[0].toLowerCase();
        String PVInputFileName = args[1];
        String propertyInputFileName = args[2];
        String populationInputFileName = args[3];
        String logFileName = args[4];

        // logging
        Logger logger = Logger.getInstance(logFileName);
        logger.logProgramStart(PVInputFileFormat,PVInputFileName,propertyInputFileName,populationInputFileName,logFileName);

        // Validate input files
        FileValidator.formatValidate(PVInputFileFormat);
        FileValidator.nameValidate(PVInputFileName);
        FileValidator.nameValidate(propertyInputFileName);
        FileValidator.nameValidate(populationInputFileName);
        FileValidator.logfileValidate(logFileName);

        // Get user input
        int userOption = Display.getUserInput();

        // Processing



    }
}
