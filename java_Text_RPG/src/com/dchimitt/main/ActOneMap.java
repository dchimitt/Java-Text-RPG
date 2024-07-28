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
		
		// Adding rooms to the ArrayList. Integers indicate the indices of the connecting rooms in directions of order (North, South, East, West)
		actOneMap.add(new Room("Town of Reizart:", " A-0", Direction.NOEXIT, 8, 1, Direction.NOEXIT));                                   		// 0
		actOneMap.add(new Room("Plains:", " A-1", Direction.NOEXIT, 9, 2, 0));                                                    		        // 1
		actOneMap.add(new Room("Plains:", " A-2", Direction.NOEXIT, 10, 3, 1));                                                    		        // 2
		actOneMap.add(new Room("Plains:", " A-3", Direction.NOEXIT, 11, 4, 2));                                                    		        // 3
		actOneMap.add(new Room("Plains:", " A-4", Direction.NOEXIT, 12, Direction.NOEXIT, 3));                                    	 	        // 4
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));                        // 5
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 6
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 7
		actOneMap.add(new Room("Plains:", " B-0", 0, Direction.NOEXIT, 9, Direction.NOEXIT));                                   	            // 8
		actOneMap.add(new Room("Plains:", " B-1", 1, Direction.NOEXIT, 10, 8));                                                 	            // 9
		actOneMap.add(new Room("Plains:", " B-2", 2, Direction.NOEXIT, 11, 9));                                                 				// 10
		actOneMap.add(new Room("Plains:", " B-3", 3, Direction.NOEXIT, 12, 10));                                                				// 11
		actOneMap.add(new Room("Reizart Cave Entrance:", " B-4", 4, Direction.NOEXIT, 13, 11));                                                	// 12
		actOneMap.add(new Room("Reizart Cave:", " B-5", Direction.NOEXIT, Direction.NOEXIT, 14, 12));                                 			// 13
		actOneMap.add(new Room("Reizart Cave:", " B-6", Direction.NOEXIT, 22, Direction.NOEXIT, 13));                                 			// 14
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 15
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 16
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 17
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 18
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 19
		actOneMap.add(new Room("Mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 20		
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
