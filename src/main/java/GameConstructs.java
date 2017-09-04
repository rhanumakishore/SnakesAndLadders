package main.java;

import java.util.ArrayList;
import java.util.List;

public class GameConstructs {
	
	private int noOfSquares;
	private int noOfPlayers;
	private List<Integer> trampolines = new ArrayList<Integer>();
	private List<Integer> elevators = new ArrayList<Integer>();
	private List<Integer> pitstops = new ArrayList<Integer>();
	private List<Integer> magicSquares = new ArrayList<Integer>();
	private List<Integer> memorySquares = new ArrayList<Integer>();
	
	
	public int getNoOfSquares() {
		return noOfSquares;
	}
	public void setNoOfSquares(int noOfSquares) {
		this.noOfSquares = noOfSquares;
	}
	public int getNoOfPlayers() {
		return noOfPlayers;
	}
	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
	
	public List<Integer> getTrampolines() {
		return trampolines;
	}
	public void setTrampolines(List<Integer> trampolines) {
		this.trampolines = trampolines;
	}
	public List<Integer> getElevators() {
		return elevators;
	}
	public void setElevators(List<Integer> elevators) {
		this.elevators = elevators;
	}
	public List<Integer> getPitstops() {
		return pitstops;
	}
	public void setPitstops(List<Integer> pitstops) {
		this.pitstops = pitstops;
	}
	public List<Integer> getMagicSquares() {
		return magicSquares;
	}
	public void setMagicSquares(List<Integer> magicSquares) {
		this.magicSquares = magicSquares;
	}
	public List<Integer> getMemorySquares() {
		return memorySquares;
	}
	public void setMemorySquares(List<Integer> memorySquares) {
		this.memorySquares = memorySquares;
	}
	
	
	

}
