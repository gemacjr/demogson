
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class NetClientGet {

    public static void main(String[] args) throws IOException, InterruptedException {


//        URL url = new URL("https://jsonplaceholder.typicode.com/todos/2");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Accept", "application/json");
//        conn.connect();
//        int responseCode = conn.getResponseCode();
//
//        Scanner sc = new Scanner(url.openStream());
//        String inline = "";
//        while(sc.hasNext()) {
//            inline+=sc.nextLine();
//        }
//        System.out.println("/JSON data format in string format");
//        System.out.println(inline);
//        sc.close();
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        JsonParser jp = new JsonParser();
//        JsonElement je = jp.parse(inline.toString());
//        String prettyJsonString = gson.toJson(je);
//        Gson newGson = new Gson();
//        Quote quote = newGson.fromJson(prettyJsonString, Quote.class);
//        System.out.println(quote.getId());
//        System.out.println(quote.getTitle());
//        System.out.println(quote.getUserId());
//        System.out.println(quote.isCompleted());
//
//        writeToFile(prettyJsonString);
//
//        Thread.sleep(3000);

//        JsonNode jsonTree = new ObjectMapper().readTree(new File("src/main/resources/demo.json"));
//         CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
//        com.fasterxml.jackson.databind.JsonNode firstObject = jsonTree.elements().next();
//        firstObject.fieldNames().forEachRemaining(fieldName -> {
//                csvSchemaBuilder.addColumn(fieldName);
//        });
//        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
//        CsvMapper csvMapper = new CsvMapper();
//        csvMapper.writerFor(JsonNode.class)
//                .with(csvSchema)
//                .writeValue(new File("src/main/resources/orderLines.csv"),jsonTree);

//        CsvMapper csvMapper = new CsvMapper();
//        CsvSchema csvSchema = csvMapper.schemaFor(QouteForCsv.class).withHeader();
//        csvMapper.addMixIn(Quote.class, QouteForCsv.class);
//
//        Quote[] quotes = new ObjectMapper().readValue(new File("src/main/resources/demo.json"), Quote[].class);
//        csvMapper.writerFor(Quote[].class)
//                .with(csvSchema)
//                .writeValue(new File("src/main/resources/orderLines.csv"), quotes);


        JsonNode jsonTree = new ObjectMapper().readTree(new File("src/main/resources/demo.json"));
        Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File("src/main/resources/orderLines.csv"),jsonTree);

    }

    private static void writeToFile(String myData) {
        File crunchifyFile = new File("src/main/resources/demo.json");
        if (!crunchifyFile.exists()) {
            try {
                File directory = new File(crunchifyFile.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                crunchifyFile.createNewFile();
            } catch (IOException e) {
                 e.toString();
            }
        }

        try {
            // Convenience class for writing character files
            FileWriter crunchifyWriter;
            crunchifyWriter = new FileWriter(crunchifyFile.getAbsoluteFile(), true);

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(crunchifyWriter);
            bufferWriter.write(myData.toString());
            bufferWriter.close();

            System.out.println("Company data saved at file location: " + "src/resources/demo.json " + " Data: " + myData + "\n");
        } catch (IOException e) {
            System.out.println("Hmm.. Got an error while saving Company data to file " + e.toString());
        }
    }
}
