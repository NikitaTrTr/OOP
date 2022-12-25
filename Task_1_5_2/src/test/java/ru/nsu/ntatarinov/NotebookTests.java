package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

/**
 * some simple tests for Notebook.
 */
public class NotebookTests {

    @Test
    void simpleTest() throws IOException, ParseException, java.text.ParseException {
        String[] args = {"add", "2134", "1234"};
        Main.main(args);
    }

    @Test
    void notebookClassTest() throws java.text.ParseException {
        Notebook notebook = new Notebook();
        Record record = new Record("First record", "hello world!");
        notebook.addRecord(record);
        notebook.addRecord("Second record", "this record will be deleted");
        assertEquals(2, notebook.records.size());
        assertEquals("First record", notebook.records.get(0).title);
        assertEquals("hello world!", notebook.records.get(0).text);
        assertEquals("Second record", notebook.records.get(1).title);
        assertEquals("this record will be deleted", notebook.records.get(1).text);
        notebook.showRecords();
        String[] keywords = {"hello world!"};
        notebook.showRecords(InputParser.parseDate("14.12.1970 7:00:00"),
            InputParser.parseDate("01.01.2077 13:00:00"), keywords);
        notebook.removeRecord("Second record");
        assertEquals(1, notebook.records.size());
        assertEquals("First record", notebook.records.get(0).title);
        assertEquals("hello world!", notebook.records.get(0).text);
    }

    @Test
    void inputParserClassTest() throws ParseException, java.text.ParseException {
        String[] argsAdd = {"-add", "title1", "text1"};
        CommandLine cmd = InputParser.parseArgs(argsAdd);
        assertTrue(cmd.hasOption("add"));
        assertEquals("title1", cmd.getOptionValues("add")[0]);
        assertEquals("text1", cmd.getOptionValues("add")[1]);

        String[] argsRemove = {"-rm", "title1"};
        cmd = InputParser.parseArgs(argsRemove);
        assertTrue(cmd.hasOption("rm"));
        assertEquals("title1", cmd.getOptionValues("rm")[0]);

        String[] argsShow = {"-show"};
        cmd = InputParser.parseArgs(argsShow);
        assertTrue(cmd.hasOption("show"));
        assertNull(cmd.getOptionValues("show"));

        String[] argsShowExtended = {"-show", "date1", "date2", "keyword1", "keyword2"};
        cmd = InputParser.parseArgs(argsShowExtended);
        assertTrue(cmd.hasOption("show"));
        assertEquals("date1", cmd.getOptionValues("show")[0]);
        assertEquals("date2", cmd.getOptionValues("show")[1]);
        assertEquals("keyword1", cmd.getOptionValues("show")[2]);
        assertEquals("keyword2", cmd.getOptionValues("show")[3]);

        Date date = new Date(2012-1900, Calendar.DECEMBER,12, 10, 10, 10);
        assertEquals(date, InputParser.parseDate("12.12.2012 10:10:10"));
    }

    @Test
    void jsonWriterReaderTests() throws IOException, java.text.ParseException {
        Notebook notebook = JsonWriterReader.readFromJson("./src/test/resources/source.json");
        Date recordTime = InputParser.parseDate("12.12.2012 12:12:12");
        Record record = new Record("title_test","empty");
        record.timestamp = recordTime;
        notebook.addRecord(record);
        JsonWriterReader.writeToJson(notebook, "./src/test/resources/source.json");

        byte[] file_result = Files.readAllBytes(Paths.get("./src/test/resources/source.json"));
        byte[] file_expected = Files.readAllBytes(Paths.get("./src/test/resources/expected.json"));

        String result = new String(file_result, StandardCharsets.UTF_8);
        String expected = new String(file_expected, StandardCharsets.UTF_8);

        assertEquals(expected, result);
    }
}
