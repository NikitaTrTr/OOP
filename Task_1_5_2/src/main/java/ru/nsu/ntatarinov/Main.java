package ru.nsu.ntatarinov;
import java.util.Arrays;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) throws ParseException, InterruptedException {
        //CommandLine cmd = InputParser.parseArgs(args);
        //if (cmd.hasOption("add")){
        //
        //}
        Notebook notebook = new Notebook();
        notebook.addRecord("123", "345");
        Thread.sleep(1000);
        notebook.addRecord("hello", "bye");
        Thread.sleep(1000);
        notebook.addRecord("Irtegov", "is a god");
        notebook.removeRecord("hello");
        notebook.showRecords();
    }
}