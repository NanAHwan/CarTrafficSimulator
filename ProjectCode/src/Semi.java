
import java.awt.*;

public class Semi extends Vehicle
{
    public Semi(int newx, int newy)
    {
        super(newx,newy);
        width =100;
        height = 40;
        speed = 5;

    }
    public void paintMe(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect(x,y,width,height);
    }

}
