/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Diogo
 */
public class TennisClub {

    private int id, daysBookInAdvance;
    private String name;
    private ArrayList<CourtClosedDays> courtClosedDays;

    public TennisClub() {
    }

    public TennisClub(int id, int daysBookInAdvance, String name, ArrayList<CourtClosedDays> courtClosedDays) {
        this.id = id;
        this.daysBookInAdvance = daysBookInAdvance;
        this.name = name;
        this.courtClosedDays = courtClosedDays;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the nome
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param nome
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public ArrayList<CourtClosedDays> getCourtClosedDays() {
        return courtClosedDays;
    }

    /**
     * 
     * @param courtClosedDays 
     */
    public void setCourtClosedDays(ArrayList<CourtClosedDays> courtClosedDays) {
        this.courtClosedDays = courtClosedDays;
    }

    public int getDaysBookInAdvance() {
        return daysBookInAdvance;
    }

    public void setDaysBookInAdvance(int daysBookInAdvance) {
        this.daysBookInAdvance = daysBookInAdvance;
    }
}
