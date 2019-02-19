/*
 * @author Pranav Singh z3461857
 * Cinema Class for Assignment 1
 * Creates Cinema Object to create and initialise cinemas
 * 
 */
import java.util.ArrayList;

public class Cinema {
	private int id;
	private ArrayList<CSeats> seats = new ArrayList<CSeats>();
	
	/**
	 * Constructor
	 * @param id
	 */
	public Cinema (int id) {
		this.id = id;
		this.seats = new ArrayList<CSeats>();
	}
	
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @pre rowmax > 0 and rowid is valid within the cinema 
	 * @post CSeats class object created
	 * @param rowid
	 * @param rowmax
	 */
	public void addRow(String rowid, int rowmax) {
		seats.add(new CSeats(rowid, rowmax));
	}
	
	
	/**
	 * @return seat list
	 */
	public ArrayList<CSeats> getSeats() {
		return seats;
	}
		
	/* 
	 * @return string printout of object
	 */
	public String toString() {
		return Integer.toString(getId()) + " " + getSeats();
	}
}