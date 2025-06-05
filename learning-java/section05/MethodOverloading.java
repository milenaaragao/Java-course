
///////////////////////////// MAIN class  ///////////////////////////////////

public class Main {

    public static void main(String[] args) {

        System.out.println("New score is " + calculateScore( "Tim", 500));
        System.out.println("New score is " + calculateScore(10));


    }

    public static int calculateScore(String playerName, int score) {

        System.out.println("Player " + playerName + " scored " + score + " points");
        return score*1000;

    }

    public static int calculateScore(int score) {

        return calculateScore("Anonymous", score);
    }

    public static int calculateScore() {

        System.out.println("No player name, no player scored.");
        return 0;
    }


}


////////////////// Overloading Class ////////////////////////////////////


    public static void main(String[] args) {

        System.out.println(getDurationString(-3945)); //This is the first case
        System.out.println(getDurationString(-65, 45));
        System.out.println(getDurationString(65, 145));
        System.out.println(getDurationString(65,45));
        System.out.println(getDurationString(3945));

    }

    public static String getDurationString(int seconds) {

        if (seconds < 0) {
            return "Invalid data for seconds(" + seconds + "), must be a positive integer";
        }
            int minutes = seconds / 60;
            return getDurationString(seconds/60, seconds % 60);

    }

    public static String getDurationString(int minutes, int seconds) {

        if (minutes < 0) {
            return "Invalid data for minutes(" + minutes + "), must be a positive integer";
        }

        if (seconds < 0 || seconds > 59) {
            return "Invalid data for seconds(" + seconds + "), must be between 0 and 59";
        }

        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;

        return hours + "h " + remainingMinutes + "m " + seconds + "S";

    }


}
