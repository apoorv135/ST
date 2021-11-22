package com.example.SampleTry.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="exchgreqst")
public class exchgreqstVO implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "rqstno")
    private int requestNumber;

    @Column(name = "fromSid")
    private String from_SID;

//    @Column(name = "rno")
//    private int rno;

    @OneToOne
    @JoinColumn(name="rno")
    private hostelRoomVO host_room;


    @Column(name = "comnt")
    private String comment;

    @Column(name = "rqstDate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date request_date;

    @Column(name = "rqstStatus")
    private String request_status;

    public hostelRoomVO getHost_room() {
        return host_room;
    }

    public void setHost_room(hostelRoomVO host_room) {
        this.host_room = host_room;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getFrom_SID() {
        return from_SID;
    }

    public void setFrom_SID(String from_SID) {
        this.from_SID = from_SID;
    }

//    public int getRno() {
//        return rno;
//    }
//
//    public void setRno(int rno) {
//        this.rno = rno;
//    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    public String getRequest_status() {
        return request_status;
    }

    public void setRequest_status(String request_status) {
        this.request_status = request_status;
    }
}
