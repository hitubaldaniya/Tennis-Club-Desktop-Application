/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DatabaseObjects.TennisCourtDbo;
import Entities.CourtClosedDays;
import Entities.CourtWorkingHour;
import Entities.TennisCourt;
import Entities.TennisCourtList;
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
public class TennisCourtForm extends javax.swing.JFrame {

    // Defines mode of operation - 1: create, 2: view, 3: update
    private int mode;

    // Tennis Court Dbo
    private TennisCourtDbo tennisCourts;
    private TennisCourtManagement tennisCourtManagement;
    private int tennisCourtId = 0;
    private int jListIndex = 0;
    private DefaultListModel listModelWorkingHours = new DefaultListModel();
    private ArrayList<CourtWorkingHour> courtWorkingHours = new ArrayList<>();

    /**
     * Creates new form TennisCourtForm
     *
     * @param tennisCourtManagement Reference to tennisCourtManagement window,
     * to update data on create or edit
     * @param tennisCourts Tennis Court database object
     *
     */
    public TennisCourtForm(TennisCourtManagement tennisCourtManagement, TennisCourtDbo tennisCourts) {
        this.tennisCourts = tennisCourts;
        this.tennisCourtManagement = tennisCourtManagement;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void createTennisCourt() {
        this.setTitle("Create Tennis Court");
        this.btnSave.setText("Create");
        this.mode = 1; //create mode
    }

    public void viewTennisCourt(TennisCourt tennisCourt) {
        this.textName.setEnabled(false);
        this.comboSurface.setEnabled(false);
        this.CBIndoors.setEnabled(false);
        this.textLatitude.setEditable(false);
        this.ComboLat.setEnabled(false);
        this.textLongitude.setEditable(false);
        this.ComboLong.setEnabled(false);
        this.CBArtificial.setEnabled(false);
        this.CBTennisCourt.setEnabled(false);
        
        this.lstWorkingHours.setEnabled(false);
        this.comboPeriodStartDay.setEnabled(false);
        this.comboPeriodStartMonth.setEnabled(false);
        this.comboPeriodEndDay.setEnabled(false);
        this.comboPeriodEndMonth.setEnabled(false);
        this.comboOpeningHour.setEnabled(false);
        //       this.comboOpeningMinutes.setEnabled(false);
        this.comboClosingHour.setEnabled(false);
//        this.comboClosingMinutes.setEnabled(false);
        this.btnAddWorkingHour.setEnabled(false);
        this.btnRemoveWorkingHour.setEnabled(false);

        this.setTitle("View Tennis Courts");
        this.btnSave.setText("OK");
        this.btnCancel.setVisible(false);

        loadTennisCourt(tennisCourt);
        this.mode = 2; // View mode
    }

    public void editTennisCourt(TennisCourt tennisCourt) {
        loadTennisCourt(tennisCourt);
        tennisCourtId = tennisCourt.getId();
        this.setTitle("Edit Tennis Court");
        this.btnSave.setText("Edit");
        this.mode = 3; // Edit mode
    }

    private void loadTennisCourt(TennisCourt tennisCourt) {
        int latitudeCombo, longitudeCombo;
        String lat = "", lon = "";
        float latitude = 0f, longitude = 0f;

        try {
            if (Float.parseFloat(tennisCourt.getLatitude()) < 0) {
                latitudeCombo = 1;
                latitude = Float.parseFloat(tennisCourt.getLatitude());
                latitude = latitude * -1;

                lat = Float.toString(latitude);
            } else {
                latitudeCombo = 0;
                lat = tennisCourt.getLatitude();
            }
        } catch (NumberFormatException nfe) {
            latitudeCombo = 0;
        }
        try {
            if (Float.parseFloat(tennisCourt.getLongitude()) < 0) {
                longitudeCombo = 1;
                longitude = Float.parseFloat(tennisCourt.getLongitude());
                longitude = longitude * -1;

                lon = Float.toString(longitude);
            } else {
                longitudeCombo = 0;
                lon = tennisCourt.getLongitude();
            }
        } catch (NumberFormatException nfe) {
            longitudeCombo = 0;
        }

        this.ComboLat.setSelectedIndex(latitudeCombo);
        this.ComboLong.setSelectedIndex(longitudeCombo);
        this.textName.setText(tennisCourt.getName());
        this.textLatitude.setText(lat);
        this.textLongitude.setText(lon);
        this.CBIndoors.setSelected(tennisCourt.isIndoors());
        this.CBArtificial.setSelected(tennisCourt.isArtificialLight());
        this.CBTennisCourt.setSelected(tennisCourt.getEnabled());
        this.comboSurface.setSelectedIndex(tennisCourt.getSurfaceType());

        //this.ComboDay.setSelectedIndex(cal_day.get(Calendar.DAY_OF_MONTH));
        //this.ComboMonth.setSelectedIndex(cal_month.get(Calendar.MONTH));
       

        // Load court working hours if it is not null
        if (tennisCourt.getCourtWorkingHour() != null) {
            courtWorkingHours = tennisCourt.getCourtWorkingHour();
        } else {
            courtWorkingHours = new ArrayList<CourtWorkingHour>();
        }
        // courtClosedDays = tennisCourt.getCourtClosedDays();
        showWorkingHours();
    }

    // Creates a tennis court object from the form data
    private TennisCourt createTennisCourtObject() {
        TennisCourt tennisCourt = new TennisCourt();
        float latitude, longitude;
        String lat = "0", lon = "0", name;
        //Calendar cal = Calendar.getInstance();

        try {
            //Latitude
            if (this.ComboLat.getSelectedIndex() == 1) {
                latitude = Float.parseFloat(this.textLatitude.getText());
                latitude = latitude * -1;

                lat = Float.toString(latitude);
            } else {
                lat = this.textLatitude.getText();
            }
            tennisCourt.setLatitude(lat);

        } catch (NumberFormatException nfe) {
            tennisCourt.setLatitude("0");
        }

        try {
            //Longitude
            if (this.ComboLong.getSelectedIndex() == 1) {
                longitude = Float.parseFloat(this.textLongitude.getText());
                longitude = longitude * -1;

                lon = Float.toString(longitude);
            } else {
                lon = this.textLongitude.getText();
            }
            tennisCourt.setLongitude(lon);
        } catch (NumberFormatException nfe) {
            tennisCourt.setLongitude("0");
        }

        tennisCourt.setLatitude(lat);
        tennisCourt.setLongitude(lon);
        tennisCourt.setName(this.textName.getText());
        tennisCourt.setIndoors(this.CBIndoors.isSelected());
        tennisCourt.setArtificialLight(this.CBArtificial.isSelected());
        tennisCourt.setEnabled(this.CBTennisCourt.isSelected());

        /*
         0- Clay court
         1 - Grass court
         2 - Hard court
         3 - Carpet court
         */
        tennisCourt.setSufaceType(this.comboSurface.getSelectedIndex());
           
        // Set court working hours
        if (courtWorkingHours != null) {
            tennisCourt.setCourtWorkingHours(courtWorkingHours);
        } else {
            tennisCourt.setCourtWorkingHours(new ArrayList<CourtWorkingHour>());
        }
        return tennisCourt;
    }

    private int validTennisCourtData() {
        int errorCode = 0;

//http://stackoverflow.com/questions/6344867/checking-whether-a-string-contains-a-number-value-in-java
        if (this.textName.getText().length() < 3) {
            this.textName.setBackground(Color.red);
            errorCode = 1;
        } else {
            this.textName.setBackground(Color.white);
        }

        // Empty name
        /* if (this.textName.getText().equals("")) {
         return 1;
         }*/
        return errorCode;
    }

    private void showWorkingHours() {
        SimpleDateFormat sdfDays = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);
        SimpleDateFormat sdfHours = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        CourtWorkingHour tmpCourtWorkingHours;

        this.listModelWorkingHours.clear();

        Iterator<CourtWorkingHour> it = this.courtWorkingHours.iterator();
        while (it.hasNext()) {
            tmpCourtWorkingHours = it.next();
            listModelWorkingHours.addElement(
                    "From " + sdfDays.format(tmpCourtWorkingHours.getPeriodStart())
                    + " to " + sdfDays.format(tmpCourtWorkingHours.getPeriodEnd())
                    + " - Opening: " + sdfHours.format(tmpCourtWorkingHours.getOppeningHour())
                    + " Closing: " + sdfHours.format(tmpCourtWorkingHours.getClosingHour())
            );
        }
        if (courtWorkingHours.size() > 0) {
            this.lstWorkingHours.setSelectedIndex(0);
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

        jRadioButton1 = new javax.swing.JRadioButton();
        jComboBox5 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CBArtificial = new javax.swing.JCheckBox();
        CBTennisCourt = new javax.swing.JCheckBox();
        comboSurface = new javax.swing.JComboBox<String>();
        textLatitude = new javax.swing.JTextField();
        ComboLat = new javax.swing.JComboBox();
        textLongitude = new javax.swing.JTextField();
        ComboLong = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        CBIndoors = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        comboClosingHour = new javax.swing.JComboBox<String>();
        comboPeriodEndMonth = new javax.swing.JComboBox<String>();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboOpeningHour = new javax.swing.JComboBox<String>();
        comboPeriodStartDay = new javax.swing.JComboBox<String>();
        comboPeriodStartMonth = new javax.swing.JComboBox<String>();
        comboPeriodEndDay = new javax.swing.JComboBox<String>();
        btnAddWorkingHour = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstWorkingHours = new javax.swing.JList<String>();
        btnRemoveWorkingHour = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tennis court", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Surface ");

        jLabel2.setText("Artificial Light");

        jLabel4.setText("Enabled");

        comboSurface.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Clay court", "Grass court", "Hard court", "Carpet court" }));

        textLatitude.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        ComboLat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N", "S" }));

        ComboLong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E", "W" }));

        jLabel7.setText("Latitude");

        jLabel8.setText("Longitude");

        jLabel9.setText("Name");

        jLabel10.setText("Indoors");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Working hours", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add working hour", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel13.setText("To day");

        jLabel17.setText("Closing hour is");

        comboClosingHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        comboPeriodEndMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        jLabel15.setText("Opening hour is");

        jLabel5.setText("From day");

        comboOpeningHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        comboPeriodStartDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        comboPeriodStartMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        comboPeriodEndDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        btnAddWorkingHour.setText("Add");
        btnAddWorkingHour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddWorkingHourMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboPeriodStartDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboPeriodEndDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboPeriodStartMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboPeriodEndMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboOpeningHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboClosingHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddWorkingHour, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboPeriodStartDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPeriodStartMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPeriodEndDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(comboPeriodEndMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(comboOpeningHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(comboClosingHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddWorkingHour)
                .addGap(31, 31, 31))
        );

        lstWorkingHours.setModel(listModelWorkingHours);
        jScrollPane2.setViewportView(lstWorkingHours);

        btnRemoveWorkingHour.setText("Remove");
        btnRemoveWorkingHour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRemoveWorkingHourMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRemoveWorkingHour)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveWorkingHour)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(42, 42, 42)
                        .addComponent(CBIndoors))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSurface, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboLat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBArtificial)
                            .addComponent(CBTennisCourt)))
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSurface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBIndoors)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ComboLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBArtificial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(CBTennisCourt))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancel.setText("Cancel");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelMouseReleased(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSaveMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(497, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        TennisCourt tennisCourt;
        int operationResult;

        switch (mode) {
            case 1: // Create tennisCourt
                if(validTennisCourtData() != 0) {
                    return;
                }
                
                tennisCourt = createTennisCourtObject();
                operationResult = tennisCourts.addTennisCourt(tennisCourt);
                switch (operationResult) {
                case 1:
                    JOptionPane.showConfirmDialog(null, "Name already exists!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    return;
                case 2:
                    JOptionPane.showConfirmDialog(null, "Invalid tennis court name!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    return;

                /*      JOptionPane.showConfirmDialog(null, "Invalid tennis court name!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                 return;
                 */                        
                }

                // Update Tennis Court list
                TennisCourtList tennisCourtAddList = new TennisCourtList(tennisCourt.getId(), tennisCourt.getName());
                tennisCourtManagement.getTennisCourtList().add(tennisCourtAddList);

                // Update Tennis Court JList
                tennisCourtManagement.getListModel().addElement(tennisCourt.getName());
                if (tennisCourts.count() == 1) {
                    tennisCourtManagement.getTennisCourtJList().setSelectedIndex(0);
                }

                this.setVisible(false);
                this.dispose();
                break;
            case 2: // View tennis Court, cancel button
                this.setVisible(false);
                this.dispose();
                break;
            case 3: // Edit tennis Court
                switch (validTennisCourtData()) {
                    case 1:
                        JOptionPane.showConfirmDialog(null, "Invalid tennis court name!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        return;
                }
                tennisCourt = createTennisCourtObject();

                tennisCourt.setId(tennisCourtId);
                tennisCourts.setTennisCourt(tennisCourt);

                // Update Tennis Court list
                TennisCourtList tennisCourtEditList = new TennisCourtList(tennisCourt.getId(), tennisCourt.getName());
                tennisCourtManagement.getTennisCourtList().set(tennisCourtManagement.getSectedTennisCourtIndex(), tennisCourtEditList);

                // Update Tennis Court JList
                tennisCourtManagement.getListModel().set(tennisCourtManagement.getSectedTennisCourtIndex(), tennisCourt.getName());

                this.setVisible(false);
                this.dispose();
                break;
        }
    }//GEN-LAST:event_btnSaveMouseReleased

    private void btnRemoveWorkingHourMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveWorkingHourMouseReleased
        // Remove working hours
        if (courtWorkingHours.size() > 0) {
            courtWorkingHours.remove(this.lstWorkingHours.getSelectedIndex());
            //System.out.println(courtWorkingHours.size() + "\n" + this.lstClosedDays.getSelectedIndex());
            showWorkingHours();
        }
    }//GEN-LAST:event_btnRemoveWorkingHourMouseReleased

    private void btnAddWorkingHourMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddWorkingHourMouseReleased

        CourtWorkingHour courtWorkingHoursTmp = new CourtWorkingHour();
        Date periodStart, periodEnd, oppeningHour, closingHour;
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, this.comboPeriodStartDay.getSelectedIndex() + 1);
        cal.set(Calendar.MONTH, this.comboPeriodStartMonth.getSelectedIndex());
        periodStart = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, this.comboPeriodEndDay.getSelectedIndex() + 1);
        cal.set(Calendar.MONTH, this.comboPeriodEndMonth.getSelectedIndex());
        periodEnd = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, this.comboOpeningHour.getSelectedIndex());
