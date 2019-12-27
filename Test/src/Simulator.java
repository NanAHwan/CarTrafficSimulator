public class Simulator {
    public static void main(String[] args) throws InterruptedException,NullPointerException {

        Vehicle vehicle;
        vehicle=new Vehicle("Car 1",0,null);

        TrafficLight t1=new TrafficLight("Red",5.0);
        Boolean end=false;

        Road road;
        Road r3=new Road("Road 3",6,vehicle,null,t1);
        Road r2=new Road("Road 2",5,vehicle,r3,t1);
        Road r1=new Road("Road 3",4,vehicle,r2,t1);

        //Put Vehicle on the Road
        vehicle.drivingRoad=r1;
        road=vehicle.drivingRoad;

        while(end==false){ //the simulator will keep running until user choose to end
            t1.operateColor();
            System.out.println("Vehicle:"+vehicle.name+" Road:"+vehicle.drivingRoad.name+" Position:"+vehicle.drivingRoad+" Traffic Light: "+t1.lightColor);
            vehicle.moveVehicle();
            road.updateStatus(vehicle);
            if(road.reached==true)
            {

                if(road.nextRoad==null){
                    road=vehicle.drivingRoad;
                }
            }
            Thread.sleep(2000);
        }
    }
}
