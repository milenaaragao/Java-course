public class Main {

    public static void main(String[] args) {

        int count = 0; // Count how many prime numbers we find

        // Loop through numbers from 10 to 50
        for (int i = 10; i <= 50; i++) {
            // Use your method to check if "i" is a prime number
            if (isPrime(i)) {
                System.out.println("number " + i + " is a prime number");
                count++;

                // Stop after finding 3 prime numbers
                if (count == 3) {
                    System.out.println("Found 3 - Exiting for loop");
                    break;
                }

            }

        }
    }
// checker method: determines whether a given number is prime
    public static boolean isPrime(int wholeNumber) {


        // If the number is less than or equal to 2, only 2 is prime
        if (wholeNumber <= 2) {
            return (wholeNumber == 2);
        }

        // loop through possible divisors from 2 to one less than the number
        for (int divisor  = 2; divisor < wholeNumber; divisor++) {
            if (wholeNumber % divisor == 0) {
                return false; // not prime is divisible
            }
        }

        return true; // prime if no divisors found
    }
}
