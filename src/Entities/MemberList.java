package Entities;
/**
 *
 * @author Diogo Novais
 */
public class MemberList {
    
    private int id, memberNumber, listIndex;

    // Empty object constructor
    public MemberList(){}
    
    /**
     * @param id Id
     * @param memberNumber member number
     * @param listIndex
     */
    public MemberList(int id, int memberNumber, int listIndex) {
        this.listIndex = listIndex;
        this.id = id;
        this.memberNumber = memberNumber;
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

    /**
     * Returns member number
     * 
     * @return name
     */
    public int getMemberNumber() {
        return memberNumber;
    }
    
    public int getListIndex() {
        return listIndex;
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

    /**
     * Changes member name
     * 
     * @param memberNumber
     */
    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }
    
    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }
}
