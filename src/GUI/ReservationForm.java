/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DatabaseObjects.MemberDbo;
import DatabaseObjects.ReservationDbo;
import DatabaseObjects.TennisClubDbo;
import DatabaseObjects.TennisCourtDbo;
import Entities.Member;
import Entities.MemberList;
import Entities.Reservation;
import Entities.ReservationList;
import Entities.TennisCourt;
import Entities.TennisCourtList;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;

/**
 *
 * @author Diogo
 */
public class ReservationForm extends javax.swing.JFrame {

    // Defines mode of operation - 1: create, 2: view, 3: update
    private int mode;

    // Member Dbo
    private ReservationDbo reservations;
    private ReservationManagement reservationManagement;
    private int reservationId = 0;
    private int jListIndex = 0;
    private DefaultComboBoxModel modelComboMembers = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelComboTennisCourts = new DefaultComboBoxModel();
    private MemberDbo members;
    private ArrayList<MemberList> memberList;
    private ArrayList<TennisCourtList> tennisCourtList;
    private TennisCourtDbo tennisCourts;
    private TennisClubDbo tennisClubs;
    private SpinnerDateModel modelSpinerReservationStart;

    /**
     * Creates new form ReservationForm
     */

    public ReservationForm(ReservationManagement reservationManagement, ReservationDbo reservations, MemberDbo members, TennisCourtDbo tennisCourts, TennisClubDbo tennisClubs) {
        this.reservations = reservations;
        this.reservationManagement = reservationManagement;
        this.members = members;
        this.tennisCourts = tennisCourts;
        this.tennisClubs = tennisClubs;
        Calendar cal = Calendar.getInstance();
        // Go to the next hour (ex: now 15:20, go to 16:00)
        cal.add(Calendar.HOUR, 1);
        cal.add(Calendar.MINUTE, -cal.get(Calendar.MINUTE));
        Date initDate = cal.getTime();
        cal.add(Calendar.YEAR, -1);
        Date earliestDate = cal.getTime();
        cal.add(Calendar.YEAR, 2);
        Date latestDate = cal.getTime();
        
        // Create a spinner with range one year before and after the current date
        modelSpinerReservationStart = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        
        initComponents();
        showMembersComboBox();
        showTennisCourtsComboBox();
        this.setLocationRelativeTo(null);
    }

    private void showMembersComboBox() {
        memberList = members.getMemberList();
        for (MemberList item : memberList) {
            modelComboMembers.addElement(Integer.toString(item.getMemberNumber()));
        }
    }

    private void showTennisCourtsComboBox() {
        tennisCourtList = tennisCourts.getTennisCourtList();
        for (TennisCourtList item : tennisCourtList) {
            modelComboTennisCourts.addElement(item.getName());
        }
    }

    public void createReservation() {
        this.setTitle("Create reservation");
        this.btnSave.setText("Create");
        this.mode = 1; // Create mode
    }

    public void viewReservation(Reservation reservation) {
        this.comboMembers.setEnabled(false);
        this.comboTennisCourts.setEnabled(false);
        /*this.radioToday.setEnabled(false);
         this.radioTomorrow.setEnabled(false);
         this.radioAfterTomorrow.setEnabled(false);*/
//        this.comboDay.setEnabled(false);
//        this.comboMonth.setEnabled(false);
//        this.comboHours.setEnabled(false);
//        this.comboMinutes.setEnabled(false);
        this.spinnerReservationStart.setEnabled(false);

        this.setTitle("View Reservation");
        this.btnSave.setText("OK");
        this.btnCancel.setVisible(false);

        loadReservation(reservation);
        this.mode = 2;  //View mode

    }

    public void editReservation(Reservation reservation) {
        loadReservation(reservation);
        reservationId = reservation.getId();

        this.setTitle("Edit Reservation");
        this.btnSave.setText("Edit");
        this.mode = 3; //Edit mode

    }

