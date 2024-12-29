package org.jbwebtech.sandbox;

public class Main {
    public static void main(String[] args) {
        var boat = new Boat("Lucky", 800, 240.781);
        System.out.println(boat);
        boat = new Boat("Drifter", 1200, 319);
        System.out.println(boat);

        final var b1 = boat;
        final var b2 = boat;

        System.out.println("Equal reference b1 = b2? " + (b1 == b2));

        Boat b3 = new Boat("Lightning", 2000, 400);
        Boat b4 = new Boat(b3.name(), b3.horsepower(), b3.lengthInches());

        System.out.println("Equal value b1 & b2? " + (b1.equals(b2)));
        System.out.println("Equal value b3 & b4? " + (b3.equals(b4)));
      }
}
