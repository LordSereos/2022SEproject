public class Main {
    public static void main(String[] args) {
        RectangleCoordinates roof = new RectangleCoordinates();

        roof.findCordinates("C6", "C5", "C1");
        roof.findCordinates("C5", "C2", "C6");
        roof.findCordinates("C2", "C1", "C5");
        roof.findCordinates("C1", "C6", "C2");

        roof.findCordinates("C1", "C2", "C4");
        roof.findCordinates("C2", "C3", "C1");
        roof.findCordinates("C3", "C4", "C2");
        roof.findCordinates("C4", "C1", "C3");

        roof.printZoneOne();
        roof.printZoneTwo();
        roof.printZoneThree();


    }
}