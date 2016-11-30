package me.realmoriss.prog3.nagyhf;

import com.sun.org.apache.bcel.internal.generic.ICONST;
import me.realmoriss.prog3.nagyhf.entities.*;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

import javax.json.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created on 11/28/16.
 */
public class GameFrame extends JFrame {
	private static final String FRAME_TITLE = "NagyHF";
	private static final int BRICK_SCORE = 5;
	private static final int BRICK_GREEN_SCORE = 10;
	private static final int BRICK_CYAN_SCORE = 15;
	private static final int BRICK_RED_SCORE = 20;
	private static final int BRICK_YELLOW_SCORE = 30;
	private static final int PADDLE_SCORE = -1;

	private int gameState = 0;
	private GameCanvas canvas;

	private JMenuBar gameMenu;

	private Ball ball;
	private Paddle paddle;
	private int score;
	private int highScore = 0;

	private ArrayList<GraphicEntity> entList;
	private ArrayList<Brick> brickList;
	private ArrayList<Brick> removeList;

	private GameMenu menu;

	private GraphicEntity background;

	public GameFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle(FRAME_TITLE);

		canvas = new GameCanvas();
		this.add(canvas, BorderLayout.CENTER);

		gameMenu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_X);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exit);
		gameMenu.add(fileMenu);
		this.add(gameMenu, BorderLayout.NORTH);

		this.pack();

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		try {
			JsonReader reader = Json.createReader(new FileInputStream("assets/save.json"));
			JsonObject obj = reader.readObject();
			highScore = obj.getInt("highscore");
		} catch (FileNotFoundException | JsonException e) {
			e.printStackTrace();
			highScore = 0;
		}
	}

	/**
	 * Lepteti a jatekot a kovetkezo allapotra
	 */
	public void step() {
		if (gameState == 0) {
			if (!menuInitState()) {
				gameState = 1;
			}
		} else if (gameState == 1) {
			if (!menuState()) {
				gameState = 2;
			}
		} else if (gameState == 2) {
			if (!gameInitState()) {
				gameState = 3;
			}
		} else if (gameState == 3) {
			if (!gameState()) {
				try {
					JsonWriter writer = Json.createWriter(new FileOutputStream("assets/save.json"));
					JsonObject obj = Json.createObjectBuilder().add("highscore", highScore).build();
					writer.writeObject(obj);
					writer.close();
				} catch (FileNotFoundException | JsonException e) {
					e.printStackTrace();
				}
				gameState = 0;
				System.gc();
			}
		}
	}

	/**
	 * A menu inicializalasa allapot
	 * @return	false, ha az allapot tevekenysege veget ert
	 */
	private boolean menuInitState() {
		menu = new GameMenu();
		this.addKeyListener(menu);
		background = new GraphicEntity(new Vec2D(0, 0), "Background", "assets/img/background.png");
		return false;
	}

	/**
	 * A menu megjelenitese allapot
	 * @return	false, ha az allapot tevekenysege veget ert
	 */
	private boolean menuState() {
		canvas.clear();
		canvas.paintEntity(background);

		BufferedImage menuImg = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) menuImg.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.BOLD, 14));

		g.drawString("High Score: " + highScore, 172, 32);
		g.drawString("Press enter to start" , 96, 240);
		canvas.paintImage(menuImg, 0, 0);

		canvas.repaint();
		return menu.isInMenu();
	}

	/**
	 * A jatek inicializalasa allapot
	 * @return	false, ha az allapot tevekenysege veget ert
	 */
	private boolean gameInitState() {
		entList = new ArrayList<>(100);
		brickList = new ArrayList<>(100);
		removeList = new ArrayList<>(100);

		entList.add(background);

		int brickHeight = 16;
		int brickWidth = 32;
		int xStart = 24;
		int yStart = 64;

		for (int i=0; i<8; i++) {
			int row = 0;
			Brick b = new Brick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new Brick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new GreenBrick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new GreenBrick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new YellowBrick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new YellowBrick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new CyanBrick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new CyanBrick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new RedBrick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
			++row;
			b = new RedBrick(new Vec2D(xStart+i*brickWidth+(row%2)*brickWidth/2, yStart+row*brickHeight), "Brick"+i+"_"+row);
			entList.add(b);
			brickList.add(b);
		}

		int ballsize = 16;
		ball = new Ball(new Vec2D(canvas.getWidth()/2-ballsize/2, canvas.getHeight()-32-ballsize), "DefBall", ballsize/2);
		ball.setSpeed(new Vec2D(0, -6));
		entList.add(ball);

		paddle = new Paddle(new Vec2D(canvas.getWidth()/2-48, canvas.getHeight()-32), "DefPaddle");
		entList.add(paddle);
		this.addKeyListener(paddle);

		score = 0;

		return false;
	}

	/**
	 * A jatek allapot
	 * @return	false, ha az allapot tevekenysege veget ert
	 */
	private boolean gameState() {
		boolean ballFallen = false;
		ball.setPos(ball.getPos().add(ball.getSpeed()));

		paddle.step();
		checkPaddleCollides();

		if (ballHitsPaddle()) {
			score += PADDLE_SCORE;
		}

		if (checkBallCollides()) {
			ballFallen = true;
		}

		int i = 0;
		for (Brick b: brickList) {
			if (ball.doesCollide(b.getRect())) {
				removeList.add(b);
			}
		}

		Brick nearestBrick = null;
		double minDistance = -1;

		for (Brick b: removeList) {
			Point brickCenter = b.getCenterPoint();
			Point ballCenter = ball.getCenterPoint();
			double dist = brickCenter.distance(ballCenter);
			if (dist < minDistance || minDistance == -1) {
				minDistance = dist;
				nearestBrick = b;
			}
		}

		removeList.clear();

		if (nearestBrick != null) {
			entList.remove(nearestBrick);
			brickList.remove(nearestBrick);
			nearestBrick.destroy();
			switch (nearestBrick.getClassname()) {
				case "prop_brick":
					score += BRICK_SCORE;
					break;
				case "prop_brick_green":
					score += BRICK_GREEN_SCORE;
					break;
				case "prop_brick_cyan":
					score += BRICK_CYAN_SCORE;
					break;
				case "prop_brick_red":
					score += BRICK_RED_SCORE;
					break;
				case "prop_brick_yellow":
					score += BRICK_YELLOW_SCORE;
					break;
			}
			ball.setSpeed(ball.getSpeed().mirrorY());
		}

		removeList.clear();
		canvas.clear();

		for (GraphicEntity ge: entList) {
			canvas.paintEntity(ge);
		}

		BufferedImage menuImg = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) menuImg.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.BOLD, 14));
		g.drawString("Score: " + score, 32, 32);
		g.drawString("High Score: " + highScore, 172, 32);
		canvas.paintImage(menuImg, 0, 0);

		canvas.repaint();

		if (brickList.isEmpty()) {
			System.out.println("You're Winner");
			System.out.println("Score: " + score);
			if (highScore < score) {
				highScore = score;
			}
			return false;
		}

		if (ballFallen) {
			if (highScore < score) {
				highScore = score;
			}
			return false;
		}

		return true;
	}

	/**
	 * Megvizsgalja, hogy a labda utkozik-e a fallal
	 * @return true, ha van utkozes
	 */
	public boolean checkBallCollides() {
		Rectangle topRect = new Rectangle(0, 0, canvas.getWidth(), 16);
		Rectangle rightRect = new Rectangle(canvas.getWidth()-16, 16, 16, canvas.getHeight()-16);
		Rectangle bottomRect = new Rectangle(0, canvas.getHeight()+1, canvas.getWidth(), 1);
		Rectangle leftRect = new Rectangle(0, 16, 16, canvas.getHeight()-16);
		boolean bottomCollide = false;

		if (ball.doesCollide(topRect)) {
			ball.setSpeed(ball.getSpeed().mirrorY());
			ball.setPos(ball.getPos().getX(), 16);
		}
		if (ball.doesCollide(bottomRect)) {
			bottomCollide = true;
		}
		if (ball.doesCollide(leftRect)) {
			ball.setSpeed(ball.getSpeed().mirrorX());
			ball.setPos(16, ball.getPos().getY());
		}
		if (ball.doesCollide(rightRect)) {
			ball.setSpeed(ball.getSpeed().mirrorX());
			ball.setPos(canvas.getWidth()-ball.getRect().getWidth()-16, ball.getPos().getY());
		}

		return bottomCollide;
	}

	/**
	 * Megvizsgalja, hogy az uto utkozik-e a fallal
	 * @return true, ha van utkozes
	 */
	private boolean checkPaddleCollides() {
		Rectangle rightRect = new Rectangle(canvas.getWidth()-16, 16, 16, canvas.getHeight()-16);
		Rectangle leftRect = new Rectangle(0, 16, 16, canvas.getHeight()-16);

		if (paddle.getRect().intersects(leftRect)) {
			paddle.setPos(16, paddle.getPos().getY());
			return true;
		}
		if (paddle.getRect().intersects(rightRect)) {
			paddle.setPos(canvas.getWidth()-paddle.getRect().getWidth()-16, paddle.getPos().getY());
			return true;
		}

		return false;
	}

	/**
	 * Megvizsgalja, hogy a labda utkozik-e az utovel
	 * @return true, ha van utkozes
	 */
	private boolean ballHitsPaddle() {
		if (ball.doesCollide(paddle.getRect())) {
			Rectangle is = paddle.getRect().intersection(ball.getRect());
			double dist = paddle.getRect().getCenterX() - is.getCenterX();
			Vec2D ballSpeed = ball.getSpeed();
			ballSpeed.setX(-dist/10);
			ballSpeed.mirrorY();
			ball.setPos(ball.getPos().getX(), paddle.getPos().getY()-ball.getRect().getHeight());
			ball.setSpeed(ballSpeed);
			return true;
		} else {
			return false;
		}
	}
}
