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

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Mydesktopgame;

import sprites.Bird;
import sprites.Boss;
import sprites.Bullet;
import sprites.Monster;
import sprites.PowerUps;
import sprites.Coin;

public class PlayState extends State {
	//Our player
	private Bird bird;
	
	//Monsters
	private Array<Monster> monsters;
	private Array<Monster> removeMonsters;
	private float monsterTimer;
	private float monsterSpawnTime = 1.0f;
	
	//Coins
	private Array<Coin> coins;
	private Array<Coin> removeCoins;
	private float coinTimer;
	private float coinSpawnTime = 5.0f;
	
	//Powerups
	private Array<PowerUps> powerups;
	private Array<PowerUps> removePowerups;
	private float powerupTimer;
	private float powerupSpawnTime = 8.0f;
	private float shotspeedTimer, morebulletsTimer, morecoinsTimer;
	private boolean doubleBullets = false;
	
	//Bullets
	private Array<Bullet> bullets;
	private Array<Bullet> removeBullets;
	private float bulletTimer;
	private float bulletInterval = 0.6f;
	
	//Boss
	private Boss boss;
	private float bossSpawnTime;
	private boolean bossAppear = false;
	private int incrementBossHealth = 0;
	//Boss's Bullets
	private Array<Bullet> bossbullets;
	private Array<Bullet> removebossBullets;
	private float bossbulletTimer;
	private float bossbulletInterval = 0.5f;
	
	//Other awesome variables
	private HUD hud;
	private Background bg;
	private Random rand;
	
	protected PlayState(GameStateManager gsm) {		//Constructor
		super(gsm);
		bird = new Bird(-100f, 300f);		//position of bird at start of game
		//Screen size, what we will see
		cam.setToOrtho(false, Mydesktopgame.WIDTH , Mydesktopgame.HEIGHT);	//this cam follows player
		fixedCam.setToOrtho(false, Mydesktopgame.WIDTH , Mydesktopgame.HEIGHT);	//this cam stays on screen
		bg = new Background();
		
		//Initialize and create arrays
		monsters = new Array<Monster>();
		removeMonsters = new Array<Monster>();
		
		bullets = new Array<Bullet>();
		removeBullets = new Array<Bullet>();
		
		coins = new Array<Coin>();
		removeCoins = new Array<Coin>();
		
		powerups = new Array<PowerUps>();
		removePowerups = new Array<PowerUps>();
		
		bossbullets = new Array<Bullet>();
		removebossBullets = new Array<Bullet>();
		
		hud = new HUD(gsm);
		rand = new Random();
	}

	//handles input
	protected void handleInput() {
		if (Gdx.input.isTouched()) {		//when tap or hold down, bird jumps
			bird.jump();
		}
	}

