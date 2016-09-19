/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseObjects;

import Entities.CourtClosedDays;
import Entities.CourtWorkingHour;
import Entities.Member;
import Entities.Reservation;
import Entities.TennisClub;
import Entities.TennisCourt;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Diogo
 */
public class TennisClubDboCollection {

    private MemberDbo members = new MemberDbo();
    private TennisCourtDbo tennisCourts = new TennisCourtDbo();
    private TennisClubDbo tennisClubs = new TennisClubDbo();
    private ReservationDbo reservations = new ReservationDbo(tennisCourts, tennisClubs);

    public TennisClubDboCollection() {
        
        // Tennis club
        tennisClubs.addTennisClub(new TennisClub(
                0,
                30,
                "OHP Tennis Club",
                new ArrayList<CourtClosedDays>(){{   // Court closed days
                    add(new CourtClosedDays(
                            new Date(30931200000L),  // 25-12-1970 00:00:00
                            "Christmas"
                    ));
                }}
        ));
        
        // Test members
        members.addMember(new Member(0, 13, "Test User", "Rua Doutor Antonio Ribeiro Garcia", "Rua General Santos Costa", 3400, 124, "01/02/1990", "122333444 8ZZ8", 444333221, 210000001, 220000001, "test@estgoh.ipc.pt", "userPhoto.jpg"));
        members.addMember(new Member(1, 31, "Test User2", "Rua Doutor Antonio Ribeiro Garcia", "Rua General Santos Costa", 3400, 124, "01/02/1990", "122333444 8ZZ8", 444333221, 210000001, 220000001, "test@estgoh.ipc.pt", "userPhoto.jpg"));

        // Test courts
        tennisCourts.addTennisCourt(new TennisCourt(
                0,                                 // ID
                "40.3610137",                      // Latitude
                "-7.8613233",                      // Longitude
                true,                              // Indoors
                "Estgoh tennis Court",             // Name
                5,                                 // Number
                true,                              // Enabled
                false,                             // Artificial light
                2,                                 // Surface type
                /* new ArrayList<CourtClosedDays>(){{ // CourtClosedDays
                    add(new CourtClosedDays(new Date(30931200000L), "Christmas"));
                }},*/
                /*
                new ArrayList<CourtWorkingHour>(){{ // CourtWorkingHour
                    add(new CourtWorkingHour(
                            new Date(32400000L),    // 01-01-1970 09:00:00
                            new Date(82800000L),    // 01-01-1970 23:00:00
                            new Date(5097600000L),  // 01-03-1970 00:00:00
                            new Date(25225200000L), // 20-10-1970 00:00:00
                            "Normal working hours"
                    ));
                }}
                */
                new ArrayList<CourtWorkingHour>()
        ));
        tennisCourts.addTennisCourt(new TennisCourt(1, "40.3624544", "-7.8575418", true, "Mandanelho", 5, true, false, 2, /*new ArrayList<CourtClosedDays,*/ new ArrayList<CourtWorkingHour>()));
    }
    public MemberDbo getMemberDbo() {
        return members;
    }

    public ReservationDbo getReservationsDbo() {
        return reservations;
    }

    public TennisCourtDbo getTennisCourtsDbo() {
        return tennisCourts;
    }
    
    public TennisClubDbo getTennisClubsDbo (){
        return tennisClubs;
    }
}
