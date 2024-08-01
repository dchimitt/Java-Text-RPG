package maps;
import java.util.ArrayList;

import com.dchimitt.main.Room;

public class ActOneMap implements java.io.Serializable {
	
	public enum Direction {
		NORTH, 
		SOUTH, 
		EAST,  
		WEST; 
		
		// named variable so not confused with -1 later
		public static final int NOEXIT = -1;
	};
	
	private static int currentPlayerPosition = 0;
	private static ArrayList <Room> actOneMap;
	
	public ActOneMap() {
		actOneMap = new ArrayList<Room>();
		
		// Adding rooms to the ArrayList. Integers indicate the indices of the connecting rooms in directions of order (North, South, East, West)
		actOneMap.add(new Room("Town of Reizart:", " A-0", Direction.NOEXIT, 8, 1, Direction.NOEXIT));                                   		// 0 (TOWN)
		actOneMap.add(new Room("Plains:", " A-1", Direction.NOEXIT, 9, 2, 0));                                                    		        // 1
		actOneMap.add(new Room("Plains:", " A-2", Direction.NOEXIT, 10, 3, 1));                                                    		        // 2
		actOneMap.add(new Room("Plains:", " A-3", Direction.NOEXIT, 11, 4, 2));                                                    		        // 3
		actOneMap.add(new Room("Plains:", " A-4", Direction.NOEXIT, 12, Direction.NOEXIT, 3));                                    	 	        // 4
		actOneMap.add(new Room("cave wall", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));                        // 5
		actOneMap.add(new Room("cave wall", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 6
		actOneMap.add(new Room("cave wall", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 7
		actOneMap.add(new Room("Plains:", " B-0", 0, Direction.NOEXIT, 9, Direction.NOEXIT));                                   	            // 8
		actOneMap.add(new Room("Plains:", " B-1", 1, Direction.NOEXIT, 10, 8));                                                 	            // 9
		actOneMap.add(new Room("Plains:", " B-2", 2, Direction.NOEXIT, 11, 9));                                                 				// 10
		actOneMap.add(new Room("Plains:", " B-3", 3, Direction.NOEXIT, 12, 10));                                                				// 11
		actOneMap.add(new Room("Reizart Cave Entrance:", " B-4", 4, Direction.NOEXIT, 13, 11));                                                	// 12
		actOneMap.add(new Room("Reizart Cave:", " B-5", Direction.NOEXIT, Direction.NOEXIT, 14, 12));                                 			// 13
		actOneMap.add(new Room("Reizart Cave:", " B-6", Direction.NOEXIT, 22, Direction.NOEXIT, 13));                                 			// 14
		actOneMap.add(new Room("cave wall", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 15
		actOneMap.add(new Room("mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 16
		actOneMap.add(new Room("mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 17
		actOneMap.add(new Room("mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 18
		actOneMap.add(new Room("mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 19
		actOneMap.add(new Room("mountain", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 20
		actOneMap.add(new Room("cave wall", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));     	                // 21
		actOneMap.add(new Room("Reizart Cave:", " C-6", 14, Direction.NOEXIT, 23, Direction.NOEXIT));                                 			// 22
		actOneMap.add(new Room("Reizart Cave:", " C-7", Direction.NOEXIT, 31, Direction.NOEXIT, 22));                                           // 23
		actOneMap.add(new Room("IDK YET:", " D-0", Direction.NOEXIT, 32, 25, Direction.NOEXIT));                                                // 24
		actOneMap.add(new Room("IDK YET:", " D-1", Direction.NOEXIT, 33, 26, 24));                                    	 	                    // 25
		actOneMap.add(new Room("IDK YET:", " D-2", Direction.NOEXIT, 34, 27, 25));                                                              // 26
		actOneMap.add(new Room("IDK YET:", " D-3", Direction.NOEXIT, 35, 28, 26));     	                										// 27
		actOneMap.add(new Room("IDK YET:", " D-4", Direction.NOEXIT, 36, 29, 27));     	                										// 28
		actOneMap.add(new Room("IDK YET:", " D-5", Direction.NOEXIT, 37, Direction.NOEXIT, 28));                                   	            // 29
		actOneMap.add(new Room("cave wall", "", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT));                        // 30
		actOneMap.add(new Room("Reizart Cave:", " D-7", 23, 39, Direction.NOEXIT, Direction.NOEXIT));                                           // 31
		actOneMap.add(new Room("IDK YET:", " E-0", 24, 40, 33, Direction.NOEXIT));                                                    		    // 32
		actOneMap.add(new Room("IDK YET:", " E-1", 25, 41, 34, 32));                                    	 	                                // 33
		actOneMap.add(new Room("IDK YET:", " E-2", 26, 42, 35, 33));                         													// 34
		actOneMap.add(new Room("IDK YET:", " E-3", 27, 43, 36, 34));     	                                                                    // 35
		actOneMap.add(new Room("IDK YET:", " E-4", 28, 44, 37, 35));     	                                                                    // 36
		actOneMap.add(new Room("SOME TOWN:", " E-5", 29, 45, 38, 36));                                   	                                    // 37 (TOWN)
		actOneMap.add(new Room("Reizart Cave Exit:", " E-6", Direction.NOEXIT, Direction.NOEXIT, 39, 37));     	                                // 38 (BOSS)
		actOneMap.add(new Room("Reizart Cave:", " E-7", 31, Direction.NOEXIT, Direction.NOEXIT, 38));     	                                    // 39
		actOneMap.add(new Room("IDK YET:", " F-0", 32, 48, 41, Direction.NOEXIT));     	                                                        // 40
		actOneMap.add(new Room("IDK YET:", " F-1", 33, 49, 42, 40));     	                                                                    // 41
	}
	
	public Room getActOneMapStartingRoom()
	{
		return actOneMap.get(0);
	}
	
	public static Room getCurrentPlayerPosition() {
		Room currentRoom = actOneMap.get(currentPlayerPosition);
		return currentRoom;
	}
	
	public static void printPlayerPosition() {
		Room currentRoom = actOneMap.get(currentPlayerPosition);
		System.out.println("Location: " + currentRoom.getName() + "" + currentRoom.getDescription());
	}
	
	// Method for moving player to various rooms on map
	public static void teleportPlayerTo(Room targetRoom) {
		int targetIndex = actOneMap.indexOf(targetRoom);
		if (targetIndex != -1) {
			currentPlayerPosition = targetIndex;
			Room currentRoom = actOneMap.get(currentPlayerPosition);
			System.out.println("Location - \n" + currentRoom.getName() + "" + currentRoom.getDescription());
		}
		else
			System.out.println("Unable to teleport to a blocked space on the map!");
	}
	
	// Method to move the player in N, S, E, or W direction
	public static int movePlayerTo(Direction direction) {
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
		
		if (newPlayerPosition != Direction.NOEXIT) {
			currentPlayerPosition = newPlayerPosition;
			currentRoom = actOneMap.get(currentPlayerPosition);
			System.out.println("Location: " + currentRoom.getName() + "" + currentRoom.getDescription());
		}
		else 
			// TODO fix output later. Unsure how to extract the ArrayList index of the room blocking player due to Direction.NOEXIT being set to -1
			// Ideally, I would like to show the name of the room (aka object) blocking the player's path for more clarity on map location.
	        System.out.println("Your path is blocked!");
	        		
		return currentPlayerPosition;
	}
}
