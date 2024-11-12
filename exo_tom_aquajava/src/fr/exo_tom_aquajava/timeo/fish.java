package fr.exo_tom_aquajava.timeo;

public abstract class fish {
	protected String type;
	protected int energy;
	protected int x;
	protected int y;
	protected int maxvelocity;
	protected int partvelocity;
	protected int velocityx;
	protected int velocityy;
	protected boolean reproduction;
	protected String name;
	public fish() {
		this.type = "type";
		this.energy = 0;
		this.x = 0;
		this.y = 0;
		this.maxvelocity = 0;
		this.partvelocity = 0;
		this.velocityx = 0;
		this.velocityy = 0;
		this.reproduction = false;
		this.name = "name";
	}
}
