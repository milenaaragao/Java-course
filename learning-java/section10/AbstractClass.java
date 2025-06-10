////////////////// main /////////////////////

package dev.lpa;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

// introduction to abstract class
        //abstract methods can only be declared on an abstract class or interface


       // Animal animal = new Animal("animal", "big", 100);

        Dog dog = new Dog("Wolf", "big", 100);
        dog.makeNoise();
        doAnimalStuff(dog);

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(new Dog("German Shepard", "big", 150));
        animals.add(new Fish("Goldfish", "small", 1));
        animals.add(new Fish("Barracuda", "big", 75));
        animals.add(new Dog("Pug", "small", 20));

        animals.add(new Horse("Clydesdale", "large", 1000));

        for (Animal animal : animals) {
            doAnimalStuff(animal);
            if(animal instanceof Mammal currentMammal) {
                currentMammal.shedHair(); // instantiating method
            }
        }


    }

    private static void doAnimalStuff(Animal animal) {
        animal.makeNoise();
        animal.move("slow");

    }
}


///////////////// animal //////////////////////////////

package dev.lpa;

abstract class Mammal extends Animal {

    public Mammal(String type, String size, double weight) {
        super(type, size, weight);
    } // abstract methods can include additional abstract methods
    // abstract methods can implement all of the parent's abstract methods


    @Override
    public void move(String speed) {

        System.out.println(getExplicitType() + " ");
        System.out.println(speed.equals("slow") ? "walks" : "runs");

    }

    public abstract void shedHair();
}

public abstract class Animal {

    protected String type; //protected = subclasses can access type directly without getter method
    private String size;
    private double weight;

    public Animal(String type, String size, double weight) { //constructor with three
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    // methods in abstract class are implemented with this signature:
    // (the same for concrete class that is overridden)
    public abstract void move(String speed);
    public abstract void makeNoise();

    // creating a helper method to print instance's class name and its type
// I use FINAL in the method preventing overridden with modifications in the classes that inherits Animal
    //other words = subclass cannot override it
    public final String getExplicitType() {
        return getClass().getSimpleName() + " (" + type + ")";

    } // that's a method I can use in subclasses
}



/////////////////////////// horse ////////////////////////

package dev.lpa;

public class Horse extends Mammal {


    public Horse(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " sheds in the spring");

    }

    @Override
    public void makeNoise() {

    }
}

//////////////////////// dog /////////////////////////////

package dev.lpa;

public class Dog extends Mammal { //dog inherits animal

    public Dog(String type, String size, double weight) { //implement constructor
        super(type, size, weight);
    }
//methods with same signatures in animal:
    @Override
    public void move(String speed) {

        if(speed.equals("slow")) {
            System.out.println(getExplicitType() + " walking");
        } else {
            System.out.println(getExplicitType() + " running");
        }

    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " shed hair all the time");

    }

    @Override
    public void makeNoise() {

        if (type == "Wolf") {
            System.out.println("Howling! ");
        } else {
            System.out.print("Woof! ");
        }

    }
}


//////////////////////////////// fish //////////////////////////

package dev.lpa;

public class Fish extends Animal { //dog inherits animal

    public Fish(String type, String size, double weight) { //implement constructor
        super(type, size, weight);
    }
//methods with same signatures in animal:
    @Override
    public void move(String speed) {

        if(speed.equals("slow")) {
            System.out.println(getExplicitType() + " lazily swimming");
        } else {
            System.out.println(getExplicitType() + " darting frantically");
        }

    }

    @Override
    public void makeNoise() {

        if (type == "Goldfish") {
            System.out.println("swish ");
        } else {
            System.out.print("splash ");
        }

    }
}


