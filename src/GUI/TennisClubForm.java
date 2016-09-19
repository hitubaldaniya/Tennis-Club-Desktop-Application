/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DatabaseObjects.TennisClubDbo;
import DatabaseObjects.TennisClubDboCollection;
import Entities.CourtClosedDays;
import Entities.TennisClub;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Liliana Paiva
 */
public class TennisClubForm extends javax.swing.JFrame {

    private int mode;
    private int tennisClubId = 0;
    private TennisClubDbo tennisClubs;
    private DefaultListModel listModelClosedDays = new DefaultListModel();
    private ArrayList<CourtClosedDays> courtClosedDays = new ArrayList<>();

    /**
     * Creates new form TennisClubForm
     */
    public TennisClubForm(TennisClubDbo tennisClubs) {
        this.tennisClubs = tennisClubs;
        initComponents();
        this.setLocationRelativeTo(null);

    }

    /* public void createTennisCourt() {
     this.setTitle("Create Tennis Court");
     this.btnSave.setText("Create");
     this.mode = 1; //create mode
     }*/
    public void viewTennisClub() {
        this.textName.setEnabled(false);
        this.lstClosedDays.setEnabled(false);
        this.ComboDay.setEnabled(false);
        this.ComboMonth.setEnabled(false);
        this.textObs.setEnabled(false);
        this.btnAddClosedDay.setEnabled(false);
        this.btnRemoveClosedDay.setEnabled(false);

        loadTennisClub(getTennisClub());
        this.setTitle("View Tennis Club");
        this.btnSave.setText("OK");
        this.mode = 2; // View mode
    }

    public void editTennisClub() {
        loadTennisClub(getTennisClub());
        this.setTitle("Edit Tennis Club");
        this.btnSave.setText("Ok");
        this.mode = 1; // Edit mode
    }
    
    private TennisClub getTennisClub(){
        TennisClub tennisClub = tennisClubs.getTennisClub(0);
        
        // If there is no tennis club, create a new one
        if(tennisClub == null){
            tennisClub = new TennisClub();
            tennisClubs.addTennisClub(tennisClub);
        }
        return tennisClub;
    }

    private void loadTennisClub(TennisClub tennisClub) {

        this.textName.setText(tennisClub.getName());
        
        if (tennisClub.getDaysBookInAdvance() > 0) {
            this.spinnerDaysBookInAdvance.setValue(tennisClub.getDaysBookInAdvance());
        }

        // Load court closed days if it is not null
        if (tennisClub.getCourtClosedDays() != null) {
            courtClosedDays = tennisClub.getCourtClosedDays();
        } else {
            courtClosedDays = new ArrayList<CourtClosedDays>();
        }

        // courtClosedDays = tennisCourt.getCourtClosedDays();
        showClosedDays();
    }

    // Creates a tennis court object from the form data
    private TennisClub createTennisClubObject() {
        TennisClub tennisClub = new TennisClub();

        tennisClub.setName(this.textName.getText());
        tennisClub.setDaysBookInAdvance((Integer) this.spinnerDaysBookInAdvance.getValue());
        // Set court closed days
        if (courtClosedDays != null) {
            tennisClub.setCourtClosedDays(courtClosedDays);
        } else {
            tennisClub.setCourtClosedDays(new ArrayList<CourtClosedDays>());
        }

        return tennisClub;
    }

    private int validTennisClubData() {
        int errorCode = 0;

        if (this.textName.getText().length() < 3) {
            this.textName.setBackground(Color.red);
            errorCode = 1;
        } else {
            this.textName.setBackground(Color.white);
        }

        return errorCode;
    }

    private void showClosedDays() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);
        CourtClosedDays tmpCourtClosedDays;

        listModelClosedDays.clear();

        Iterator<CourtClosedDays> it = courtClosedDays.iterator();

        while (it.hasNext()) {
            tmpCourtClosedDays = it.next();
            listModelClosedDays.addElement(sdf.format(tmpCourtClosedDays.getDay()) + " - " + tmpCourtClosedDays.getObs());
        }
        if (courtClosedDays.size() > 0) {
            this.lstClosedDays.setSelectedIndex(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnRemoveClosedDay = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ComboDay = new javax.swing.JComboBox();
        ComboMonth = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnAddClosedDay = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        textObs = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstClosedDays = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        textName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        spinnerDaysBookInAdvance = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tennis Club", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Closed days", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnRemoveClosedDay.setText("Remove");
        btnRemoveClosedDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRemoveClosedDayMouseReleased(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Closed Day", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ComboDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        ComboMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        jLabel3.setText("Day");

        btnAddClosedDay.setText("Add");
        btnAddClosedDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddClosedDayMouseReleased(evt);
            }
        });

        jLabel11.setText("Observations");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textObs))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddClosedDay, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(textObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddClosedDay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lstClosedDays.setModel(listModelClosedDays);
        jScrollPane1.setViewportView(lstClosedDays);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRemoveClosedDay))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveClosedDay)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Name");

        btnSave.setText("OK");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSaveMouseReleased(evt);
            }
        });

        jLabel2.setText("Maximum number of days to book in advance");

        spinnerDaysBookInAdvance.setModel(new javax.swing.SpinnerNumberModel(1, 1, 60, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(spinnerDaysBookInAdvance, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spinnerDaysBookInAdvance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveClosedDayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveClosedDayMouseReleased

        // Remove a closed day
        if (courtClosedDays.size() > 0) {
            courtClosedDays.remove(this.lstClosedDays.getSelectedIndex());
            //System.out.println(courtClosedDays.size() + "\n" + this.lstClosedDays.getSelectedIndex());
            showClosedDays();
        }
    }//GEN-LAST:event_btnRemoveClosedDayMouseReleased

    private void btnAddClosedDayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddClosedDayMouseReleased
        CourtClosedDays courtClosedDaysTmp = new CourtClosedDays();
        Date day = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, this.ComboDay.getSelectedIndex() + 1);
        cal.set(Calendar.MONTH, this.ComboMonth.getSelectedIndex());

        day = cal.getTime();
        courtClosedDaysTmp.setObs(this.textObs.getText());
        courtClosedDaysTmp.setDay(day);

        if (courtClosedDays != null) {
            courtClosedDays.add(courtClosedDaysTmp);
        } else {
            this.courtClosedDays = new ArrayList<CourtClosedDays>();
            courtClosedDays.add(courtClosedDaysTmp);
        }
        showClosedDays();
    }//GEN-LAST:event_btnAddClosedDayMouseReleased

    private void btnSaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseReleased
        TennisClub tennisClub;
        int operationResult;

        switch (mode) {
        case 1: // Edit tennis club
            switch (validTennisClubData()) {
                case 1:
                    JOptionPane.showConfirmDialog(null, "Invalid tennis club name!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    return;
            }
            tennisClub = createTennisClubObject();

            tennisClubs.setTennisClub(tennisClub);

            this.setVisible(false);
            this.dispose();
            break;
        }
        
    }//GEN-LAST:event_btnSaveMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboDay;
    private javax.swing.JComboBox ComboMonth;
    private javax.swing.JButton btnAddClosedDay;
    private javax.swing.JButton btnRemoveClosedDay;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstClosedDays;
    private javax.swing.JSpinner spinnerDaysBookInAdvance;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textObs;
    // End of variables declaration//GEN-END:variables
}
