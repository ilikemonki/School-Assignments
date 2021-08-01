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


//Main menu
public class MenuState extends State{
    private Stage stage;

    private Texture background;
    private Texture texture;
    private Texture playBtnActive, playBtnInactive;
    private Texture creditBtnActive, creditBtnInactive;
    private Texture musicBtnActive, musicBtnInactive, musicBtnOff;

    private TextureRegion playBtnRegion;
    private TextureRegionDrawable playBtnRegionDrawable;
    private ImageButton playButton;

    private TextureRegion creditBtnRegion;
    private TextureRegionDrawable creditBtnRegionDrawable;
    private ImageButton creditButton;

    private TextureRegion musicBtnRegion;
    private TextureRegionDrawable musicBtnRegionDrawable;
    private ImageButton musicButton;
    final Mydesktopgame game = null;


    public MenuState(GameStateManager gsm) {
        super(gsm);		//calls constructor of superclass State
        cam.setToOrtho(false, Mydesktopgame.WIDTH , Mydesktopgame.HEIGHT );	//set cam
        texture = new Texture("SFJR.png");
        background = texture;
        playBtnActive = new Texture("playbutton2000.png");	// active play button image
        playBtnInactive = new Texture("playbutton0000.png"); //inactive play button image
        playBtnRegion = new TextureRegion(playBtnInactive);
        playBtnRegionDrawable = new TextureRegionDrawable(playBtnRegion);
        playButton = new ImageButton(playBtnRegionDrawable); //Set the button up
        playButton.setPosition(Mydesktopgame.WIDTH  / 2 - playButton.getWidth() / 2, cam.position.y / 2);	//set button position

        creditBtnActive = new Texture("credits2000.png");
        creditBtnInactive = new Texture("credits0000.png");
        creditBtnRegion = new TextureRegion(creditBtnInactive);
        creditBtnRegionDrawable = new TextureRegionDrawable(creditBtnRegion);
        creditButton = new ImageButton(creditBtnRegionDrawable); //Set the button up
        creditButton.setPosition(Mydesktopgame.WIDTH  / 2 - creditButton.getWidth() / 2, cam.position.y / 2 - 40);	//set button position

        musicBtnActive = new Texture("music2000.png");
        musicBtnInactive = new Texture("music0000.png");
        musicBtnOff = new Texture("music4000.png");
        musicBtnRegion = new TextureRegion(musicBtnInactive);
        musicBtnRegionDrawable = new TextureRegionDrawable(musicBtnRegion);
        musicButton = new ImageButton(musicBtnRegionDrawable); //Set the button up
        musicButton.setPosition(Mydesktopgame.WIDTH  / 2 - musicButton.getWidth() / 2, cam.position.y / 2 - 80);	//set button position

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(playButton); //Add the button to the stage to perform rendering and take input.
        stage.addActor(creditButton);
        stage.addActor(musicButton);
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        //add a listener to the buttons when it gets clicked
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickPlay();	//go to main menu
            }
        });

        creditButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickCredit();	//go to playstate
            }
        });

        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	//if music is playing, stop it
                if (Mydesktopgame.music.isPlaying()) {
                    Mydesktopgame.music.stop();
                }
                else	//else play it again.
                    Mydesktopgame.music.play();
            }
        });
    }

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
    	
    }

    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0, Mydesktopgame.WIDTH, Mydesktopgame.HEIGHT);
        
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();	//draw stage on screen
    }

    //actions for play button
    public void clickPlay() {
        gsm.set(new PlayState(gsm));
    }
    //try again button actions
    public void clickCredit() {
        gsm.set(new CreditState(gsm));
    }
    //actions for music button
    public void clickMusic() {
        gsm.set(new MenuState(gsm));
    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        playBtnActive.dispose();
        playBtnInactive.dispose();
        creditBtnActive.dispose();
        creditBtnInactive.dispose();
        musicBtnActive.dispose();
        musicBtnInactive.dispose();
    }



}

