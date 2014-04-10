package fr.game.engine.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Line2D;

import fr.game.engine.entity.Ball;
import fr.game.engine.entity.Bat;
import fr.game.engine.entity.Entity;
import fr.game.engine.entity.Sprite;
import fr.game.engine.view.Camera;
import fr.game.engine.view.FollowCamera;

public class Pong extends Game {
	public Pong() {
		super();
	}
	
	Bat bat1, bat2;
	Ball ball;
	Entity border;
	
	int score1 = 0;
	int score2 = 0;
	
	@Override
	protected void initialize() {
		// window.getViewport().setCamera();
	}
	
	@Override
	protected void load() {
		bat1 = new Bat(this);
		bat2 = new Bat(this);
		ball = new Ball(this);
		reset();
		
		window.getViewport().setCamera();
		
		border = new Entity(this);
		border.setSprite(new Sprite("Border", 800, 600));
		border.setWidth(800);
		border.setHeight(600);
		border.translate(400, 300);
		Graphics2D g = border.getSprite().getImage().createGraphics();
		g.setColor(Color.white);
		g.drawRect(0, 0, 799, 599);
		g.drawLine(400, 0, 400, 600);
		g.dispose();
		
		sceneManager.add(border);
		sceneManager.add(bat1);
		sceneManager.add(bat2);
		sceneManager.add(ball);
	}
	
	boolean playFrame = false;
	boolean lock = false;
	
	@Override
	protected void update(float dt) {
		
		if(control.isPressed(KeyEvent.VK_SPACE) && !lock) {
			playFrame = true;
			lock = true;
		}
		
		if(control.isPressed(KeyEvent.VK_N))
			playFrame = true;
		
		if(!control.isPressed(KeyEvent.VK_SPACE))
			lock = false;
		
		if(control.isPressed(KeyEvent.VK_ESCAPE)) {
			running = false;
		}
			
		sceneManager.update(dt);
			
			
		if(ball.getBounds().intersects(bat1.getBounds())/* || ball.getBounds().intersects(bat2.getBounds()) */) {
			float oldx = ball.getPosX() - ball.dx * dt;
			float oldy = ball.getPosY() - ball.dy * dt;
			float curx = ball.getPosX();
			float cury = ball.getPosY();
			
			float x1 = bat1.getBounds().x, x2 = bat1.getBounds().x + bat1.getBounds().width;
			float y1 = bat1.getBounds().y, y2 = bat1.getBounds().y + bat1.getBounds().height;
			
			float x3 = ball.getBounds().x, x4 = ball.getBounds().x + ball.getBounds().width;
			float y3 = ball.getBounds().y, y4 = ball.getBounds().y + ball.getBounds().height;
			
			if(Line2D.linesIntersect(oldx, oldy, bat1.getPosX(), bat1.getPosY(), x2, y1, x2, y2)) {
				ball.warp(oldx, oldy);
				ball.dx = -ball.dx;
			} else if(Line2D.linesIntersect(oldx, oldy, bat1.getPosX(), bat1.getPosY(), x1, y1, x2, y1)) {
				ball.warp(oldx, oldy);
				if(control.isPressed(KeyEvent.VK_UP))
					ball.translate(0, -0.5f * dt);
				ball.dy = -ball.dy;
			} else if(Line2D.linesIntersect(oldx, oldy, bat1.getPosX(), bat1.getPosY(), x1, y1, x1, y2)) {
				ball.warp(oldx, oldy);
				ball.dx = -ball.dx;
			} else if(Line2D.linesIntersect(oldx, oldy, bat1.getPosX(), bat1.getPosY(), x1, y2, x2, y2)) {
					ball.warp(oldx, oldy);
				if(control.isPressed(KeyEvent.VK_DOWN))
					ball.translate(0 , 0.5f * dt);
				ball.dy = -ball.dy;
			}	
		}
		
		if(ball.getBounds().intersects(bat2.getBounds())) {
			float oldx = ball.getPosX() - ball.dx * dt;
			float oldy = ball.getPosY() - ball.dy * dt;
			float curx = ball.getPosX();
			float cury = ball.getPosY();
			
			float x1 = bat2.getBounds().x, x2 = bat2.getBounds().x + bat2.getBounds().width;
			float y1 = bat2.getBounds().y, y2 = bat2.getBounds().y + bat2.getBounds().height;
			
			float x3 = ball.getBounds().x, x4 = ball.getBounds().x + ball.getBounds().width;
			float y3 = ball.getBounds().y, y4 = ball.getBounds().y + ball.getBounds().height;
			
			if(Line2D.linesIntersect(oldx, oldy, bat2.getPosX(), bat2.getPosY(), x2, y1, x2, y2)) {
				ball.warp(oldx, oldy);
				ball.dx = -ball.dx;
			} else if(Line2D.linesIntersect(oldx, oldy, bat2.getPosX(), bat2.getPosY(), x1, y1, x2, y1)) {
				ball.warp(oldx, oldy);
				if(control.isPressed(KeyEvent.VK_UP))
					ball.translate(0, -0.5f * dt);
				ball.dy = -ball.dy;
			} else if(Line2D.linesIntersect(oldx, oldy, bat2.getPosX(), bat2.getPosY(), x1, y1, x1, y2)) {
				ball.warp(oldx, oldy);
				ball.dx = -ball.dx;
			} else if(Line2D.linesIntersect(oldx, oldy, bat2.getPosX(), bat2.getPosY(), x1, y2, x2, y2)) {
					ball.warp(oldx, oldy);
				if(control.isPressed(KeyEvent.VK_DOWN))
					ball.translate(0 , 0.5f * dt);
				ball.dy = -ball.dy;
			}
		}
			
			if(ball.getBounds().y < 0 || ball.getBounds().y > 600) {
				ball.translate(-ball.dx, -ball.dy);
				ball.dy = -ball.dy;
			}
			
			if(ball.getBounds().intersectsLine(0, 0, 0, 600)) {
				++score2;
				// reset();
				ball.dx = -ball.dx;
			}
			
			if(ball.getBounds().intersectsLine(800, 0, 800, 600)) {
				++score1;
				// reset();
				ball.dx = -ball.dx;
			}
		
		playFrame = false;
			
		if(control.isPressed(KeyEvent.VK_R))
			reset();
		
	}
	
	private void reset() {
		bat1.warp(30, 300);
		bat2.warp(800 - 30, 300);
		ball.warp(400, 300);
	}
	
	@Override
	protected void draw() {
		window.render();
	}
}
