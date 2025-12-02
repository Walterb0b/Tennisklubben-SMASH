package main.java.util;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public void writeFile(String fileInput, String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            bw.write(fileInput);
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
    public ArrayList<String[]> readFromFile(String filename){
        ArrayList<String[]> fileContent = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                fileContent.add(parts);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent;
    }
}
