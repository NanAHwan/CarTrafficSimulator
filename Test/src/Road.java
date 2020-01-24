public class Road {
    Object[]object;
    String name;
    int length;
    Vehicle vehicle=new Vehicle();
    TrafficLight trafficLight=new TrafficLight();
    Road connectedRoad;
    boolean reached=false;

    public Road(String name,int length,Vehicle vehicle, Road connectedRoad,TrafficLight trafficLight) {
        this.object=new Object[length];
        this.name=name;
        this.length=length;
        this.vehicle=vehicle;
        this.trafficLight=trafficLight;
        this.connectedRoad = connectedRoad;
        object[0]=vehicle;
        object[this.length-1]=trafficLight;
    }
    public void update(Vehicle vehicle)
    {
        if(vehicle.position<this.length) {
            this.object[vehicle.position] = this.vehicle;
        }
        else if(vehicle.position==this.length){
            if(trafficLight.color.equalsIgnoreCase("Green")) {
                vehicle.position = 0;
                vehicle.currentRoad = connectedRoad;
                reached = true;
            }
            else{
                vehicle.position-=1;
            }
        }
    }
}
