import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Traffic1 implements ActionListener, Runnable
{
    JFrame frame = new JFrame("Traffic Simulator");
    Road1 r = new Road1();
    JButton start= new JButton("Start");
    JButton stop = new JButton("Stop");
    JLabel throughput = new JLabel("Throughput:0");
    Container south = new Container();

    JButton semi = new JButton("Add Semi");
    JButton suv = new JButton("Add SUV");
    JButton sports= new JButton("Add Sports");
    Container west = new Container();

    boolean running= false;
    int carCount = 0;
    long startTime =0;

    public Traffic1()
    {
        frame.setSize(1000,550);
        frame.setLayout(new BorderLayout( ));
        frame.add(r,BorderLayout.CENTER);

        south.setLayout(new GridLayout(1,2));
        south.add(start);
        start.addActionListener(this);
        south.add(stop);
        stop.addActionListener(this);
        south.add(throughput);
        frame.add(south,BorderLayout.SOUTH);

        west.setLayout(new GridLayout(3,1));
        west.add(semi);
        semi.addActionListener(this);
        west.add(suv);
        suv.addActionListener(this);
        west.add(sports);
        sports.addActionListener(this);
        frame.add(west, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.repaint();
    }
    public static void main(String[]args)
    {
        new Traffic1();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(start))
        {
            if (running == false){
                running = true;
                r.resetCarCount();
                startTime = System.currentTimeMillis();

                Thread t = new Thread(this);
                t.start();
            }
        }
        if(event.getSource().equals(stop))
        {
            running = false;
        }
        if(event.getSource().equals(semi)){
            Semi semi = new Semi(0,30);
            r.addCar(semi);
            for (int x = 0; x < r.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 400; y = y + 150) {
                    semi.setX(x);
                    semi.setY(y);
                    if(r.collision(x,y,semi)==false)
                    {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
        if(event.getSource().equals(suv)){
            SUV suv = new SUV(0,30);
            r.addCar(suv);
            for (int x = 0; x < r.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 400; y = y + 150) {
                    suv.setX(x);
                    suv.setY(y);
                    if(r.collision(x,y,suv)==false)
                    {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
        if(event.getSource().equals(sports)){
            Sports sports = new Sports(0,30);
            r.addCar(sports);
            for (int x = 0; x < r.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 400; y = y + 150) {
                    sports.setX(x);
                    sports.setY(y);
                    if(r.collision(x,y,sports)==false)
                    {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        while (running ==true )
        {
            r.step();
            carCount = r.getCarCount();
            double throughputCalc = carCount/1000*(double)(System.currentTimeMillis()-startTime);
            throughput.setText("Throughput:"+throughputCalc);
            frame.repaint();
            try {
                Thread.sleep(100);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }


}
