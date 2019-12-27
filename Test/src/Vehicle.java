public class Vehicle {
    String name;
    int currentPosition;
    Road drivingRoad;
    public Vehicle() {
    }

    public Vehicle(String name, int currentPosition, Road drivingRoad) {
        this.name = name;
        this.currentPosition = currentPosition;
        this.drivingRoad = drivingRoad;
    }

    public void moveVehicle(){
        this.currentPosition+=1;
    }
}
