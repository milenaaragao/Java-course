public class Hello {

    public static void main(String[] args) {
        System.out.println("Hello, Milena");

        boolean isAlien = false;
        if (isAlien == false) {
            System.out.println("It is not a alien");
            System.out.println("And I am afraid of aliens");
        }

        int topScore = 80;
        if (topScore < 100) {
            System.out.println("You got the high score!");
        }

        int secondTopScore = 81;
        if ((topScore > secondTopScore) && (topScore < 100)) {
            System.out.println("Greater than the top score and less than 100");
        }

        if ((topScore > 90) || (secondTopScore <= 90)) {
            System.out.println("Either or both of the conditions are true");
        }

        int newValue = 50;
        if (newValue == 50) {
            System.out.println("This is true!");
        }

        boolean isCar = false;
        if (!isCar) {
            System.out.println("This is not supposed to happen");
        }

        String makeOfCar = "Volkswagen";
        boolean isDomestic = makeOfCar == "Volkswagen" ? false : true;

        if (isDomestic) {
            System.out.println("This car is domestic to our country");
        }

        double variableDouble = 20.00d;
        double variableDouble2 = 80.00d;
        double newVariable = (variableDouble + variableDouble2) * 100.00d;
        System.out.println("newVariable = " + newVariable);
        double theRemainder = newVariable % 40.00;
        System.out.println("theRemainder = " + theRemainder);
        boolean isNoRemainder = (theRemainder == 0)? true: false;
        System.out.println("isNoRemainder = " + isNoRemainder);
        if (isNoRemainder) {
            System.out.println("Got some remainder");
        }


    }
}

