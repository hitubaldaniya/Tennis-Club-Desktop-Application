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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diogo
 */
public class ReservationManagement extends javax.swing.JFrame {

    private ReservationDbo reservations;
    private MemberDbo members;
    private TennisClubDbo tennisClubs;
    private TennisCourtDbo tennisCourts;
    private ReservationForm reservationForm;
    private DefaultComboBoxModel modelComboMembers = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelComboTennisCourts = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelComboTennisCourtsOccupation = new DefaultComboBoxModel();
    private DefaultListModel listModel = new DefaultListModel();
    //private ReservationsTableModel reservationsTableModel = new ReservationsTableModel();
    private DefaultTableModel reservationsTableModel;
    
    private ArrayList<ReservationList> reservationList;
    private ArrayList<MemberList> memberList;
    private ArrayList<TennisCourtList> tennisCourtList;
    private ArrayList<TennisCourtList> tennisCourtOccupationList;
    
    /**
     * Creates new form ReservationManagement
     *
     * @param reservations
     */
    public ReservationManagement(ReservationDbo reservations, TennisCourtDbo tennisCourts, MemberDbo members, TennisClubDbo tennisClubs) {
        this.reservations = reservations;
        this.members = members;
        this.tennisCourts = tennisCourts;
        this.tennisClubs = tennisClubs;
        int i;
        
        String columns[] = tennisCourts.getTennisCourtsNames().toArray(new String[0]);
        reservationsTableModel = new DefaultTableModel(columns, 0);
        
        initComponents();
        showMembersComboBox();
        showTennisCourtsComboBox();
        showTennisCourtsOccupatiponComboBox();
        showAllReservations();
        
        // Set width for all columns
        for(i = 0; i < columns.length; i++){
            this.tableReservations.getColumnModel().getColumn(i).setPreferredWidth(60);
        }
        
        fillReservationsTable();
        this.setLocationRelativeTo(null);
    }

    private void showMembersComboBox() {
        ArrayList<MemberList> memberList = members.getMemberList();
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
    
    private void showTennisCourtsOccupatiponComboBox() {
        tennisCourtOccupationList = tennisCourts.getTennisCourtList();
        for (TennisCourtList item : tennisCourtList) {
            modelComboTennisCourtsOccupation.addElement(item.getName());
        }
    }

    public void showAllReservations() {

        // Load reservation to list model
        listModel.clear();
        reservationList = reservations.getReservationList();
        ReservationList reservation_temp;
        Iterator<ReservationList> it = reservationList.iterator();
        while (it.hasNext()) {
            reservation_temp = it.next();
            //listModel.addElement(it.next().getId());
            listModel.addElement(reservation_temp.getId());
            // System.out.println(reservation_temp.getId());
        }

        // Select first Reservation (if exists)
        if (reservations.count() > 0) {
            lstReservations.setSelectedIndex(0);
        }

    }
    
    private void fillReservationsTable(){
        ArrayList<Reservation> reservationsDay = reservations.getReservationsForDay((Date) this.spinnerDay.getValue());
        Reservation tmpReservation;
        Iterator<Reservation> it = reservationsDay.iterator();
        int i;
        
        ArrayList<Integer> tennisCourtsIDs = tennisCourts.getTennisCourtsIDs();
        Integer tmpTennisCourtsID;
        Iterator<Integer> itTennisCourtID;
        int tennisCourtCount = tennisCourtsIDs.size();
        String rowData[] = new String[tennisCourtCount];
        
        // Set cal to selected day, iterate through whole hours
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) this.spinnerDay.getValue());
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        // Remove all rows
        int rowNumber = reservationsTableModel.getRowCount();
        for(i = rowNumber - 1; i >= 0; i--) {
            reservationsTableModel.removeRow(i);
        }
        
