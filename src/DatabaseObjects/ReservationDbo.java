package DatabaseObjects;

import Entities.Reservation;
import Entities.ReservationList;
import Entities.TennisCourt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class ReservationDbo {

    private ArrayList<Reservation> reservations = new ArrayList<>();
    private static int autoIncrementId = 0;
    private TennisCourtDbo tennisCourts; // Needed for validations
    private TennisClubDbo tennisClubs;
    

    // Constructor
    public ReservationDbo(TennisCourtDbo tennisCourts, TennisClubDbo tennisClubs) {
        this.tennisCourts = tennisCourts;
        this.tennisClubs = tennisClubs;
    }

    // Adds a Reservation object to the array
    public int addReservation(Reservation reservation) {
        Calendar cal = Calendar.getInstance();
        int errorCode;

        // Set minutes, seconds, miliseconds to zero
        cal.setTime(reservation.getDateTimeStart());
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        reservation.setDateTimeStart(cal.getTime());

        errorCode = validateReservation(reservation);
        if (errorCode != 0) {
            return errorCode;
        }

        reservation.setId(autoIncrementId);
        reservations.add(reservation);
        autoIncrementId++;
        return 0;
    }

    // Return Reservation object with specified id
    public Reservation getReservation(int id) {
        int index = searchReservationIndex(id);
        if (index != -1) {
            return reservations.get(index);
        } else {
            return null;
        }
    }

    // Replace a Reservation object with specified id with a new one
    public int setReservation(Reservation reservation) {
        Calendar cal = Calendar.getInstance();
        int errorCode;

        // Set minutes, seconds, miliseconds to zero
        cal.setTime(reservation.getDateTimeStart());
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        reservation.setDateTimeStart(cal.getTime());

        errorCode = validateReservation(reservation);

        if (errorCode != 0) {
            return errorCode;
        }
        
        int index = searchReservationIndex(reservation.getId());
        if (index != -1) {
            reservations.set(index, reservation);
            return 0;
        } else {
            return -1;
        }
    }

    // Removes a Reservation object from the array, by reservation id
    public boolean removeReservation(int id) {
        int index = searchReservationIndex(id);
        if (index != -1) {
            reservations.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // Returns an ArrayList with all reservations
    public ArrayList<Reservation> getAllReservations() {
        return reservations;
    }

    // Returns an ArrayList with all members name
    public ArrayList<ReservationList> getReservationList() {
        ArrayList<ReservationList> reservationList = new ArrayList<>();
        Iterator<Reservation> it = reservations.iterator();
        Reservation tmpReservation;

        while (it.hasNext()) {
            tmpReservation = it.next();
            reservationList.add(new ReservationList(tmpReservation.getId()));
        }
        return reservationList;
    }

    /*
     Returns the index of an object Reservation in the array, by reservation id
     Returns -1 if it isn't found
     For use in this class only
     */
    private int searchReservationIndex(int id) {
        Iterator<Reservation> it = reservations.iterator();
        Reservation tmpReservation;

        while (it.hasNext()) {
            tmpReservation = it.next();
            if (tmpReservation.getId() == id) {
                return reservations.indexOf(tmpReservation);
            }
        }
        return -1;
    }
    
    // Check if a reservation is overlapping existing reservations
    private boolean reservationIsOverlapping(Reservation reservation) {
        Iterator<Reservation> it = reservations.iterator();
        Reservation tmpReservation;
        Date reservationStart = reservation.getDateTimeStart(), tmpReservationStart;

        while (it.hasNext()) {
            tmpReservation = it.next();
            tmpReservationStart = tmpReservation.getDateTimeStart();
            if (tmpReservation.getDateTimeStart().getTime() == reservation.getDateTimeStart().getTime()) {
                return true;
            }
        }
        return false;
    }

    // Check if there is a reservation for a given date
    public boolean dateIsReserved(Date date) {
        Iterator<Reservation> it = reservations.iterator();
        Reservation tmpReservation;

        while (it.hasNext()) {
            tmpReservation = it.next();
            if (tmpReservation.getDateTimeStart().getTime() == date.getTime()) {
                return true;
            }
        }
        return false;
    }
    
    /*
     Returns an ArrayList with Reservation objects which memberId matches
     the memberId passed by parameter
     Returns an empty ArrayList if there is no match
     */
    public ArrayList<ReservationList> searchByMemberId(int memberId) {
        Iterator<Reservation> it = reservations.iterator();
        Reservation tmpReservation;
        ArrayList<ReservationList> queryResult = new ArrayList<>();

        while (it.hasNext()) {
            tmpReservation = it.next();
            if (tmpReservation.getMemberId() == memberId) {
                queryResult.add(new ReservationList(tmpReservation.getId()));
            }
        }
        return queryResult;
    }

    /*
     Returns an ArrayList with Reservation objects which courtId matches
     the courtId passed by parameter
     Returns an empty ArrayList if there is no match
     */
    public ArrayList<ReservationList> searchByCourtId(int courtId) {
        Iterator<Reservation> it = reservations.iterator();
        Reservation tmpReservation;
        ArrayList<ReservationList> queryResult = new ArrayList<>();

        while (it.hasNext()) {
            tmpReservation = it.next();
            if (tmpReservation.getCourtId() == courtId) {
                queryResult.add(new ReservationList(tmpReservation.getId()));
            }
        }
        return queryResult;
    }
    
    /*
     Returns the percentage of ocupation for a given tennis court and date interval
     */
    public int getTennisCourtOccupation(int courtId, Date dateFrom, Date dateTo) {
        Iterator<Reservation> it = reservations.iterator();
        Reservation tmpReservation;
        ArrayList<ReservationList> queryResult = new ArrayList<>();
        int reservedHours = 0;
        int availableHours = 1;

        // Find reservations of a court
        while (it.hasNext()) {
            tmpReservation = it.next();
            if (tmpReservation.getCourtId() == courtId) {
                reservedHours++;
            }
        }
        
        // Check how many hour are available in the time frame
        
        // Test
        availableHours = 24;
        return (int)((reservedHours * 100.0f) / availableHours);
    }
    
    /*
     Returns an ArrayList with Reservation objects for a given day
     Returns an empty ArrayList if there is no reservations in that day
     */
    public ArrayList<Reservation> getReservationsForDay(Date day) {
        Iterator<Reservation> it = reservations.iterator();
        ArrayList<Reservation> queryResult = new ArrayList<>();
        Reservation tmpReservation;
        Calendar calDay = Calendar.getInstance();
        Calendar calTmpReservationDay = Calendar.getInstance();
        calDay.setTime(day);

        while (it.hasNext()) {
            tmpReservation = it.next();
            calTmpReservationDay.setTime(tmpReservation.getDateTimeStart());
            
            if (calDay.get(Calendar.YEAR) == calTmpReservationDay.get(Calendar.YEAR) &&
                calDay.get(Calendar.MONTH) == calTmpReservationDay.get(Calendar.MONTH) &&
                calDay.get(Calendar.DAY_OF_MONTH) == calTmpReservationDay.get(Calendar.DAY_OF_MONTH)
            ) {
                queryResult.add(tmpReservation);
            }
        }
        return queryResult;
    }

    // Returns reservations count
    public int count() {
        return reservations.size();
    }

    private int validateReservation(Reservation reservation) {
//        TennisCourt tennisCourt = tennisCourts.getTennisCourt(reservation.getCourtId());
        
        // Check if a reservation is being made for the past
        if (reservation.getDateTimeStart().getTime() < System.currentTimeMillis()) {
            return 1;
        }

        // Check if a reservation is being made for a day which a court is closed
        if (tennisClubs.tennisClubIsClosedInDay(reservation.getDateTimeStart())) {
            return 2;
        }

        // Check if a reservation is being made for a time when a tennis court have not yet opened
        int courtId = reservation.getCourtId();
        Date dateStart = reservation.getDateTimeStart();
        if (tennisCourts.tennisCourtIsNotOpened(reservation.getCourtId(), reservation.getDateTimeStart())) {
            return 3;
        }
        
        // Get maximum days to book in advance
        int daysBookInAdvance = tennisClubs.getTennisClub(0).getDaysBookInAdvance();
        
        // Calculate the days in advance
        long milisecStart = reservation.getDateTimeStart().getTime();
        long milisecNow = System.currentTimeMillis();
        long milisecDifference = milisecStart - milisecNow;
        long currentDaysInAdvance = milisecDifference / (1000*60*60*24);
        
        // Check if a reservation is being made for more than allowed days in advance
        if (currentDaysInAdvance > daysBookInAdvance) {
            return 4;
        }
        
        // Check if a reservation overlaps another reservation
        if (reservationIsOverlapping(reservation)) {
            return 5;
        }

        return 0;
    }
}
