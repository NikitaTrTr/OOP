package ru.nsu.ntatarinov;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for reading to and writing from json files.
 */
public class JsonWriterReader {

    /**
     * Write to json file.
     *
     * @param notebook object from which get information to write
     * @param path     path, where to save data
     * @throws IOException IOException
     */
    public static void writeToJson(Notebook notebook, String path) throws IOException {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        String json = gson.toJson(notebook);
        FileWriter writer = new FileWriter(path, false);
        writer.write(json);
        writer.flush();
    }

    /**
     * Read data from json into Notebook object.
     *
     * @param path path to a json file
     * @return Notebook object with filled fields
     * @throws IOException IOException
     */
    public static Notebook readFromJson(String path) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String buff = input.readLine();
            if (buff == null) {
                break;
            }
            sb.append(buff).append("\n");
        }
        String json = sb.toString();
        input.close();

        return new Gson().fromJson(json, Notebook.class);
    }
}
