// Exercise 01
// Author: Milene Pacheco Esquinarila

import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String hangman1 = "+-------+\n"
                + "|  |  \n"
                + "|     \n"
                + "|     \n"
                + "|     \n"
                + "|     \n"
                + "========= ";

        String hangman2 = "+-------+\n"
                + "|  |  \n"
                + "|  O  \n"
                + "|     \n"
                + "|     \n"
                + "|     \n"
                + "=========";

        String hangman3 = "+-------+\n"
                + "|  |  \n"
                + "|  O  \n"
                + "|  |  \n"
                + "|     \n"
                + "|     \n"
                + "=========";

        String hangman4 = "+-------+\n"
                + "|  |  \n"
                + "|  O  \n"
                + "| /|  \n"
                + "|     \n"
                + "|     \n"
                + "=========";

        String hangman5 = "+-------+\n"
                + "|  |   \n"
                + "|  O   \n"
                + "| /|\\ \n"
                + "|      \n"
                + "|      \n"
                + "=========";

        String hangman6 = "+-------+\n"
                + "|  |   \n"
                + "|  O   \n"
                + "| /|\\ \n"
                + "| /    \n"
                + "|      \n"
                + "=========";

        String hangman7 = "+-----+\n"
                + "|  |   \n"
                + "|  O   \n"
                + "| /|\\ \n"
                + "| / \\ \n"
                + "|      \n"
                + "=========";

        String[] hangmanFigures = { hangman1, hangman2, hangman3, hangman4, hangman5, hangman6, hangman7 };
        int attempts = 1;
        int errorCount = 1;
        String letter;
        String progress = "";
        String[] words = { "programming", "java", "indentation", "classes", "objects", "developer", "testing" };
        String secretWord = getRandomWord(words);

        for (int i = 0; i < secretWord.length(); i++)
            progress = progress + "_ ";

        System.out.println(hangmanFigures[0]);
        displayBlanks(secretWord);
        System.out.println("\n");

        while (errorCount <= 6) {
            letter = enterLetter(scanner);
            if (letterInSecretWord(letter, secretWord))
                progress = displayUpdatedBlanks(letter, secretWord, progress);
            else {
                System.out.println(hangmanFigures[errorCount]);
                errorCount++;
                progress = displayUpdatedBlanks(letter, secretWord, progress);
            }
            attempts++;
            for (int i = 0; i < progress.length(); i++) {
                if (!progress.substring(i, i + 1).equals("_")) {
                    if (i == progress.length() - 1) {
                        System.out.println("You won!");
                        System.out.println("Number of attempts: " + attempts);
                        System.exit(0);
                    }
                } else
                    break;
            }
            System.out.println("\n");
        }
        System.out.println("You lost :(");
        System.out.println("The secret word was: " + secretWord);

        scanner.close();
    }

    public static String getRandomWord(String[] words) {
        int index;
        int lastIndex = words.length - 1;
        int firstIndex = 0;

        index = (int) ((Math.random() * (lastIndex - firstIndex + 1) + firstIndex));

        return words[index];
    }

    public static void displayBlanks(String word) {
        for (int i = 0; i < word.length(); i++) {
            System.out.print("_ ");
        }
    }

    public static String enterLetter(Scanner scanner) {
        String letter;
        System.out.print("Enter a letter: ");
        letter = scanner.next();

        while (letter.length() != 1 || !isValidCharacter(letter)) {
            System.out.print("Enter a letter: ");
            letter = scanner.next();
        }

        return letter;
    }

    public static boolean letterInSecretWord(String letter, String secretWord) {
        for (int i = 0; i < secretWord.length(); i++)
            if (letter.equals(secretWord.substring(i, i + 1)))
                return true;

        return false;
    }

    public static String displayUpdatedBlanks(String letter, String secretWord, String progress) {
        String updatedProgress = "";
        for (int i = 0; i < secretWord.length(); i++)
            if (letter.equals(secretWord.substring(i, i + 1)))
                updatedProgress = updatedProgress + letter + " ";
            else
                updatedProgress = updatedProgress + progress.substring(2 * i, 2 * i + 2);

        System.out.print(updatedProgress);
        return updatedProgress;
    }

    public static boolean isValidCharacter(String letter) {
        String alphabet[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z" };
    
        for (int i = 0; i < alphabet.length; i++) {
            if (letter.equalsIgnoreCase(alphabet[i])) {
                return true;
            }
        }
        return false; 
    }
}