import java.applet.*;
import java.awt.*;
public class anu extends Applet {

    public void timea()
    {
        try {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {

        }
    }

    public void paint(Graphics g)
    {
        for(int i=0;i<10;i++)
        {
            g.drawLine(5,1,5, 1000);
            g.drawLine(210,1,210, 1000);
            g.drawLine(5,600,210,600);

            timea();
            g.setColor(Color.red);
            g.fillOval(10, 10,200,200);
            g.setColor(Color.black);
            g.drawString("STOP",100,100);


            timea();
            g.setColor(Color.gray);
            g.fillOval(10, 10,200,200);


            timea();
            g.setColor(Color.yellow);
            g.fillOval(10,210,200,200);
            g.setColor(Color.black);
            g.drawString("START",100,300);

            timea();
            g.setColor(Color.gray);
            g.fillOval(10,210,200,200);



            timea();
            g.setColor(Color.green);
            g.fillOval(10,410,200,200);
            g.setColor(Color.black);
            g.drawString("GO",100,500);



            timea();
            g.setColor(Color.gray);
            g.fillOval(10,410,200,200);





        }
    }
}