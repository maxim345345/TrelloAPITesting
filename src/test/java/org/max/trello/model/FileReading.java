package org.max.trello.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileReading {

    private String fileName;

    public FileReading(String fileName) {
        this.fileName = fileName;
    }

    public String fileReadings() {

        String line = null;
        StringBuilder tempbuilder = new StringBuilder();
        try {
            FileReader fileReader =
                    new FileReader(fileName);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                tempbuilder.append(line);
                tempbuilder.append("\n");
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        return tempbuilder.toString();

    }

    public List<String> splitIntoWords() {
        List<String> words = new ArrayList<>();
        String newString = fileReadings();

        BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
        iterator.setText(newString);
        int start = iterator.first();

        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            String sentence = newString.substring(start, end);
            //System.out.println(sentence);
            words.add(sentence);
        }
        return words;
    }

    public static void main(String[] args) {

        FileReading file = new FileReading("D:/temp.txt");
        List<String> words = file.splitIntoWords();
        System.out.println(words.get(2));

    }
}