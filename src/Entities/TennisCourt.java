/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author Liliana Paiva
 */
public class TennisCourt {
    
    private String latitude, longitude, name;
    private int id, numberCourt, surfaceType;
    private boolean enabled, artificialLight, indoors;
    private ArrayList<CourtWorkingHour> courtWorkingHours;
    
    // Empty object constructor
    public TennisCourt(){}
   
    /**
     * @param id    
     * @param latitude          
     * @param longitude 
     * @param indoors           Tennis Courst Indoors or Outdoors
     * @param name              Name of Court
     * @param numberCourt       Number of Court 
     * @param enabled           State of Court 
     * @param artificialLight   Artificial Light 
     * @param surfaceType       Surface Type
     * @param courtClosedDays
     * @param courtWorkingHours
     */
    public TennisCourt(int id, String latitude, String longitude,
            boolean indoors, String name, int numberCourt, boolean enabled,
            boolean artificialLight, int surfaceType,
            ArrayList<CourtWorkingHour> courtWorkingHours) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.indoors = indoors;
        this.name = name;
        this.numberCourt = numberCourt;
        this.enabled = enabled;
        this.artificialLight = artificialLight;
        this.surfaceType = surfaceType;
        this.courtWorkingHours = courtWorkingHours;
    }

    // ******************** Getters ********************

    /**
     * Returns Tennis Court Id
     * 
     * @return id
     */
    public int getId(){
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public boolean isIndoors() {
        return indoors;
    }
    
    public String getName() {
        return name;
    }
     
    /**
     * Returns Tennis Court numebr
     * 
     * @return numberCourt
     */
    public int getNumberCourt() {
        return numberCourt;
    }

    /**
     * Returns Tennis Court State
     * 
     * @return enabled
     */
    public boolean getEnabled() {
        return enabled;
    }

    /**
     * Returns Tennis Court Artificial Light
     * 
     * @return artificialLight
     */
    public boolean isArtificialLight() {
        return artificialLight;
    }
    
    public int getSurfaceType(){
        return this.surfaceType;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public ArrayList<CourtWorkingHour> getCourtWorkingHour() {
        return courtWorkingHours;
    }
    
    // ******************** Setters ********************

    /**
     * Changes Tennis Court ID
     * 
     * @param id 
     */
    public void setId(int id){
        this.id  = id;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setIndoors(boolean indoors) {
        this.indoors = indoors;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Changes Tennis Court Number
     * 
     * @param numberCourt 
     */
    public void setNumberCourt(int numberCourt) {
        this.numberCourt = numberCourt;
    }

    /**
     * Changes Tennis Court State
     * 
     * @param enabled 
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Changes Tennis Court Artificial Light
     * 
     * @param artificialLight 
     */
    public void setArtificialLight(boolean artificialLight) {
        this.artificialLight = artificialLight;
    }   
    
    public void setSufaceType(int surfaceType){
        this.surfaceType = surfaceType;
    }

    public void setSurfaceType(int surfaceType) {
        this.surfaceType = surfaceType;
    }

  

    public void setCourtWorkingHours(ArrayList<CourtWorkingHour> courtWorkingHour) {
        this.courtWorkingHours = courtWorkingHour;
    }
}
