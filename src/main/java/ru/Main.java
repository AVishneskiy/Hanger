package ru;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int countOfLife;
    public static List<String> wordForGuessing;
    public static List<String> wordWhichGuessPlayer;

    public static void main(String[] args) {
        print("Добро пожаловать в игру Виселица!\n" +
                "Вам нужно отгадать слово, у вас есть 6 попыток\n");
        prepareGame();
        print(
                "_____________\n" +
                        " |       |\n" +
                        " O       |\n" +
                        " |       |\n" +
                        " |       |\n" +
                        "         |\n" +
                        "         |\n" +
                        "_____________\n"
        );
//        while (true) {
//            wordWhichGuessPlayer.forEach(Main::print);
//
//        }
    }

    public static void writeInformationForPlayer() {
        print("Введите букву которую хотите угадать.\n");
        wordWhichGuessPlayer.forEach(Main::print);
        drawHangman(countOfLife);
    }

    private static void drawHangman(int countOfLife) {
        switch (countOfLife) {
            case 0:
                print(
                        "_____________\n" +
                                " |       |\n" +
                                " O       |\n" +
                                "\\|/      |\n" +
                                " |       |\n" +
                                "/ \\      |\n" +
                                "         |\n" +
                                "_____________\n"
                );
                break;
            case 1:
                print(
                        "_____________\n" +
                                " |       |\n" +
                                " O       |\n" +
                                "\\|/      |\n" +
                                " |       |\n" +
                                "/        |\n" +
                                "         |\n" +
                                "_____________\n"
                );
                break;
            case 2:
                print(
                        "_____________\n" +
                                " |       |\n" +
                                " O       |\n" +
                                "\\|/      |\n" +
                                " |       |\n" +
                                "         |\n" +
                                "         |\n" +
                                "_____________\n"
                );
                break;
            case 3:
                print(
                        "_____________\n" +
                                " |       |\n" +
                                " O       |\n" +
                                "\\|       |\n" +
                                " |       |\n" +
                                "         |\n" +
                                "         |\n" +
                                "_____________\n"
                );
                break;
            case 4:
                print(
                        "_____________\n" +
                                " |       |\n" +
                                " O       |\n" +
                                " |       |\n" +
                                " |       |\n" +
                                "         |\n" +
                                "         |\n" +
                                "_____________\n"
                );
                break;
            case 5:
                print(
                        "_____________\n" +
                                " |       |\n" +
                                " O       |\n" +
                                "         |\n" +
                                "         |\n" +
                                "         |\n" +
                                "         |\n" +
                                "_____________\n"
                );
                break;
            case 6:
                print(
                        "_____________\n" +
                                " |       |\n" +
                                "         |\n" +
                                "         |\n" +
                                "         |\n" +
                                "         |\n" +
                                "         |\n" +
                                "_____________\n"
                );
                break;
        }
    }

    public static void prepareGame() {
        countOfLife = 6;
        wordForGuessing = getWord();
        wordWhichGuessPlayer = new ArrayList<>();
        for (int i = 0; i < wordForGuessing.size(); i++) {
            wordWhichGuessPlayer.add("*");
        }
    }

    public static List<String> getWord() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/dictionary.txt"));) {
            String line = fileReader.readLine();
            String[] dictionary = line.split(",");
            int randomNumber = (int) (Math.random() * 99);
            return List.of(dictionary[randomNumber].trim().split(""));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(String text) {
        System.out.print(text);
    }
}