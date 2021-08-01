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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Mydesktopgame;

public class CreditState extends State {
    /** private instance vars for credits and texture*/
    private Texture credits, texture, backBtnActive, backBtnInactive;

    /** Constructor for Credits State */
    public CreditState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, Mydesktopgame.WIDTH , Mydesktopgame.HEIGHT );	//set cam
        texture = new Texture("Credits.png"); //Retrieves the image from the assets folder
        credits = texture; //texture is stored in the instance variable credits
        backBtnActive = new Texture("back2000.png");
        backBtnInactive = new Texture("back0000.png");
    }
    /** Not used, but required by class to be implemented */
    public void handleInput(){

    }
    /** Not used, but required by class to be implemented */
    public void update(float dt){

    }
    /** Renders the sprite batch and displays the credits image from the assets folder */
    public void render(SpriteBatch sb){
        sb.setProjectionMatrix(cam.combined); //sets projection of camera in game
        sb.begin(); //start sprite batch for drawing
        sb.draw(credits, 0,0, Mydesktopgame.WIDTH, Mydesktopgame.HEIGHT); //sprite batch draws the png image
        int x = Mydesktopgame.WIDTH - backBtnActive.getWidth() - 20;
        if(Gdx.input.getX() < x + backBtnActive.getWidth() && Gdx.input.getX() > x && Mydesktopgame.HEIGHT - Gdx.input.getY() < 420 + backBtnActive.getHeight() &&
        		Mydesktopgame.HEIGHT - Gdx.input.getY() > 420){
            sb.draw(backBtnActive, x, 420, backBtnActive.getWidth(), backBtnActive.getHeight());
            if(Gdx.input.isTouched()) {
                gsm.set(new MenuState(gsm));
            }
        }else{
            sb.draw(backBtnInactive, x, 420, backBtnInactive.getWidth(), backBtnInactive.getHeight());
        }
        sb.end(); //end sprite batch
    }
    /** Not used, but required by class to be implemented */
    public void dispose(){
        backBtnActive.dispose();
        backBtnInactive.dispose();
    }

}
