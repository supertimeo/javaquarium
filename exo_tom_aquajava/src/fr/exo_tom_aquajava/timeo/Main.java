package fr.exo_tom_aquajava.timeo;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws InterruptedException {
        String fish_name = "poisson";
        int number_name = 1;
        
        // Création d'une ArrayList pour stocker des objets de type fish
        ArrayList<herbivore_fish> herbivore = new ArrayList<>();
        ArrayList<carnivore_fish> carnivore = new ArrayList<>();
        
        // Ajout d'une nouvelle instance des fish à la liste
        herbivore.add(new herbivore_fish(fish_name + number_name));
        number_name++;
        carnivore.add(new carnivore_fish(fish_name + number_name));
        
        // démarrer la boucle de simulation
        while(true) {
            // Créer une liste temporaire pour les nouveaux poisson
            ArrayList<herbivore_fish> newherbivore = new ArrayList<>();
            ArrayList<herbivore_fish> newremoveherbivore = new ArrayList<>();
            ArrayList<carnivore_fish> newcarnivore = new ArrayList<>();
            ArrayList<carnivore_fish> newremovecarnivore = new ArrayList<>();
            
            // pour chaque poisson...
            for(herbivore_fish herb : herbivore) {
                System.out.println("coordonée de " + herb.getName() + " : " + herb.getX() + "," + herb.getY() + " ; énergie : " + herb.eat());
                
                // faire bouger le poisson
                herb.move();
                
                // faire manger le poisson
                herb.eat();
                
                // reproduire de poisson
                if(herb.reproductions()) {
                    number_name++;
                    newherbivore.add(new herbivore_fish(fish_name + number_name));
                }
                
                if(herb.eat() <= 0) {
                	System.out.println(herb.getName() + " est mort de fain. ");
                	newremoveherbivore.add(herb);
                	number_name--;
                }
                Thread.sleep(1000); // attendre 1 seconde
            }
            
            for(carnivore_fish carn : carnivore) {
                System.out.println("coordonée de " + carn.getName() + " : " + carn.getX() + "," + carn.getY() + " ; énergie : " + carn.getEnergy());
                
                // faire bouger le poisson
                carn.move();
                
                // faire manger le poisson
                if(carn.eat()) {
                	for(herbivore_fish herb : herbivore) {
                		int distancex = Math.abs(herb.getX() - carn.getX());
                		int distancey = Math.abs(herb.getX() - carn.getY());
                		if(distancex < 5 && distancey < 5) {
                			carn.energy += 10;
                			newremoveherbivore.add(herb);
                			System.out.println(herb.getName() + " est mort, manger par " + carn.getName() + ".");
                			number_name--;
                			
                		}
                	}
                }
                // reproduire de poisson
                if(carn.reproductions()) {
                	number_name++;
                    newcarnivore.add(new carnivore_fish(fish_name + number_name));
                	} 
                
                
                if(carn.getEnergy() <= 0) {
                	System.out.println(carn.getName() + " est mort. ");
                	newremovecarnivore.add(carn);
                	number_name--;
                }
                Thread.sleep(1000); // attendre 1 seconde
            }
            
            // Ajouter en enlever tous les nouveaux poisson à la liste principale après la boucle
            if(newherbivore.size() != 0 || newremoveherbivore.size() != 0 || newcarnivore.size() != 0 || newremovecarnivore.size() != 0) {
            	int nb_poisson = herbivore.size() + carnivore.size();
            	System.out.println("nombre initial de poisson : " + nb_poisson);
            	
            	Thread.sleep(200); // attendre 0.2 seconde
            	
            	if(newherbivore.size() != 0 || newcarnivore.size() != 0) {
            		herbivore.addAll(newherbivore);
            		carnivore.addAll(newcarnivore);
            		
            		if(newherbivore.size() + newcarnivore.size() > 1) {
            			System.out.println((newherbivore.size() + newcarnivore.size()) + " poisson sont né");
            		} else {
            			System.out.println((newherbivore.size() + newcarnivore.size()) + " poisson est né");
            		}
                    
            	}
                Thread.sleep(200); // attendre 0.2 seconde
                if(newremoveherbivore.size() != 0 || newremovecarnivore.size() != 0) {
                	herbivore.removeAll(newremoveherbivore);
                	carnivore.removeAll(newremovecarnivore);
                	
                	if(newremoveherbivore.size() + newremovecarnivore.size() > 1) {
                		System.out.println((newremoveherbivore.size() + newremovecarnivore.size()) + " sont mort.");
                	} else {
                		System.out.println((newremoveherbivore.size() + newremovecarnivore.size()) + " est mort.");
                	}
                    
            	}
                System.out.println("nombre final de poisson : " + (herbivore.size() + carnivore.size()));   
            }
        }
    }
}
