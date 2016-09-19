/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseObjects;

import Entities.CourtClosedDays;
import Entities.CourtWorkingHour;
import Entities.TennisCourt;
import Entities.TennisCourtList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Liliana Paiva
 */
public class TennisCourtDbo {

    private ArrayList<TennisCourt> tennisCourts = new ArrayList<>();
    private static int autoIncrementId = 0;

    // Empty constructor
    public TennisCourtDbo() {
    }

    // Adds a Tennis Court object to the array
    public int addTennisCourt(TennisCourt tennisCourt) {
        if (false) {
            return 1;
        }
        tennisCourt.setId(autoIncrementId);
        tennisCourts.add(tennisCourt);
        autoIncrementId++;
        return 0;
    }

    // Return Tennis Court object with specified id
    public TennisCourt getTennisCourt(int id) {
        int index = searchTennisCourtIndex(id);
        if (index != -1) {
            return tennisCourts.get(index);
        } else {
            return null;
        }
    }
    
        // Get tennis courts names
    public ArrayList<String> getTennisCourtsNames() {
        Iterator<TennisCourt> it = tennisCourts.iterator();
        TennisCourt tmpTennisClub;
        ArrayList<String> names = new ArrayList<>();

        while (it.hasNext()) {
            tmpTennisClub = it.next();
            names.add(tmpTennisClub.getName());
        }
        return names;
    }

    // Replace a Tennis Court object with specified id with a new one
    public boolean setTennisCourt(TennisCourt tennisCourt) {
        int index = searchTennisCourtIndex(tennisCourt.getId());
        if (index != -1) {
            tennisCourts.set(index, tennisCourt);
            return true;
        } else {
            return false;
        }
    }

