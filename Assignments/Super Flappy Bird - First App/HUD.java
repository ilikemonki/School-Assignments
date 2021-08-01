package states;

/*
 * Group 1: Super Flappy Joyride
 * Meng Cha
 * Karthik Sivaramakrishnan
 * Tyler Ton
 * Justin Lee
 * Ervin Torres
 * Michael Serrano
 * 
 * Jayden Khakurel
 * CECS 277 Spring 2018 CSULB
 * 5/17/18
 */

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

//displays and initialize score and coin.
public class HUD extends State{
	public static int score;
	public static int coinTotal;
	Label scoreLabel;
	Label coinLabel;
	
	public String scoreName;
	public String coinName;
	BitmapFont bitmapfontName;
	
	protected HUD(GameStateManager gsm) {
		super(gsm);
		scoreName = "Score: " + score;	//display what score looks like at start
		coinName = "Coins: " + coinTotal;	//same for coin
		bitmapfontName = new BitmapFont();
		bitmapfontName.getData().setScale(2);	//scale the bitmapfont larger
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		bitmapfontName.dispose();
		
	}
}
