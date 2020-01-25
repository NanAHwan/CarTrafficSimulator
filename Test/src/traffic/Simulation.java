package traffic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import traffic.Vehicle;
import traffic.Vehicle.VehicleDirection;
import traffic.Vehicle.VehicleState;



public class Simulation extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Image car1;
	private Image mTerrain;
	private Timer tm = new Timer(1, this);
	private int x = 0, velX = 2;
	private float mAngle = 0;
	private Random random = new Random();
	//Arrays of vehicles
	private ArrayList<Vehicle> vehiclesRight;
	private ArrayList<Vehicle> vehiclesDown;
	private ArrayList<Vehicle> vehiclesLeft;
	private ArrayList<Vehicle> vehiclesUp;
	private String[] carImages = {"Test/car1.png", "Test/car2.png", "Test/car3.png"};

	private ArrayList<TrafficLight> trafficLights;
	private int carSpawnTimer = 0;

	Vehicle v1, v2, v3, v4;
	float move=0;

	public void rightDirection(Graphics2D g2D)
	{
		//displays all cars going in the right direction
		for(int i=0; i<vehiclesRight.size(); i++){
			Vehicle v = vehiclesRight.get(i);
			if(v.isInView())
				g2D.drawImage(v.getImage(), v.getTrans(), this);
			else{
				vehiclesRight.remove(v);
			}
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;

		g2D.drawImage(mTerrain,0,0,this);
		g2D.drawImage(v1.getImage(), v1.getTrans(), this);
		rightDirection(g2D);
		for(int i=0; i<vehiclesLeft.size(); i++){
			Vehicle v = vehiclesLeft.get(i);
			if(v.isInView())
				g2D.drawImage(v.getImage(), v.getTrans(), this);
			else{
				vehiclesLeft.remove(v);
			}
		}
		for(int i=0; i<vehiclesDown.size(); i++){
			Vehicle v = vehiclesDown.get(i);
			if(v.isInView())
				g2D.drawImage(v.getImage(), v.getTrans(), this);
			else{
				vehiclesDown.remove(v);
			}
		}
		for(int i=0; i<vehiclesUp.size(); i++){
			Vehicle v = vehiclesUp.get(i);
			if(v.isInView())
				g2D.drawImage(v.getImage(), v.getTrans(), this);
			else{
				vehiclesUp.remove(v);
			}
		}
		AffineTransform identity = g2D.getTransform();
		identity.setToTranslation(300, 200+move);
		identity.rotate(Math.toRadians(mAngle), car1.getWidth(this), car1.getHeight(this));
		g2D.drawImage(car1, identity, this);

		//Draw trafficLights
		for(TrafficLight t: trafficLights){
			Color colors[] = t.getCurrentLightColor();
			if(t.getOrientation() == 0){
				g2D.setColor(colors[1]);
				g2D.fillRect(t.getForward_pos().x, t.getForward_pos().y, 21, 29);
				g2D.setColor(colors[0]);
				g2D.fillRect(t.getLeft_light_pos().x, t.getLeft_light_pos().y, 20, 29);
				g2D.setColor(colors[2]);
				g2D.fillRect(t.getRight_light_pos().x, t.getRight_light_pos().y, 20, 29);
			}else{
				g2D.setColor(colors[1]);
				g2D.fillRect(t.getForward_pos().x, t.getForward_pos().y, 29, 22);
				g2D.setColor(colors[0]);
				g2D.fillRect(t.getLeft_light_pos().x, t.getLeft_light_pos().y, 29, 22);
				g2D.setColor(colors[2]);
				g2D.fillRect(t.getRight_light_pos().x, t.getRight_light_pos().y, 29, 22);
			}
			g2D.drawImage(t.getLayoutImg(), t.getTrans(), this);

		}
		if(!tm.isRunning())
			tm.start();
	}

	public void actionPerformed(ActionEvent e)
	{
		carSpawnTimer++;
		if(x < 0 || x > 550) {
			velX = -velX;
		}
		x = x + velX;
		if(mAngle < 450) {
			move += 0.2;
		}
		else
			move += 2;
		steerTowards(450,120);

		if(carSpawnTimer%500 == 0){
			//create new car objects over here
			int carImageId = 0;
			int vAheadID = 1000;
			for(int i = 0;i <20; i++){
				if(vehiclesRight.size() < 30){
					int line = (vehiclesRight.size())/3;
					if(line > 0){
						vAheadID = vehiclesRight.size() - 3;
					}
					carImageId = random.nextInt(carImages.length);
					if(carImageId < 0){
						carImageId = 0;
					}
					//int spd = 7- random.nextInt(2);
					if(vehiclesRight.size() > 0 && vAheadID < vehiclesRight.size())
						vehiclesRight.add(new Vehicle(new File(carImages[carImageId]), 6, VehicleState.MOVE_X, VehicleDirection.RIGHT, trafficLights.get(1), this, vehiclesRight.get(vAheadID), vehiclesRight.size()));
					else
						vehiclesRight.add(new Vehicle(new File(carImages[carImageId]), 6, VehicleState.MOVE_X, VehicleDirection.RIGHT, trafficLights.get(1), this, null, vehiclesRight.size()));
				}

				if(vehiclesDown.size() < 20){
					int line = (vehiclesDown.size())/3;
					int laneID = vehiclesDown.size()%3;
					if(line > 0){
						switch(laneID){
							case 0:
								vAheadID = 3*line - 3;
								break;
							case 1:
								vAheadID = 3*line - 2;
								break;
							case 2:
								vAheadID = 3*line - 1;
								break;
						}
					}
					carImageId = random.nextInt(carImages.length);
					if(carImageId < 0){
						carImageId = 0;
					}
					int spd = 7- random.nextInt(2);
					//System.out.println("line: " +line + " vehicle: "+vehiclesDown.size() + "vAheadId: " +vAheadID);
					if(vehiclesDown.size() > 0 && vAheadID < vehiclesDown.size())
						vehiclesDown.add(new Vehicle(new File(carImages[carImageId]), spd, VehicleState.MOVE_Y, VehicleDirection.DOWN, trafficLights.get(0), this, vehiclesDown.get(vAheadID), vehiclesDown.size()));
					else
						vehiclesDown.add(new Vehicle(new File(carImages[carImageId]), spd, VehicleState.MOVE_Y, VehicleDirection.DOWN, trafficLights.get(0), this, null, vehiclesDown.size()));
				}

				if(vehiclesLeft.size() < 30){
					int line = (vehiclesLeft.size())/3;
					int laneID = vehiclesLeft.size()%3;

					if(line > 0){
						switch(laneID){
							case 0:
								vAheadID = 3*line - 3;
								break;
							case 1:
								vAheadID = 3*line - 2;
								break;
							case 2:
								vAheadID = 3*line - 1;
								break;
						}
					}
					carImageId = random.nextInt(carImages.length);
					if(carImageId < 0){
						carImageId = 0;
					}
					int spd = 7- random.nextInt(2);
					if(vehiclesLeft.size() > 0 && vAheadID < vehiclesLeft.size())
						vehiclesLeft.add(new Vehicle(new File(carImages[carImageId]), spd, VehicleState.MOVE_X, VehicleDirection.LEFT, trafficLights.get(3),this, vehiclesLeft.get(vAheadID), vehiclesLeft.size()));
					else
						vehiclesLeft.add(new Vehicle(new File(carImages[carImageId]), spd, VehicleState.MOVE_X, VehicleDirection.LEFT, trafficLights.get(3),this, null, vehiclesLeft.size()));
				}

				if(vehiclesUp.size() < 30){
					int line = (vehiclesUp.size())/3;
					if(line > 0)
					{
						vAheadID = vehiclesUp.size() - 3;
					}
					carImageId = random.nextInt(carImages.length);
					if(carImageId < 0){
						carImageId = 0;
					}
					int spd = 7- random.nextInt(2);
					if(vehiclesUp.size() > 0 && vAheadID < vehiclesUp.size())
						vehiclesUp.add(new Vehicle(new File(carImages[carImageId]), spd, VehicleState.MOVE_Y, VehicleDirection.UP, trafficLights.get(2),this, vehiclesUp.get(vAheadID), vehiclesUp.size()));
					else
						vehiclesUp.add(new Vehicle(new File(carImages[carImageId]), spd, VehicleState.MOVE_Y, VehicleDirection.UP, trafficLights.get(2),this, null, vehiclesUp.size()));
				}
			}
			carSpawnTimer = 0;
		}
		repaint();
	}

	private void steerTowards(float angle, float t){
		float angularVel = angle/t;
		if(Math.abs(mAngle) < Math.abs(angle))
			mAngle += angularVel;

	}

	public Simulation(){

		trafficLights = new ArrayList<TrafficLight>();

		TrafficLight t1 = new TrafficLight(new File("Test/trafficLight.png"), 320, 145, 180, 0, 1, this);
		t1.setLeft_light_pos(new Vector2(320,145));
		t1.setForward_pos(new Vector2(320+23, 145));
		t1.setRight_light_pos(new Vector2(320+47, 145));
		trafficLights.add(t1);

		TrafficLight t2 = new TrafficLight(new File("Test/trafficLight.png"), 260, 520, 90, 1, 2, this);
		t2.setLeft_light_pos(new Vector2(282,500));
		t2.setForward_pos(new Vector2(282,523));
		t2.setRight_light_pos(new Vector2(282, 548));
		trafficLights.add(t2);

		TrafficLight t3 = new TrafficLight(new File("Test/trafficLight.png"), 950, 580, 0, 0, 3, this);
		t3.setLeft_light_pos(new Vector2(950, 580));
		t3.setForward_pos(new Vector2(950+25, 580));
		t3.setRight_light_pos(new Vector2(950+48, 580));
		trafficLights.add(t3);

		TrafficLight t4 = new TrafficLight(new File("Test/trafficLight.png"), 1010, 195, -90, 1, 4, this);
		t4.setLeft_light_pos(new Vector2(1030, 175));
		t4.setForward_pos(new Vector2(1030, 175+25));
		t4.setRight_light_pos(new Vector2(1030, 175+47));
		trafficLights.add(t4);

		//First front vehicles
		v1 = new Vehicle(new File("Test/car2.png"), 6, VehicleState.MOVE_X, VehicleDirection.RIGHT, trafficLights.get(1),this, null, 0);
		v2 = new Vehicle(new File("Test/car2.png"), 5, VehicleState.MOVE_Y, VehicleDirection.DOWN, trafficLights.get(0),this, null, 0);
		v3 = new Vehicle(new File("Test/car2.png"), 6, VehicleState.MOVE_X, VehicleDirection.LEFT, trafficLights.get(3),this, null, 0);
		v4 = new Vehicle(new File("Test/car2.png"), 5, VehicleState.MOVE_Y, VehicleDirection.UP, trafficLights.get(2),this, null, 0);

		vehiclesRight = new ArrayList<Vehicle>();
		vehiclesLeft = new ArrayList<Vehicle>();
		vehiclesDown = new ArrayList<Vehicle>();
		vehiclesUp = new ArrayList<Vehicle>();
		vehiclesRight.add(v1); vehiclesDown.add(v2); vehiclesLeft.add(v3); vehiclesUp.add(v4);

		//car1.jpg->png
		try {
			car1 = ImageIO.read(new File("Test/car1.png"));
			mTerrain = ImageIO.read(new File("Test/road1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
