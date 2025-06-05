public class MethodChallenge {

    public static void main(String[] args) {

        int highScorePosition = calculateHighScorePosition(1500);
        displayHighScorePosition("Tim", highScorePosition);

        highScorePosition = calculateHighScorePosition(1000);
        displayHighScorePosition("Bob", highScorePosition);

        highScorePosition = calculateHighScorePosition(500);
        displayHighScorePosition("Ana", highScorePosition);

        highScorePosition = calculateHighScorePosition(100);
        displayHighScorePosition("John", highScorePosition);

        highScorePosition = calculateHighScorePosition(25);
        displayHighScorePosition("Percy", highScorePosition);

    }

    public static void displayHighScorePosition (String playersName, int highScorePosition) {
        System.out.println(playersName +
                " managed to get into " +
                highScorePosition +
                " on the high score list.");

    }

    public static int calculateHighScorePosition (int playersScore) {

        int position = 0;
        if (playersScore >= 1000) {
            position = 1;
        } else if (playersScore >= 500) {
            position = 2;
        } else if (playersScore >= 100) {
            position = 3;
        } else {
            position = 4;
        }

        return position;

        }

    }


