package main.java;

public class Player {
	
	private int playerPosition;
	private int playerEnergy;
	
	public int getPlayerPosition() {
		return playerPosition;
	}
	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}
	public int getPlayerEnergy() {
		return playerEnergy;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerEnergy;
		result = prime * result + playerPosition;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerEnergy != other.playerEnergy)
			return false;
		if (playerPosition != other.playerPosition)
			return false;
		return true;
	}
	public void setPlayerEnergy(int playerEnergy) {
		this.playerEnergy = playerEnergy;
	}	

	
}
