package me.realmoriss.prog3.nagyhf.entities;

import me.realmoriss.prog3.nagyhf.entities.primitives.Entity;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created on 11/28/16.
 */
public class GraphicEntity extends Entity {
	private static final String DEF_CLASSNAME = "entity_graphic";
	protected BufferedImage img;
	protected static final int DEF_W = 32;
	protected static final int DEF_H = 32;
	protected Vec2D size;

	/**
	 * Letrehoz egy GraphicEntityt a megadott parameterekkel
	 * Az entity texturaja es merete alapertelmezetten DEF_W*DEF_H meretu ciankek kitoltott negyszog
	 * @param pos  Az entity pozicioja
	 * @param name Az entity neve
	 */
	public GraphicEntity(Vec2D pos, String name) {
		super(pos, name);
		classname = DEF_CLASSNAME;
		img = new BufferedImage(DEF_W, DEF_H, BufferedImage.TYPE_INT_ARGB);
		Graphics img_g = img.getGraphics();
		img_g.setColor(Color.cyan);
		img_g.fillRect(0, 0, img.getWidth(), img.getHeight());
		img_g.dispose();
		size = new Vec2D(DEF_W, DEF_H);
	}

	/**
	 * Letrehoz egy GraphicEntityt a megadott parameterekkel
	 * Az entity merete a textura merete lesz
	 * @param pos  Az entity pozicioja
	 * @param name Az entity neve
	 * @param img  Az entity texturaja
	 */
	public GraphicEntity(Vec2D pos, String name, BufferedImage img) {
		super(pos, name);
		classname = DEF_CLASSNAME;
		this.img = img;
		size = new Vec2D(img.getWidth(), img.getHeight());
	}

	/**
	 * Letrehoz egy GraphicEntityt a megadott parameterekkel
	 * @param pos  Az entity pozicioja
	 * @param name Az entity neve
	 * @param img  Az entity texturaja
	 * @param size	Az entity merete
	 */
	public GraphicEntity(Vec2D pos, String name, BufferedImage img, Vec2D size) {
		super(pos, name);
		classname = DEF_CLASSNAME;
		this.img = img;
		this.size = size;
		scaleImg(size);
	}

	/**
	 * Letrehoz egy GraphicEntityt a megadott parameterekkel
	 * @param pos_x  Az entity x koordinataja
	 * @param pos_y  Az entity y koordinataja
	 * @param name Az entity neve
	 * @param img  Az entity texturaja
	 * @param size_x  Az entity szelessege
	 * @param size_y  Az entity magassaga
	 */
	public GraphicEntity(int pos_x, int pos_y, String name, BufferedImage img, int size_x, int size_y) {
		super(new Vec2D(pos_x, pos_y), name);
		classname = DEF_CLASSNAME;
		this.img = img;
		this.size = new Vec2D(size_x, size_y);
		scaleImg(new Vec2D(size_x, size_y));
	}

	/**
	 * Letrehoz egy GraphicEntityt a megadott parameterekkel
	 * Az entity merete a textura merete lesz
	 * @param pos  Az entity pozicioja
	 * @param name Az entity neve
	 * @param filename	A textura fajl eleresi utvonala
	 */
	public GraphicEntity(Vec2D pos, String name, String filename) {
		super(pos, name);
		classname = DEF_CLASSNAME;
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		size = new Vec2D(img.getWidth(), img.getHeight());
	}

	/**
	 * Letrehoz egy GraphicEntityt a megadott parameterekkel
	 * @param pos  Az entity pozicioja
	 * @param name Az entity neve
	 * @param filename	A textura fajl eleresi utvonala
	 * @param size	Az entity merete
	 */
	public GraphicEntity(Vec2D pos, String name, String filename, Vec2D size) {
		super(pos, name);
		classname = DEF_CLASSNAME;
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.size = size;
		scaleImg(size);
	}

	/**
	 * Letrehoz egy GraphicEntityt a megadott parameterekkel
	 * @param pos_x  Az entity x koordinataja
	 * @param pos_y  Az entity y koordinataja
	 * @param name Az entity neve
	 * @param filename	A textura fajl eleresi utvonala
	 * @param size_x  Az entity szelessege
	 * @param size_y  Az entity magassaga
	 */
	public GraphicEntity(int pos_x, int pos_y, String name, String filename, int size_x, int size_y) {
		super(new Vec2D(pos_x, pos_y), name);
		classname = DEF_CLASSNAME;
		try {
			this.img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.size = new Vec2D(size_x, size_y);
		scaleImg(new Vec2D(size_x, size_y));
	}

	/**
	 * Atmeretezi a texturat a megadott meretre
	 * @param newSize a kivant meret
	 */
	protected void scaleImg(Vec2D newSize) {
		BufferedImage tmp = new BufferedImage(size.getXi(), size.getYi(), BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(size.getX()/this.img.getWidth(), size.getY()/this.img.getHeight());
		AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		this.img = ato.filter(this.img, tmp);
	}

	public BufferedImage getImg() {
		return img;
	}

	/**
	 * Megadja az entityt befoglalo dobozt
	 * @return a befoglalo doboz
	 */
	public Rectangle getRect() {
		return new Rectangle(pos.getXi(), pos.getYi(), size.getXi(), size.getYi());
	}

	/**
	 * Megadja az entity kozeppontjat
	 * @return az entity kozeppontja
	 */
	public Point getCenterPoint() {
		return new Point(pos.getXi() + size.getXi()/2, pos.getYi() + size.getYi()/2);
	}
}
