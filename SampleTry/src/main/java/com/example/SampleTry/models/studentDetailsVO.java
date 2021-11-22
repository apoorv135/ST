package com.example.SampleTry.models;

import com.example.SampleTry.models.hostelRoomVO;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="studentDetails")
public class studentDetailsVO implements Serializable{

    @Id
    @Column(name = "sid")
    private String sid;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "bdate")
    private java.sql.Date birthday;

    @Column(name = "address")
    private String Address;

    @Column(name = "sex")
    private char sex;

    @Column(name = "pwd")
    private String passWord;

    @OneToOne
    @JoinColumn(name="rno")
    private hostelRoomVO host_room;



    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public hostelRoomVO getHost_room() {
        return host_room;
    }

    public void setHost_room(hostelRoomVO host_room) {
        this.host_room = host_room;
    }

}
