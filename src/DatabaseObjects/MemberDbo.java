package DatabaseObjects;

import Entities.Member;
import Entities.MemberList;
import GUI.MemberForm;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class MemberDbo {

    private ArrayList<Member> members = new ArrayList<>();
    private MemberForm memberForm;
    private static int autoIncrementId = 0;

    // Empty constructor
    public MemberDbo() {
    }

    // Adds a Member object to the array
    public int addMember(Member member) {
        int errorCode = validateMemberData(member);
        
        if(errorCode != 0){
            return errorCode;
        }
        
        member.setId(autoIncrementId);
        members.add(member);
        autoIncrementId++;
        return 0;
    }

    // Return Member object with specified id
    public Member getMember(int id) {
        int index = searchMemberIndex(id);
        if (index != -1) {
            return members.get(index);
        } else {
            return null;
        }
    }

    // Replace a Member object with specified id with a new one
    public boolean setMember(Member member) {
        int index = searchMemberIndex(member.getId());
        if (index != -1) {
            members.set(index, member);
            return true;
        } else {
            return false;
        }
    }

    // Removes a Member object from the array, by member id
    public boolean removeMember(int id) {
        int index = searchMemberIndex(id);
        if (index != -1) {
            members.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // Returns an ArrayList with all members
    public ArrayList<Member> getAllMembers() {
        return members;
    }

    //Search By Member Name and Return member id 
    public int getMemberIdByName(int number) {
        int id;
        Iterator<Member> it = members.iterator();
        Member tmpMember;
        while (it.hasNext()) {
            tmpMember = it.next();
             //System.out.println(tmpMember.getName());
            //System.out.println(name);
            if (tmpMember.getMemberNumber() == number) {
                return tmpMember.getId();
            }
        }
        return -1;
    }
    /*
    public boolean validateMemberData(){
        boolean validData = true;
        
        try {
            Integer.parseInt(memberForm.txtMemberNumber.getText());
            this.txtMemberNumber.setBackground(Color.white);
        } catch (NumberFormatException e) {
            this.txtMemberNumber.setBackground(Color.red);
            validData = false;
        }
        
        try {
            Integer.parseInt(this.txtPostalCode1.getText());
            this.txtPostalCode1.setBackground(Color.white);
        } catch (NumberFormatException e) {
            this.txtPostalCode1.setBackground(Color.red);
            validData = false;
        }
        
        try {
            Integer.parseInt(this.txtPostalCode1.getText());
            this.txtPostalCode1.setBackground(Color.white);
        } catch (NumberFormatException e) {
            this.txtPostalCode1.setBackground(Color.red);
            validData = false;
        }
        
        try {
            Integer.parseInt(this.txtPostalCode2.getText());
            this.txtPostalCode2.setBackground(Color.white);
        } catch (NumberFormatException e) {
            this.txtPostalCode2.setBackground(Color.red);
            validData = false;
        }
        
        ////////
        this.txtBirthDate.getText();
        
        try {
            Integer.parseInt(this.txtTaxIdNumber.getText());
            this.txtTaxIdNumber.setBackground(Color.white);
        } catch (NumberFormatException e) {
            this.txtTaxIdNumber.setBackground(Color.red);
            validData = false;
        }
        
        try {
            Integer.parseInt(this.txtTelephone.getText());
            this.txtTelephone.setBackground(Color.white);
        } catch (NumberFormatException e) {
            this.txtTelephone.setBackground(Color.red);
            validData = false;
        }

        try {
            Integer.parseInt(this.txtMobilePhone.getText());
            this.txtMobilePhone.setBackground(Color.white);
        } catch (NumberFormatException e) {
            this.txtMobilePhone.setBackground(Color.red);
            validData = false;
        }

        this.txtEmail.getText();
        this.txtPhoto.getText();
        return validData;
    }
*/
    // Returns an ArrayList with all memmbers name
    public ArrayList<MemberList> getMemberList() {
        ArrayList<MemberList> memberList = new ArrayList<>();
        Iterator<Member> it = members.iterator();
        Member tmpMember;

        while (it.hasNext()) {
            tmpMember = it.next();
            memberList.add(new MemberList(tmpMember.getId(), tmpMember.getMemberNumber(), 0));
        }
        return memberList;
    }

    /*
     Returns the index of an object Member in the array, by member id
     Returns -1 if it isn't found
     For use in this class only
     */
    private int searchMemberIndex(int id) {
        Iterator<Member> it = members.iterator();
        Member tmpMember;

        while (it.hasNext()) {
            tmpMember = it.next();
            if (tmpMember.getId() == id) {
                return members.indexOf(tmpMember);
            }
        }
        return -1;
    }

    /*
     Returns the Member object with the specified number
     Returns NULL if it isn't found
     */
    public Member searchByMemberNumber(int memberNumber) {
        Iterator<Member> it = members.iterator();
        Member tmpMember;

        while (it.hasNext()) {
            tmpMember = it.next();
            if (tmpMember.getMemberNumber() == memberNumber) {
                return tmpMember;
            }
        }
        return null;
    }

    /*
     Returns an ArrayList with Member object wich name contain
     Returns an empty ArrayList if there is no match
     */
    public ArrayList<Member> searchByMemberName(String name) {
        Iterator<Member> it = members.iterator();
        Member tmpMember;
        ArrayList<Member> queryResult = new ArrayList<>();

        while (it.hasNext()) {
            tmpMember = it.next();
            if (tmpMember.getName().contains(name)) {
                queryResult.add(tmpMember);
            }
        }
        return queryResult;
    }

    // Returns members count
    public int count() {
        return members.size();
    }
    
    /* Validate member data
        Error list:
        1 - Member number already exists
        2 - Zero member number
    */
    
    private int validateMemberData(Member member) {
        
        if (searchByMemberNumber(member.getMemberNumber()) != null) {
            return 1;
        } else if(member.getMemberNumber() == 0) {
            return 2;
        }/*else if (searchByMemberName (member.getName()) != null){
            return 3;
        }else if (member.getName().equals(member)){
            return 4;
        }*/
        return 0;
    }
}
