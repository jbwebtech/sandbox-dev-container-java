package org.jbwebtech.sandbox;

import java.util.Objects;

public record Boat(String name, int horsepower, double lengthInches) {
    public Boat {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (horsepower <= 0 || lengthInches <= 0) {
            throw new IllegalArgumentException("Horsepower and Length must be greater than 0");
        }

        System.out.println("Created Boat: " + name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, horsepower, lengthInches);
    }

    @Override
    public final boolean equals(Object that) {
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }

        Boat boat = (Boat) that;

        if (this == that) {
            return true;
        }

        return (Objects.equals(this.name, boat.name)
                && this.horsepower == boat.horsepower
                && Double.compare(this.lengthInches, boat.lengthInches) == 0);
    }
}
