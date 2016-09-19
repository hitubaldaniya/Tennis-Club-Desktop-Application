/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Liliana Paiva
 */
public class ReservationList {
    private int id;
    //private String name;

    // Empty object constructor
    public ReservationList(){
    
    }
    
    /**
     * @param id Id
     * @param name Name
     */
    public ReservationList(int id) {
        this.id = id;
        //this.name = name;
    }
    
    
    // ******************** Getters ********************

    /**
     * Returns reservation id
     * 
     * @return id 
     */
    public int getId() {
        return id;
    }

    /*public String getName() {
        return name;
    }*/

    
   
    // ******************** Setters ********************
    
    /**
     * Changes reservation id
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/
   
    
}
