public class Main {

    public static void main(String[] args) {

        // to print Tim all in uppercase using function
        String name = "Tim";
        Function<String,String> uCase = String::toUpperCase;
        System.out.println(uCase.apply(name));

        //using Lambda expression:
        Function<String,String> lastName = s -> s.concat(" Buchalka");
        Function<String,String> uCaseLastName = uCase.andThen(lastName); // the output of uCase becomes the input to lastName
        System.out.println(uCaseLastName.apply(name));

        //executing compose method
        // only available to lambda expressions
        //target a function or a unary operator interface
        uCaseLastName = uCase.compose(lastName);
        System.out.println(uCaseLastName.apply(name));

        //expect the final result to be an array of strings
        Function<String,String[]> f0 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name)));
        // the functions change in sush a way that the last one returns a String Array
        //which matches the expected return type.

        //the first function uCase is a method reference that converts the input string to uppercase
        //the second function is a lambda that takes this uppercase string and appends "Buchalka".
        // the final function above , another lambda , splits the string into an array of strings
        //only the last function in the chain needs to return a value that matches the declared return type
        //specified by the function.
        //split method. put in an array.


        // full name all in caps, but last name is called first and then first name
        //using .addThen to add functionality
        Function<String,String> f1 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(f1.apply(name));


        // return type will be an integer.
        Function<String,Integer> f2 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s -> String.join(", ", s))
                .andThen(String::length);
        System.out.println(f2.apply(name));

        // to return calling first letter of each name, space with - and full name
        String[] names = {"Ann", "Bob", "Carol"};
        Consumer<String> s0 = s -> System.out.print(s.charAt(0));
        Consumer<String> s1 = System.out::println;
        Arrays.asList(names).forEach(s0
                .andThen(s-> System.out.print(" - "))
                .andThen(s1));


        //return boolean result (true or false)
        Predicate<String> p1 = s -> s.equals("TIM");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Tim");
        Predicate<String> p3 = s -> s.startsWith("T");
        Predicate<String> p4 = s -> s.endsWith("e");

        Predicate<String> combined1 = p1.or(p2);
        System.out.println("combined1 = " + combined1.test(name));

        Predicate<String> combined2 = p3.and(p4);
        System.out.println("combined2 = " + combined2.test(name));

        Predicate<String> combined3 = p3.and(p4).negate();
        System.out.println("combined3 = " + combined3.test(name));

        record Person(String firstName, String lastName) {}

        List<Person> list = new ArrayList<>(Arrays.asList(
                new Person("Peter", "Pan"),
                new Person("Peter", "PumpkinEater"),
                new Person("Minnie", "Mouse"),
                new Person("Mickey", "Mouse")
        ));

        list.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        list.forEach(System.out::println);

        System.out.println("------------------------------------");
        list.sort(Comparator.comparing(Person::lastName));
        list.forEach(System.out::println);

        System.out.println("------------------------------------");
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName));
        list.forEach(System.out::println);

        System.out.println("------------------------------------");
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName).reversed());
        list.forEach(System.out::println);


    }



}
