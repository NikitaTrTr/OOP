package ru.nsu.ntatarinov;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

/**
 * Main class for executing from console.
 */
public class Main {

    /**
     * Main method, that receives args from console and perform actions given in console.
     *
     * @param args arguments from console.
     * @throws IOException              IOException
     * @throws ParseException           ParseException
     * @throws java.text.ParseException java.text.ParseException
     */
    public static void main(String[] args)
        throws IOException, ParseException, java.text.ParseException {
        String notebookPath = "./src/main/resources/Notebook.json";
        Notebook notebook = JsonWriterReader.readFromJson(notebookPath);
        CommandLine cmd = InputParser.parseArgs(args);
        if (cmd.hasOption("add")) {
            String title = cmd.getOptionValues("add")[0];
            String text = cmd.getOptionValues("add")[1];
            notebook.addRecord(title, text);
        }
        if (cmd.hasOption("rm")) {
            String title = cmd.getOptionValue("rm");
            notebook.removeRecord(title);
        }
        if (cmd.hasOption("show")) {
            String[] arguments = cmd.getOptionValues("show");
            if (arguments == null) {
                notebook.showRecords();
            } else {
                Date date1 = InputParser.parseDate(arguments[0]);
                Date date2 = InputParser.parseDate(arguments[1]);
                notebook.showRecords(date1, date2,
                    Arrays.stream(arguments).skip(2).toArray(String[]::new));
            }
        }
        JsonWriterReader.writeToJson(notebook, notebookPath);
    }
}