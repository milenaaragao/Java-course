import java.util.ArrayList; // import for dynamic lists
import java.util.Arrays; // creating lists from arrays
import java.util.Comparator; //to compare and sort objects
import java.util.List;

public class Main {

    // creating a compact data class
    // it automatically provides constructor, getters, equals, hashCode and toString

    //I override TOSTRING to make it print names nicely.
    record Person (String firstName, String lastName) {

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {

        // creating a list of Person records with various first and last names

        List<Person> people = new ArrayList<>(Arrays.asList( // "Arrays.aslist" = fixed-size list.
                                                            // but when I use NEW ArrayList<>, makes it a dynamic list
                                                            // I will be able now to call .add() or .remove() etc
                new Main.Person("Lucy", "Van Pelt"),
                new Person("Sally", "Brown"),
                new Person("Linus", "Van Pelt"),
                new Person("Peppermint", "Patty"),
                new Person("Charlie", "Brown")));

        // Using anonymous class Comparator
        // it will compare two Person objects by their last name.

        var comparatorLastName = new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) { // "o" are parameter names. stands for "object" being compared
                return o1.lastName().compareTo(o2.lastName());
            }
        };

        //LAMBDA COMPARATOR ///////////////////////////////// IMPORTANT:
        // lambda replaces the compare method.

        people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
        System.out.println(people); // sorts the list by last name using a lambda expression instead of anonymous class


        // Enhanced comparator interface:
        // this is a generic interface that extends COMPARATOR<T> and adds a SECONDLEVEL() method.
        // generic T because can work with any type .
        // this lets me define PRIMARY and SECONDARY sorting logic.

        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }

        //anonymous class with enhanced comparator:

        var comparatorMixed = new EnhancedComparator<Person>() {

            //first sort by last name

            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName().compareTo(o2.lastName());
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

            // then sort by first name

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName().compareTo(o2.firstName());
            }
        };

        people.sort(comparatorMixed);
        System.out.println(people);

    }
}
