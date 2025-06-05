////////////////////////////////// MAIN CLASS /////////////////////////////////////////

public class Main {
    public static void main(String[] args) {

// I create an animal object to test and I gave it the type "generic animal" and the size "huge" and weight of 400
        Animal animal = new Animal("Generic Animal ", "Huge", 400);
        doAnimalStuff(animal, "slow");

// Now I am passing the dog object as the method argument when the type was declared as an animal.
// It works because Dog inherits from animal. So I can pass Dig instance in any method that takes animal.

        Dog dog = new Dog();
        doAnimalStuff(dog, "fast");

        Dog yorkie = new Dog("Yorkie ", 15);
        doAnimalStuff(yorkie, "fast");
        Dog retriever = new Dog("Labrador Retriever ", 65,
                "Floppy", "Swimmer");
        doAnimalStuff(retriever, "slow");

        Fish goldie = new Fish("Goldfish", 0.25, 2, 3);
        doAnimalStuff(goldie, "fast");

    }


// I will create a method on the main class that will take any animal object and execute its three methods.
    public static void doAnimalStuff(Animal animal, String speed) {

        animal.makeNoise();
        animal.move(speed);
        System.out.println(animal);
    // this animal above did not call the animal toString method (line 25 ) ********IMPORTANT
    // It called the dog toString method when animal is an instance of a dog (overridden line 39 from dog)
        System.out.println("_ _ _ _");


    }
}

/////////////////////////////// ANIMAL CLASS //////////////////////////////////////////

public class Animal {

//    First: attributes (definition)

    private String type;
    private String size;
    private double weight;

// I need to create this following so, I can use Dog inherit from this class Animal .

    public Animal() {

    }

// Constructor method.

    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

// A way to print information about the animal using toString.

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", weight=" + weight +
                '}';
    }

// Now I am gonna to create two methods for animal's behaviour.

    public void move(String speed) {
        System.out.println(type + " moves " + speed);
    }

    public void makeNoise() {
        System.out.println(type + " makes some kind of noise");
    }

}


///////////////////////// FISH CLASS /////////////////////////////////////

public class Fish extends Animal {

    private int gills;
    private int fins;

    public Fish(String type, double weight, int gills, int fins) {
        super(type, "small", weight);
        this.gills = gills;
        this.fins = fins;
    }

    private void moveMuscles() {
        System.out.println("Muscles moving");
    }

    private void moveBackFin() {
        System.out.println("Backfin moving");
    }

// Now I will override the move method from animal, so that the fish moves or swims.


    @Override
    public void move(String speed) {
        super.move(speed);
        moveMuscles();
        if (speed.equals("fast")) {
            moveBackFin();
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Fish{" +
                "gills=" + gills +
                ", fins=" + fins +
                "} " + super.toString();
    }
}


/////////////////////////// DOG CLASS //////////////////////////////////

public class Dog extends Animal {

// I'm going to implement specific fields

    private String earShape;
    private String tailShape;
    

// I want this class to inherit from Animal. So I'm going to use EXTENDS.
// This will work just if I create a constructor.
// I will use super() because it is a way to call the constructor on the parent class.
// When I call super I will pass values to animal's three-parameter constructor.
// I will pass the type of dog. which I will call Matt size big and 50 for the weight.

    public Dog() {
        super("Mutt ", "big", 50);
    }


// Simpler constructor to create a dog object for the majority dogs.
// It calls the other dog constructor that has 4 parameters bellow.


    public Dog (String type, double weight) {
        this(type, weight, "Perky", "Curled");
    }

// This constructor has a combination of the dog and the animal fields in its argument list.
// I call the super constructor to set some of our fields, the animal-specific fields.
// Inside the super () I can perform some calculations and pass the results.
// After the call super I call the specific attributes.

    public Dog(String type, double weight, String earShape, String tailShape) {
        super(type, weight < 15 ? "small" : (weight < 35 ? "medium" : "large"),weight);
        this.earShape = earShape;
        this.tailShape = tailShape;
    }
    @Override
    public String toString() {
        return "Dog{" +
                "earShape='" + earShape + '\'' +
                ", tailShape='" + tailShape + '\'' +
                "} " + super.toString();
    }

    public void makeNoise() {

    }
// Above I have overridden animal's makeNoise method.
    @Override
    public void move(String speed) {
        super.move(speed);
        System.out.println("Dogs walk, run and wag their tail");

    }
}

///////////////////////////////////////////////////////////////////////////////





