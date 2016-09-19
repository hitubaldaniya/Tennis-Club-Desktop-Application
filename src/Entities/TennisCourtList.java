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
public class TennisCourtList {
    private int id;
    private String name;

    // Empty object constructor
    public TennisCourtList(){}
    
    /**
     * @param id Id
     * @param name Name
     */
    public TennisCourtList(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    // ******************** Getters ********************

    /**
     * Returns member id
     * 
     * @return id 
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    
   
    // ******************** Setters ********************
    
    /**
     * Changes member id
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}

