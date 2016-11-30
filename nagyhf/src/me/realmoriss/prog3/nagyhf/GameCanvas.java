package me.realmoriss.prog3.nagyhf;

import me.realmoriss.prog3.nagyhf.entities.GraphicEntity;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Created on 11/28/16.
 */
public class GameCanvas extends JPanel {
	private static final int DEF_W = 320;
	private static final int DEF_H = 480;
	private int canvas_w;
	private int canvas_h;
	private BufferedImage canvas_img;

	public GameCanvas() {
		super();
		canvas_w = DEF_W;
		canvas_h = DEF_H;
		canvas_img = new BufferedImage(DEF_W, DEF_H, BufferedImage.TYPE_INT_ARGB);
		Graphics g = canvas_img.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, canvas_w, canvas_h);
		g.dispose();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(canvas_w, canvas_h);
	}

	/**
	 * Kirajzol egy GraphicEntity a canvasra, az entity koordinataival
	 * @param ent a kirajzolni kivant entity
	 */
	public void paintEntity(GraphicEntity ent) {
		Graphics g = canvas_img.getGraphics();
		g.drawImage(ent.getImg(), ent.getPos().getXi(), ent.getPos().getYi(), null);
		g.dispose();
	}

	/**
	 * Kirajzol egy kepet a canvasra, a megadott pontra
	 * @param image	a kirajzolni kivant kep
	 * @param pos_x a kep x koordinataja
	 * @param pos_y a kep y koordinataja
	 */
	public void paintImage(Image image, int pos_x, int pos_y) {
		Graphics g = canvas_img.getGraphics();
		g.drawImage(image, pos_x, pos_y, null);
		g.dispose();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(canvas_img,0,0,null);
	}

	/**
	 * Letorli a canvast feher szinure
	 */
	public void clear() {
		Graphics g = canvas_img.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, canvas_w, canvas_h);
		g.dispose();
	}

	/**
	 * Visszaadja a canvast befoglalo dobozt
	 * @return a befoglalo doboz
	 */
	public Rectangle getRect() {
		return new Rectangle(0, 0, canvas_w, canvas_h);
	}
}
