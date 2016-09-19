/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DatabaseObjects.MemberDbo;
import Entities.Member;
import Entities.MemberList;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Diogo
 */
public class MemberForm extends javax.swing.JFrame {

    // Defines mode of operation - 1: create, 2: view, 3: update
    private int mode;

    // Member Dbo
    private MemberDbo members;
    private MemberManagement memberManagement;
    private int memberId = 0;
    private int jListIndex = 0;

    /**
     * Creates new form MemberForm
     *
     * @param memberManagement Reference to MemberManagement window, to update
     * data on create or edit
     * @param members Members database object
     */
    public MemberForm(MemberManagement memberManagement, MemberDbo members) {
        this.members = members;
        this.memberManagement = memberManagement;

        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void createMember() {
        this.setTitle("Create member");
        this.btnSave.setText("Create");
        this.mode = 1; // Create mode
    }

    public void viewMember(Member member) {

        this.txtMemberNumber.setEditable(false);
        this.txtName.setEditable(false);
        this.txtAddress.setEditable(false);
        this.txtResidencyAddress.setEditable(false);
        this.txtPostalCode1.setEditable(false);
        this.txtPostalCode2.setEditable(false);
        this.txtBirthDate.setEditable(false);
        this.txtIdCardNumber.setEditable(false);
        this.txtTaxIdNumber.setEditable(false);
        this.txtTelephone.setEditable(false);
        this.txtMobilePhone.setEditable(false);
        this.txtEmail.setEditable(false);
        this.txtPhoto.setEditable(false);
        this.btnBrowsePhoto.setEnabled(false);

        loadMember(member);
        this.setTitle("View member");
        this.btnSave.setText("OK");
        this.btnCancel.setVisible(false);
        this.mode = 2; // View mode
    }

    public void editMember(Member member) {
        loadMember(member);
        memberId = member.getId();
        this.setTitle("Edit member");
        this.btnSave.setText("Edit");
        this.mode = 3; // Edit mode
    }

    private void loadMember(Member member) {
        this.txtMemberNumber.setText(Integer.toString(member.getMemberNumber()));
        this.txtName.setText(member.getName());
        this.txtAddress.setText(member.getAddress());
        this.txtResidencyAddress.setText(member.getResidencyAddress());

        if (member.getPostalCode() != 0) {
            this.txtPostalCode1.setText(Integer.toString(member.getPostalCode()));
        }

        if (member.getPostalCodeSuffix() != 0) {
            this.txtPostalCode2.setText(Integer.toString(member.getPostalCodeSuffix()));
        }

        this.txtBirthDate.setText(member.getBirthDate());
        this.txtIdCardNumber.setText(member.getIdCardNumber());

        if (member.getTaxIdNumber() != 0) {
            this.txtTaxIdNumber.setText(Integer.toString(member.getTaxIdNumber()));
        }

        if (member.getTelephone() != 0) {
            this.txtTelephone.setText(Integer.toString(member.getTelephone()));
        }

        if (member.getMobilePhone() != 0) {
            this.txtMobilePhone.setText(Integer.toString(member.getMobilePhone()));
        }
        this.txtEmail.setText(member.getEmail());
        this.txtPhoto.setText(member.getPhoto());
    }

    // Creates a member object from the form data
    private Member createMemberObject() {
        Member member = new Member();

        member.setMemberNumber(Integer.parseInt(this.txtMemberNumber.getText()));

        member.setName(this.txtName.getText());
        member.setAddress(this.txtAddress.getText());
        member.setResidencyAddress(this.txtResidencyAddress.getText());

        try {
            member.setPostalCode(Integer.parseInt(this.txtPostalCode1.getText()));
        } catch (NumberFormatException e) {
            member.setPostalCode(0);
        }

        try {
            member.setPostalCodeSuffix(Integer.parseInt(this.txtPostalCode2.getText()));
        } catch (NumberFormatException e) {
            member.setPostalCodeSuffix(0);
        }

        member.setBirthDate(this.txtBirthDate.getText());
        member.setIdCardNumber(this.txtIdCardNumber.getText());

        try {
            member.setTaxIdNumber(Integer.parseInt(this.txtTaxIdNumber.getText()));
        } catch (NumberFormatException e) {
            member.setTaxIdNumber(0);
        }

        try {
            member.setTelephone(Integer.parseInt(this.txtTelephone.getText()));
        } catch (NumberFormatException e) {
            member.setTelephone(0);
        }
        try {
            member.setMobilePhone(Integer.parseInt(this.txtMobilePhone.getText()));
        } catch (NumberFormatException e) {
            member.setMobilePhone(0);
        }
        member.setEmail(this.txtEmail.getText());
        member.setPhoto(this.txtPhoto.getText());

        return member;
    }

    private boolean validateMemberData() {
        boolean validData = true;

        try {
            Integer.parseInt(this.txtMemberNumber.getText());
            this.txtMemberNumber.setBackground(Color.white);

        } catch (NumberFormatException e) {
            this.txtMemberNumber.setBackground(Color.red);
            validData = false;
        }

        //http://stackoverflow.com/questions/6344867/checking-whether-a-string-contains-a-number-value-in-java
        if (this.txtName.getText().length() < 3 || this.txtName.getText().matches(".*\\d.*")) {
            this.txtName.setBackground(Color.red);
            validData = false;
        } else {
            this.txtName.setBackground(Color.white);

        }

        try {
            if (!this.txtPostalCode1.getText().equals("")) {
                Integer.parseInt(this.txtPostalCode1.getText());
                this.txtPostalCode1.setBackground(Color.white);
            }
        } catch (NumberFormatException e) {
            this.txtPostalCode1.setBackground(Color.red);
            validData = false;
        }

        try {
            if (!this.txtPostalCode2.getText().equals("")) {
                Integer.parseInt(this.txtPostalCode2.getText());
                this.txtPostalCode2.setBackground(Color.white);
            }
        } catch (NumberFormatException e) {
            this.txtPostalCode2.setBackground(Color.red);
            validData = false;
        }

        ////////
        //this.txtBirthDate.getText();

        try {
            if (!this.txtTaxIdNumber.getText().equals("")) {
                Integer.parseInt(this.txtTaxIdNumber.getText());
                this.txtTaxIdNumber.setBackground(Color.white);
            }
        } catch (NumberFormatException e) {
            this.txtTaxIdNumber.setBackground(Color.red);
            validData = false;
        }

        try {
            if (!this.txtTelephone.getText().equals("")) {
                Integer.parseInt(this.txtTelephone.getText());
                this.txtTelephone.setBackground(Color.white);
            }
        } catch (NumberFormatException e) {
            this.txtTelephone.setBackground(Color.red);
            validData = false;
        }

        try {
            if (!this.txtMobilePhone.getText().equals("")) {
                Integer.parseInt(this.txtMobilePhone.getText());
                this.txtMobilePhone.setBackground(Color.white);
            }
        } catch (NumberFormatException e) {
            this.txtMobilePhone.setBackground(Color.red);
            validData = false;
        }

        //this.txtEmail.getText();
        //this.txtPhoto.getText();
        return validData;
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
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtResidencyAddress = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPostalCode1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIdCardNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTaxIdNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtBirthDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPostalCode2 = new javax.swing.JTextField();
        txtMemberNumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTelephone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMobilePhone = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtPhoto = new javax.swing.JTextField();
        btnBrowsePhoto = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Member");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setName(""); // NOI18N

        jLabel1.setText("Name");

        jLabel2.setText("Address");

        jLabel3.setText("Residency address");

        jLabel4.setText("Postal code");

        jLabel5.setText("ID card number");

        jLabel6.setText("Tax ID number");

        jLabel10.setText("Birth date");

        jLabel11.setText("-");

        txtMemberNumber.setToolTipText("");

        jLabel13.setText("Member number");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName)
                    .addComponent(txtResidencyAddress)
                    .addComponent(txtAddress)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtIdCardNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(txtTaxIdNumber)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtPostalCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPostalCode2))
                                .addComponent(txtBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMemberNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMemberNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResidencyAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPostalCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPostalCode2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTaxIdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contact", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel7.setText("Telephone");

        jLabel8.setText("Mobile phone");

        jLabel9.setText("Email");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTelephone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMobilePhone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMobilePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Miscellaneous", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel12.setText("Photo");

        btnBrowsePhoto.setText("Browse");
        btnBrowsePhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnBrowsePhotoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPhoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBrowsePhoto)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowsePhoto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowsePhotoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowsePhotoMouseReleased
        JFileChooser chooser = new JFileChooser();
        File photo;

        int choice = chooser.showOpenDialog(this);
        if (choice != JFileChooser.APPROVE_OPTION) {
            return;
        }

        photo = chooser.getSelectedFile();
        this.txtPhoto.setText(photo.getAbsolutePath());
    }//GEN-LAST:event_btnBrowsePhotoMouseReleased

    private void btnSaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseReleased
        Member member;
        int operationResult;

        switch (mode) {
            case 1: // Create member
                if (validateMemberData()) {
                    member = createMemberObject();
                    operationResult = members.addMember(member);
                    switch (operationResult) {
                        case 1:
                            JOptionPane.showConfirmDialog(null, "Member number already exists!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                        case 2:
                            JOptionPane.showConfirmDialog(null, "Invalid member number!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            return;
                        case 3:
                         JOptionPane.showConfirmDialog(null, "Invalid member name!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                         return;
                    }

                    // Update member list
                    int lastListIndex = memberManagement.getListModel().getSize();
                    MemberList memberAddList = new MemberList(member.getId(), member.getMemberNumber(), lastListIndex);
                    /*
                
                     */
                    memberManagement.getMemberList().add(memberAddList);

                    // Update member JList
                    memberManagement.getListModel().addElement(member.getMemberNumber());
                    if (members.count() == 1) {
                        memberManagement.getMemberJList().setSelectedIndex(0);
                    }

                    this.setVisible(false);
                    this.dispose();
                }
                break;
            case 2: // View member, cancel button
                this.setVisible(false);
                this.dispose();
                break;
            case 3: // Edit member
                if (validateMemberData()) {
                    member = createMemberObject();
                    member.setId(memberId);
                    members.setMember(member);

                    // Update member list
                    int lastListIndex = memberManagement.getMemberJList().getSelectedIndex();
                    MemberList memberEditList = new MemberList(member.getId(), member.getMemberNumber(), lastListIndex);
                    memberManagement.getMemberList().set(memberManagement.getSectedMemberIndex(), memberEditList);

                    // Update member JList
                    memberManagement.getListModel().set(memberManagement.getSectedMemberIndex(), member.getMemberNumber());

                    this.setVisible(false);
                    this.dispose();
                }
                break;
        }
    }//GEN-LAST:event_btnSaveMouseReleased

    private void btnCancelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseReleased
        setVisible(false); // hide the JFrame
        dispose();         //Destroy the JFrame object
    }//GEN-LAST:event_btnCancelMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowsePhoto;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBirthDate;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdCardNumber;
    private javax.swing.JTextField txtMemberNumber;
    private javax.swing.JTextField txtMobilePhone;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoto;
    private javax.swing.JTextField txtPostalCode1;
    private javax.swing.JTextField txtPostalCode2;
    private javax.swing.JTextField txtResidencyAddress;
    private javax.swing.JTextField txtTaxIdNumber;
    private javax.swing.JTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
