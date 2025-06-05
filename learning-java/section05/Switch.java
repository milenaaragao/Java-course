public class Main {

    printDayOfWeek (2);
}

    public static void main(String[] args) {

//        int value = 3;
//        if (value == 1) {
//
//            System.out.println("Value was 1");
//
//        } else if (value == 2) {
//            System.out.println("Value was 2");
//
//        } else {
//            System.out.println("Was not 1 or 2");
//        }

        int switchValue = 3;

        switch (switchValue) {
            case 1 -> System.out.println("Value was 1");
            case 2 -> System.out.println("Value was 2");
            case 3, 4, 5, 6, 7, 8, 9 -> {
                System.out.println("Was a 3, 4, 5, 6, 7, 8, 9");
                System.out.println("Actually it was a " + switchValue);
            }
            default -> System.out.println("Was not 1, 2, 3, 4, 5, 6, 7, 8, 9");
        }
        String month = "MAY";
        System.out.println(month + " is in the " + getQuarter(month) + " quarter");

    }



    public static String getQuarter(String month) {

        return switch (month) {
            case "JANUARY", "FEBRUARY", "MARCH" -> { yield "1st"; }
            case "APRIL", "MAY", "JUN" -> "2nd";
            case "JUL", "AUGUST", "SEPTEMBER" -> "3rd";
            case "OCTOBER", "NOVEMBER", "DECEMBER" -> "4th";
            default -> {
                String badresponse = month + " is bad";
                yield badresponse;
            }
        };

    }


    public static void printDayOfWeek(int day) {

        String dayOfWeek = switch (day) {
            case 0 -> { yield "Sunday"; }
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "Invalid Day";
        };
        System.out.println(day + " stands for " + dayOfWeek);

}
