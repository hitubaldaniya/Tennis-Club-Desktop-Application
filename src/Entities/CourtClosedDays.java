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
public class CourtClosedDays {
    private Date day;
    private String obs;

    public CourtClosedDays(){};
    
    public CourtClosedDays(Date day, String Obs) {
        this.day = day;
        this.obs = Obs;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public void setObs(String Obs) {
        this.obs = Obs;
    }

    public Date getDay() {
        return day;
    }

    public String getObs() {
        return obs;
    }
}