    // Removes a Tennis Court object from the array, by id
    public boolean removeTennisCourt(int id) {
        int index = searchTennisCourtIndex(id);
        if (index != -1) {
            tennisCourts.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // Returns an ArrayList with all Tennis Courts 
    public ArrayList<TennisCourt> getAllTennisCourts() {
        return tennisCourts;
    }
    
    //Search By Tennis Court Name and Return tennis court id 
    public int getTennisCourtIdByName(String name) {
        int id;
        Iterator<TennisCourt> it = tennisCourts.iterator();
        TennisCourt tmpTennisCourt;
        while (it.hasNext()) {
            tmpTennisCourt = it.next();
            if (tmpTennisCourt.getName().equals(name)) {
                return tmpTennisCourt.getId();
            }
        }
        return -1;
    }

    // Returns an ArrayList with all members name
    public ArrayList<TennisCourtList> getTennisCourtList() {
        ArrayList<TennisCourtList> tennisCourtList = new ArrayList<>();
        Iterator<TennisCourt> it = tennisCourts.iterator();
        TennisCourt tmpTennisCourt;

        while (it.hasNext()) {
            tmpTennisCourt = it.next();
            tennisCourtList.add(new TennisCourtList(tmpTennisCourt.getId(), tmpTennisCourt.getName()));
        }
        return tennisCourtList;
    }

    /*
     Returns the index of an object TennisCourt in the array, by id
     Return -1 if it isn't found
     */
    private int searchTennisCourtIndex(int id) {
        Iterator<TennisCourt> it = tennisCourts.iterator();
        TennisCourt tmpTennisCourt;

        while (it.hasNext()) {
            tmpTennisCourt = it.next();
            if (tmpTennisCourt.getId() == id) {
                return tennisCourts.indexOf(tmpTennisCourt);
            }
        }
        return -1;
    }

    /*
     Returns the TennisCourts object with the specified name
     Returns NULL if it isn't found
     */
    public TennisCourt searchByTennisCourtName(String tennisCourtName) {
        Iterator<TennisCourt> it = tennisCourts.iterator();
        TennisCourt tmpTennisCourt;

        while (it.hasNext()) {
            tmpTennisCourt = it.next();
            if (tmpTennisCourt.getName().equals(tennisCourtName)) {
                return tmpTennisCourt;
            }
        }
        return null;
    }

    public TennisCourt searchTennisCourt() {
        return null;
    }
    
    /*
     Check if a court is closed in a specific day
     Return true if it is
     */
  /*  public boolean tennisCourtIsClosedInDay(int id, Date day) {
        Iterator<TennisCourt> itTennisCourt = tennisCourts.iterator();
        TennisCourt tmpTennisCourt;

        while (itTennisCourt.hasNext()) {
            tmpTennisCourt = itTennisCourt.next();
            
            if (tmpTennisCourt.getId() == id) {
                CourtClosedDays tmpCourtClosedDays;
                Calendar cal = Calendar.getInstance(), cal2 = Calendar.getInstance();
                Iterator<CourtClosedDays> itCourtClosedDays = tmpTennisCourt.getCourtClosedDays().iterator();
                
                cal.setTime(day);
                while (itCourtClosedDays.hasNext()) {
                    tmpCourtClosedDays = itCourtClosedDays.next();
                    cal2.setTime(tmpCourtClosedDays.getDay());
                    if(cal.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) &&
                       cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)){
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

    // 
    public ArrayList<Integer> getTennisCourtsIDs() {
        Iterator<TennisCourt> it = tennisCourts.iterator();
        TennisCourt tmpTennisClub;
        ArrayList<Integer> tennisCourtIDs = new ArrayList<>();

        while (it.hasNext()) {
            tmpTennisClub = it.next();
            tennisCourtIDs.add(tmpTennisClub.getId());
        }
        return tennisCourtIDs;
    }
    
    /*
     Check if a court is not yet open in a specific time
     Return true if it is not yet open
     */
    public boolean tennisCourtIsNotOpened(int id, Date reservationDateTime) {
        TennisCourt tmpTennisCourt = getTennisCourt(id);
        CourtWorkingHour tmpCourtWorkingHour;
        Calendar calReservation = Calendar.getInstance();
        Calendar calWorkingHourPeriodStart = Calendar.getInstance();
        Calendar calWorkingHourPeriodEnd = Calendar.getInstance();
        Calendar calOppeningHour = Calendar.getInstance();
        Calendar calClosingHour = Calendar.getInstance();
        Iterator<CourtWorkingHour> itCourtWorkingHour = tmpTennisCourt.getCourtWorkingHour().iterator();

        calReservation.setTime(reservationDateTime);
        
        // Iterate through this tennis court working hours
        while (itCourtWorkingHour.hasNext()) {
            tmpCourtWorkingHour = itCourtWorkingHour.next();

            calWorkingHourPeriodStart.setTime(tmpCourtWorkingHour.getPeriodStart());
            calWorkingHourPeriodEnd.setTime(tmpCourtWorkingHour.getPeriodEnd());
            calOppeningHour.setTime(tmpCourtWorkingHour.getOppeningHour());
            calClosingHour.setTime(tmpCourtWorkingHour.getClosingHour());
            
            // Move period start/end to current year
            calWorkingHourPeriodStart.set(Calendar.YEAR, calReservation.get(Calendar.YEAR));
            calWorkingHourPeriodEnd.set(Calendar.YEAR, calReservation.get(Calendar.YEAR));
            
            // Move period start to the beginning of day
            calWorkingHourPeriodStart.set(Calendar.HOUR_OF_DAY, 0);
            calWorkingHourPeriodStart.set(Calendar.MINUTE, 0);
            calWorkingHourPeriodStart.set(Calendar.SECOND, 0);
            calWorkingHourPeriodStart.set(Calendar.MILLISECOND, 0);
            
            // Move period end to the end of day
            calWorkingHourPeriodEnd.set(Calendar.HOUR_OF_DAY, 23);
            calWorkingHourPeriodEnd.set(Calendar.MINUTE, 59);
            calWorkingHourPeriodEnd.set(Calendar.SECOND, 59);
            calWorkingHourPeriodEnd.set(Calendar.MILLISECOND, 999);
            
            // Move openning/closing hour to current day, correct seconds/miliseconds
            calOppeningHour.set(Calendar.YEAR, calReservation.get(Calendar.YEAR));
            calOppeningHour.set(Calendar.MONTH, calReservation.get(Calendar.MONTH));
            calOppeningHour.set(Calendar.DAY_OF_MONTH, calReservation.get(Calendar.DAY_OF_MONTH));
            calOppeningHour.set(Calendar.SECOND, 0);
            calOppeningHour.set(Calendar.MILLISECOND, 0);
            calClosingHour.set(Calendar.YEAR, calReservation.get(Calendar.YEAR));
            calClosingHour.set(Calendar.MONTH, calReservation.get(Calendar.MONTH));
            calClosingHour.set(Calendar.DAY_OF_MONTH, calReservation.get(Calendar.DAY_OF_MONTH));
            calClosingHour.set(Calendar.SECOND, 0);
            calClosingHour.set(Calendar.MILLISECOND, 0);
            
//            long test1 = calReservation.getTimeInMillis();
//            long test2 = calOppeningHour.getTimeInMillis();
//            long test3 = calClosingHour.getTimeInMillis();
//            
//            System.out.println(test2);
//            System.out.println(test1);
//            System.out.println(test3);
//            System.out.println();
            
            // Check if reservation date is in this period
            if(calReservation.getTimeInMillis() >= calWorkingHourPeriodStart.getTimeInMillis() &&
               calReservation.getTimeInMillis() <= calWorkingHourPeriodEnd.getTimeInMillis()
            ){
               // Check if tennis court is open in the reservation time
               if(calReservation.getTimeInMillis() >= calOppeningHour.getTimeInMillis() &&
                  calReservation.getTimeInMillis() <= calClosingHour.getTimeInMillis()
                ){
                   return false;
               } else {
                   return true;
               }
            }
        }
        return true;
    }
    
    // Returns tennis courts count
    public int count() {
        return tennisCourts.size();
    }
    
    
    private int validateTennisCourtData(TennisCourt tennisCourt) {
        
        if (searchByTennisCourtName(tennisCourt.getName()) != null) {
            return 1;
        } else if(tennisCourt.getName().equals("")) {
            return 2;
        }/*else if (searchByMemberName (member.getName()) != null){
            return 3;
        }else if (member.getName().equals(member)){
            return 4;
        }*/
        return 0;
    }

}
