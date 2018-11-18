import java.util.Scanner;

public class Cipher {
    public static String newline = System.lineSeparator();

    public static String encrypt(String message, String key) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int messageLength = message.length();
        int keyLength = key.length();

        String encryptedMessage = "";
        for (int i = 0; i < messageLength; i++) {
            char keyCharacter = Character.toLowerCase(message.charAt(i % keyLength));
            char character = message.charAt(i);

            if (Character.isLetter(character)) {
                character = Character.toLowerCase(character);
                System.out.println(character);
                int shift = (((int)keyCharacter - (int)character) - 26) % 26;

                character = (char)(((int)character + shift) % 26);
                System.out.println((int)character);
            }

            encryptedMessage += Character.toString(character);
        }

        return encryptedMessage;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String message = "";
        String key = "";

        // Prompts the user to enter their message.
        System.out.println("Enter your message: ");
        message = input.nextLine();

        // Prompts the user to enter their encryption key.
        System.out.println(newline + "Enter your key to encrypt your message: ");
        key = input.nextLine();

        // Returns the encrypted message to the user for later use.
        System.out.println(newline + "Here is your encrypted message: ");
        System.out.println(encrypt(message, key));

        input.close();
    }
}