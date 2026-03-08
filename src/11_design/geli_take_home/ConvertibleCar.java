package geli_take_home;

/**
 * Convertible Car that extends over base Car
 *
 * Exercise 2
 *
 */
public class ConvertibleCar extends Car{
    private static final String ROOF_OPEN = "Open";
    private static final  String ROOF_CLOSED = "Closed";
    boolean roofOpen;
    public ConvertibleCar(String name, int seats, double speed, String productionNumber, String carType, Engine engine){
        super(name, seats, speed, productionNumber, carType, engine);
        roofOpen = false;
    }

    /**
     * Toggle Roof's for convertible car's
     */
    public void toggleRoof(){
        String currentStatus = this.roofOpen ? ROOF_OPEN : ROOF_CLOSED;
        this.roofOpen = !this.roofOpen;
        String newStatus = this.roofOpen ? ROOF_OPEN : ROOF_CLOSED;
        System.out.println("Toggling roof from:" + currentStatus  + " to:" + newStatus);
    }
}
