package fr.exo_tom_aquajava.timeo;

import java.util.Random;

public class carnivore_fish extends fish {

	Random r = new Random();
	
	public carnivore_fish(String name) {
		this.type = "carnivore";
		this.energy = 100;
		this.x = 5;
		this.y = 5;
		this.maxvelocity = r.nextInt(7)+1;
		this.partvelocity = 0;
		this.velocityx = 0;
		this.velocityy = 0;
		this.reproduction = false;
		this.name = name;
	}
	
	public void move() {
	    this.partvelocity = r.nextInt(maxvelocity);
	    this.velocityx = this.partvelocity;
	    this.velocityy = this.maxvelocity - this.partvelocity;

	    if (r.nextInt(2) == 0) {
	        this.x += this.velocityx;
	        this.y += this.velocityy;
	        this.energy--;
	    }
	    
	}
	
	public boolean eat() {
		if(energy < 91 && r.nextInt(5) + 1 == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean reproductions() {
		if(energy > 70 && r.nextInt(10) + 1 == 1) {
			this.reproduction = true;
			this.energy -= 60;
		} else {
			this.reproduction = false;
		}
		
		return reproduction;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getEnergy() {
		return this.energy;
	}
}
