/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Liliana Paiva
 */
public class ComboBoxHelper {

    private int selectedIndex, id;
    private String displayText;

    public ComboBoxHelper() {
    }

    public ComboBoxHelper(int selectedIndex, int id, String displayText) {
        this.selectedIndex = selectedIndex;
        this.id = id;
        this.displayText = displayText;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}
