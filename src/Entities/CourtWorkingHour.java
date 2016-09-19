/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Diogo
 */
public class CourtWorkingHour {
    private Date oppeningHour;
    private Date closingHour;
    private Date periodStart;
    private Date periodEnd;
    private String Obs;

    public CourtWorkingHour(){};

    public CourtWorkingHour(Date oppeningHour, Date closingHour, Date periodStart, Date periodEnd, String Obs) {
        this.oppeningHour = oppeningHour;
        this.closingHour = closingHour;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.Obs = Obs;
    }

    public void setOppeningHour(Date oppeningHour) {
        this.oppeningHour = oppeningHour;
    }

    public void setClosingHour(Date closingHour) {
        this.closingHour = closingHour;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Date getOppeningHour() {
        return oppeningHour;
    }

    public Date getClosingHour() {
        return closingHour;
    }

    public String getObs() {
        return Obs;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }
}
