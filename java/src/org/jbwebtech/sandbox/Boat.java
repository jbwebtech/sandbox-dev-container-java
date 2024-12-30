public class Boat {
    private final String name;
    private final int horsepower;
    private final double lengthInches;

    public Boat(String name, int horsepower, double lengthInches) {
        this.name = name;
        this.horsepower = horsepower;
        this.lengthInches = lengthInches;
        System.out.println("Created Boat: " + name);
    }

    public String getName() {
        return name;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public double getLengthInches() {
        return lengthInches;
    }

    @Override
    public String toString() {
        return "Boat{" +
                "name='" + name + '\'' +
                ", horsepower=" + horsepower +
                ", lengthInches=" + lengthInches +
                '}';
    }
}
