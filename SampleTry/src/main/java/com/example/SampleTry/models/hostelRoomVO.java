package com.example.SampleTry.models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;


@Entity
@Table(name="hostelRooms")
public class hostelRoomVO implements Serializable{

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "rno")
    private int rno;

    @Column(name = "room")
    private int room;

    @Column(name = "hostelNo")
    private int hostelNo;


    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getHostelNo() {
        return hostelNo;
    }

    public void setHostelNo(int hostelNo) {
        this.hostelNo = hostelNo;
    }
}
