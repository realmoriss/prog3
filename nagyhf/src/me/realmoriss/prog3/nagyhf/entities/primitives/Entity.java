package me.realmoriss.prog3.nagyhf.entities.primitives;

/**
 * Created on 11/28/16.
 */
abstract public class Entity {
	protected Vec2D pos;
	protected String name;
	protected static final String DEF_CLASSNAME = "entity_generic";
	protected String classname = DEF_CLASSNAME;

	public Entity() {
		pos = new Vec2D();
		name = "";
	}

	public Entity(Vec2D pos, String name) {
		this.pos = pos;
		this.name = name;
	}

	public Vec2D getPos() {
		return pos;
	}

	public Vec2D move(Vec2D delta) {
		pos.add(delta);
		return pos;
	}

	public Vec2D setPos(Vec2D pos) {
		this.pos = pos;
		return this.pos;
	}

	public Vec2D move(int delta_x, int delta_y) {
		pos.add(delta_x, delta_y);
		return pos;
	}

	public Vec2D setPos(double pos_x, double pos_y) {
		this.pos = new Vec2D(pos_x, pos_y);
		return this.pos;
	}

	public String getName() {
		return name;
	}

	public String getClassname() {
		return classname;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CName=" + classname + ";Name=" + name + ";Pos=" + pos;
	}
}
