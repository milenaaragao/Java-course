public class Main {

    public static void main(String[] args) {

        for(int counter = 1; counter <= 5; counter++) {
            System.out.println(counter);
        }

        for (double rate = 2.0; rate <= 5.0; rate++) {
            double interestAMount = calculateInterest(10000, rate);
            System.out.println("10,000 at " + rate + "% interest = " + interestAMount);
        }

        for (double i = 7.5; i <= 10; i += 0.25) {
            double interestAMount = calculateInterest(100.00, i);
            if (interestAMount > 8.5) {
                break;
            }
            System.out.println("$100.00 at " + i + "% interest = $" + interestAMount);
        }


    }

    public static double calculateInterest(double amount, double interestRate) {

        return (amount * (interestRate / 100));
    }


}
