////////////////// main ////////////////////

package dev.lpa;

public class Main {
    public static void main(String[] args) {
        var nationalUSParks = new Park[]{
                new Park("Yellowstone", "44.4882, -110.5916"),
                new Park("Grand Canyon", "36.1085, -112.0965"),
                new Park("Yosemite", "37.8855, -119.5360")
        };

        Layer<Park> parkLayer = new Layer<>(nationalUSParks);
        parkLayer.renderLayer();

        var majorUSRivers = new River[]{
                new River("Mississippi",
                        "47.2160, -95.2348", "29.1566, -89.2495",
                        "35.1556, -90.0659"),
                new River("Missouri",
                        "45.9239, -111.4983", "38.8146, -90.1218")
        };

        Layer<River> riverLayer = new Layer<>(majorUSRivers);

        riverLayer.addElements(
                new River("Colorado",
                        "40.4708, -105.8286", "31.7811, -114.7724"),
                new River("Delaware",
                        "42.2026, -75.00836", "39.4955, -75.5592"));

        riverLayer.renderLayer();
    }


}


//////////////////////// mappable /////////////////////////

age dev.lpa;

import java.util.Arrays;

public interface Mappable {

    void render(); //abstract method

    static double[] stringToLatLon(String location) { //public class by deafult

        var splits = location.split(","); //split the string by the comma
        double lat = Double.valueOf(splits[0]); //getts latitude as the first element (making double - wrapper 0 for first element
        double lng = Double.valueOf(splits[1]); // gets longitude as the second element(making double - wrapper) 1 for seconds element
        return new double[]{lat, lng}; // return an array of strings
    }
}

abstract class Point implements Mappable { //abstract class

    private double[] location = new double[2]; // this field will hold two doubles -latitude and longitude

    public Point(String location) {
        this.location = Mappable.stringToLatLon(location);
    }

    @Override
    public void render() {

        System.out.println("Render " + this + " as POINT (" + location() + ")"); // call to the location method
    }

    private String location() {
        return Arrays.toString(location); //returning latitude and longitude
    }
}


abstract class Line implements Mappable {

    private double[][] locations; // this means I am using two-dimensional array

    public Line(String... locations) { // variable argument of string
        this.locations = new double[locations.length][]; // instantiate the locations field based on number of strings
        int index = 0; //local variable INDEX -just position in the array
        for (var l : locations) {
            this.locations[index++] = Mappable.stringToLatLon(l);
        } // for each iteration I set locations using index to what we get back from the Mappable method
    }

    @Override
    public void render() {
        System.out.println("Render " + this + " as LINE (" + locations() + ")"); // call the location method
    }

    private String locations() {
        return Arrays.deepToString(locations); // returning latitude and longitude

    }
}

////////////////////////  layers /////////////////////////


package dev.lpa;

import java.util.ArrayList;
import java.util.List;

// LAYERS in maps represent a set of data
public class Layer <T extends Mappable> { //GENERIC CLASS/ this sets an upper bound,
    // meaning anything that is mappable can use this Layer class.

    private List<T> layerElements;

    public Layer(T[] layerElements) { //constructor
        this.layerElements = new ArrayList<T>(List.of(layerElements));
    }

    public void addElements(T... elements) {
        layerElements.addAll(List.of(elements));
    }

    public void renderLayer() {

        for (T element : layerElements) {
            element.render();
        }
    }
}


/////////////////////////// park ////////////////////////

package dev.lpa;

public class Park extends Point {

    private String name;

    public Park(String name, String location) {
        super(location);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " National Park";
    }
}

//////////////////// river //////////////////////

package dev.lpa;

public class River extends Line {

    private String name;

    public River(String name, String... locations) {
        super(locations);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " River";
    }


}


////////////////////////////////////////////////////
