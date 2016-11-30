package me.realmoriss.prog3.nagyhf.entities;

import javafx.scene.shape.Circle;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

import java.awt.*;

/**
 * Created on 11/29/16.
 */
public class Ball extends GraphicEntity {
	private static final String DEF_CLASSNAME = "weapon_ball";
	private static final String BALL_IMG = "assets/img/ball.png";
	protected Vec2D speed;
	protected Circle box;

	public Ball(Vec2D pos, String name) {
		super(pos, name, BALL_IMG);
		classname = DEF_CLASSNAME;
		size.set(img.getWidth(), img.getWidth());
		scaleImg(size);
		speed = new Vec2D(0,0);
		box = new Circle(pos.getX()-size.getX()/2, pos.getY()-size.getY()/2, size.getX()/2);
	}

	public Ball(Vec2D pos, String name,  double radius) {
		super(pos, name, BALL_IMG, new Vec2D(radius*2, radius*2));
		classname = DEF_CLASSNAME;
		speed = new Vec2D(0,0);
		box = new Circle(pos.getX()-radius, pos.getY()-radius, radius);
	}

	@Override
	public Vec2D move(Vec2D delta) {
		box.setCenterX(pos.getX()+delta.getX()+size.getX()/2);
		box.setCenterY(pos.getY()+delta.getY()+size.getY()/2);
		return super.move(delta);
	}

	@Override
	public Vec2D move(int delta_x, int delta_y) {
		box.setCenterX(pos.getX()+delta_x+size.getX()/2);
		box.setCenterY(pos.getY()+delta_y+size.getY()/2);
		return super.move(delta_x, delta_y);
	}

	@Override
	public Vec2D setPos(Vec2D pos) {
		box.setCenterX(pos.getX()+size.getX()/2);
		box.setCenterY(pos.getY()+size.getY()/2);
		return super.setPos(pos);
	}

	@Override
	public Vec2D setPos(double pos_x, double pos_y) {
		box.setCenterX(pos_x-size.getX()/2);
		box.setCenterY(pos_y-size.getY()/2);
		return super.setPos(pos_x, pos_y);
	}

	public Vec2D getSpeed() {
		return speed;
	}

	public void setSpeed(Vec2D speed) {
		this.speed = speed;
	}

	public void addSpeed(Vec2D speed) {
		this.speed.add(speed);
	}

	public boolean doesCollide(Rectangle rect) {
		return box.intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}
}
