package ru;

import java.io.*;
import java.util.*;

public class Main {
    public static int countOfLife;
    public static String[] wordForGuessing;
    public static String[] wordWhichGuessPlayer;
    public static Set<String> triedLetter = new HashSet<>();

    public static void main(String[] args) {
        print("Добро пожаловать в игру Виселица!\n");
        print("Вам нужно отгадать слово, у вас есть 6 попыток\n");
        prepareGame();

        while (true) {
            if (countOfLife == 0) {
                print("Вы проиграли!");
                print("Было загадано слово: ");
                print(Arrays.toString(wordForGuessing));
                print("\n");
                print("Если хотите начать игру заново введите \"Д\", если хотите завершить игру введите \"Н\".\n");
                String answer = write();
                if (answer.equalsIgnoreCase("Д")) {
                    prepareGame();
                } else {
                    return;
                }
            }
            writeInformationForPlayer();
            String letter = write();
            compareAnswerOfPlayer(letter);
            drawHangman();
        }
    }

    public static void writeInformationForPlayer() {
        print("Введите букву которую хотите угадать.\n");
        print("Оставшееся количество жизней: " + countOfLife + "\n");
        print("Введенные буквы которых нет в загадоном слове: ");
        if (triedLetter != null) {
            triedLetter.forEach(Main::print);
            print("\n");
        }
        print(Arrays.toString(wordWhichGuessPlayer));
        print("\n");
    }

    public static void compareAnswerOfPlayer(String letter) {
        boolean isWasGuessedLetter = false;
        for (int i = 0; i < wordForGuessing.length; i++) {
            if (wordForGuessing[i].equalsIgnoreCase(letter)) {
                wordWhichGuessPlayer[i] = letter;
                isWasGuessedLetter = true;
            }
        }
        if (!isWasGuessedLetter) {
            triedLetter.add(letter);
            countOfLife -= 1;
        }
    }

    private static void drawHangman() {
        switch (countOfLife) {
            case 0:
                print("_____________\n");
                print(" |       |\n");
                print(" O       |\n");
                print("\\|/      |\n");
                print(" |       |\n");
                print("/ \\      |\n");
                print("         |\n");
                print("_____________\n");
                break;
            case 1:
                print("_____________\n");
                print(" |       |\n");
                print(" O       |\n");
                print("\\|/      |\n");
                print(" |       |\n");
                print("/        |\n");
                print("         |\n");
                print("_____________\n");

                break;
            case 2:
                print("_____________\n");
                print(" |       |\n");
                print(" O       |\n");
                print("\\|/      |\n");
                print(" |       |\n");
                print("         |\n");
                print("         |\n");
                print("_____________\n");

                break;
            case 3:
                print("_____________\n");
                print(" |       |\n");
                print(" O       |\n");
                print("\\|       |\n");
                print(" |       |\n");
                print("         |\n");
                print("         |\n");
                print("_____________\n");
                break;
            case 4:
                print("_____________\n");
                print(" |       |\n");
                print(" O       |\n");
                print(" |       |\n");
                print(" |       |\n");
                print("         |\n");
                print("         |\n");
                print("_____________\n");
                break;
            case 5:
                print("_____________\n");
                print(" |       |\n");
                print(" O       |\n");
                print("         |\n");
                print("         |\n");
                print("         |\n");
                print("         |\n");
                print("_____________\n");
                break;
            case 6:
                print("_____________\n");
                print(" |       |\n");
                print("         |\n");
                print("         |\n");
                print("         |\n");
                print("         |\n");
                print("         |\n");
                print("_____________\n");
                break;
        }
    }

    public static void prepareGame() {
        countOfLife = 6;
        wordForGuessing = getWord();
        wordWhichGuessPlayer = new String[wordForGuessing.length];
        for (int i = 0; i < wordForGuessing.length; i++) {
            wordWhichGuessPlayer[i] = "*";
        }
        triedLetter = new HashSet<>();
    }

    public static String[] getWord() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/dictionary.txt"))) {
            String line = fileReader.readLine();
            String[] dictionary = line.split(",");
            int randomNumber = (int) (Math.random() * 99);
            return dictionary[randomNumber].trim().split("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(String text) {
        System.out.print(text);
    }

    public static String write() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}