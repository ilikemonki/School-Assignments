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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Mydesktopgame;


public class GameOverState extends State{
	private Texture bg;
	private Texture gameOver;
	private HUD hud;
	
	private Stage stage;
	
	//Main menu's texture and button
	private Texture mainMenu;
	private Texture offMainMenu;
	private TextureRegion mMRegion;
    private TextureRegionDrawable mMRegionDrawable;
    private ImageButton mMButton;

    //try again's texture and button
	private Texture tryAgain;
	private Texture offTryAgain;
    private TextureRegion tARegion;
    private TextureRegionDrawable tARegionDrawable;
    private ImageButton tAButton;

	protected GameOverState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, Mydesktopgame.WIDTH , Mydesktopgame.HEIGHT );	//set cam
		bg = new Texture("background.jpg");		//bg image
		gameOver = new Texture("Game Over.png");
		hud = new HUD(gsm);
		
		//Set image and button for main menu
		mainMenu = new Texture("main_menu_20000.png");
		offMainMenu = new Texture("main_menu_10000.png");
        mMRegion = new TextureRegion(mainMenu);
        mMRegionDrawable = new TextureRegionDrawable(mMRegion);
        mMButton = new ImageButton(mMRegionDrawable); //Set the button up
        mMButton.setPosition(Mydesktopgame.WIDTH  - 600, 150);	//set button position
        
        //Set image and button for try again
        tryAgain = new Texture("try_again_20000.png");
        offTryAgain = new Texture("try_again_10000.png");
        tARegion = new TextureRegion(tryAgain);
        tARegionDrawable = new TextureRegionDrawable(tARegion);
        tAButton = new ImageButton(tARegionDrawable); //Set the button up
        tAButton.setPosition(Mydesktopgame.WIDTH - 400, 150);	//set button position

        //Add buttons to stage
        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(mMButton); //Add the button to the stage to perform rendering and take input.
        stage.addActor(tAButton);
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
        
        //Main menu button click/actions
        mMButton.addListener(new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		    	HUD.score = 0;
		        clickMainMenu();	//go to main menu
		    }
		});
        
        //Try again button click/actions
        tAButton.addListener(new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		    	HUD.score = 0;
		        clickTryAgain();	//go to playstate
		    }
		});

	}

	@Override
	protected void handleInput() {
	//Only input should be the buttons
	}
	
	//main menu button actions
	public void clickMainMenu() {
		gsm.set(new MenuState(gsm));
	}
	//try again button actions
	public void clickTryAgain() {
		gsm.set(new PlayState(gsm));
	}
		    
	@Override
	public void update(float dt) {
		handleInput();

	}

	@Override
	public void render(SpriteBatch sb) {
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();	
		sb.draw(bg, 0, 0, Mydesktopgame.WIDTH , Mydesktopgame.HEIGHT);
		sb.draw(gameOver, cam.position.x - gameOver.getWidth() / 2, cam.position.y  - gameOver.getHeight() / 2);	//set gameover texture at middle	
		//display score and coin before reseting
		hud.bitmapfontName.draw(sb, hud.scoreName, Mydesktopgame.WIDTH - 770, Mydesktopgame.HEIGHT - 20);	//hud score
		hud.bitmapfontName.draw(sb, hud.coinName, Mydesktopgame.WIDTH - 768, Mydesktopgame.HEIGHT - 50);	//hud coin

		sb.end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();	//draw stage on screen
	}

	@Override
	public void dispose() {
		bg.dispose();
		gameOver.dispose();
		stage.dispose();
		mainMenu.dispose();
		tryAgain.dispose();
		hud.dispose();
	}

}
