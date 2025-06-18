/////////////////////// main ////////////////////////////

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        for (String s : list) { // traditional for-each loop.
            System.out.println(s);
        }

        System.out.println("-------");
        list.forEach((var myString) -> System.out.println(myString)); //lambda forEach
        // does the same thing as the loop above, but using a lambda expression.
        // forEach() expects a function (called a consumer) that takes each item and does something with it
        // myString is the item from the list


        System.out.println("-------");

        // here i am using lambda with multiple statements
        String prefix = "nato";
        list.forEach((var myString) -> {
            char first = myString.charAt(0); // getting the first letter of each word and combine with prefix "nato"
            System.out.println(prefix + " " + myString + " means " + first);
        });

        //note: VAR MYSTRING = is the Java's type inference feature (var) - it tells Java "Figure out the type of myString

        // ---------------------------------------- another example


        //calculator takes a lambda (add two integers).
        // calculator takes a lambda (divides two doubles)
        // lastly concatenates two strings in uppercase

        int result = calculator((var a, var b) -> a + b,5, 2);
        var result2 = calculator((a, b) -> a / b, 10.0, 2.5);
        var result3 = calculator(
                (a, b) -> a.toUpperCase() + " " + b.toUpperCase(),
                "Ralph", "Kramden");


    }

    // from the functional interface:
    public static <T> T calculator(Operation<T> function, T value1, T value2) {

        T result = function.operate(value1, value2); //this call the lambda
        System.out.println("Result of operation: " + result);
        return result;
    } // it needed a generic method that works with any type (called from interface)
    // then it pass in different behavior using lambdas
}


/////////////////// operation interface ///////////////////////

@FunctionalInterface //verify if it is a functional interface


// this functional interface has exactly one abstract method - this is what allows lambdas to be used
public interface Operation<T> {

    //generic interface

    T operate(T value1, T value2); //takes two args. of the same type and return a value also the same type




}
