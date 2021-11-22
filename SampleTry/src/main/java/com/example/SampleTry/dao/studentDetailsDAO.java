package com.example.SampleTry.dao;

import com.example.SampleTry.models.exchgreqstVO;
import com.example.SampleTry.models.hostelRoomVO;
import com.example.SampleTry.models.studentDetailsVO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class studentDetailsDAO {
    public void RefreshIt(studentDetailsVO obj) {
        try {
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.studentDetailsVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();
            s.refresh(obj);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List<studentDetailsVO> authenticate(String sid,String password) {
        List<studentDetailsVO> answer = new ArrayList<>();
        System.out.println("Initial list size : " + answer.size());
        try {
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.studentDetailsVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();
            answer = s.createQuery("from studentDetailsVO where sid = \'"+sid+"\' and passWord = " +
                            "\'" +password+ "\'" ).list();
            System.out.println(answer.size());
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("return from stundetdetailsDAO");
        return answer;
    }

    public void updateRno(String currSid, String fromSid, hostelRoomVO chrv,  hostelRoomVO fhrv ) {
        try {
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.studentDetailsVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            studentDetailsVO curr = s.get(studentDetailsVO.class, currSid);
            curr.setHost_room(fhrv);
            studentDetailsVO from = s.get(studentDetailsVO.class, fromSid);
            from.setHost_room(chrv);
            s.getTransaction().commit();

            s.close();
        } catch (Exception e) {
            System.out.println("studentDetailsDAO :: "+e);
        }
    }

}