        // Loop for every hour
        for(i = 0; i < 24; i++){
            cal.set(Calendar.HOUR_OF_DAY, i);
            itTennisCourtID = tennisCourtsIDs.iterator();
            Date testDate = cal.getTime();
            int j = 0;
            
            // Loop for every tennis court, check if is open on date
            while (itTennisCourtID.hasNext()) {
                tmpTennisCourtsID = itTennisCourtID.next();
            
                // Check if a tennis court is not opened in the current hour
                if(tennisCourts.tennisCourtIsNotOpened(tmpTennisCourtsID, testDate)){
                    rowData[j] = ":: Not open ::";
                } else {
                    //rowData[j] = "";
                    
                    // Check if a tennis court is reserved for a given date
                    if(reservations.dateIsReserved(testDate)){
                        rowData[j] = ">> RESERVED <<";
                    } else {
                        rowData[j] = "~~   Free   ~~";
                    }
                }
                j++;
            }
//            System.out.println(cal.get(Calendar.DAY_OF_MONTH) + "/" +
//                    cal.get(Calendar.MONTH) + "/" +
//                    cal.get(Calendar.YEAR) + " " +
//                    cal.get(Calendar.HOUR_OF_DAY) + ":" +
//                    cal.get(Calendar.MINUTE) + ":" +
//                    cal.get(Calendar.SECOND)
//            );
            reservationsTableModel.addRow(rowData);
        }
    }

    public ArrayList<ReservationList> getReservationList() {
        return reservationList;
    }

    public javax.swing.JList<String> getReservationJList() {
        return lstReservations;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public int getSectedReservationIndex() {
        return lstReservations.getSelectedIndex();
    }

    private Reservation getSelectedReservation() {
        if (lstReservations.getSelectedIndex() < 0) {
            return null;
        }
       // System.out.println(lstReservations.getSelectedIndex() + " \n");
        //System.out.println();

        // System.out.println(reservationList);
        int selectedReservationIndex = lstReservations.getSelectedIndex();
        ReservationList selectedReservation = reservationList.get(selectedReservationIndex);
        return reservations.getReservation(selectedReservation.getId());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rgroupSearchBy = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstReservations = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioSearchMember = new javax.swing.JRadioButton();
        radioSearchTennisCourt = new javax.swing.JRadioButton();
        comboMembers = new javax.swing.JComboBox<>();
        comboTennisCourts = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        spinnerDay = new javax.swing.JSpinner();
        btnReservationsDay = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableReservations = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        spinnerOccupatioFrom = new javax.swing.JSpinner();
        labelTo = new javax.swing.JLabel();
        spinnerOccupatioTo = new javax.swing.JSpinner();
        btnCheckOccupation = new javax.swing.JButton();
        progressOccupatioPercentage = new javax.swing.JProgressBar();
        lblOccupatioPercentage = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboTennisCourtsOccupation = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reservations management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnCreate.setText("Create");
        btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCreateMouseReleased(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDeleteMouseReleased(evt);
            }
        });

        btnView.setText("View");
        btnView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnViewMouseReleased(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEditMouseReleased(evt);
            }
        });

        lstReservations.setModel(listModel);
        lstReservations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstReservationsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstReservations);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Reservations by");

        rgroupSearchBy.add(radioSearchMember);
        radioSearchMember.setSelected(true);
        radioSearchMember.setText("member");

        rgroupSearchBy.add(radioSearchTennisCourt);
        radioSearchTennisCourt.setText("tennis court");

        comboMembers.setModel(modelComboMembers);

        comboTennisCourts.setModel(modelComboTennisCourts);

        btnSearch.setText("Search");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSearchMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearch))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioSearchTennisCourt)
                            .addComponent(radioSearchMember))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboTennisCourts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboMembers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioSearchMember)
                    .addComponent(comboMembers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioSearchTennisCourt)
                    .addComponent(comboTennisCourts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnView)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Schedule", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setText("Select a day");

        spinnerDay.setModel(new javax.swing.SpinnerDateModel());
        spinnerDay.setEditor(new javax.swing.JSpinner.DateEditor(spinnerDay, "dd/mm/yyyy"));

        btnReservationsDay.setText("Show reservations");
        btnReservationsDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnReservationsDayMouseReleased(evt);
            }
        });

        tableReservations.setModel(reservationsTableModel);
        jScrollPane3.setViewportView(tableReservations);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerDay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReservationsDay))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReservationsDay)
                    .addComponent(spinnerDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check ocuppation of a tennis court", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel3.setText("From");

        spinnerOccupatioFrom.setModel(new javax.swing.SpinnerDateModel());
        spinnerOccupatioFrom.setEditor(new javax.swing.JSpinner.DateEditor(spinnerOccupatioFrom, "dd/mm/yyyy"));

        labelTo.setText("to");

        spinnerOccupatioTo.setModel(new javax.swing.SpinnerDateModel());
        spinnerOccupatioTo.setEditor(new javax.swing.JSpinner.DateEditor(spinnerOccupatioTo, "dd/mm/yyyy"));

        btnCheckOccupation.setText("Check");
        btnCheckOccupation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCheckOccupationMouseReleased(evt);
            }
        });

        lblOccupatioPercentage.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel4.setText("Tennis court");

        comboTennisCourtsOccupation.setModel(modelComboTennisCourtsOccupation);

        jLabel5.setText("Occupation");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinnerOccupatioFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelTo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinnerOccupatioTo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboTennisCourtsOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(progressOccupatioPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblOccupatioPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCheckOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCheckOccupation))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(spinnerOccupatioFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTo)
                            .addComponent(spinnerOccupatioTo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(comboTennisCourtsOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(progressOccupatioPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOccupatioPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseReleased
        int memberId = 0, tennisCourtId = 0;
        memberId = members.getMemberIdByName(Integer.parseInt(this.comboMembers.getSelectedItem().toString()));
        tennisCourtId = tennisCourts.getTennisCourtIdByName(this.comboTennisCourts.getSelectedItem().toString());

        if (this.radioSearchMember.isSelected()) {
            reservationList = reservations.searchByMemberId(memberId);
            // Select reservation member
            if (reservationList == null) {
                JOptionPane.showConfirmDialog(null, "Member not found, displaying all reservations", "Search", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                reservationList = reservations.getReservationList();
            }
        } else if (this.radioSearchTennisCourt.isSelected()) {
            reservationList = reservations.searchByCourtId(tennisCourtId);
            // Select reservation tennisCourts
            if (reservationList == null) {
                JOptionPane.showConfirmDialog(null, "Tennis court not found, displaying all reservations", "Search", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                reservationList = reservations.getReservationList();
            }
        }
        listModel.clear();
        ReservationList reservation_temp;
        Iterator<ReservationList> it = reservationList.iterator();
        while (it.hasNext()) {
            reservation_temp = it.next();
            listModel.addElement(reservation_temp.getId());
        }
        // Select first Reservation (if exists)
        if (reservations.count() > 0) {
            lstReservations.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnSearchMouseReleased

    private void btnCreateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseReleased
        reservationForm = new ReservationForm(this, reservations, members, tennisCourts, tennisClubs);
        reservationForm.createReservation();
        reservationForm.setVisible(true);
    }//GEN-LAST:event_btnCreateMouseReleased

    private void btnDeleteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseReleased
        Reservation reservation = getSelectedReservation();

        if (reservation == null) {
            return;
        }
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to delete the Reservation '" + reservation.getId() + "'?", "Delete", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            reservations.removeReservation(reservation.getId());
            reservationList.remove(lstReservations.getSelectedIndex());
            listModel.remove(lstReservations.getSelectedIndex());
        }
    }//GEN-LAST:event_btnDeleteMouseReleased

    private void btnViewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewMouseReleased
        Reservation reservation = getSelectedReservation();

        if (reservation == null) {
            return;
        }
        reservationForm = new ReservationForm(this, reservations, members, tennisCourts, tennisClubs);
        reservationForm.viewReservation(reservation);
        reservationForm.setVisible(true);
    }//GEN-LAST:event_btnViewMouseReleased

    private void btnEditMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseReleased
        Reservation reservation = getSelectedReservation();

        if (reservation == null) {
            return;
        }
        reservationForm = new ReservationForm(this, reservations, members, tennisCourts, tennisClubs);
        reservationForm.editReservation(reservation);
        reservationForm.setVisible(true);
    }//GEN-LAST:event_btnEditMouseReleased

    private void lstReservationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstReservationsMouseClicked

        // Double click
        if (evt.getClickCount() == 2) {
            Reservation reservation = getSelectedReservation();

            if (reservation == null) {
                return;
            }
            reservationForm = new ReservationForm(this, reservations, members, tennisCourts, tennisClubs);
            reservationForm.viewReservation(reservation);
            reservationForm.setVisible(true);
        }
    }//GEN-LAST:event_lstReservationsMouseClicked

    private void btnReservationsDayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservationsDayMouseReleased
        fillReservationsTable();
    }//GEN-LAST:event_btnReservationsDayMouseReleased

    private void btnCheckOccupationMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheckOccupationMouseReleased
        int tennisCourtId = tennisCourts.getTennisCourtIdByName(this.comboTennisCourts.getSelectedItem().toString());
        int occupation = reservations.getTennisCourtOccupation(tennisCourtId, (Date)this.spinnerOccupatioFrom.getValue(), (Date)this.spinnerOccupatioTo.getValue());
        this.lblOccupatioPercentage.setText(Integer.toString(occupation) + "%");
        this.progressOccupatioPercentage.setValue(occupation);
    }//GEN-LAST:event_btnCheckOccupationMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckOccupation;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReservationsDay;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> comboMembers;
    private javax.swing.JComboBox<String> comboTennisCourts;
    private javax.swing.JComboBox<String> comboTennisCourtsOccupation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelTo;
    private javax.swing.JLabel lblOccupatioPercentage;
    private javax.swing.JList lstReservations;
    private javax.swing.JProgressBar progressOccupatioPercentage;
    private javax.swing.JRadioButton radioSearchMember;
    private javax.swing.JRadioButton radioSearchTennisCourt;
    private javax.swing.ButtonGroup rgroupSearchBy;
    private javax.swing.JSpinner spinnerDay;
    private javax.swing.JSpinner spinnerOccupatioFrom;
    private javax.swing.JSpinner spinnerOccupatioTo;
    private javax.swing.JTable tableReservations;
    // End of variables declaration//GEN-END:variables
}
