/////////////////// main ////////////////

package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       //list is an interface type and mappable also has interface reference type.
        List<Mappable> mappables = new ArrayList<>();

        mappables.add(new Building("Sydney Town Hall", UsageType.GOVERNMENT));
        mappables.add(new Building("Sydney Opera House",
                UsageType.ENTERTAINMENT));
        mappables.add(new Building("Stadium Australia", UsageType.SPORTS));

        mappables.add(new UtilityLine("College St", UtilityType.FIBER_OPTIC));
        mappables.add(new UtilityLine("Olympic Blvd", UtilityType.WATER));

        for (var m : mappables) {
            Mappable.mapIt(m);
        }

    }
}


//////////////// Mappable /////////////////////

package dev.lpa;

enum Geometry {LINE, POINT, POLYGON};

enum Color {BLACK, BLUE, GREEN, ORANGE, RED};
enum PointMarkers {CIRCLE, PUSH_PIN, STAR, SQUARE, TRIANGLE};
enum LineMarkers {DASHED, DOTTED, SOLID};



public interface Mappable {

    // 1st: calling my constant
    String JSON_PROPERTY = """
            "properties": {%s}""";

    // Now defining three abstract methods.
    String getLabel(); // return map label
    Geometry getShape(); // return a ENUM geometry type
    String getMarker(); // return map icon

    // creating the default method.
    default String toJSON() {

        return """
                "type": "%s", "label": "%s", "marker": "%s" """ //property names are in quotes: TYPE, LABEL, MARKER
                .formatted(getShape(), getLabel(), getMarker());
    }

    //lastly: static method. takes mappable as a parameter
    static void mapIt(Mappable mappable) {
        System.out.println(JSON_PROPERTY.formatted(mappable.toJSON()));
    }

}


///////////////////////// Utility Line ////////////////////////

package dev.lpa;

enum UtilityType {ELECTRICAL, FIBER_OPTIC, GAS, WATER}

public class UtilityLine implements Mappable {

    private String name;
    private UtilityType type;

    public UtilityLine(String name, UtilityType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return name + " (" + type + ")";
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    @Override
    public String getMarker() {
        return switch (type) {
            case ELECTRICAL -> Color.RED + " " + LineMarkers.DASHED;
            case FIBER_OPTIC -> Color.GREEN + " " + LineMarkers.DOTTED;
            case GAS -> Color.ORANGE + " " + LineMarkers.SOLID;
            case WATER -> Color.BLUE + " " + LineMarkers.SOLID;
            default -> Color.BLACK + " " + LineMarkers.SOLID;
        };
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + """
                , "name": "%s", "utility": "%s" """.formatted(name, type);
    }
}


///////////////////// Building ////////////////////////////



package dev.lpa;

public class Building {
    enum UsageType {ENTERTAINMENT, GOVERNMENT, RESIDENTIAL, SPORTS}
    //i want this enum because each type of building may have a different color. or maker shape.

    public class Building implements Mappable { // implements so it became interface

        //create fields.
        private String name;
        private UsageType usage;

        // add a constructor
        public Building(String name, UsageType usage) {
            this.name = name;
            this.usage = usage;
        }

        @Override
        public String getLabel() {
            return name + " (" + usage + ")";
        }

        @Override
        public Geometry getShape() {
            return Geometry.POINT;
        }

        @Override
        public String getMarker() {
            return switch (usage) { //using switch
                case ENTERTAINMENT -> Color.GREEN + " " + PointMarkers.TRIANGLE;
                case GOVERNMENT -> Color.RED + " " + PointMarkers.STAR;
                case RESIDENTIAL -> Color.BLUE + " " + PointMarkers.SQUARE;
                case SPORTS -> Color.ORANGE + " " + PointMarkers.PUSH_PIN;
                default -> Color.BLACK + " " + PointMarkers.CIRCLE;
            };
        }

        @Override
        public String toJSON() {
            return Mappable.super.toJSON() + """
                , "name": "%s", "usage": "%s" """.formatted(name, usage);
        }
    }

}

///////////////////////////////////////////////


