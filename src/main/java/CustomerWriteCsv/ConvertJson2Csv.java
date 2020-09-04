package CustomerWriteCsv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertJson2Csv {

    public static void main(String[] args) {
        // Step 1: Read JSON File to List Objects
        String jsonStr = "[{\"id\":\"1\",\"name\":\"Jack Smith\",\"address\":\"Massachusetts\",\"age\":23},{\"id\":\"2\",\"name\":\"Adam Johnson\",\"address\":\"New York\",\"age\":27},{\"id\":\"3\",\"name\":\"Katherin Carter\",\"address\":\"Washington DC\",\"age\":26},{\"id\":\"4\",\"name\":\"Jack London\",\"address\":\"Nevada\",\"age\":33},{\"id\":\"5\",\"name\":\"Jason Bourne\",\"address\":\"California\",\"age\":36}]";

        List<Customer> customers = convertJsonString2Objects(jsonStr);

        // Step 2: Write List Objects to CSV File
        writeListObjects2CsvFile(customers, "customers.csv");
    }

    /**
     *
     * Convert JSON String to List Java Objects
     *
     * @param pathFile
     * @return
     */
    private static List<Customer> convertJsonString2Objects(String jsonString){
        List<Customer> customers = null;

        try {
            customers = new ObjectMapper().readValue(jsonString, new TypeReference<List<Customer>>(){});
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Write List Objects to CSV File
     *
     * @param customers
     */
    private static void writeListObjects2CsvFile(List<Customer> customers, String pathFile) {
        final String[] CSV_HEADER = { "id", "name", "address", "age" };

        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;

        try {
            fileWriter = new FileWriter(pathFile);
            csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(CSV_HEADER));

            for (Customer customer : customers) {
                List<String> data = Arrays.asList(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        String.valueOf(customer.getAge()));

                csvPrinter.printRecord(data);
            }
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvPrinter.close();
            } catch (IOException e) {
                System.out.println("Flushing/closing error!");
                e.printStackTrace();
            }
        }
    }
}
