package center.of.mass;

import java.util.ArrayList;

import it.unibs.fp.mylib.*;

public class Planet extends CelestialBody{
	private static final String NO_SPACE = "No space left";
	private static final String NO_MOONS = "This planet has no moons";
	private static final String FOUND_MOON = "Moon has been found";
	private static final int LIM_MOONS = 5000;
	private ArrayList<Moon> moons;
	private double radius;
	
	public ArrayList<Moon> getMoons() {
		return moons;
	}

	public void setMoons(ArrayList<Moon> moons) {
		this.moons = moons;
	}
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	public Moon lookForMoon(Position pos) {
		boolean foundMoonX=false;
		boolean foundMoonY=false;
		try {
			for (int i = 0; i < moons.size(); i++) {
				Moon m = new Moon();
				m = moons.get(i);
				foundMoonX = MyMath.compareDouble(m.getPosition().getX(), pos.getX());
				foundMoonY = MyMath.compareDouble(m.getPosition().getY(), pos.getY());
				if (foundMoonX && foundMoonY) {
					System.out.println(FOUND_MOON);
					return m;
				}
			} 
		}catch (Exception NullPointerException) {
			return null;
		}
		return null;
	}
	public void displayMoons(){
		
		try {
			for (int i = 0; i < moons.size(); i++) {
				Moon m = new Moon();
				m = moons.get(i);
				System.out.println(m.getId());
			} 
		} catch (Exception NullPointerException) {
			System.out.println(NO_MOONS);
		}
	}
	public void actionMoon(int label) {
		switch(label) {
		case 1:
			Moon m1=PlanetariumUtils.readMoon();
			checkPlace(m1);
			break;
		case 2:
			moons.remove(lookForMoon(PlanetariumUtils.readPosition()));
			break;
		default:
			System.out.println("Error");
			break;
		}
		} 
		
	public double partialXMoon() {
		double partialXMoon=0;
		double mass=0;
		try {
			for (int i = 0; i < moons.size(); i++) {
				mass = moons.get(i).getMass();
				partialXMoon += mass * (moons.get(i).getPosition().getX());
			} 
		} catch (Exception NullPointerException) {
			partialXMoon=0;
		}
		return partialXMoon;
	}
	public double partialYMoon() {
		double partialYMoon=0;
		double mass=0;
		try {
			for (int i = 0; i < moons.size(); i++) {
				mass = moons.get(i).getMass();
				partialYMoon += mass * (moons.get(i).getPosition().getY());
			} 
		} catch (Exception NullPointerException) {
			partialYMoon=0;
		}
		return partialYMoon;
	}
	public double totalMassMoon() {

		double totalMass=0;
	try {
				for (int i = 0; i < moons.size(); i++) {
			totalMass+=moons.get(i).getMass();
		}

		} catch (Exception NullPointerException) {
			totalMass=0;
		}		return totalMass;
	}

	public void checkPlace(Moon m) {
		if(moons.size()<LIM_MOONS) {
			moons.add(m);
		}
		else {
			System.out.println(NO_SPACE);
		}
		
	}


}
