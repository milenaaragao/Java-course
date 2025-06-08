package dev.lpa;


import java.util.LinkedList;
import java.util.Scanner;

// creating a type that has fields name and distance, in this case A RECORD (special kind of class)
// using a record I have a constructor, accessor methods, and an overridden toString.
record Place(String name, int distance) {

    // it is overridden by default for toString(). to return what is in brackets.
    @Override
    public String toString() {
        return String.format("%s (%d)", name, distance);
    }
}

public class Main {
    public static void main(String[] args) {

        //The code below is saying that placesToVisit is going to contain a LinkedList of my Place records.
        LinkedList<Place> placesToVisit = new LinkedList<>();

        // parsing an object
        Place adelaide = new Place("Adelaide", 1374);
        addPlace(placesToVisit, adelaide); //calling it

        addPlace(placesToVisit, new Place("adelaide", 1374));
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));
        addPlace(placesToVisit, new Place("Darwin", 3972));
        addPlace(placesToVisit, new Place("Melbourne", 877));

        placesToVisit.addFirst(new Place("Sydney", 0));
        System.out.println(placesToVisit);

        //local variables:
        var iterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;

        printMenu();

        while (!quitLoop) {
            if (!iterator.hasPrevious()) {
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("Final : " + iterator.previous());
                forward = false;
            }
            System.out.print("Enter Value: ");
            String menuItem = scanner.nextLine().toUpperCase().substring(0, 1);

            switch (menuItem) {
                case "F":
                    System.out.println("User wants to go forward");
                    if (!forward) {           // Reversing Direction
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();  // Adjust position forward
                        }
                    }

                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }

                    break;

                case "B":
                    System.out.println("User wants to go backwards");
                    if (forward) {           // Reversing Direction
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();  // Adjust position backwards
                        }
                    }

                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                    break;

                case "M":
                    printMenu();
                    break;

                case "L":
                    System.out.println(placesToVisit);
                    break;

                default:
                    quitLoop = true;
                    break;
            }
        }



    }

    //the method is going to let me add the place in order of the distance from the starting place
    // and also check for duplicates.

    // Takes TWO PARAMETERS. LIST: linkedList of place objects and PLACE: a place object I want to add to the list.
    private static void addPlace(LinkedList<Place> list, Place place) {

        if (list.contains(place)) { //does list.contains passing place find a match?
            System.out.println("Found duplicate: " + place); //if yes return it
            return;
        }

        for (Place p : list) { //using enhanced for loop with local variable p which is a Place Record
            if (p.name().equalsIgnoreCase(place.name())) { //loops trough each place p in the list
                                                            // compares the existing place's name with new places name
                                                            // uses .equalsIgnoreCase to ignore letter case
                System.out.println("Found duplicate: " + place); // if a match is found prints it
                return;
            }
        }

        int matchedIndex = 0; //index variable (keep track of what element position is being processed
        for (var listPlace: list) { //loops through the list of place objects list
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place); //tracking the current index using matchedIndex
                                                //for each item, compares the new place's distance with the current
                                                //item's distance. if the new places distance is smaller,
                                                //it inserts the new place at that index.
                //if loops finishes without finding a smaller distance it adds the place at the end of the list.
                return;
            }

            matchedIndex++;
        }

        list.add(place);
    }

    private static void printMenu() {

        // with triple quotes because I am using text box

        System.out.println("""
                Available actions (select word or letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit""");
    }




}
