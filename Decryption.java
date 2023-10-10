package 정보보안2;

import java.util.Arrays;
import java.util.Scanner;

public class main {

    public static void main(String[] args)
    {
    	Scanner sc=new Scanner(System.in);
    	
    	System.out.print("Enter cipher to decrypt:");
    	String ciphertext=sc.nextLine();
      
        boolean foundDecryption = false;

        for (int k = 0; k < 26; k++) {
            StringBuilder decryptedText = new StringBuilder();

            // 현재 키를 사용하여 텍스트를 해독합니다.
            for (char letter : ciphertext.toCharArray()) {
                if (Character.isLetter(letter)) {
                    char shiftedChar = (char) ((((Character.toLowerCase(letter) - 'a' - k + 26) % 26) + 'a'));
                    if (Character.isUpperCase(letter)) {
                        decryptedText.append(Character.toUpperCase(shiftedChar));
                    } else {
                        decryptedText.append(shiftedChar);
                    }
                } else {
                    decryptedText.append(letter);
                }
            }

            // 올바른 문장 판별
            if (isValidSentence(decryptedText.toString())) {
                System.out.println("Decrypted sentence: "+ decryptedText.toString());
                foundDecryption = true;
            }
        }
        
        if (!foundDecryption) {
            System.out.println("Not a valid sentence!");
        }
    }

    // 올바른 문장 판별 함수
    private static boolean isValidSentence(String text) {
        String[] words = text.split("\\s+");
        int validWordCount = 0;
        for (String word : words) {
            if (isValidWord(word)) {
                validWordCount++;
            }
        }
        // 문장이 유효하려면 단어의 수가 2개 이상이어야 합니다.
        return validWordCount >= 2;
    }

    // 올바른 단어 판별 함수
    private static boolean isValidWord(String word) {
        String[] commonWords = {"this", "simple", "test", "of", "a", "in", "is", "it", "you", "that"};
        return word.length() > 1 && contains(commonWords, word.toLowerCase());
    }

    private static boolean contains(String[] arr, String target) {
        return Arrays.stream(arr).anyMatch(target::equals);
    }
}
