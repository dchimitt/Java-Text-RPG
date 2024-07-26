package com.dchimitt.main;
import java.util.*;

public class ActOne {
	
	public enum Direction {
		NORTH,
		SOUTH,
		EAST,
		WEST;
		
		// named variable so not confused with -1 later
		public static final int NOEXIT = -1;
	};
	
	private ArrayList <Room> actOne;
	
	List<String> commands = new ArrayList<>(Arrays.asList("n", "s", "e", "w"));
	
	public ActOne() {
		this.actOne = new ArrayList<Room>();
		
		// add rooms to the act one map
		actOne.add(new Room("Placeholder1", "description1", Direction.NOEXIT, 2, Direction.NOEXIT, 1));
		actOne.add(new Room("Placeholder2", "description2", Direction.NOEXIT, Direction.NOEXIT, 0, Direction.NOEXIT));
		actOne.add(new Room("Placeholder3", "description3", 0, Direction.NOEXIT, Direction.NOEXIT, 3));
		actOne.add(new Room("Placeholder4", "description4", Direction.NOEXIT, Direction.NOEXIT, 2, Direction.NOEXIT));
	}
}
