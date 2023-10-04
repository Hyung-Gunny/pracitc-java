package 정보보안2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ciphertext to decrypt: ");
        String ciphertext = sc.nextLine();

        System.out.println("Decrypted sentences:");

        for (int key = 0; key < 26; key++) {
            String decryptedText = decrypt(ciphertext, key);

            System.out.println("Key " + key + ": " + decryptedText);
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
}
