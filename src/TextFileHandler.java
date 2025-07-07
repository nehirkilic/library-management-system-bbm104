import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for handling text file operations such as reading and writing.
 */
class TextFileHandler {

    /**
     * Reads a text file line by line and splits each line by commas.
     *
     * @param filePath the path to the input file
     * @return a list of string arrays, where each array represents the parts of a line
     */
    public static List<String[]> readTxt(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                data.add(parts);
            }
        } catch (IOException e) {
            System.out.println("Error:" + filePath);
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Appends a single line to the specified text file.
     *
     * @param filePath the path to the output file
     * @param line     the line to be written
     */
    public static void writeLine(String filePath, String line) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}