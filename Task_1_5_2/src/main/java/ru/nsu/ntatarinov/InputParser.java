package ru.nsu.ntatarinov;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Class used to parse arguments from cmd input.
 */
public class InputParser {

    /**
     * Parse arguments from cmd input.
     *
     * @param args line with arguments
     * @return CommandLine object that can be used for getting arguments
     * @throws ParseException ParseException
     */
    public static CommandLine parseArgs(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder()
            .option("add")
            .hasArgs()
            .desc("Add new record")
            .numberOfArgs(2)
            .build());
        options.addOption(Option.builder()
            .option("rm")
            .hasArgs()
            .desc("Remove record by its name")
            .numberOfArgs(1)
            .build());
        Option showOption = Option.builder()
            .option("show")
            .hasArgs()
            .desc("Remove record by its name")
            .build();
        showOption.setOptionalArg(true);
        CommandLineParser parser = new DefaultParser();
        options.addOption(showOption);
        return parser.parse(options, args);
    }

    public static Date parseDate(String dateString) throws java.text.ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return parser.parse(dateString);
    }
}
