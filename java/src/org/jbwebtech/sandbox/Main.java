public class Main {
    public static void main(String[] args) {
        final var b1 = new Boat("Lucky", 800, 240.781);
        System.out.println(b1);

        final var b2 = new Boat("Drifter", 1200, 319);
        System.out.println(b2);

        final var b3 = new Boat("Lightning", 2000, 400);
        System.out.println(b3);

        final var b4 = new Boat(b3.name(), b3.horsepower(), b3.lengthInches());
        System.out.println(b4);
      }
}
