// Meng Cha
// CECS 277
// Two Cars Moving
// 03/11/2018

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Car extends JPanel implements ActionListener {
	Timer timer = new Timer(10, this);	// time/speed
	//Variables for Car 1
	int posX1 = 0;
	int velocityX1 = 2;
	int posY1 = 100;
	
	//Variables for Car 2
	int posX2 = 430;
	int velocityX2 = -2;
	int posY2 = 200;
	
	//creates shapes with color
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//Shape and color of Car 1
		g.setColor(Color.red);
		g.fillRect(posX1, posY1,  50,  30);
		g.setColor(Color.black);
		g.fillOval(posX1, posY1 + 20, 20, 20);
		g.setColor(Color.black);
		g.fillOval(posX1 + 30, posY1 + 20, 20, 20);
		
		//Shape and color of Car 2
		g.setColor(Color.blue);
		g.fillRect(posX2, posY2,  50,  30);
		g.setColor(Color.black);
		g.fillOval(posX2, posY2 + 20, 20, 20);
		g.setColor(Color.black);
		g.fillOval(posX2 + 30, posY2 + 20, 20, 20);

		timer.start();
	}
	
	//Performs this action
	public void actionPerformed(ActionEvent e)
	{
		//The position of car1/car2 will increase/decrease by 2
		posX1 += velocityX1;	//velocity of car 1
		posX2 += velocityX2;	//velocity of car 2
		repaint();
	}

}