	//updates everything that moves or needs to be updated
	public void update(float dt) {
		
		handleInput();
		bird.update(dt);	//updates bird's position, animation, bounds, etc.
		cam.position.x = bird.getPosition().x + 350f;		//set camera to follow bird
		cam.update();
		fixedCam.update();
		bg.update(dt);	//animates background
		
		//spawn monsters and make them move and collide with player
		monsterTimer += dt;
		if (monsterTimer > monsterSpawnTime) {		//Spawn monsters
			monsters.add(new Monster((int)(cam.position.x + cam.viewportWidth) , rand.nextInt(440), rand.nextInt(100)));
			monsterTimer = 0;	//reset monster timer
		}
		//For all monsters
		for (Monster monster : monsters) {
			monster.update(dt);	//update monster
			if (monster.collides(bird.getBounds())) {	//monster collides with player
				gsm.set(new GameOverState(gsm));	//play gameover state
				break;
			}
			if(monster.getPosition().x < cam.position.x - (cam.viewportWidth / 2)) {
				removeMonsters.add(monster);	//remove monster when out of bounds
			}
			if (removeMonsters.size > 0)
			monsters.removeAll(removeMonsters, true);	//remove monster array
		}
		
		//spawn coins and make player collect them
		coinTimer += dt;
		if (coinTimer > coinSpawnTime) {		//Spawn monsters
			int coinY = rand.nextInt(450);	//get random y-axis for coin
			coins.add(new Coin((int)(cam.position.x + cam.viewportWidth) , coinY));
			if (rand.nextInt(3) == 0) {		//30% chance to spawn second coin
				coins.add(new Coin((int)(cam.position.x + cam.viewportWidth) + 40, coinY));
				if (rand.nextInt(3) == 0) {		//30% to spawn third coin
					coins.add(new Coin((int)(cam.position.x + cam.viewportWidth) + 80, coinY));
				}
			}
			coinTimer = 0;	//reset coin spawn time
		}
		//For all coins
		for (Coin coin : coins) {
			if (coin.collides(bird.getBounds())) {	//monster collides with player
				removeCoins.add(coin);	//remove coin when collide
				HUD.score += 500;	//add score
				HUD.coinTotal += 1;		//add coin total
				hud.coinName = "Coins: " + HUD.coinTotal;	//updates coin and score
				hud.scoreName = "Score: " + HUD.score;
			}
			if(coin.getPosition().x < cam.position.x - (cam.viewportWidth / 2)) {
				removeCoins.add(coin);	//remove when coin is out of bounds
			}
			if (removeCoins.size > 0) {
				coins.removeAll(removeCoins, true);	//delete the removed coins
			}
		}
		
		//spawn powerups and activate their effects/timer
		powerupTimer += dt;
		if (powerupTimer > powerupSpawnTime) {		//Spawn power ups
			if (rand.nextInt(3) == 0) {		//chance to spawn powerups
				powerups.add(new PowerUps((int)(cam.position.x + cam.viewportWidth) , rand.nextInt(450)));
			}
			powerupTimer = 0;	//reset powerup timer
		}
		//For all powerups
		for (PowerUps powerup : powerups) {
			if (powerup.collides(bird.getBounds())) {	//powerup collides with player
				removePowerups.add(powerup);
				switch(powerup.getPowerType()) {	//find powerup type and activates its effect
					case 0: bulletInterval = 0.3f; 
							shotspeedTimer = 12.0f;	
							break;
					case 1: doubleBullets = true; 
							morebulletsTimer = 14.0f;
							break;
					case 2: coinSpawnTime = 1.0f; 
							morecoinsTimer = 8.0f;
							break;
				}
			}
			if(powerup.getPosition().x < cam.position.x - (cam.viewportWidth / 2)) {
				removePowerups.add(powerup);	//remove powerup if out screen
			}
			if (removePowerups.size > 0) {
				powerups.removeAll(removePowerups, true);	//delete the removed powerups
			}
		}
		
		//Spawn bullets and make them hit stuff
		bulletTimer += dt;	
		if (bulletTimer > bulletInterval) {	//bullet interval and position
			if (doubleBullets) {	//true when player gets double bullet powerup
				bullets.add(new Bullet((int)bird.getPosition().x + 25, (int)bird.getPosition().y + 30, true));
				bullets.add(new Bullet((int)bird.getPosition().x + 25, (int)bird.getPosition().y + 10, true));
			}
			else
				bullets.add(new Bullet((int)bird.getPosition().x + 25, (int)bird.getPosition().y + 10, true));
			bulletTimer = 0;	//reset the timer.
		}
		//For all bullets
		for (Bullet bullet : bullets) {
			//For all monsters and bullets
			for (Monster monsterHit : monsters) {			//For all bullet&monster
				if (bullet.collides(monsterHit.getBounds())) {
					removeBullets.add(bullet);		//remove them if hit and increase score.
					removeMonsters.add(monsterHit);
					HUD.score += 100;
					hud.scoreName = "Score: " + HUD.score;
				}
			}
			bullet.update(dt);	//update bullets
			
			if(bossAppear) {		//If boss appears, bullet can hit it and it will disappear and reset
				if(bullet.collides(boss.getBounds())) {
					removeBullets.add(bullet);
					boss.setBossHealth(boss.getBossHealth() - 1);	//subtract 1 when bullets hits boss
					if (boss.getBossHealth() == 0) {	//when boss's life is zero
						bossAppear = false;		//set boolean to false
						bossSpawnTime = 0;		//reset boss timer
						incrementBossHealth++;
						boss.dispose();			//dispose boss
						removebossBullets.addAll(bossbullets);	//remove all boss's bullets
						HUD.score += 1000;
						hud.scoreName = "Score: " + HUD.score;
					}
					bossbullets.removeAll(removebossBullets, true);		//delete the removed boss bullets
				}
			}
			
			if(bullet.getPosition().x >  cam.position.x + cam.viewportWidth / 2) {
				removeBullets.add(bullet);		//remove bullet if it goes out
			}
			if (removeBullets.size > 0)
			bullets.removeAll(removeBullets, true);
		}
		
		//Decrease powerup timers to zero then revert state back to original.
		if(shotspeedTimer > 0)
			shotspeedTimer -= dt;	//decrease timer
		else if (shotspeedTimer < 0)
			bulletInterval = 0.6f;		//revert back to normal bullet interval
		if(morebulletsTimer > 0)
			morebulletsTimer -= dt;
		else if (morebulletsTimer < 0)
			doubleBullets = false;		//set double bullets to false to revert back to single bullet
		if(morecoinsTimer > 0)
			morecoinsTimer -= dt;
		else if (morecoinsTimer < 0)
			coinSpawnTime = 5.0f;		//revert back to normal coin spawn time
		
		//spawn boss at a certain time, set health
		bossSpawnTime += dt;
		if (bossSpawnTime > 10 && !bossAppear) {
			bossAppear = true;
			boss = new Boss(bird.getPosition().x + 650, rand.nextInt(400));
			boss.setBossHealth(5 + incrementBossHealth);
			}
		
		//Spawn boss's bullets
		if(bossAppear) {
			boss.update(dt);
			bossbulletTimer += dt;	
			if (bossbulletTimer > bossbulletInterval) {	//boss's bullet interval and position
				bossbullets.add(new Bullet((int)boss.getPosition().x - 20, (int)boss.getPosition().y + 10, false));
				bossbulletTimer = 0;	//reset the timer.
			}
			//For all boss's bullets	
			for (Bullet bossbullet : bossbullets) {
				//if boss's bullet hits me, its game over man
				if (bossbullet.collides(bird.getBounds())) {
					removebossBullets.add(bossbullet);
					gsm.set(new GameOverState(gsm));
					break;
				}
				
				bossbullet.setMOVEMENT(-250);	//set boss's bullet speed
				bossbullet.update(dt);	//update boss's bullets
					
				if(bossbullet.getPosition().x >  cam.position.x + cam.viewportWidth / 2) {
					removebossBullets.add(bossbullet);		//remove bullet if it goes out
				}
				if (removebossBullets.size > 0)
					bossbullets.removeAll(removebossBullets, true);
			}
		}
	}

	
	public void render(SpriteBatch sb) {
		//Fixed camera
		sb.setProjectionMatrix(fixedCam.combined);
		sb.begin();
		sb.draw(bg.getTexture(), 0, 0, Mydesktopgame.WIDTH, Mydesktopgame.HEIGHT);	//bg stays with cam view
		//display score
		hud.bitmapfontName.draw(sb, hud.scoreName, Mydesktopgame.WIDTH - 770, Mydesktopgame.HEIGHT - 20);
		//display coin
		hud.bitmapfontName.draw(sb, hud.coinName, Mydesktopgame.WIDTH - 768, Mydesktopgame.HEIGHT - 50);
		sb.end();
		
		//Bird camera
		sb.setProjectionMatrix(cam.combined);	//for things that move with camera
		sb.begin();
		sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);	//draw player

