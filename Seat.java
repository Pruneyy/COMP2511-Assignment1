/*
 * @author Pranav Singh z3461857
 * Seat Class for Assignment 1
 * Creates Seat Object, used to hold an extended arraylist of seats and hold whether they are booked or not
 */

public class Seat {
	private String rowId;
	private int seatNo;
	private boolean isBooked;	
	private Integer bookingId;

	/**
	 * Constructor
	 * @param rowId
	 * @param seatNo
	 */
	public Seat (String rowId, int seatNo) {
		this.rowId = rowId;
		this.seatNo = seatNo;
		this.bookingId = null;
		this.isBooked = false;
	}
	
	/**
	 * Set a seat to "booked" and sets the seats bookingId to id
	 * @pre id > 0
	 * @post assign seat as booked
	 * @param id
	 */
	public void setBooked (int id) {
		this.isBooked = true;
		this.bookingId = id;
	}
	
	/**
	 * Unbooks a seat
	 * @pre seat is already assigned
	 * @post unassign a booked seat
	 */
	public void setUnbooked () {
		this.isBooked = false;
		this.bookingId = null;
	}
	
	/**
	 * @post boolean result based on whether seat is booked or not
	 * @return true if the seat is booked, false otherwise
	 */
	public boolean isBooked() {
		return isBooked;
	}
	
	/**
	 * @pre id > 0
	 * @post boolean result based on whether seat is booked and has an id or not
	 * @param id
	 * @return true if the seat is booked and has been assigned an id, false otherwise
	 */
	public boolean isBookedWithId(int id) {
		return isBooked && bookingId == id;
	}
	
	/**
	 * @return rowId
	 */
	public String getRowId() {
		return rowId;
	}
	
	/**
	 * @return seatNo
	 */
	public int getSeatNo() {
		return seatNo;
	}
	
	/**
	 * @return bookingId
	 */
	public Integer getBookingId() {
		return bookingId;
	}
	
}
	