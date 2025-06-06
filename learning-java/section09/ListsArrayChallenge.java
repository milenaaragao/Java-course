import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in); // interaction with user
// now I will create a while loop for each iteration
// grab an integer from the user.

    public static void main(String[] args) {

        boolean flag = true;
        ArrayList<String> groceries = new ArrayList<>(); // list of groceries
        while (flag) { // using that flag as the condition.
            printActions(); // first execute print actions
            switch (Integer.parseInt(scanner.nextLine())) { // compound expression. - involves multiple methods calls
                case 1 -> addItems(groceries); //first method
                case 2 -> removeItems(groceries);
                default -> flag = false; // if the user enter sth else rather than 1 or 2. break the while loop
            }
            groceries.sort(Comparator.naturalOrder()); // argument of the list
            System.out.println(groceries);
        }
    }

    private static void addItems(ArrayList<String> groceries) {

        System.out.println("Add item(s) [separate items by comma]:"); // prompt the user to enter a list of items
        String[] items = scanner.nextLine().split(","); // enter items on a single line separated by commas - reads data
//        groceries.addAll(List.of(items));

        for (String i: items) { // for loop to check
            String trimmed = i.trim(); // array element getting rid of leading and trailing while space if the user included
            if (groceries.indexOf(trimmed) < 0) { // check if the element is already in the list
                groceries.add(trimmed); // so add trimmed data
            }
        }
    }

    private static void removeItems(ArrayList<String> groceries) {

        System.out.println("Remove item(s) [separate items by comma]:"); // method to remove items for the iteration.
        String[] items = scanner.nextLine().split(",");

        for (String i: items) {
            String trimmed = i.trim();
            groceries.remove(trimmed);
        }
    }

    // using text block feature
    private static void printActions() {
// textBlock is a way to format multi-line strings (using three """")
        String textBlock = """
                Available actions:
                                
                0 - to shutdown
                                
                1 - to add item(s) to list (comma delimited list)
                                
                2 - to remove any items (comma delimited list)
                                
                Enter a number for which action you want to do:""";
        System.out.print(textBlock + " "); // herew I am adding extra space when I print this because of the text block
    }
}
