package qcells_take_home;

/**
 * Car object that contains name, seats, engine (mileage, hpw) etc
 */
public class Car {
    private String name;
    private int seats;
    private double speed;
    private String productionNumber;
    private String carType;
    private Engine engine;

    public Car(String name, int seats, double speed, String productionNumber, String carType, Engine engine){
        this.name = name;
        this.setSpeed(speed);
        this.productionNumber = productionNumber;
        this.carType = carType;
        this.setSeats(seats);
        this.engine = engine;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: " + this.name + ";");
        stringBuilder.append("Production Number: " + this.productionNumber + ";");
        stringBuilder.append("Car Type: " + this.carType + ";");
        stringBuilder.append("Horse Power: " + this.engine.getHpw() + ";");
        stringBuilder.append("Mileage: " + this.engine.getMpg());
        return stringBuilder.toString();
    }

    /**
     * Get characteristic String of the car
     *
     * @return String
     */
    public String getCharacteristic(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: " + this.name + ";");
        stringBuilder.append("Current Speed: " + this.getSpeed() + ";");
        stringBuilder.append("Horse Power: " + this.engine.getHpw() + ";");
        stringBuilder.append("Mileage: " + this.engine.getMpg());
        return stringBuilder.toString();
    }

    /**
     * Accelerate car's speed by the factor
     *
     * Acceleration: newSpeed = currentSpeed + factor*hpw
     * (assuming mass, wind_resistance etc remains constant as per requirements)
     *
     * @param factor (acceleration/deceleration factor)
     * @throws IllegalArgumentException throws exception if factor in not [-1.0, 1.0]
     */
    public void accelerate(double factor) throws IllegalArgumentException{
        if(factor < -1.0 || factor > 1.0){
            throw new IllegalArgumentException("Invalid factor for acceleration");
        }
        if(this.getSpeed() == 0){
            this.setSpeed(1);
        }
        // give mass is constant, wind resistance extra is constant
        this.setSpeed(this.getSpeed() + factor * this.engine.getHpw());

    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
