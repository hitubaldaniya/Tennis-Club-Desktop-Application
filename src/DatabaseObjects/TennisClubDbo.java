/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseObjects;

import Entities.CourtClosedDays;
import Entities.TennisClub;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Diogo
 */
public class TennisClubDbo {

    private ArrayList<TennisClub> tennisClubs = new ArrayList<>();
    private static int autoIncrementId = 0;

    // Empty constructor
    public TennisClubDbo() {
    }

    // Adds a Tennis Club object to the array
    public int addTennisClub(TennisClub tennisClub) {
        if (false) {
            return 1;
        }
        tennisClub.setId(autoIncrementId);
        tennisClubs.add(tennisClub);
        autoIncrementId++;
        return 0;
    }

    // Return Tennis Club object with specified id
    public TennisClub getTennisClub(int id) {
        int index = searchTennisClubIndex(id);
        if (index != -1) {
            return tennisClubs.get(index);
        } else {
            return null;
        }
    }

    // Replace a Tennis Club object with specified id with a new one
    public boolean setTennisClub(TennisClub tennisClub) {
        int index = searchTennisClubIndex(tennisClub.getId());
        if (index != -1) {
            tennisClubs.set(index, tennisClub);
            return true;
        } else {
            return false;
        }
    }

    // Removes a Tennis Club object from the array, by id
    public boolean removeTennisClub(int id) {
        int index = searchTennisClubIndex(id);
        if (index != -1) {
            tennisClubs.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // Returns an ArrayList with all Tennis Clubs
    public ArrayList<TennisClub> getAllTennisClubs() {
        return tennisClubs;
    }

    /*
        Returns the index of an object TennisClub in the array, by id
        Return -1 if it isn't found
     */
    private int searchTennisClubIndex(int id) {
        Iterator<TennisClub> it = tennisClubs.iterator();
        TennisClub tmpTennisClub;

        while (it.hasNext()) {
            tmpTennisClub = it.next();
            if (tmpTennisClub.getId() == id) {
                return tennisClubs.indexOf(tmpTennisClub);
            }
        }
        return -1;
    }

    /*
     Check if a court is closed in a specific day
     Return true if it is
     */
    public boolean tennisClubIsClosedInDay(Date day) {
        TennisClub thisTennisClub = tennisClubs.get(0);
        CourtClosedDays tmpCourtClosedDays;
        Calendar cal = Calendar.getInstance(), cal2 = Calendar.getInstance();
        Iterator<CourtClosedDays> itCourtClosedDays = thisTennisClub.getCourtClosedDays().iterator();

        cal.setTime(day);
        while (itCourtClosedDays.hasNext()) {
            tmpCourtClosedDays = itCourtClosedDays.next();
            cal2.setTime(tmpCourtClosedDays.getDay());
            if (cal.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
                    && cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                return true;
            }
        }
        return false;
    }
}
