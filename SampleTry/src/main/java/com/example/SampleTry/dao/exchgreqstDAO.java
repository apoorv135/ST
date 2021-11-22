package com.example.SampleTry.dao;


import com.example.SampleTry.models.exchgreqstVO;
import com.example.SampleTry.models.hostelRoomVO;
import com.example.SampleTry.models.studentDetailsVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class exchgreqstDAO {

    public List<exchgreqstVO> activeRequestCheck(String currSID,studentDetailsVO sdvo ) {
        List<exchgreqstVO> temp = new ArrayList<>();
        try{
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.exchgreqstVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();

             temp=
                    s.createQuery("from exchgreqstVO exc where exc.from_SID = \'"+currSID+"\' " +
                            " and exc.request_status = \'Active\' and exc.host_room.rno <> "+sdvo.getHost_room().getRno()).list();

            s.close();
        }catch(Exception e) {
            System.out.println("exchgreqstDAO :: activeRequestCheck" + e);
        }

        return temp;
    }

    public boolean insertIntoDatabase(exchgreqstVO excobj) {
        try{
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.exchgreqstVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();
            s.save(excobj);
            s.close();
            return true;
        }catch(Exception e) {
            System.out.println("exchgreqstDAO :: activeRequestCheck" + e);
        }
        return false;
    }

    public boolean updateExchangeRequest(exchgreqstVO excobj, int newRoom, String newComment) {

        try {
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.exchgreqstVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();

            s.beginTransaction();
            System.out.println("updateExchangeRequest :: " + newComment);
            System.out.println("updateExchangeRequest :: " + newRoom);
            exchgreqstVO temp = s.get(exchgreqstVO.class, excobj.getRequestNumber());
            temp.setComment(newComment);
            hostelRoomVO vot = new hostelRoomVO();
            vot.setRno(newRoom);
            temp.setHost_room(vot);
            s.getTransaction().commit();
            s.close();

            return true;
        } catch(Exception e) {
            System.out.println(e);
        }


        return false;
    }

    public List<exchgreqstVO> currRequestQuery(studentDetailsVO currStudent) {
        List<exchgreqstVO> answer = new ArrayList<>();

        try {
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.exchgreqstVO.class).
                    addAnnotatedClass(com.example.SampleTry.models.studentDetailsVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();

            answer = s.createQuery("from exchgreqstVO exc where exc.request_status = \'Active\' " +
                    " and exc.from_SID <> \'"+currStudent.getSid()+"\'   and exc.host_room.rno = "+currStudent.getHost_room().getRno()).list();
            System.out.println("currRequestQuery :: "+answer.size());
            s.close();
        } catch (Exception e) {
            System.out.println("exchgreqstDAO :: currRequestQuery " +e);
        }


        return answer;
    }

    public  void approveExchangeReq(String requestNumber, String status) {
        try {
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.exchgreqstVO.class).
                    addAnnotatedClass(com.example.SampleTry.models.studentDetailsVO.class).
                    addAnnotatedClass(com.example.SampleTry.models.hostelRoomVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();

            s.beginTransaction();
            System.out.println("exchgreqstDAO :: approveExchangeReq :: " + requestNumber);
            System.out.println("exchgreqstDAO :: approveExchangeReq :: status :: " + status);
            exchgreqstVO temp = s.get(exchgreqstVO.class, Integer.parseInt(requestNumber));

            temp.setRequest_status(status);
            s.getTransaction().commit();
            s.close();


        } catch (Exception e) {
            System.out.println("exchgreqstDAO :: currRequestQuery " +e);
        }

    }

}
