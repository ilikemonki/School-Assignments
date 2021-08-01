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

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
	
	protected OrthographicCamera cam;	//camera to follow bird
	protected OrthographicCamera fixedCam;	//fixed camera
	protected Vector3 mouse;	//input
	protected GameStateManager gsm;
	
	
	protected State(GameStateManager gsm) {
		this.gsm = gsm;
		cam = new OrthographicCamera();
		fixedCam = new OrthographicCamera();
		mouse = new Vector3();
	}
	
	protected abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	public abstract void dispose();
	
	

}
