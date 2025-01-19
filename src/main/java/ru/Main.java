package ru;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int randoNumber = (int) (Math.random() * 99);
        System.out.println(getWord());
    }

    public static String getWord() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/dictionary.txt"));) {
            String line = fileReader.readLine();
            String[] dictionary = line.split(",");
            int randomNumber = (int) (Math.random() * 99);
            return dictionary[randomNumber].trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}