//        cal.set(Calendar.MINUTE, this.comboOpeningMinutes.getSelectedIndex() * 10);
        cal.set(Calendar.MINUTE, 0);
        oppeningHour = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, this.comboClosingHour.getSelectedIndex());
        //      cal.set(Calendar.MINUTE, this.comboClosingMinutes.getSelectedIndex() * 10);
        cal.set(Calendar.MINUTE, 0);
        closingHour = cal.getTime();

        courtWorkingHoursTmp.setPeriodStart(periodStart);
        courtWorkingHoursTmp.setPeriodEnd(periodEnd);
        courtWorkingHoursTmp.setOppeningHour(oppeningHour);
        courtWorkingHoursTmp.setClosingHour(closingHour);

        if (courtWorkingHours != null) {
            this.courtWorkingHours.add(courtWorkingHoursTmp);
        } else {
            this.courtWorkingHours = new ArrayList<CourtWorkingHour>();
            this.courtWorkingHours.add(courtWorkingHoursTmp);
        }

        showWorkingHours();
    }//GEN-LAST:event_btnAddWorkingHourMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CBArtificial;
    private javax.swing.JCheckBox CBIndoors;
    private javax.swing.JCheckBox CBTennisCourt;
    private javax.swing.JComboBox ComboLat;
    private javax.swing.JComboBox ComboLong;
    private javax.swing.JButton btnAddWorkingHour;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRemoveWorkingHour;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> comboClosingHour;
    private javax.swing.JComboBox<String> comboOpeningHour;
    private javax.swing.JComboBox<String> comboPeriodEndDay;
    private javax.swing.JComboBox<String> comboPeriodEndMonth;
    private javax.swing.JComboBox<String> comboPeriodStartDay;
    private javax.swing.JComboBox<String> comboPeriodStartMonth;
    private javax.swing.JComboBox<String> comboSurface;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstWorkingHours;
    private javax.swing.JTextField textLatitude;
    private javax.swing.JTextField textLongitude;
    private javax.swing.JTextField textName;
    // End of variables declaration//GEN-END:variables
}
