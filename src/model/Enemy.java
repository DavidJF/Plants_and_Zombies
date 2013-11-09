package model;

import java.util.ArrayList;
import java.util.Observable;

public class Enemy extends Npc {
	
	int number=100;

	public Enemy(String type, int health, int damage, int i, Observable pnz){
		super(type, health, damage, pnz);
		this.number = i;
		pnz.addObserver(this);
	}
	
	public int move(ArrayList<ArrayList<Npc>> grid){
		//System.out.println("number: "+ this.number);
		for (ArrayList<Npc> n:grid){
			for (Npc e:n){
				if (e instanceof Enemy){
					Enemy en = (Enemy) e;
					if (this.equals(en)){
						//System.out.println("they are equal"+ this.number+en.getNumber());
						int place = n.indexOf(e);
						if(n.get(place-1)==null){
							n.set(place, null);
							n.set(place-1, e);
							if(place==1){
								return -1;
							}
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		PnZModel thing = ((PnZModel)arg0);
		int over = move(thing.getGrid());
		if (over==-1){
			thing.setRunning(false);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enemy other = (Enemy) obj;
		if (number != other.number)
			return false;
		return true;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}	
	
}
