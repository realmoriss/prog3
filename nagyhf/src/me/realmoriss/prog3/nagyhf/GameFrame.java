package me.realmoriss.prog3.nagyhf;

import me.realmoriss.prog3.nagyhf.entities.Ball;
import me.realmoriss.prog3.nagyhf.entities.Brick;
import me.realmoriss.prog3.nagyhf.entities.GraphicEntity;
import me.realmoriss.prog3.nagyhf.entities.Macska;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created on 11/28/16.
 */
public class GameFrame extends JFrame {
	private int gameState = 0;

	private static final String FRAME_TITLE = "NagyHF";
	private GameCanvas canvas;
	private Ball ball;

	private ArrayList<GraphicEntity> entList;
	private ArrayList<Brick> brickList;
	private ArrayList<Brick> removeList;

	private int mul = 1;

	public GameFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle(FRAME_TITLE);

		JPanel centerPanel = new JPanel();
		canvas = new GameCanvas();

		centerPanel.add(canvas);
		this.add(centerPanel, BorderLayout.CENTER);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void checkBallCollides() {
		Rectangle topRect = new Rectangle(0, -1, canvas.getWidth(), 1);
		Rectangle rightRect = new Rectangle(canvas.getWidth()+1, 0, 1, canvas.getHeight());
		Rectangle bottomRect = new Rectangle(0, canvas.getHeight()+1, canvas.getWidth(), 1);
		Rectangle leftRect = new Rectangle(-1, 0, 1, canvas.getHeight());

		if (ball.doesCollide(topRect)) {
			ball.setSpeed(ball.getSpeed().mirrorY());
			ball.setPos(ball.getPos().getX(), 1);
		}
		if (ball.doesCollide(bottomRect)) {
			ball.setSpeed(ball.getSpeed().mirrorY());
			ball.setPos(ball.getPos().getX(), canvas.getHeight()-ball.getRect().getHeight()-1);
		}
		if (ball.doesCollide(leftRect)) {
			ball.setSpeed(ball.getSpeed().mirrorX());
			ball.setPos(1, ball.getPos().getY());
		}
		if (ball.doesCollide(rightRect)) {
			ball.setSpeed(ball.getSpeed().mirrorX());
			ball.setPos(canvas.getWidth()-ball.getRect().getWidth()-1, ball.getPos().getY());
		}
	}

	private boolean menuState() {
		canvas.clear();
		canvas.repaint();

		return false;
	}

	private boolean initState() {
		entList = new ArrayList<>(100);
		brickList = new ArrayList<>(100);
		removeList = new ArrayList<>(100);

		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				Brick b = new Brick(new Vec2D(16+i*(32+2)+(j%2)*16, 32+j*(16+2)), "Brick"+i+"_"+j);
				entList.add(b);
				brickList.add(b);
			}
		}

		int ballsize = 16;
		ball = new Ball(new Vec2D(canvas.getWidth()/2-ballsize/2, canvas.getHeight()-32-ballsize/2), "DefBall", ballsize/2);
		ball.setSpeed(new Vec2D(1, -6));
		entList.add(ball);

		return false;
	}

	private boolean gameState() {
		ball.setPos(ball.getPos().add(ball.getSpeed()));
		checkBallCollides();

		for (Brick b: brickList) {
			if (ball.doesCollide(b.getRect())) {
				System.out.println(b.getName());
				b.destroy();
				removeList.add(b);
			}
		}

		for (Brick b: removeList) {
			entList.remove(b);
			brickList.remove(b);
		}

		removeList.clear();
		canvas.clear();

		for (GraphicEntity ge: entList) {
			canvas.paintEntity(ge);
		}

		canvas.repaint();

		if (brickList.isEmpty()) {
			brickList.clear();
			entList.clear();
			System.gc();
			System.out.println("You're Winner");
			return false;
		} else {
			return true;
		}
	}

	public void step() {
		if (gameState == 0) {
			if (!menuState()) {
				gameState = 1;
			}
		} else if (gameState == 1) {
			if (!initState()) {
				gameState = 2;
			}
		} else if (gameState == 2) {
			if (!gameState()) {
				gameState = 0;
			}
		}
	}
}
