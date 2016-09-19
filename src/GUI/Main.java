/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DatabaseObjects.TennisClubDboCollection;
import Entities.Member;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Liliana Paiva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StartupForm startupForm;
        TennisClubDboCollection dboCollection = new TennisClubDboCollection();
        
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
           // handle exception
        }
        catch (ClassNotFoundException e) {
           // handle exception
        }
        catch (InstantiationException e) {
           // handle exception
        }
        catch (IllegalAccessException e) {
           // handle exception
        }
        
        startupForm = new StartupForm(dboCollection);
        startupForm.setVisible(true);
        System.out.println("Application is running");
    }
}
