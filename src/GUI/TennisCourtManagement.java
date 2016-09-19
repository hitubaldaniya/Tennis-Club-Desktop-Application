/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DatabaseObjects.TennisCourtDbo;
import Entities.CourtClosedDays;
import Entities.TennisCourt;
import Entities.TennisCourtList;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Liliana Paiva
 */
public class TennisCourtManagement extends javax.swing.JFrame {
    
    private TennisCourtDbo tennisCourts;
    private ArrayList<TennisCourtList> tennisCourtList;
    private DefaultListModel listModel = new DefaultListModel();
    //formulario para criar um novo campo!
    private TennisCourtForm tennisCourtForm;

    /**
     * Creates new form TennisCourtManagement
     */
    public TennisCourtManagement(TennisCourtDbo tennisCourts ) {
        this.tennisCourts = tennisCourts;
        initComponents();
        showAllTennisCourts();
        this.setLocationRelativeTo(null);
    }
    
    private void showAllTennisCourts(){
        
        // Load tennis courts to list model
        listModel.clear();
        tennisCourtList = tennisCourts.getTennisCourtList();
        Iterator<TennisCourtList> it = tennisCourtList.iterator();
        while(it.hasNext()) {
            //listModel.addElement(it.next().getId());
            listModel.addElement(it.next().getName());
            //tmpMember = it.next();
        }
        
        // Select first member (if exists)
        if(tennisCourts.count() > 0){
            lstTennisCourts.setSelectedIndex(0);
        }
    }
      
    public ArrayList<TennisCourtList> getTennisCourtList(){
        return tennisCourtList;
    }
    
    public javax.swing.JList<String> getTennisCourtJList(){
        return lstTennisCourts;
    }
    
    public DefaultListModel getListModel(){
        return listModel;
    }
    
    public int getSectedTennisCourtIndex(){
        return lstTennisCourts.getSelectedIndex();
    }
    
    private TennisCourt getSelectedTennisCourt(){
        if(lstTennisCourts.getSelectedIndex() < 0){
            return null;
        }
        int selectedTennisCourtIndex = lstTennisCourts.getSelectedIndex();
        TennisCourtList selectedTennisCourt = tennisCourtList.get(selectedTennisCourtIndex);
        return tennisCourts.getTennisCourt(selectedTennisCourt.getId());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDelete = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTennisCourts = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tennis courts management");
        setResizable(false);

        btnDelete.setText("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDeleteMouseReleased(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCreateMouseReleased(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEditMouseReleased(evt);
            }
        });

        btnView.setText("View");
        btnView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnViewMouseReleased(evt);
            }
        });

        lstTennisCourts.setModel(listModel);
        lstTennisCourts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTennisCourts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstTennisCourtsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstTennisCourts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCreate))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnView)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewMouseReleased
        TennisCourt tennisCourt = getSelectedTennisCourt();
        
        if(tennisCourt == null){
            return;
        }
        tennisCourtForm = new TennisCourtForm(this, tennisCourts);
        tennisCourtForm.viewTennisCourt(tennisCourt);
        tennisCourtForm.setVisible(true);
    }//GEN-LAST:event_btnViewMouseReleased

    private void btnEditMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseReleased
        TennisCourt tennisCourt = getSelectedTennisCourt();
        
        if(tennisCourt == null){
            return;
        }
        tennisCourtForm = new TennisCourtForm(this, tennisCourts);
        tennisCourtForm.editTennisCourt(tennisCourt);
        tennisCourtForm.setVisible(true);
    }//GEN-LAST:event_btnEditMouseReleased

    private void btnCreateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseReleased
        tennisCourtForm = new TennisCourtForm(this, tennisCourts);
        tennisCourtForm.createTennisCourt();
        tennisCourtForm.setVisible(true);
    }//GEN-LAST:event_btnCreateMouseReleased

    private void btnDeleteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseReleased
        TennisCourt tennisCourt = getSelectedTennisCourt();
        
        if(tennisCourt == null){
            return;
        }
        int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to delete the Tennis Court '" + tennisCourt.getName() + "'?", "Delete", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            tennisCourts.removeTennisCourt(tennisCourt.getId());
            tennisCourtList.remove(lstTennisCourts.getSelectedIndex());
            listModel.remove(lstTennisCourts.getSelectedIndex());
        }
    }//GEN-LAST:event_btnDeleteMouseReleased

    private void lstTennisCourtsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstTennisCourtsMouseClicked
        
        // Double click
        if (evt.getClickCount() == 2) {
            TennisCourt tennisCourt = getSelectedTennisCourt();
        
            if(tennisCourt == null){
                return;
            }
            tennisCourtForm = new TennisCourtForm(this, tennisCourts);
            tennisCourtForm.viewTennisCourt(tennisCourt);
            tennisCourtForm.setVisible(true);
        }
    }//GEN-LAST:event_lstTennisCourtsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstTennisCourts;
    // End of variables declaration//GEN-END:variables
}