		//draw boss when it spawns
		if (bossAppear)
			sb.draw(boss.getTexture(), boss.getPosition().x, boss.getPosition().y);
		
		//draw all powerups that spawn
		for(PowerUps powerup : powerups) {
			sb.draw(powerup.getPowerup(), powerup.getPosition().x, powerup.getPosition().y);
		}
		//draw all coins that spawn
		for(Coin coin : coins) {
			sb.draw(coin.getCoin(), coin.getPosition().x, coin.getPosition().y, 30, 30);
		}
		//draw all monsters that spawn
		for(Monster monster : monsters) {
			sb.draw(monster.getTexture(), monster.getPosition().x, monster.getPosition().y);
		}
		//draw all bullets that spawn
		for (Bullet bullet : bullets) {
			sb.draw(bullet.getBullet(), bullet.getPosition().x, bullet.getPosition().y);
		}
		//draw all boss bullets that spawn
		for (Bullet bossbullet : bossbullets) {
			sb.draw(bossbullet.getBullet(), bossbullet.getPosition().x, bossbullet.getPosition().y);
		}
		
		sb.end();
		
	}

	//dispose all disposable resource
	public void dispose() {
		bg.dispose();
		
		bird.dispose();
		//For everything in an array, dispose them.
		for (Monster monster : monsters) monster.dispose();
		for (Monster reMonster : removeMonsters) reMonster.dispose();
		for (Bullet bullet : bullets) bullet.dispose();
		for (Bullet reBullet : removeBullets) reBullet.dispose();
		for (Coin coin : coins) coin.dispose();
		for (Coin reCoin : removeCoins) reCoin.dispose();
		for (PowerUps powerup : powerups) powerup.dispose();
		for (PowerUps rePowerup : removePowerups) rePowerup.dispose();
		for (Bullet bossbullet : bossbullets) bossbullet.dispose();
		for (Bullet rebossBullet : removebossBullets) rebossBullet.dispose();
		
		if(bossAppear)
			boss.dispose();
		hud.dispose();
		
	}
	
	

}
