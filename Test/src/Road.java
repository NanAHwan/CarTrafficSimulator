public class Road {
    Object[]object;
    String name;
    int length;
    Vehicle vehicle=new Vehicle();
    TrafficLight trafficLight=new TrafficLight();
    Road nextRoad;
    boolean isDestination=false;

    public Road(String name,int length,Vehicle vehicle, Road nextRoad,TrafficLight trafficLight) {
        this.object=new Object[length];
        this.name=name;
        this.length=length;
        this.vehicle=vehicle;
        this.trafficLight=trafficLight;
        this.nextRoad = nextRoad;
        object[0]=vehicle;
        object[this.length-1]=trafficLight;
    }
    public void updateStatus(Vehicle vehicle)
    {
        if(vehicle.currentPosition<this.length) {
            this.object[vehicle.currentPosition] = this.vehicle;
        }
        else if(vehicle.currentPosition==this.length){
            if(trafficLight.lightColor.equalsIgnoreCase("Green")) {
                vehicle.currentPosition = 0;
                vehicle.drivingRoad = nextRoad;
                isDestination = true;
            }
            else{
                vehicle.currentPosition-=1;
            }
        }
    }
}
