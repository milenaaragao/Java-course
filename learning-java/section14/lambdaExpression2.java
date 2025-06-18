///////////////// main /////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("-------");
        list.forEach((var myString) -> System.out.println(myString));

        System.out.println("-------");
        String prefix = "nato";
        list.forEach((var myString) -> {
            char first = myString.charAt(0);
            System.out.println(prefix + " " + myString + " means " + first);
        });

        int result = calculator((var a, var b) -> a + b,5, 2);
        var result2 = calculator((a, b) -> a / b, 10.0, 2.5);
        var result3 = calculator(
                (a, b) -> a.toUpperCase() + " " + b.toUpperCase(),
                "Ralph", "Kramden");


        // new example ------------------------------------

        // list representing latitude and longitude
        var coords = Arrays.asList(
                new double[]{47.2160, -95.2348},
                new double[]{29.1566, -89.2495},
                new double[]{35.1556, -90.0659});

        coords.forEach(s -> System.out.println(Arrays.toString(s))); // print each point

        // USING BICONSUMER to process points ********* IMPORTANT
        //BiConsumer is a BUILT-IN functional interface that accepts two inputs and return nothing
        BiConsumer<Double,Double> p1 = (lat, lng) ->
                System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng); // but print in this formatted way

        //processing one point with lambda

        var firstPoint = coords.get(0); // grabs the first coordinate
        processPoint(firstPoint[0], firstPoint[1], p1); // passes it to processPoint() along with the p1 lambda

        System.out.println("-------");

        //processing all points: loop through all coordinates
        coords.forEach(s -> processPoint(s[0], s[1], p1)); // uses p1 lambda to print each
        coords.forEach(s -> processPoint(s[0], s[1], // do same thing as above but without p1
                (lat, lng) ->
                        System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng)));

        // Filtering a string list.
        list.removeIf(s -> s.equalsIgnoreCase("bravo")); // removes "bravo" (case-insensitive) from list
        list.forEach(s -> System.out.println(s));

        list.addAll(List.of("echo", "easy", "earnest")); //add new elements to the list
        list.forEach(s -> System.out.println(s));

        System.out.println("-------");
        list.removeIf(s -> s.startsWith("ea")); // remove all strings that start with ea
        list.forEach(s -> System.out.println(s));


        // New Example: creating and filling arrays -----------------------------------------------

        String[] emptyStrings = new String[10]; //creating an empty string array
        System.out.println(Arrays.toString(emptyStrings)); // all initialized to null
        Arrays.fill(emptyStrings,""); // filling with an empty string
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) + ". "); // here array takes a lambda
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) + ". " // extra label using switch
                        + switch (i) {
                    case 0 -> "one";
                    case 1 -> "two";
                    case 2 -> "three";
                    default -> "";
                }
        );
        System.out.println(Arrays.toString(emptyStrings));


        // source pool of names : randomly selecting values using a lambda
        String[] names = {"Ann", "Bob", "Carol", "David", "Ed", "Fred"};
        String[] randomList = randomlySelectedValues(15, names,
                () -> new Random().nextInt(0, names.length)); // return a random integer between 0 and name.lenght
        System.out.println(Arrays.toString(randomList));

    }

    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2) {
// BinaryOperator is just a special form of BiFunction <T,T,T> where input and output types are the same
        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }

    // BICONSUMER built-in ******IMPORTANT
    public static <T> void processPoint(T t1, T t2, BiConsumer<T,T> consumer) { // accept any type T
        consumer.accept(t1, t2); // calls the accept method of the lambda (p1)
    }

    public static String[] randomlySelectedValues(int count,
                                                  String[] values,
                                                  Supplier<Integer> s) { // supplier is a functional interface
                                                    // with 1 method. returns values with no input
        // s = lambda that gives you random indexes.

        String[] selectedValues = new String[count];
        for (int i = 0; i < count; i++) {
            selectedValues[i] = values[s.get()];
        }
        return selectedValues;
    }
}


///////////// new operation interface /////////////////////////
@FunctionalInterface
    public interface NewOperation<T> {

    T operate(T value1, T value2);
}
