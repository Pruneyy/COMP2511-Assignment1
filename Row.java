/*
 * @author Pranav Singh z3461857
 * Row Class for Assignment 1
 * Creates Row Object, used to hold an extended arraylist of seats
 */

import java.util.ArrayList;

public class Row {
	String rowId;
	ArrayList<Seat> seats;
	
	/**
	 * Constructor
	 * @param rowId
	 * @param maxSeats
	 */
	public Row (String rowId, int maxSeats) {
		this.rowId = rowId;
		seats = new ArrayList<Seat>();
		for (int i = 1; i <= maxSeats; i++) {
			seats.add(new Seat(rowId, i));
		}
	}
	
	/**
	 * @return rowId
	 */
	public String getRowId() {
		return rowId;
	}

	/**
	 * @return seats
	 */
	public ArrayList<Seat> getSeats() {
		return seats;
	}
	/**
	 * @pre bookingId > 0
	 * @param bookingId
	 * @return true if a booking has been made in that row
	 */
	public boolean hasBookingId(int bookingId) {
		for (Seat s : seats) {
			if (s.isBookedWithId(bookingId)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @pre bookingId > 0 and startIndex < endIndex
	 * @post seats set with bookingId
	 * @param bookingId
	 * @param startIndex
	 * @param endIndex
	 */
	public void bookSeats(int bookingId, int startIndex, int endIndex) {
		for (int i = startIndex - 1; i < endIndex; i++) {
			seats.get(i).setBooked(bookingId);
		}

	}
	
}
