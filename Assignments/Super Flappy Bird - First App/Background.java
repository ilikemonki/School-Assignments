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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.mygdx.game.Mydesktopgame;
import sprites.Animation;
//For gameplay background
public class Background {
	private Animation bgAnimation;
	private Texture bg;
	
	public TextureRegion getTexture() {
		return bgAnimation.getFrame();
	}

	public Background() {
		bg = new Texture("LandscapesLong.png");
		//Animates background
		bgAnimation = new Animation(new TextureRegion(bg), 8, 1.2f);		//gets the texture, 8 frames, cycle time.	
	}
	
	public void update(float dt) {
		bgAnimation.update(dt);	//update animation

	}
	
	public void dispose() {
		bg.dispose();
	}

}

