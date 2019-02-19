/*
 * @author Pranav Singh z3461857
 * Session Class for Assignment 1
 * Creates Session Object to create and initialise sessions
 */
import java.util.ArrayList;

public class Session {
	private int cinemaId;
	private String movie;
	private String time;
	private ArrayList<Row> rows = new ArrayList<Row>();

	/**
	 * Constructor
	 * @param cinemaId
	 * @param movie
	 * @param time
	 * @param seats
	 */
	public Session (int cinemaId, String movie, String time, ArrayList<CSeats> seats) {
		this.cinemaId = cinemaId;
		this.movie = movie;
		this.time = time;
		for(CSeats s: seats) {
			Row r = new Row(s.getId(), s.getNumSeats());
			rows.add(r);
		}
	}
	
	/**
	 * @return id
	 */
	public int getCinemaId() {
		return cinemaId;
	}
	
	/**
	 * @return movie
	 */
	public String getMovie() {
		return movie;
	}
	
	/**
	 * @return time
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * @return rows
	 */
	public ArrayList<Row> getRows() {
		return rows;
	}
		
	/* 
	 * @return string printout of object
	 */
	public String toString() {
		return Integer.toString(getCinemaId()) + " " + getMovie() + " " + getTime();
	}
}

