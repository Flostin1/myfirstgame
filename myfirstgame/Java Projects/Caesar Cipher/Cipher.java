/*
 1. Ask the user to input their message and their alphabetic shift.
 2. Start the encryption process:
     * Loop through each character in the message:
     * * Is the character a letter or a number?
     * * * If yes, then shift the character by the given shift and add it to the new encrypted message.
     * * * If no, then ass it as is and move onto the next character.
 3. Show the encrypted message to the user.
 4. Start the decryption process:
     * Sort the alphabet the letter frequency (how often each letter is used in standard English).
     * Find the most frequently used letter in the encrypted message.
     * Starting with 'e', assume it's actually the most frequency letter in the original message:
     * * Calculate a shift of the alphabet that will shift the encrypted message back to the oringal message.
     * * Use this shift and pass it into the encryption function.
     * * Ask the user if the "decrypted" message is the original message:
     * * * If yes, then celebrate.
     * * * If no, then assume the next letter in the sorted alphabet and make another attempt.
 */

import java.util.Scanner;

public class Cipher {
    public static String newline = System.getProperty("line.separator");

    // Checks for character types (lowercase, UPPERCASE, etc..) and returns the 
    // starting character and the amount of possible characters.
    public static int[] returnUnicodeData(int charUnicode) {
        int unicodeRange, unicodeOffset;

        // Checks for UPPERCASE letters.
        if (charUnicode >= 65 && charUnicode <= 90) {
            unicodeOffset = 65;
            unicodeRange = 26;
        // Checks for lowercase letters.
        } else if (charUnicode >= 97 && charUnicode <= 122) {
            unicodeOffset = 97;
            unicodeRange = 26;
        // Checks for digits between 0 and 9.
        } else if (charUnicode >= 48 && charUnicode <= 57) {
            unicodeOffset = 48;
            unicodeRange = 10;
        // If a character is neither a letter of number, simply add it as is.
        } else {
            return null;
        }

        return new int[]{unicodeOffset, unicodeRange};
    }

    // Encrypts a message using the ceaser cipher.
    public static String encrypt(String message, int offset) {
        String encryptedMessage = "";
        int length = message.length();

        // Iterates through each character of the message and changes it based on the given shift
        int unicodeOffset, unicodeRange;
        for (int i = 0; i < length; i++) {
            int charUnicode = (int)message.charAt(i);
            int[] unicodeData = returnUnicodeData(charUnicode);

            // Only changes a character if it is a letter or digit
            if (unicodeData != null) {
                unicodeOffset = unicodeData[0];
                unicodeRange = unicodeData[1];
            } else {
                encryptedMessage += message.charAt(i);
                continue;
            }

            // Adds the offset and then uses modular arithmetic to loop a letter or number back to its start.
            char encryptedChar = (char)((charUnicode - unicodeOffset + offset) % unicodeRange + unicodeOffset);
            encryptedMessage += Character.toString(encryptedChar);
        }

        return encryptedMessage;
    }

    // Attempts to decrypt a message by starting at 'e' and asking the user until it is correct.
    public static String decrypt(String message) {
        Scanner input = new Scanner(System.in);

        // The alphabet sorted by letter frequency.
        String stringFrequencyAlphabet = "etaoinsrhdlucmfywgpbvkxqjz";
        char[] frequencyAlphabet = stringFrequencyAlphabet.toCharArray();

        // Stores a counter for each letter in the frequency alphabet
        int[] frequencyTable = new int[frequencyAlphabet.length];

        // Counts the amount of times each letter is used in the encrypted message
        int length = message.length();
        for (int i = 0; i < length; i++) {
            char character = message.charAt(i);

            if (Character.isLetter(character)) {
                String letter = Character.toString(Character.toLowerCase(character));
                int index = stringFrequencyAlphabet.indexOf(letter);

                frequencyTable[index]++;
            }
        }

        // Finds the most frequent letter in the frequency table
        char mostFrequentLetter = 'e';
        int mostFrequentIndex = 0;
        for (int i = 0; i < frequencyTable.length; i++) {
            if (frequencyTable[i] > frequencyTable[mostFrequentIndex]) {
                mostFrequentIndex = i;
            }
        }

        mostFrequentLetter = frequencyAlphabet[mostFrequentIndex];

        // Starting at 'e', this loops through each letter of the frequency alphabet 
        // and assumes it is the most frequent letter in the message.
        for (char letter : frequencyAlphabet) {
            // Calculates the 
            int offset = ((26 - ((int)mostFrequentLetter - (int)letter)) + 26) % 26;

            System.out.println(newline + "Is this your decrypted message? y / n");
            System.out.println(encrypt(message, offset));

            char answer = input.nextLine().charAt(0);
            // If the user answered yes, celebrate
            if (Character.toLowerCase(answer) == 'y') {
                input.close();
                return newline + "Horray! Your message has now been decrytped for later use...";
            }
        }

        input.close();
        return newline + "Sorry, I couldn't decrypt your message.";
    }

    public static void main(String[] args) {
        // Uses a Scanner object for user input
        Scanner input = new Scanner(System.in);


        String message = "";
        String encryptedMessage = "";
        int offset = 0;

        System.out.println("Enter your message:");
        message = input.nextLine();

        System.out.println(newline + "How many letters do you want to shift by?");
        offset = input.nextInt();

        System.out.println(newline + "Here is your encrypted message:");

        encryptedMessage = encrypt(message, offset);

        System.out.println(encryptedMessage);

        System.out.println(decrypt(encryptedMessage));

        input.close();
    }
}
