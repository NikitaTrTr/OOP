package ru.nsu.ntatarinov;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriterReader {

    public static void writeToJson(Notebook notebook, String path) throws IOException {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        String json = gson.toJson(notebook);
        FileWriter writer = new FileWriter(path, false);
        writer.write(json);
        writer.flush();
    }

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
