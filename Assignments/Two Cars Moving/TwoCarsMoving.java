// Meng Cha
// CECS 277
// Two Cars Moving
// 03/11/2018
import javax.swing.JFrame;

public class TwoCarsMoving {

	public static void main(String[] args) {
	
		JFrame frame = new JFrame();
		
		frame.setSize(500, 400);	//Sets frame size
		frame.setTitle("Two Cars Moving");	//Title of frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Car car = new Car();
		frame.add(car);

		
		frame.setVisible(true);
		
	}

}
