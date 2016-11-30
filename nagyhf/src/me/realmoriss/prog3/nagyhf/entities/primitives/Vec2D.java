package me.realmoriss.prog3.nagyhf.entities.primitives;

/**
 * Created on 11/28/16.
 */
public class Vec2D {
	private double x;
	private double y;

	public Vec2D() {
		x = 0;
		y = 0;
	}

	public Vec2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vec2D(Vec2D vec) {
		this.x = vec.getX();
		this.y = vec.getY();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Vec2D addX(double delta) {
		x+=delta;
		return this;
	}

	public Vec2D addY(double delta) {
		y+=delta;
		return this;
	}

	public Vec2D add(Vec2D vec) {
		this.x+=vec.x;
		this.y+=vec.y;
		return this;
	}

	public Vec2D add(double x, double y) {
		this.x+=x;
		this.y+=y;
		return this;
	}

	public int getXi() {
		return Math.toIntExact(Math.round(x));
	}

	public int getYi() {
		return Math.toIntExact(Math.round(y));
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double length() {
		return Math.sqrt(x*x + y*y);
	}

	public void normalize() {
		x = x/length();
		y = y/length();
	}

	public Vec2D mirrorX() {
		x *= -1;
		return this;
	}

	public Vec2D mirrorY() {
		y *= -1;
		return this;
	}

	public Vec2D mirrorXY() {
		x *= -1;
		y *= -1;
		return this;
	}

	@Override
	public String toString() {
		return "(" + this.x + ";" + this.y + ")";
	}

	static Vec2D dotProd(Vec2D vec1, Vec2D vec2) {
		return new Vec2D(vec1.getX()*vec2.getX(), vec1.getY()*vec2.getY());
	}
}
