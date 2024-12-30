public class Main {
    public static void main(String[] args) {
        final Boat b1 = new Boat("Lucky", 800, 240.781);
        System.out.println(b1);

        final Boat b2 = new Boat("Drifter", 1200, 319);
        System.out.println(b2);

        final Boat b3 = new Boat("Lightning", 2000, 400);
        System.out.println(b3);

        final Boat b4 = new Boat(b3.getName(), b3.getHorsepower(), b3.getLengthInches());
        System.out.println(b4);
    }
}
