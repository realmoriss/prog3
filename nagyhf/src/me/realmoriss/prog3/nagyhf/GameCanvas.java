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

	public void paintSquare(Vec2D pos, Vec2D size, Color c) {
		Graphics g = canvas_img.getGraphics();
		g.setColor(c);
		g.fillRect(pos.getXi(), pos.getYi(), size.getXi(), size.getYi());
		g.dispose();
	}

	public void paintSquare(int x, int y, int w, int h, Color c) {
		Graphics g = canvas_img.getGraphics();
		g.setColor(c);
		g.fillRect(x, y, w, h);
		g.dispose();
	}

	public void paintEntity(GraphicEntity ent) {
		Graphics g = canvas_img.getGraphics();
		g.drawImage(ent.getImg(), ent.getPos().getXi(), ent.getPos().getYi(), null);
		g.dispose();
	}

	public void paintImage(Image i, int pos_x, int pos_y) {
		Graphics g = canvas_img.getGraphics();
		g.drawImage(i, pos_x, pos_y, null);
		g.dispose();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(canvas_img,0,0,null);
	}

	public void clear() {
		Graphics g = canvas_img.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, canvas_w, canvas_h);
		g.dispose();
	}

	public Rectangle getRect() {
		return new Rectangle(0, 0, canvas_w, canvas_h);
	}
}
