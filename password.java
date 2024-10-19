import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+";

    private static final String ALL_CHARS = LOWER_CASE + UPPER_CASE + NUMBERS + SPECIAL_CHARS;

    public static void main(String[] args) {
        int passwordLength = 12; // Change this to generate passwords of different lengths
        System.out.println("Generated Password : " + generatePassword(passwordLength));
    }

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);
        Random random = new SecureRandom();

        // Ensure password contains at least one of each type of character
        password.append(getRandomChar(LOWER_CASE, random));
        password.append(getRandomChar(UPPER_CASE, random));
        password.append(getRandomChar(NUMBERS, random));
        password.append(getRandomChar(SPECIAL_CHARS, random));

        // Fill the rest of the password with random characters
        for (int i = 4; i < length; i++) {
            password.append(getRandomChar(ALL_CHARS, random));
        }

        // Shuffle the password to avoid the first characters always being in the same character type order
        String passwordStr = password.toString();
        char[] passwordArray = passwordStr.toCharArray();
        shuffleArray(passwordArray, random);
        return new String(passwordArray);
    }

    private static char getRandomChar(String chars, Random random) {
        return chars.charAt(random.nextInt(chars.length()));
    }

    private static void shuffleArray(char[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}