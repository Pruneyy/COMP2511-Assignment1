/*
 * @author Pranav Singh z3461857
 * CSeats Class for Assignment 1
 * Creates CinemaSeats object used to hold rowid and amount of seats per row
 */

public class CSeats {
	private String id;
	private int numSeats;
	
	/**
	 * Constructor
	 * @param id
	 * @param numSeats
	 */
	public CSeats (String id, int numSeats) {
		this.id = id;
		this.numSeats = numSeats;
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return number of seats
	 */
	public int getNumSeats() {
		return numSeats;
	}

}
