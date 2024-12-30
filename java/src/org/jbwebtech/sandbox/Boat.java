public record Boat(String name, int horsepower, double lengthInches) {
    public Boat {
        System.out.println("Created Boat: " + name);
    }
}
