import java.util.Scanner;

public class Main {
    private static int publicModulus;
    private static int publicKey;
    private static int publicNumber;

    private static void printPrivateData() {
        
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the public modulus:");
        publicModulus = input.nextInt();

        System.out.println("Enter Alice's public key:");
        publicKey = input.nextInt();

        System.out.println("Enter Bob's public number:");
        publicNumber = input.nextInt();

        printPrivateData();
    }
}