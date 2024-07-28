package com.dchimitt.main;
import java.util.ArrayList;

public class ActOneMap {
	
	public enum Direction {
		NORTH,
		SOUTH,
		EAST,
		WEST;
		
		// named variable so not confused with -1 later
		public static final int NOEXIT = -1;
	};
	
	private int currentPlayerPosition = 0;
	private ArrayList <Room> actOneMap;
	
	public ActOneMap() {
		actOneMap = new ArrayList<Room>();
		
		// add rooms to the act one map
		actOneMap.add(new Room("Placeholder1", "description1", Direction.NOEXIT, 2, 1, Direction.NOEXIT));
		actOneMap.add(new Room("Placeholder2", "description2", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, 0));
		actOneMap.add(new Room("Placeholder3", "description3", 0, Direction.NOEXIT, 3, Direction.NOEXIT));
		actOneMap.add(new Room("Placeholder4", "description4", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, 2));
	}
	
	public Room getActOneMapStartingRoom()
	{
		return actOneMap.get(0);
	}
	
	public int movePlayerTo(Direction direction) {
		Room currentRoom = actOneMap.get(currentPlayerPosition);
		int newPlayerPosition = Direction.NOEXIT; // default set to -1 to handle invalid moves
		
		switch (direction) {
		case NORTH:
			newPlayerPosition = currentRoom.getNorth();
			break;
		case SOUTH:
			newPlayerPosition = currentRoom.getSouth();
			break;
		case EAST:
			newPlayerPosition = currentRoom.getEast();
			break;
		case WEST:
			newPlayerPosition = currentRoom.getWest();
			break;			
		}
		
		if (newPlayerPosition != Direction.NOEXIT) 
			currentPlayerPosition = newPlayerPosition;
		else
			System.out.println("You cannot move in that direction!");
		
		return currentPlayerPosition;
	}
}
