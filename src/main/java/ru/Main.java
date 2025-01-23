package ru;

import java.io.*;
import java.util.*;

public class Main {
    public static int countOfLife;
    public static List<String> wordForGuessing;
    public static List<String> wordWhichGuessPlayer;
    public static Set<String> triedLetter = new HashSet<>();

    public static void main(String[] args) {
        print("Добро пожаловать в игру Виселица!\n");
        print("Вам нужно отгадать слово, у вас есть 6 попыток\n");
        prepareGame();
        print(wordForGuessing.toString());
        while (true) {
            if (checkEndGame()) return;
            writeInformationForPlayer();
            String letter = write();
            compareAnswerOfPlayer(letter);
            drawHangman();
        }
    }

    private static boolean checkEndGame() {
        if (countOfLife == 0) {
            print("Вы проиграли!");
            print("Было загадано слово: ");
            print(wordForGuessing.toString());
            print("\n");
            return askAboutEndGame();
        }
        if (wordForGuessing.equals(wordWhichGuessPlayer)) {
            print("Вы выиграли.\n");
            return askAboutEndGame();
        }
        return false;
    }

    private static boolean askAboutEndGame() {
        print("Если хотите начать игру заново введите \"Д\", если хотите завершить игру введите \"Н\".\n");
        String answer = write();
        if (answer.equalsIgnoreCase("Д")) {
            prepareGame();
        } else {
            return true;
        }
        return false;
    }


    public static void writeInformationForPlayer() {
        print("Введите букву которую хотите угадать.\n");
        print("Оставшееся количество жизней: " + countOfLife + "\n");
        print("Введенные буквы которых нет в загадоном слове: ");
        if (triedLetter != null) {
            triedLetter.forEach(Main::print);
            print("\n");
        }
        print(wordWhichGuessPlayer.toString());
        print("\n");
    }

    public static void compareAnswerOfPlayer(String letter) {
        boolean isWasGuessedLetter = false;
        for (int i = 0; i < wordForGuessing.size(); i++) {
            if (wordForGuessing.get(i).equalsIgnoreCase(letter)) {
                wordWhichGuessPlayer.set(i, letter);
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
        wordWhichGuessPlayer = new ArrayList<>();
        for (int i = 0; i < wordForGuessing.size(); i++) {
            wordWhichGuessPlayer.add("*");
        }
        triedLetter = new HashSet<>();
    }

    public static List<String> getWord() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/dictionary.txt"))) {
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

    public static String write() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}