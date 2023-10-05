package 정보보안2;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ciphertext to decrypt: ");
        String ciphertext = sc.nextLine();

        Set<String> validWords = loadWordList(); // Load a word list

        String decryptedSentence = null;

        for (int key = 0; key < 26; key++) {
            String decryptedText = decrypt(ciphertext, key);

            // Check if the decrypted text contains valid words
            if (isValidDecryption(decryptedText, validWords)) {
                decryptedSentence = decryptedText;
                break;
            }
        }

        if (decryptedSentence != null) {
            System.out.println("Decrypted sentence: " + decryptedSentence);
        } else {
            System.out.println("Unable to decrypt with the provided keys.");
        }

        sc.close();
    }

    public static String decrypt(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);

            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                char decryptedChar = (char) (((c - base - key + 26) % 26) + base);
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }

    public static boolean isValidDecryption(String decryptedText, Set<String> validWords) {
        // Check if the decrypted text contains valid words
        String[] words = decryptedText.split("\\s+");
        for (String word : words) {
            if (!validWords.contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public static Set<String> loadWordList() {
        // Load a word list from a file or another data source.
        // For simplicity, you can create a HashSet with some sample words here.
        Set<String> validWords = new HashSet<>();
        validWords.add("hello");
        validWords.add("world");
        // Add more valid words as needed.
        return validWords;
    }
}