    private void loadReservation(Reservation reservation) {
        int i;

        Calendar cal_start = Calendar.getInstance(), cal_end = Calendar.getInstance();
        //int minutes[] = {0, 10, 20, 30, 40, 50};
        int comboDuration[] = {15, 30, 45, 60, 120, 180, 240};

        // Select reservation member
        for (i = 0; i < memberList.size(); i++) {
            int memberNumber = memberList.get(i).getMemberNumber();
            Member member = members.searchByMemberNumber(memberNumber);
            int selectedMemberId = reservation.getMemberId();

            if (member.getId() == selectedMemberId) {
                this.comboMembers.setSelectedIndex(i);
                break;
            }
        }

        // Select reservation tennis court
        for (i = 0; i < tennisCourtList.size(); i++) {
            String tennisCourtName = tennisCourtList.get(i).getName();
            TennisCourt tennisCourt = tennisCourts.searchByTennisCourtName(tennisCourtName);
            int selectedTennisCourtId = reservation.getCourtId();

            if (tennisCourt.getId() == selectedTennisCourtId) {
                this.comboTennisCourts.setSelectedIndex(i);
                break;
            }
        }

        cal_start.setTime(reservation.getDateTimeStart());
        cal_end.setTime(reservation.getDateTimeEnd());

        /*  if (cal_start.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
         this.radioToday.setSelected(true);
         } else if (cal_start.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH) + 1) {
         this.radioTomorrow.setSelected(true);
         } else if (cal_start.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH) + 2) {
         this.radioAfterTomorrow.setSelected(true);
         }*/
//        this.comboDay.setSelectedIndex(cal_day.get(Calendar.DAY_OF_MONTH));
//        this.comboMonth.setSelectedIndex(cal_month.get(Calendar.MONTH));
//
//        this.comboHours.setSelectedIndex(cal_start.get(Calendar.HOUR_OF_DAY));
//
//        for (i = 0; i < minutes.length; i++) {
//            if (minutes[i] == cal_start.get(Calendar.MINUTE)) {
//                this.comboMinutes.setSelectedIndex(i);
//                break;
//            }
//        }
        
        modelSpinerReservationStart.setValue(reservation.getDateTimeStart());
        
//        long durationHours = (reservation.getDateTimeEnd().getTime() - reservation.getDateTimeStart().getTime()) / 3600000;
//        if (durationHours <= 1) {
//            this.comboDuration.setSelectedIndex(0);
//        } else if (durationHours <= 2) {
//            this.comboDuration.setSelectedIndex(1);
//        } else if (durationHours <= 3) {
//            this.comboDuration.setSelectedIndex(2);
//        } else if (durationHours <= 4) {
//            this.comboDuration.setSelectedIndex(3);
//        } else if (durationHours <= 5) {
//            this.comboDuration.setSelectedIndex(4);
//        } else if (durationHours <= 6) {
//            this.comboDuration.setSelectedIndex(5);
//        } else if (durationHours <= 7) {
//            this.comboDuration.setSelectedIndex(6);
//        }

        // System.out.println(durationMinutes);
    }

    // Creates a member object from the form data
    private Reservation createReservationObject() {
        Reservation reservation = new Reservation();
        Date dateTimeStart, dateTimeEnd;
        Calendar cal = Calendar.getInstance();
//        int hour, minute, day, month;
                
//        hour = Integer.parseInt(comboHours.getSelectedItem().toString());
//        minute = Integer.parseInt(comboMinutes.getSelectedItem().toString());
//        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(comboHours.getSelectedItem().toString()));
//        cal.set(Calendar.MINUTE, Integer.parseInt(comboMinutes.getSelectedItem().toString()));
//        cal.set(Calendar.SECOND, 0);
//
//        
//        cal.set(Calendar.DAY_OF_MONTH, this.comboDay.getSelectedIndex() + 1);
//        day = this.comboDay.getSelectedIndex() + 1;
//        cal.set(Calendar.MONTH, this.comboMonth.getSelectedIndex());
//        month = this.comboMonth.getSelectedIndex();
//
        cal.setTime((Date) this.spinnerReservationStart.getValue());
        cal.add(Calendar.MINUTE, -cal.get(Calendar.MINUTE));
        dateTimeStart = cal.getTime();
//        switch (comboDuration.getSelectedIndex()) {
//            case 0:
//                cal.add(Calendar.HOUR, 1);
//                break;
//            case 1:
//                cal.add(Calendar.HOUR, 2);
//                break;
//            case 2:
//                cal.add(Calendar.HOUR, 3);
//                break;
//            case 3:
//                cal.add(Calendar.HOUR, 4);
//                break;
//            case 4:
//                cal.add(Calendar.HOUR, 5);
//                break;
//            case 5:
//                cal.add(Calendar.HOUR, 6);
//                break;
//        }
        cal.add(Calendar.HOUR, 1);
        dateTimeEnd = cal.getTime();

        reservation.setMemberId(this.comboMembers.getSelectedIndex());
        reservation.setCourtId(this.comboTennisCourts.getSelectedIndex());
        reservation.setDateTimeStart(dateTimeStart);
        reservation.setDateTimeEnd(dateTimeEnd);

        return reservation;
    }

