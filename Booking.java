/*
 * @author Pranav Singh z3461857
 * Booking Class for Assignment 1
 * Holds data to create bookings and determine if a booking can be made/removed
 * 
 */
import java.util.ArrayList;
import java.util.Objects;

public class Booking {
	private int bookingId;
	private int cinemaId;
	private int startIndex;
	private int endIndex;
	private int rowIndex;
	private Row row;
	private String bookingTime;
	
	/**
	 * Constructor
	 * @param bookingId
	 * @param cinemaId
	 * @param startIndex
	 * @param endIndex
	 * @param rowIndex
	 * @param row
	 * @param bookingTime
	 */
	public Booking (int bookingId, int cinemaId, int startIndex, int endIndex, int rowIndex, Row row, String bookingTime) {
		this.bookingId = bookingId;
		this.cinemaId = cinemaId;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.rowIndex = rowIndex;
		this.row = row;
		this.bookingTime = bookingTime;
	}
	
	/**
	 * @return bookingId
	 */
	public int getBookingId() {
		return bookingId;
	}
	/**
	 * @return cinemaId
	 */
	public int getCinemaId() {
		return cinemaId;
	}
	/**
	 * @return startIndex
	 */
	public int getstartIndex() {
		return startIndex;
	}
	/**
	 * @return endIndex
	 */
	public int getendIndex() {
		return endIndex;
	}
	/**
	 * @return rowIndex
	 */
	public int getRowIndex() {
		return rowIndex;
	}
	/**
	 * @return row
	 */
	public Row getRow() {
		return row;
	}
	/**
	 * @return bookingTime
	 */
	public String getBookingTime() {
		return bookingTime;
	}
	
	
	/**
	 * Removes a booking (for either Change or Cancel requests)
	 * @pre booking exists that needs to be removed
	 * @post booking is removed
	 * @param bid
	 * @param seatList
	 * @param type
	 * @return true if booking successfully removed
	 */
	static boolean removeBooking(int bid, ArrayList<Row> seatList, String type) {
		int flag = 0;
		for (int i = 0; i < seatList.size(); i++) {
			for (Seat s : seatList.get(i).getSeats()) {
				if (s.isBookedWithId(bid)) {
					s.setUnbooked();
					flag = 1;
				}
			}
		}
		if (flag == 0) {
			return false;
		} else {
			if (Objects.equals(type, "Cancel")) {
				System.out.println("Cancel " + bid);
				return true;
			}
			else {
				return true;
			}
		}
	}
	
	/**
	 * Determines if a booking can be set (for Request and Change requests)
	 * @pre maxSeats < greatest number of seats in any one row in the cinema 
	 * @post creates booking class object
	 * @param maxSeats
	 * @param seatList
	 * @param bid
	 * @param type
	 * @param rcid
	 * @param rtime
	 * @return Booking class object if successfully made booking, null otherwise
	 */
	static Booking newBooking(int maxSeats, ArrayList<Row> seatList, int bid, String type, int rcid, String rtime) {
		int startIndex = 0;
		Row row = null;
		int endIndex = 0;
		int flag = 1;
		int rowIndex = 0;
		int freeSeats = 0;
		//2 cases to consider for booking to be valid:
		for (int i = 0; i < seatList.size(); i++) {
			endIndex = 1;
			freeSeats = 0;
			for (Seat s : seatList.get(i).getSeats()) {
				//First case: Is the seat already booked
				if (s.isBooked()) {
					endIndex++;
					freeSeats = 0;
					continue;
				}
				//Second case: Have we reached the required number of seats asked for in the booking
				else if ((freeSeats+1)%maxSeats != 0) {
					endIndex++;
					freeSeats++;
					continue;
				}
				else {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				continue;
			}
			else {
				row = seatList.get(i);
				rowIndex = i;
				break;
			}
		}
		//If we couldn't find enough seats to make a booking
		if (row == null || endIndex == row.getSeats().size() + 1) {
			if (Objects.equals(type, "Request")) {
				System.out.println("Booking rejected");
			}
			else {
				System.out.println("Change rejected");
			}
			return null;
		}
		//If we did find seats to book
		else {
			startIndex = endIndex - maxSeats + 1;
			seatList.get(rowIndex).bookSeats(bid, startIndex, endIndex);
			if (Objects.equals(type, "Request")) {
				System.out.println("Booking " + bid + " " + row.getRowId() + startIndex + '-' + row.getRowId() + endIndex);
			}
			else {
				System.out.println("Change " + bid + " " + row.getRowId() + startIndex + '-' + row.getRowId() + endIndex);
			}
			return new Booking(bid, rcid, startIndex, endIndex, rowIndex, row, rtime);
		}
	}
}