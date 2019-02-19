/*
 * @author Pranav Singh z3461857
 * Main Cinema Booking System for Assignment 1
 * Determines what needs to be done based on reading the input files
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CinemaBookingSystem {

	/**
	 * @pre valid input text file provided with lines starting with either a comment, Cinema, Session, Request, Change, Cancel or Print
	 * @post System out prints that correspond to the prescribed actions
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		ArrayList<Session> sessions = new ArrayList<Session>();
		ArrayList<Booking> requests = new ArrayList<Booking>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(args[0]));    // args[0] is the first command line argument
	        	while(sc.hasNextLine()) {
	        		String raw = sc.nextLine();
	        		String[] input = raw.split(" ");
	        		switch(input[0]) {
		          		case "Cinema":
		          			int cid = Integer.parseInt(input[1]);
		          			String rowId = input[2];
		          			int numOfSeats = Integer.parseInt(input[3]);
		          			Boolean flag = false;
		          			
		          			//If cinema already exists, add rows to it
		          			for (Cinema c : cinemas) {
		          				if (c.getId() == cid) {
		          					c.addRow(rowId, numOfSeats);
		          					flag = true;
		          					break;
		          				}
		          			}
		          			//If cinema does not already exist, create a new one
		          			if (flag == false) {
			          			Cinema x = new Cinema(cid);
			          			x.addRow(rowId, numOfSeats);
			          			cinemas.add(x);
		          			}
		          			break;
		          			
		          			
		          		case "Session":
		          			int sid = Integer.parseInt(input[1]);
		          			String time = input[2];	
		          			String movie = input[3];
		          			int i = 4;
		          			//Ensure we get all the words for multiword length movies
		          			while (i < input.length && Objects.equals(input[i], Character.toString('#')) == false) {
		          				movie = movie + " " + input[i];
		          				i++;
		          			}
		          			movie = movie.trim();
		          			
		          			for (Cinema c : cinemas) {
		          				if (c.getId() == sid) {
		          					Session x = new Session(sid, movie, time, c.getSeats());
		          					sessions.add(x);
		          				}
		          			}
		          			break;
		          			
		          			
		          			
		          		case "Request":
		          			int bid = Integer.parseInt(input[1]);
		          			int rcid = Integer.parseInt(input[2]);
		          			String rtime = input[3];
		          			int rseats = Integer.parseInt(input[4]);
		          			String rtype = "Request";
		          			
		          			for (Session s : sessions) {
		          				if (s.getCinemaId() == rcid) {
		          					if (Objects.equals(s.getTime(), rtime)) {
		          						requests.add(Booking.newBooking(rseats, s.getRows(), bid, rtype, rcid, rtime));
		          					}
		          				}
		          			}
		          			break;
		          			
		          			
		          		case "Change":
		          			int chid = Integer.parseInt(input[1]);
		          			int chcid = Integer.parseInt(input[2]);
		          			String chtime = input[3];
		          			int chseats = Integer.parseInt(input[4]);
		          			String ctype = "Change";
		          			Booking bookingToRemove = null;
		          			Booking holder = null;
		          			Session helder = null;
		          			int flog = 0;
		          			
		          			//Remove initial request before checking for new seats
		          			for (Booking b : requests) {
		          				if (b != null && b.getBookingId() == chid) {
		          					for (Session s : sessions) {
		          						if (Objects.equals(b.getBookingTime(), s.getTime())) {
		          							Booking.removeBooking(chid, s.getRows(), ctype);
		          							helder = s;
		          							bookingToRemove = b;
		          						}
		          					}
		          				}
		          			}
		          			for (Session s : sessions) {
		          				if (s.getCinemaId() == chcid) {	
		          					if (Objects.equals(s.getTime(), chtime)) {
		          						holder = Booking.newBooking(chseats, s.getRows(), chid, ctype, chcid, chtime);
		          						if (holder == null) {
		          							flog = 1;
		          						} else {
		          							requests.add(holder);
		          						}
		          					}
		          				}
		          			}
		          			//Re-adds original booking if change request failed
		          			if (flog == 1) {
		          				helder.getRows().get(bookingToRemove.getRowIndex()).bookSeats(chid, bookingToRemove.getstartIndex(), bookingToRemove.getendIndex());
		          			}
		          			else {
			          			if (bookingToRemove != null) {
			          				requests.remove(bookingToRemove);
			          			}
		          			}
		          			break;
		          			
			          	case "Cancel":
			          		int caid = Integer.parseInt(input[1]);
		          			String catype = "Cancel";
		          			Booking cBookingToRemove = null;
		          			for (Booking b : requests) {
		          				if (b!= null && b.getBookingId() == caid) {
		          					for (Session s : sessions) {
		          						if (Objects.equals(b.getBookingTime(), s.getTime())) {
		          							Booking.removeBooking(caid, s.getRows(), catype);
		          							cBookingToRemove = b;
		          						}
		          					}
		          				}
		          			}
		          			if (cBookingToRemove != null) {
		          				requests.remove(cBookingToRemove);
		          			}
			          		break;
			          		
			          		
			          	case "Print":
			          		int pid = Integer.parseInt(input[1]);
			          		String ptime = input[2];
			          		for (Session s : sessions) {
			          			if (Objects.equals(s.getTime(), ptime) && s.getCinemaId() == pid) {
			          				System.out.println(s.getMovie());
			          				for (Row r : s.getRows()) {
			          					System.out.print(r.getRowId() + ": ");
			          					String list = "";
				          				for (Booking b: requests) {
				          					if (b != null && r.hasBookingId(b.getBookingId())) {
				          						list = list + b.getstartIndex() + "-" + b.getendIndex() + ",";				          						
				          					}
				          				}
				          				if (list.length() == 0) {
				          					System.out.println(list);
				          				}
				          				else {
				          					list = list.substring(0, list.length()-1);
				          					System.out.println(list);
				          				}
			          				}
			          			}
			          		}
			          		break;
	        		}
	        	}
		}
	      catch (FileNotFoundException e) {
	          System.out.println(e.getMessage());
	      }
	      finally {
	          if (sc != null) sc.close();
	      }

	}

}
