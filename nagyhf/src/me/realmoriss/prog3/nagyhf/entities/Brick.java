package me.realmoriss.prog3.nagyhf.entities;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

/**
 * Created on 11/29/16.
 */
public class Brick extends GraphicEntity {
	private static final String DEF_CLASSNAME = "prop_brick";
	private static final String BRICK_IMG = "assets/img/brick";
	private static final String IMG_EXT = ".png";
	protected boolean destroyed = false;

	public Brick(Vec2D pos, String name) {
		super(pos, name, BRICK_IMG+IMG_EXT);
		classname = DEF_CLASSNAME;
	}

	/**
	 * Letrehoz egy megadott szinu bricket
	 * A szin az alapertelmezett textura nevenek kibovitese, egy alahuzasjel utan
	 * @param pos   a brick pozicioja
	 * @param name  a brick neve
	 * @param color a brick szine (textura neve)
	 */
	public Brick(Vec2D pos, String name, String color) {
		super(pos, name, BRICK_IMG+"_"+color+IMG_EXT);
		classname = DEF_CLASSNAME;
	}

	public Brick(Vec2D pos, String name, Vec2D size) {
		super(pos, name, BRICK_IMG, size);
		classname = DEF_CLASSNAME;
	}

	/**
	 * Megadja, hogy a brick ossze van-e mar torve
	 * @return true, ha ossze van torve
	 */
	public boolean getDestroyed() {
		return destroyed;
	}

	/**
	 * Osszetori a brick-et
	 */
	public void destroy() {
		if (!destroyed) {
			destroyed = true;
		}
	}

}
