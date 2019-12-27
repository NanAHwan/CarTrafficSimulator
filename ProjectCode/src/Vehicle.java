import java.awt.Graphics;
public class Vehicle
{
    int x,y;
    int width=0,height=0, speed=0;
    public Vehicle(int newx,int newy)
    {
        x=newx;
        y=newy;
    }

    public void paintMe(Graphics g)
    {

    }
    public int getX()
    {
       return x;
    }
    public void setX(int newx)
    {
        x = newx;
    }
    public int getSpeed()
    {
        return speed;
    }
    public int getY()
    {
        return y;
    }
    public int getWidth()
    {
        return width;
    }
    public void setY(int newy)
    {
        y = newy;
    }

}
