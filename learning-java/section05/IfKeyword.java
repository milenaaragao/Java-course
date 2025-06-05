
///////////////////////////// MAIN CLASS //////////////////////////////////

public class Main {
    public static void main(String[] args) {

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        if (score == 5000) {
            System.out.println("Your score was 5000");
        }

        if (score < 5000) {
            System.out.println("Your score was " + score);
        } else {
            System.out.println("Got here");
        }

        if (score <= 5000) {
            System.out.println("Your score was less than" +
                    " or equal to " +
                     score);
        } else {
            System.out.println("Got here");
        }

        if (score < 5000 && score > 1000) {
            System.out.println("Your score was less than 5000" +
                    " but greater than 1000");
        } else if (score < 1000) {
            System.out.println("Your score was less than 1000");
        } else {
            System.out.println("Got here");
        }
    }
}


///////////////////////////// challenge class  //////////////////////////////////


public class MainChallenge {
    public static void main(String[] args) {

        int highScore = calculateScore(true, 800, 5, 100);
        System.out.println("High score is: " + highScore);

        System.out.println("The next high score is: " +
                calculateScore(true, 10000, 8, 200));

    }

    public static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        int finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            finalScore += 1000;
        }

        return finalScore;

    }
}
