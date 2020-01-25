package traffic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Test extends JFrame implements ActionListener{
    static Simulation panel;
    //static TestRotation ts;

    JMenuBar menu;
    JMenu simulate, edit;
    JMenuItem startSimulate, exit, stop;

    Test(){
        this.setTitle("Traffic Light Simulator");
        JLabel lbl=new JLabel("Welcome to Car and Traffic Light Simulator!",SwingConstants.CENTER);
        lbl.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int)screenSize.getWidth()/2 , (int)screenSize.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu =new JMenuBar();

        simulate=new JMenu("Simulation");
        edit =new JMenu("City Edit");

        startSimulate =new JMenuItem("Simulate");
        startSimulate.addActionListener(this);

        exit =new JMenuItem("Exit");
        exit.addActionListener(this);
        stop =new JMenuItem("Stop");
        stop.addActionListener(this);
        //rate =new JMenuItem("Rate");
        //rate.addActionListener(this);

        simulate.add(startSimulate);
        simulate.add(exit);
        simulate.add(stop);

        //edit.add(rate);
        menu.add(simulate);
        menu.add(edit);

        this.setJMenuBar(menu);
        this.add(lbl);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startSimulate) {
            panel = new Simulation();
            this.setContentPane(panel);
            this.setVisible(true);
        } else if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == stop) {
            if (panel == null)
                JOptionPane.showMessageDialog(this, "Please start simulation before stop", "error", JOptionPane.INFORMATION_MESSAGE);
            else {
                panel.setVisible(false);
            }

        }
    }
    public static void main(String[] args)
    {
        new Test();
    }
}
