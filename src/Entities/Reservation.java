/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Liliana Paiva
 */
public class Reservation {
    
    private int id, courtId, memberId;
    private Date dateTimeStart, dateTimeEnd;
    
    // Empty object constructor
    public Reservation(){}
    
    /**
     * @param id Database ID
     * @param courtId Member number
     * @param memberId Mobile phone number
     * @param dateTimeStart Landline number
     * @param dateTimeEnd Postal code
     */
    public Reservation(int id, int courtId, int memberId, Date dateTimeStart, Date dateTimeEnd){
        this.id = id;
        this.courtId = id;
        this.memberId = memberId;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
    }

    /**
     * @return the reservation id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the courtId
     */
    public int getCourtId() {
        return courtId;
    }

    /**
     * @return the memberId
     */
    public int getMemberId() {
        return memberId;
    }

    /**
     * @return the date and time that a reservation starts
     */
    public Date getDateTimeStart() {
        return dateTimeStart;
    }

    /**
     * @return the date and time that a reservation ends
     */
    public Date getDateTimeEnd() {
        return dateTimeEnd;
    }   

    /**
     * @param id sets the reservation id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param courtId sets the court id
     */
    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    /**
     * @param memberId sets the member id
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    /**
     * @param dateTimeStart sets the reservation start date/time
     */
    public void setDateTimeStart(Date dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    /**
     * @param dateTimeEnd sets the reservation end date/time
     */
    public void setDateTimeEnd(Date dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }
}