    private boolean validReservationData() {
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rgroupDay = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboMembers = new javax.swing.JComboBox<>();
        comboTennisCourts = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        spinnerReservationStart = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reservation");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reservation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Member");

        comboMembers.setModel(modelComboMembers);

        comboTennisCourts.setModel(modelComboTennisCourts);

        jLabel2.setText("Tennis court");

        jLabel4.setText("Start time");

        spinnerReservationStart.setModel(modelSpinerReservationStart);

        jLabel3.setText("(whole hours, duration is 1 hour)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboTennisCourts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboMembers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(spinnerReservationStart, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboMembers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTennisCourts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerReservationStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(44, 44, 44))
        );

        btnSave.setText("Save");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSaveMouseReleased(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 218, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseReleased
        setVisible(false); // hide the JFrame
        dispose();         //Destroy the JFrame object
    }//GEN-LAST:event_btnCancelMouseReleased

    private void btnSaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseReleased
        Reservation reservation;
        int operationResult;

        switch (mode) {
            case 1: // Create Reservation
                if (validReservationData()) {
                    reservation = createReservationObject();
                    this.spinnerReservationStart.setValue(reservation.getDateTimeStart());
                    operationResult = reservations.addReservation(reservation);
                    switch (operationResult) {
                        case 1:
                            JOptionPane.showConfirmDialog(null, "Reserving a court for the past!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                        case 2:
                            SimpleDateFormat sdfClosedDay = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);
                            JOptionPane.showConfirmDialog(null, "The club is closed in this day (" + sdfClosedDay.format(reservation.getDateTimeStart()) + ")!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                        case 3:
                            SimpleDateFormat sdfClosedCourt = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                            JOptionPane.showConfirmDialog(null, "Tennis court is not opened at this time (" + sdfClosedCourt.format(reservation.getDateTimeStart()) + ")!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                        case 4:
                            int daysInAdvance = tennisClubs.getTennisClub(0).getDaysBookInAdvance();
                            JOptionPane.showConfirmDialog(null, "Is is not allowed to make reservations for more than " + daysInAdvance + " days in advance!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                        case 5:
                            SimpleDateFormat sdfOverlap = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
                            JOptionPane.showConfirmDialog(null, "There is already a reservation for this period (" + sdfOverlap.format(reservation.getDateTimeStart()) + ")!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                    }

                    ReservationList reservationAddList = new ReservationList(reservation.getId());
                    reservationManagement.getReservationList().add(reservationAddList);

                    // Update reservation JList
                    reservationManagement.getListModel().addElement(reservation.getId());
                    if (reservations.count() == 1) {
                        reservationManagement.getReservationJList().setSelectedIndex(0);
                    }

                    this.setVisible(false);
                    this.dispose();
                }
                break;
            case 2: // View reservation, cancel button
                this.setVisible(false);
                this.dispose();
                break;
            case 3: // Edit reservation
                if (validReservationData()) {
                    reservation = createReservationObject();
                    reservation.setId(reservationId);
                    operationResult = reservations.setReservation(reservation);
                    
                    switch (operationResult) {
                        case 1:
                            JOptionPane.showConfirmDialog(null, "Reserving a court for the past!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                        case 2:
                            SimpleDateFormat sdfClosedDay = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);
                            JOptionPane.showConfirmDialog(null, "The club is closed in this day (" + sdfClosedDay.format(reservation.getDateTimeStart()) + ")!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                        case 3:
                            JOptionPane.showConfirmDialog(null, "Tennis court is not opened at this time!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                    }

                    // Update reservation list
                    ReservationList reservationEditList = new ReservationList(reservation.getId());
                    reservationManagement.getReservationList().set(reservationManagement.getSectedReservationIndex(), reservationEditList);

                    // Update reservation JList
                    reservationManagement.getListModel().set(reservationManagement.getSectedReservationIndex(), reservation.getId());

                    this.setVisible(false);
                    this.dispose();
                }
                break;
        }
    }//GEN-LAST:event_btnSaveMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> comboMembers;
    private javax.swing.JComboBox<String> comboTennisCourts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.ButtonGroup rgroupDay;
    private javax.swing.JSpinner spinnerReservationStart;
    // End of variables declaration//GEN-END:variables
}
