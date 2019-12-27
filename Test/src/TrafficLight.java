public class TrafficLight {
    String lightColor;
    double rateOfChange;

    public TrafficLight() {
    }

    public TrafficLight(String color, double rateOfChange){
        this.lightColor=color;
        this.rateOfChange=rateOfChange;
    }
    public void operateColor(){
        if(rateOfChange<(Math.random()*((1.0-10.0)+1))+10.0)
            this.lightColor="Red";
        else
            this.lightColor="Green";
    }
}
