package me.realmoriss.prog3.nagyhf.entities;

import me.realmoriss.prog3.nagyhf.entities.primitives.Entity;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

/**
 * Created on 11/28/16.
 */
public class Macska extends GraphicEntity {
	private int hunger = 50;
	private int happiness = 50;
	private static final String DEF_CLASSNAME = "npc_macska";
	private static final String DEF_IMAGE = "assets/img/macska.png";

	public Macska(Vec2D pos, String name) {
		super(pos, name, DEF_IMAGE, new Vec2D(32, 32));
		classname = DEF_CLASSNAME;
	}

	public Macska(Vec2D pos, String name, int hunger, int happiness) {
		super(pos, name, DEF_IMAGE);
		classname = DEF_CLASSNAME;
		this.hunger = hunger;
		this.happiness = happiness;
	}

	public void feed() {
		if (hunger > 0) {
			hunger--;
		}
		System.out.println("Meow!");
	}

	public void stroke() {
		if (happiness < 100) {
			happiness++;
		}
		System.out.println("Purr..");
	}
}
